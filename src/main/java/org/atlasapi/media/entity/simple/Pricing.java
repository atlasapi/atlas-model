package org.atlasapi.media.entity.simple;

import com.metabroadcast.common.currency.Price;
import org.joda.time.DateTime;

import static com.google.common.base.Preconditions.checkNotNull;

public class Pricing {

    private final DateTime startTime;
    private final DateTime endTime;
    private final Price price;

    public Pricing(DateTime startTime, DateTime endTime, Price price) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = checkNotNull(price);
    }


    public DateTime getStartTime() {
        return startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public Price getPrice() {
        return price;
    }
}
