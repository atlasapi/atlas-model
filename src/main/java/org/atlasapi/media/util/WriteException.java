package org.atlasapi.media.util;

public class WriteException extends StoreException {

    public WriteException() {
        super();
    }

    public WriteException(String message, Throwable cause) {
        super(message, cause);
    }

    public WriteException(String message) {
        super(message);
    }

    public WriteException(Throwable cause) {
        super(cause);
    }

}
