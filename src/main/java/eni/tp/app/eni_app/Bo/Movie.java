package eni.tp.app.eni_app.Bo;

public class Movie {
    public long id;
    public String title;
    public int note =2;
    public int year;
    public int duration;
    public String synopsis;
    public String url;

    public Movie(long id, String title, int year, int duration, String synopsis, String url) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.synopsis = synopsis;
        this.url = url;
    }
    public int getNote(){
        return note;
    }
}
