package eni.tp.app.eni_app.Bo;

public class Genre {
    private int id;
    private String titre;

    public Genre(int id, String titre) {
        this.id = id;
        this.titre = titre;
    }

    public Genre() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                '}';
    }
}
