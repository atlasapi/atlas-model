package org.atlasapi.media.entity;


public class Equiv {

    private final String right;
    private final String left;

    private transient String key;

    public Equiv(String left, String right) {
        if (left == null || right == null) {
            throw new IllegalArgumentException("Neither left nor right can be null");
        }
        this.left = left;
        this.right = right;
    }

    public String left() {
        return left;
    }

    public String right() {
        return right;
    }

    public String key() {
        if (key == null) {
            key = toKey();
        }
        return key;
    }

    @Override
    public int hashCode() {
        return key().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Equiv) {
            Equiv target = (Equiv) obj;
            return (key().equals(target.key()));
        }
        return false;
    }

    @Override
    public String toString() {
        return left + " and " + right + " are equivalent";
    }

    private String toKey() {
        return String.valueOf(left.hashCode() ^ right.hashCode());
    }
}
