package org.atlasapi.media.util;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nullable;

import org.joda.time.DateTime;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.ComparisonChain;
import com.metabroadcast.common.time.DateTimeZones;

/**
 * Represents the result of a call to a write/store procedure, for use with
 * *Store types.
 * 
 * Immutable given W is immutable.
 * 
 * @param <W>
 *            - type of the written resource
 */
public final class WriteResult<W> implements Comparable<WriteResult<?>> {
    
    public static final Predicate<WriteResult<?>> WRITTEN
        = new Predicate<WriteResult<?>>() {
            @Override
            public boolean apply(@Nullable WriteResult<?> input) {
                return input.written();
            }
        };
        
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static final <W> Predicate<WriteResult<? extends W>> writtenFilter() {
        return (Predicate) WRITTEN;
    }

    public static final <W> Builder<W> result(W resource, boolean wasWritten) {
        return new Builder<W>(resource, wasWritten, new DateTime(DateTimeZones.UTC));
    }

    public static final <W> Builder<W> unwritten(W resource) {
        return result(resource, false);
    }

    public static final <W> Builder<W> written(W resource) {
        return result(resource, true);
    }
    
    public static final class Builder<W> {

        private W resource;
        private boolean written;
        private DateTime writeTime;
        private W previous;

        public Builder(W resource, boolean written, DateTime writeTime) {
            this.resource = resource;
            this.written = written;
            this.writeTime = writeTime;
        }

        public Builder<W> withPrevious(W previous) {
            this.previous = previous;
            return this;
        }
        
        public WriteResult<W> build() {
            return new WriteResult<W>(resource, written, writeTime, previous);
        }
    }

    private final W resource;
    private final boolean written;
    private final DateTime writeTime;
    private final Optional<W> previous;

    public WriteResult(W resource, boolean written, DateTime writeTime, @Nullable W previous) {
        this.resource = checkNotNull(resource);
        this.written = written;
        this.writeTime = checkNotNull(writeTime);
        this.previous = Optional.fromNullable(previous);
    }

    /**
     * The resource as it was written when the write was attempted.
     * 
     * @return - the resource
     */
    public W getResource() {
        return resource;
    }

    /**
     * Indicates whether the resource was actually written at this call to
     * write.
     * 
     * @return true iff the resource was written.
     */
    public boolean written() {
        return written;
    }

    /**
     * The time at which the write was attempted.
     * 
     * @return
     */
    public DateTime getWriteTime() {
        return writeTime;
    }
    
    /**
     * The previous version of the resource.
     * @return
     */
    public Optional<W> getPrevious() {
        return previous;
    }

    @Override
    public int compareTo(WriteResult<?> other) {
        return ComparisonChain.start()
            .compare(writeTime, other.writeTime)
            .compareFalseFirst(written, other.written)
            .compare(resource.hashCode(), other.resource.hashCode())
            .result();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof WriteResult<?>) {
            WriteResult<?> other = (WriteResult<?>) that;
            return written == other.written
                && writeTime.equals(other.writeTime)
                && resource.equals(other.resource);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(resource, writeTime, written);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("resource", resource.toString())
            .add("written", written)
            .add("at", writeTime)
            .toString();
    }

}
