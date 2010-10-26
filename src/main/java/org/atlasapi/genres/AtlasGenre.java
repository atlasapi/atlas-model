package org.atlasapi.genres;

public enum AtlasGenre {
    
    COMEDY("http://ref.atlasapi.org/genres/atlas/comedy"),
    CHILDRENS("http://ref.atlasapi.org/genres/atlas/childrens"),
    DRAMA("http://ref.atlasapi.org/genres/atlas/drama"),
    LEARNING("http://ref.atlasapi.org/genres/atlas/learning"),
    MUSIC("http://ref.atlasapi.org/genres/atlas/music"),
    NEWS("http://ref.atlasapi.org/genres/atlas/news"),
    FACTUAL("http://ref.atlasapi.org/genres/atlas/factual"),
    SPORT("http://ref.atlasapi.org/genres/atlas/sports"),
    LIFESTYLE("http://ref.atlasapi.org/genres/atlas/lifestyle"),
    ANIMALS("http://ref.atlasapi.org/genres/atlas/animals"),
    ENTERTAINMENT("http://ref.atlasapi.org/genres/atlas/entertainment"),
    FILM("http://ref.atlasapi.org/genres/atlas/film"),
    ANIMATION("http://ref.atlasapi.org/genres/atlas/animation");
    
    private final String uri;
    
    private AtlasGenre(String uri) {
        this.uri = uri;
    }
    
    public String getUri() {
        return uri;
    }
}
