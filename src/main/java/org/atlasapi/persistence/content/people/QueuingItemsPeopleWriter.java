package org.atlasapi.persistence.content.people;

import org.atlasapi.media.content.CrewMember;
import org.atlasapi.media.content.Item;
import org.atlasapi.persistence.logging.AdapterLog;
import org.atlasapi.persistence.logging.AdapterLogEntry;
import org.atlasapi.persistence.logging.AdapterLogEntry.Severity;

import com.google.common.base.Preconditions;

public class QueuingItemsPeopleWriter implements ItemsPeopleWriter {
    private final AdapterLog log;
    private final QueuingPersonWriter personWriter;

    public QueuingItemsPeopleWriter(QueuingPersonWriter personWriter, AdapterLog log) {
        Preconditions.checkNotNull(personWriter);
        this.personWriter = personWriter;
        this.log = log;
    }

    public void createOrUpdatePeople(Item item) {
        try {
            for (CrewMember crewMember : item.people()) {
                personWriter.addItemToPerson(crewMember.toPerson(), item);
            }
        } catch (Exception e) {
            log.record(new AdapterLogEntry(Severity.ERROR).withCause(e).withSource(QueuingItemsPeopleWriter.class));
        }
    }
}
