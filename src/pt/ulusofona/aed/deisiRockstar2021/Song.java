package pt.ulusofona.aed.deisiRockstar2021;

public class Song {

    String id;
    String titulo;
    Artista[] artistas;
    int ano;
    int duracao;
    boolean letra;
    int popular;
    double dancabilidade;
    double vivacidade;
    double volume;

    public Song() {}

    public Song(String id,String titulo,int ano){
        this.id = id;
        this.titulo = titulo;
        this.ano = ano;
    }

    public Song(String id,Artista[] artista){
        this.id = id;
        this.artistas = artista;
    }

    public Song(String id,String titulo,Artista[] artistas,int ano) {
        this.id = id;
        this.titulo = titulo;
        this.ano = ano;
        this.artistas = artistas;
    }

    public Song(String id,int duracao,boolean letra,int popular,double dancabilidade,double vivacidade,double volume){
        this.id = id;
        this.duracao = duracao;
        this.letra = letra;
        this.popular = popular;
        this.dancabilidade = dancabilidade;
        this.vivacidade = vivacidade;
        this.volume = volume;
    }

    public Song(String id,String titulo,int ano,Artista[] artista,int duracao,boolean letra,int popular,double dancabilidade,double vivacidade,double volume){
        this.id = id;
        this.titulo = titulo;
        this.artistas = artista;
        this.ano = ano;
        this.duracao = duracao;
        this.letra = letra;
        this.popular = popular;
        this.dancabilidade = dancabilidade;
        this.vivacidade = vivacidade;
        this.volume = volume;
    }

    public Song(String id, String titulo, int ano, int duracao, boolean letra, int popular, double dancabilidade, double vivacidade, double volume) {
        this.id = id;
        this.titulo = titulo;
        this.ano = ano;
        this.duracao = duracao;
        this.letra = letra;
        this.popular = popular;
        this.dancabilidade = dancabilidade;
        this.vivacidade = vivacidade;
        this.volume = volume;
    }

    public Song(String id, String titulo, Artista[] artistas, int ano, int duracao, boolean letra, int popularidade, double dancabilidade, double vivacidade, double volume) {
        this.id = id;
        this.titulo = titulo;
        this.artistas = artistas;
        this.ano = ano;
        this.duracao = duracao;
        this.letra = letra;
        this.popular = popularidade;
        this.dancabilidade = dancabilidade;
        this.vivacidade = vivacidade;
        this.volume = volume;
    }

    public Song(String titulo,Artista[] artistas,int ano){
        this.titulo = titulo;
        this.artistas = artistas;
        this.ano = ano;
    }

    public String getId(){
        return id;
    }

    public void setId(){
        this.id = id;
    }

    public String getArtistName(){
        return artistas[0].nome;
    }

    public double getDancabilidade(){
        return dancabilidade;
    }
    public double getVivacidade(){
        return vivacidade;
    }

    public int getAno(){
        return ano;
    }
    public void setAno(){
        this.ano = ano;
    }

    public String toString(){

        int seconds = duracao / 1000;
        int minutes = seconds / 60;
        seconds = seconds % 60;

        int i, j, count;
        String artistsStr = "";
        String countsStr = "";

        if (artistas != null) {

            Integer[] counts = new Integer[artistas.length];

            for (i = 0; i < artistas.length; i++) {
                if (i == 0) {
                    artistsStr += artistas[i];
                } else {
                    artistsStr += " / " + artistas[i];
                }
            }

            for (j = 0; j < artistas.length; j++) {
                count = 0;

                for (Song song : Main.songs.values()) {
                    if (song.artistas != null) {
                        for (Artista a : song.artistas) {
                            if (a.nome.equals(artistas[j].nome)) {
                                count++;
                            }
                        }
                    }
                }

                counts[j] = count;
                Main.artistCountSongs.put(artistas[j].nome, count);
            }

            for (i = 0; i < artistas.length; i++) {
                if (i == 0) {
                    countsStr += counts[i];
                } else {
                    countsStr += " / " + counts[i];
                }
            }

        } else {
            artistsStr = "";
        }

        return "" + id + " | " + titulo + " | " + ano + " | " + minutes + ":" + seconds + " | " + popular + ".0 | " + artistsStr + " | (" + countsStr + ")" ;
    }

    public boolean compareTo(Song pivot) {
        return false;
    }
}
