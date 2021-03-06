package org.atlasapi.media.entity.simple;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;
import java.util.Set;

@XmlRootElement(namespace = PLAY_SIMPLE_XML.NS)
@XmlType(name = "broadcast" , namespace = PLAY_SIMPLE_XML.NS)
public class Broadcast extends Version implements Comparable<Broadcast> {

    public static final Predicate<Broadcast> IS_CURRENT_OR_UPCOMING = input ->
            new DateTime(input.getTransmissionEndTime(), DateTimeZone.UTC).isAfterNow();
    public static final Function<Broadcast, Broadcast> TO_COPY = Broadcast::copy;

    private Date transmissionTime;
    private Date transmissionEndTime;
    private Date actualTransmissionTime;
    private Date actualTransmissionEndTime;
    private Integer broadcastDuration;
    private String broadcastOn;
    private LocalDate scheduleDate;
    private String id;
    private Boolean repeat;
    private Boolean subtitled;
    private Boolean signed;
    private Boolean audioDescribed;
    private Boolean highDefinition;
    private Boolean widescreen;
    private Boolean surround;
    private Boolean revisedRepeat;
    private Boolean live;
    private Boolean premier;
    private Boolean continuation;
    private Boolean newSeries;
    private Boolean newEpisode;
    private Boolean newOneOff;
    private Channel channel;
    private BlackoutRestriction blackoutRestriction;
    private Set<String> aliases = Sets.newHashSet();

    public Broadcast(String broadcastOn, DateTime transmissionTime, DateTime transmissionEndTime) {
        this(broadcastOn, transmissionTime, transmissionEndTime, null, null);
    }

    public Broadcast(
            String broadcastOn,
            DateTime transmissionTime,
            DateTime transmissionEndTime,
            String id,
            BlackoutRestriction blackoutRestriction
    ) {
        this.broadcastOn = broadcastOn;
        this.transmissionTime = transmissionTime.toDate();
        this.transmissionEndTime = transmissionEndTime.toDate();
        this.broadcastDuration = (int) new Duration(transmissionTime, transmissionEndTime).getStandardSeconds();
        this.id = id;
        this.blackoutRestriction = blackoutRestriction;
    }

    public Broadcast() {

    }

    public Date getTransmissionTime() {
        return transmissionTime;
    }

    public void setTransmissionTime(Date transmissionTime) {
        this.transmissionTime = transmissionTime;
    }

    public Date getTransmissionEndTime() {
        return transmissionEndTime;
    }

    public void setTransmissionEndTime(Date transmissionEndTime) {
        this.transmissionEndTime = transmissionEndTime;
    }

    public Date getActualTransmissionTime() {
        return actualTransmissionTime;
    }

    public void setActualTransmissionTime(Date actualTransmissionTime) {
        this.actualTransmissionTime = actualTransmissionTime;
    }

    public Date getActualTransmissionEndTime() {
        return actualTransmissionEndTime;
    }

    public void setActualTransmissionEndTime(Date actualTransmissionEndTime) {
        this.actualTransmissionEndTime = actualTransmissionEndTime;
    }

    public Integer getBroadcastDuration() {
        return broadcastDuration;
    }

    public void setBroadcastDuration(Integer broadcastDuration) {
        this.broadcastDuration = broadcastDuration;
    }

    public String getBroadcastOn() {
        return broadcastOn;
    }

    public void setBroadcastOn(String broadcastOn) {
        this.broadcastOn = broadcastOn;
    }

    public LocalDate getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getRepeat() {
        return repeat;
    }

    public void setRepeat(Boolean repeat) {
        this.repeat = repeat;
    }

    public Boolean getSubtitled() {
        return subtitled;
    }

    public void setSubtitled(Boolean subtitled) {
        this.subtitled = subtitled;
    }

    public Boolean getSigned() {
        return signed;
    }

    public void setSigned(Boolean signed) {
        this.signed = signed;
    }

    public Boolean getAudioDescribed() {
        return audioDescribed;
    }

    public void setAudioDescribed(Boolean audioDescribed) {
        this.audioDescribed = audioDescribed;
    }

    public Boolean getHighDefinition() {
        return highDefinition;
    }

    public void setHighDefinition(Boolean highDefinition) {
        this.highDefinition = highDefinition;
    }

    public Boolean getWidescreen() {
        return widescreen;
    }

    public void setWidescreen(Boolean widescreen) {
        this.widescreen = widescreen;
    }

    public Boolean getSurround() {
        return surround;
    }

    public void setSurround(Boolean surround) {
        this.surround = surround;
    }

    public Boolean getLive() {
        return live;
    }

    public void setLive(Boolean live) {
        this.live = live;
    }

    public Boolean getPremier() {
        return premier;
    }

    public void setPremiere(Boolean premier) {
        this.premier = premier;
    }

    public Boolean getNewSeries() {
        return newSeries;
    }

    public void setNewSeries(Boolean newSeries) {
        this.newSeries = newSeries;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Boolean getNewEpisode() {
        return newEpisode;
    }

    public void setNewEpisode(Boolean newEpisode) {
        this.newEpisode = newEpisode;
    }

    public Boolean getContinuation() {
        return continuation;
    }

    public void setContinuation(Boolean continuation) {
        this.continuation = continuation;
    }

    public Boolean getNewOneOff() {
        return newOneOff;
    }

    public void setNewOneOff(Boolean newOneOff) {
        this.newOneOff = newOneOff;
    }

    @XmlElementWrapper(name = "aliases")
    @XmlElement(name = "alias")
    public Set<String> getAliases() {
        return aliases;
    }

    public void setAliases(Set<String> aliases) {
        this.aliases = aliases;
    }

    public BlackoutRestriction getBlackoutRestriction() {
        return blackoutRestriction;
    }

    public void setBlackoutRestriction(BlackoutRestriction blackoutResstriction) {
        this.blackoutRestriction = blackoutResstriction;
    }

    public Boolean getRevisedRepeat() {
        return revisedRepeat;
    }

    public void setRevisedRepeat(Boolean revisedRepeat) {
        this.revisedRepeat = revisedRepeat;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(id)
                .addValue(broadcastOn)
                .addValue(transmissionTime)
                .addValue(transmissionEndTime)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Broadcast) {
            return this.compareTo((Broadcast) obj) == 0;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(transmissionTime, broadcastDuration);
    }

    public Broadcast copy() {
        Broadcast copy = new Broadcast();

        copyTo(copy);

        if (getTransmissionTime() != null) {
            copy.setTransmissionTime((Date) getTransmissionTime().clone());
        }
        if (getTransmissionEndTime() != null) {
            copy.setTransmissionEndTime((Date) getTransmissionEndTime().clone());
        }
        copy.setActualTransmissionTime(getActualTransmissionTime());
        copy.setActualTransmissionEndTime(getActualTransmissionEndTime());
        copy.setBroadcastDuration(getBroadcastDuration());
        copy.setBroadcastOn(getBroadcastOn());
        copy.setScheduleDate(getScheduleDate());
        copy.setId(getId());
        copy.setRepeat(getRepeat());
        copy.setSubtitled(getSubtitled());
        copy.setSigned(getSigned());
        copy.setAudioDescribed(getAudioDescribed());
        copy.setHighDefinition(getHighDefinition());
        copy.setWidescreen(getWidescreen());
        copy.setSurround(getSurround());
        copy.setLive(getLive());
        copy.setAliases(getAliases());
        copy.setNewSeries(getNewSeries());
        copy.setNewEpisode(getNewEpisode());
        copy.setContinuation(getContinuation());
        copy.setNewOneOff(getNewOneOff());
        copy.setBlackoutRestriction(getBlackoutRestriction());
        copy.setRevisedRepeat(getRevisedRepeat());
        return copy;
    }

    @Override
    public int compareTo(Broadcast other) {
        return ComparisonChain.start()
                .compare(transmissionTime, other.transmissionTime)
                .compare(broadcastDuration, other.broadcastDuration)
                .compare(broadcastOn, other.broadcastOn, Ordering.natural().nullsFirst())
                .result();
    }
}
