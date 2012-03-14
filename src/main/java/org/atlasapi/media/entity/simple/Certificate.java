package org.atlasapi.media.entity.simple;

public class Certificate {

    private String classification;
    private String code;

    public Certificate() {}
    
    public Certificate(String classification, String code) {
        this.classification = classification;
        this.code = code;
    }

    public String getClassification() {
        return this.classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
