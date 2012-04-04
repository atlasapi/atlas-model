package org.atlasapi.media.content.item;

public enum RevenueContract {
    
    PAY_TO_BUY,
    PAY_TO_RENT,
    SUBSCRIPTION,
    FREE_TO_VIEW,
    VOLUNTARY_DONATION,
    PRIVATE;
    
    public String key() {
        return name().toLowerCase();
    }
    
    public static RevenueContract fromKey(String key) {
        for (RevenueContract contract: values()) {
            if (contract.key().equals(key)) {
                return contract;
            }
        }
        return FREE_TO_VIEW;
    }
}