package org.atlasapi.media.content.item;

import org.atlasapi.media.content.Specialization;

import com.google.common.collect.ImmutableSet;

public final class Film extends Item {
    
    public static final FilmBuilder builder() {
        return new FilmBuilder();
    }
    
    public static final class FilmBuilder extends Item.BaseBuilder<Film, FilmBuilder> {
        
        private Integer year;
        private String websiteUrl;
        private ImmutableSet<Subtitles> subtitles = ImmutableSet.of();
        private ImmutableSet<String> languages = ImmutableSet.of();
        private ImmutableSet<ReleaseDate> releaseDates = ImmutableSet.of();
        private ImmutableSet<Certificate> certificates = ImmutableSet.of();

        @Override
        public FilmBuilder copy(Film film) {
            super.copy(film);
            this.year = film.year;
            this.websiteUrl = film.websiteUrl;
            this.subtitles = film.subtitles;
            this.languages = film.languages;
            this.releaseDates = film.releaseDates;
            this.certificates = film.certificates;
            return builder();
        }

        public FilmBuilder withyear(Integer year) {
            this.year = year;
            return builder();
        }

        public FilmBuilder withwebsiteUrl(String websiteUrl) {
            this.websiteUrl = websiteUrl;
            return builder();
        }

        public FilmBuilder withsubtitles(Iterable<Subtitles> subtitles) {
            this.subtitles = ImmutableSet.copyOf(subtitles);
            return builder();
        }

        public FilmBuilder withlanguages(Iterable<String> languages) {
            this.languages = ImmutableSet.copyOf(languages);
            return builder();
        }

        public FilmBuilder withreleaseDates(Iterable<ReleaseDate> releaseDates) {
            this.releaseDates = ImmutableSet.copyOf(releaseDates);
            return builder();
        }

        public FilmBuilder withcertificates(Iterable<Certificate> certificates) {
            this.certificates = ImmutableSet.copyOf(certificates);
            return builder();
        }

        @Override
        protected FilmBuilder builder() {
            return this;
        }

        @Override
        public Film build() {
            this.specialization = Specialization.FILM;
            return new Film(this);
        }
        
    }

    private final Integer year;
    private final String websiteUrl;
    private final ImmutableSet<Subtitles> subtitles;
    private final ImmutableSet<String> languages;
    private final ImmutableSet<ReleaseDate> releaseDates;
    private final ImmutableSet<Certificate> certificates;

    private Film(FilmBuilder b) {
        super(b);
        this.year = b.year;
        this.websiteUrl = b.websiteUrl;
        this.subtitles = b.subtitles;
        this.languages = b.languages;
        this.releaseDates = b.releaseDates;
        this.certificates = b.certificates;
    }
    
    public Integer year() {
        return year;
    }

    public String websiteUrl() {
        return websiteUrl;
    }

    public ImmutableSet<Subtitles> subtitles() {
        return subtitles;
    }

    public ImmutableSet<String> languages() {
        return languages;
    }

    public ImmutableSet<ReleaseDate> releaseDates() {
        return releaseDates;
    }

    public ImmutableSet<Certificate> certificates() {
        return certificates;
    }
}
