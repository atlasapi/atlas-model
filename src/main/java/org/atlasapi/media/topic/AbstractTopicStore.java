package org.atlasapi.media.topic;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Iterator;

import javax.annotation.Nullable;

import org.atlasapi.media.common.Id;
import org.atlasapi.media.entity.Alias;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.util.WriteResult;
import org.joda.time.DateTime;

import com.google.common.base.Equivalence;
import com.metabroadcast.common.ids.IdGenerator;
import com.metabroadcast.common.time.Clock;


public abstract class AbstractTopicStore implements TopicStore {

    private final IdGenerator idGenerator;
    private final Equivalence<? super Topic> equivalence;
    private final Clock clock;

    public AbstractTopicStore(IdGenerator idGenerator, Equivalence<? super Topic> equivalence, Clock clock) {
        this.idGenerator = checkNotNull(idGenerator);
        this.equivalence = checkNotNull(equivalence);
        this.clock = checkNotNull(clock);
    }
    
    @Override
    public WriteResult<Topic> writeTopic(Topic topic) {
        checkNotNull(topic, "write null topic");
        checkNotNull(topic.getPublisher(), "write unsourced topic");
        
        Topic previous = getPreviousTopic(topic);
        if (previous != null) {
            if (equivalence.equivalent(topic, previous)) {
                return WriteResult.unwritten(topic)
                    .withPrevious(previous)
                    .build();
            }
            topic.setId(previous.getId());
            topic.setFirstSeen(previous.getFirstSeen());
        }
        
        DateTime now = clock.now();
        if (topic.getFirstSeen() == null) {
            topic.setFirstSeen(now);
        }
        topic.setLastUpdated(now);
        doWrite(ensureId(topic));
        return WriteResult.written(topic)
                .withPrevious(previous)
                .build();
    }

    private Topic ensureId(Topic topic) {
        topic.setId(topic.getId() != null ? topic.getId()
                                          : Id.valueOf(idGenerator.generateRaw()));
        return topic;
    }

    protected abstract void doWrite(Topic topic);

    private Topic getPreviousTopic(Topic topic) {
        Topic previous = null;
        if (topic.getId() != null) {
            previous = doResolveId(topic.getId());
        }
        if (previous == null) {
            Iterator<Alias> aliases = topic.getAliases().iterator();
            while (previous == null && aliases.hasNext()) {
                previous = doResolveAlias(aliases.next(), topic.getPublisher());
            }
        }
        return previous;
    }

    @Nullable
    protected abstract Topic doResolveId(Id id);

    @Nullable
    protected abstract Topic doResolveAlias(Alias alias, Publisher publisher);
    
}
