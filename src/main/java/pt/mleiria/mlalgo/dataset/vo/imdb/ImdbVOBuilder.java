package pt.mleiria.mlalgo.dataset.vo.imdb;

public final class ImdbVOBuilder {
    private int rank;
    private String title;
    private String genre;
    private String description;
    private String director;
    private String actors;
    private int year;
    private int runtimeMinutes;
    private double rating;
    private int votes;
    private double revenueMillions;
    private int metascore;

    private ImdbVOBuilder() {
    }

    public static ImdbVOBuilder anImdbVO() {
        return new ImdbVOBuilder();
    }

    public ImdbVOBuilder withRank(int rank) {
        this.rank = rank;
        return this;
    }

    public ImdbVOBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public ImdbVOBuilder withGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public ImdbVOBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ImdbVOBuilder withDirector(String director) {
        this.director = director;
        return this;
    }

    public ImdbVOBuilder withActors(String actors) {
        this.actors = actors;
        return this;
    }

    public ImdbVOBuilder withYear(int year) {
        this.year = year;
        return this;
    }

    public ImdbVOBuilder withRuntimeMinutes(int runtimeMinutes) {
        this.runtimeMinutes = runtimeMinutes;
        return this;
    }

    public ImdbVOBuilder withRating(double rating) {
        this.rating = rating;
        return this;
    }

    public ImdbVOBuilder withVotes(int votes) {
        this.votes = votes;
        return this;
    }

    public ImdbVOBuilder withRevenueMillions(double revenueMillions) {
        this.revenueMillions = revenueMillions;
        return this;
    }

    public ImdbVOBuilder withMetascore(int metascore) {
        this.metascore = metascore;
        return this;
    }

    public ImdbVO build() {
        ImdbVO imdbVO = new ImdbVO();
        imdbVO.setRank(rank);
        imdbVO.setTitle(title);
        imdbVO.setGenre(genre);
        imdbVO.setDescription(description);
        imdbVO.setDirector(director);
        imdbVO.setActors(actors);
        imdbVO.setYear(year);
        imdbVO.setRuntimeMinutes(runtimeMinutes);
        imdbVO.setRating(rating);
        imdbVO.setVotes(votes);
        imdbVO.setRevenueMillions(revenueMillions);
        imdbVO.setMetascore(metascore);
        return imdbVO;
    }
}
