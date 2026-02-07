package pt.ulusofona.aed.deisiRockstar2021;

public class Artista {

    String id;
    String nome;

    public Artista() {}

    public Artista(String nome) {
        this.nome = nome;
    }

    public Artista(String id, String nome) {
        this.id = id;
        this.nome = nome;

    }

    public String toString(){
        return ""+nome;
    }

}
