package org.atlasapi.persistence.lookup.entry;

import java.util.List;
import java.util.Set;

import org.atlasapi.media.common.Id;
import org.atlasapi.media.content.Content;
import org.atlasapi.media.entity.LookupRef;
import org.joda.time.DateTime;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.metabroadcast.common.time.DateTimeZones;

@Deprecated
public class LookupEntry {
 
    public static LookupEntry lookupEntryFrom(Content c) {
        DateTime now = new DateTime(DateTimeZones.UTC);
        LookupRef lookupRef = LookupRef.from(c);
        Set<String> aliases = ImmutableSet.copyOf(c.getAllUris());
        ImmutableSet<LookupRef> reflexiveSet = ImmutableSet.of(lookupRef);
        return new LookupEntry(c.getCanonicalUri(), c.getId(), lookupRef, aliases, reflexiveSet, reflexiveSet, reflexiveSet, now, now);
    }
    
    public static Function<LookupEntry,Id> TO_ID = new Function<LookupEntry, Id>() {
        @Override
        public Id apply(LookupEntry input) {
            return input.id();
        }
    };
    
    public static final Function<LookupEntry, String> TO_URI = new Function<LookupEntry, String>() {
        @Override
        public String apply(LookupEntry input) {
            return input.uri();
        }
    };
    
    public static Function<LookupEntry, LookupRef> TO_SELF = new Function<LookupEntry, LookupRef>() {
        @Override
        public LookupRef apply(LookupEntry input) {
            return input.self;
        }
    };
    
    public static Function<LookupEntry,Set<LookupRef>> TO_EQUIVS = new Function<LookupEntry, Set<LookupRef>>() {
        @Override
        public Set<LookupRef> apply(LookupEntry input) {
            return input.equivalents();
        }
    };
    
    public static Function<LookupEntry,List<LookupRef>> TO_DIRECT_EQUIVS = new Function<LookupEntry, List<LookupRef>>() {
        @Override
        public List<LookupRef> apply(LookupEntry input) {
            return ImmutableList.copyOf(input.directEquivalents());
        }
    };
    
    private final String uri;
    private final Id id;
    private final Set<String> aliases;
    
    private final Set<LookupRef> directEquivalents;
    private final Set<LookupRef> explicit;
    private final Set<LookupRef> equivs;
    
    private final DateTime created;
    private final DateTime updated;

    private final LookupRef self;

    public LookupEntry(String uri, long id, LookupRef self, Set<String> aliases, Set<LookupRef> directEquivs, Set<LookupRef> explicit, Set<LookupRef> equivs, DateTime created, DateTime updated) {
        this(uri, Id.valueOf(id), self, aliases, directEquivs, explicit, equivs, created, updated);
    }

    public LookupEntry(String uri, Id id, LookupRef self, Set<String> aliases, Set<LookupRef> directEquivs, Set<LookupRef> explicit, Set<LookupRef> equivs, DateTime created, DateTime updated) {
        this.uri = uri;
        this.id = id;
        this.self = self;
        this.aliases = aliases;
        this.directEquivalents = directEquivs != null ? ImmutableSet.copyOf(directEquivs) : ImmutableSet.<LookupRef>of();
        this.explicit = explicit != null ? ImmutableSet.copyOf(explicit) : ImmutableSet.<LookupRef>of();
        this.equivs = equivs != null ? ImmutableSet.copyOf(equivs) : ImmutableSet.<LookupRef>of();
        this.created = created;
        this.updated = updated;
    }

    public String uri() {
        return uri;
    }
    
    public Id id() {
        return id;
    }

    public Set<String> aliases() {
        return aliases;
    }
    
    public Set<String> identifiers() {
        return ImmutableSet.<String>builder().add(uri).addAll(aliases).build();
    }

    public Set<LookupRef> explicitEquivalents() {
        return explicit;
    }

    public LookupEntry copyWithExplicitEquivalents(Iterable<LookupRef> newExplicits) {
        List<LookupRef> explicit = ImmutableList.<LookupRef>builder().addAll(newExplicits).add(self).build();
        return new LookupEntry(uri, id, self, aliases, directEquivalents, ImmutableSet.copyOf(explicit), this.equivs, created, new DateTime(DateTimeZones.UTC));
    }
    
    public Set<LookupRef> equivalents() {
        return equivs;
    }

    public LookupEntry copyWithEquivalents(Iterable<LookupRef> newEquivs) {
        Set<LookupRef> equivs = ImmutableSet.<LookupRef>builder().addAll(newEquivs).add(self).build();
        return new LookupEntry(uri, id, self, aliases, directEquivalents, explicit, equivs, created, new DateTime(DateTimeZones.UTC));
    }
    
    public Set<LookupRef> directEquivalents() {
        return directEquivalents;
    }
    
    public LookupEntry copyWithDirectEquivalents(Iterable<LookupRef> directEquivalents) {
        List<LookupRef> dequivs = ImmutableList.<LookupRef>builder().addAll(directEquivalents).add(self).build();
        return new LookupEntry(uri, id, self, aliases, ImmutableSet.copyOf(dequivs), explicit, equivs, created, new DateTime(DateTimeZones.UTC));
    }

    public DateTime created() {
        return created;
    }

    public DateTime updated() {
        return updated;
    }
    
    public List<LookupEntry> entriesForIdentifiers() {
        List<LookupEntry> entries = Lists.newArrayList(this);
        for (String alias : aliases) {
            entries.add(new LookupEntry(alias, id, self, ImmutableSet.<String>of(), ImmutableSet.<LookupRef>of(), ImmutableSet.<LookupRef>of(), this.equivs, created, updated));
        }
        return ImmutableList.copyOf(entries);
    }

    public LookupRef lookupRef() {
        return self;
    }
    
    @Override
    public boolean equals(Object that) {
        if(this == that) {
            return true;
        }
        if(that instanceof LookupEntry) {
            LookupEntry other = (LookupEntry) that;
            return uri.equals(other.uri) && equivs.equals(other.equivs) && created.equals(other.created) && updated.equals(other.updated);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(uri, equivs, created, updated);
    }
    
    @Override
    public String toString() {
        return "Lookup entry for " + uri;
    }

}
