package org.atlasapi.media.entity.simple;

public class Language {

    private String display;
    private String code;
    
    public Language(){}
    
    public Language(String code, String display) {
        this.code = code;
        this.display = display;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDisplay() {
        return display;
    }
    public void setDisplay(String display) {
        this.display = display;
    }
    
}
