package org.atlasapi.persistence.content.people;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;

import org.atlasapi.media.content.ChildRef;
import org.atlasapi.media.content.Item;
import org.atlasapi.media.content.Person;
import org.atlasapi.persistence.logging.AdapterLog;
import org.atlasapi.persistence.logging.AdapterLogEntry;
import org.atlasapi.persistence.logging.AdapterLogEntry.Severity;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class QueuingPersonWriter {

    private final PersonWriter store;
    private final BlockingQueue<Person> queue = new LinkedBlockingQueue<Person>();
    private final ScheduledExecutorService schedule;
    private final Set<String> processedUris = Sets.newHashSet();
    private final AdapterLog log;
    
    public QueuingPersonWriter(PersonWriter store, AdapterLog log) {
        this(store, log, Executors.newSingleThreadScheduledExecutor());
    }

    public QueuingPersonWriter(PersonWriter store, AdapterLog log, ScheduledExecutorService schedule) {
        this.store = store;
        this.log = log;
        this.schedule = schedule;
        this.schedule.scheduleWithFixedDelay(new WritePeople(), 2, 2, TimeUnit.MINUTES);
    }
    
    @PreDestroy
    public void shutDown() {
        schedule.shutdown();
        try {
            new WritePeople().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addItemToPerson(Person person, Item item) {
        person.setContents(ImmutableList.<ChildRef>builder().addAll(person.getContents()).add(item.childRef()).build());
        queue.add(person);
    }
    
    class WritePeople implements Runnable {

        @Override
        public void run() {
            try {
                List<Person> people = Lists.newArrayListWithExpectedSize(queue.size());
                queue.drainTo(people);
                
                for (Person person: dedupePeople(people)) {
                    if (! processedUris.contains(person.getCanonicalUri())) {
                        
                        store.createOrUpdatePerson(person);
                        processedUris.add(person.getCanonicalUri());
                    }
                    
                    store.updatePersonItems(person);
                }
            } catch (Exception e) {
                log.record(new AdapterLogEntry(Severity.ERROR).withCause(e).withSource(this.getClass())); 
            }
        }
        
        private Set<Person> dedupePeople(List<Person> people) {
            Map<String, Person> peopleLookup = Maps.newHashMap();
            
            for (Person person: people) {
                if (peopleLookup.containsKey(person.getCanonicalUri())) {
                    Person existingPerson = peopleLookup.get(person.getCanonicalUri());
                    existingPerson.setContents(ImmutableList.<ChildRef>builder().addAll(existingPerson.getContents()).addAll(person.getContents()).build());
                } else {
                    peopleLookup.put(person.getCanonicalUri(), person);
                }
            }
            
            return ImmutableSet.copyOf(peopleLookup.values());
        }
    }
}
