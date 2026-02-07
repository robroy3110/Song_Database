package pt.ulusofona.aed.deisiRockstar2021;

import java.util.*;

public class SimpleQuery {

    public static String countSongsYear(int year) {
        int count = 0;

        for (Song song : Main.songs.values()) {
            if (song.ano != -1) {
                if (song.ano == year) {
                    count++;
                }
            }
        }

        return "" + count;
    }

    public static String countDuplicateSongsYear(int year) {
        int pos = 0;
        int count = 0;

        Collection<Song> song = Main.songs.values();
        ArrayList<Song> songs = new ArrayList<>(song);

        Comparator<Song> compareByAno = Comparator.comparing(Song::getAno);
        songs.sort(compareByAno);


        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).ano == year) {
                pos = i;
                break;
            }
        }

        if(songs.size() > 1){
            for (int i = pos; songs.get(i).ano == year; i++) {
                String titulo = songs.get(i).titulo;
                for (int a = i + 1; songs.get(a).ano == year; a++) {
                    if (titulo.equals(songs.get(a).titulo)) {
                        count++;
                        break;
                    }
                }
            }
        }

        return "" + count;
    }

    public static String getMostDanceable(int firstYear, int lastYear, int results) {
        String res = "";
        Collection<Song> song = Main.songs.values();
        ArrayList<Song> songs = new ArrayList<>(song);

        Comparator<Song> compareByDancabilidade = Comparator.comparing(Song::getDancabilidade);
        songs.sort(compareByDancabilidade.reversed());

        for (int i = 0, j = 0; i < songs.size() && j < results; i++){
            if(songs.get(i).ano >= firstYear && songs.get(i).ano <= lastYear){
                res += songs.get(i).titulo + " : " + songs.get(i).ano + " : " + songs.get(i).dancabilidade + "\n";
                j++;

            }
        }

        return res;
    }

    public static String getTop10LivelinessSongs(int year, double liveliness) {
        String res = "";
        int count = 0;
        Collection<Song> song = Main.songs.values();
        ArrayList<Song> songs = new ArrayList<>(song);

        Comparator<Song> compareByVivacidade = Comparator.comparing(Song::getVivacidade);
        songs.sort(compareByVivacidade.reversed());

        for (int i = 0, j = 0; i < songs.size() && j < 10; i++){
            if(songs.get(i).ano == year && songs.get(i).vivacidade >= liveliness){
                res += songs.get(i).titulo + " : " + songs.get(i).ano + " : " + songs.get(i).vivacidade + "\n";
                j++;
                count++;
            }
        }
        if (count == 0) {
            res += "No results\n";
        } else if (count != 10) {
            res += "No more results\n";
        }

        return res;
    }

    public static void savingData(ArrayList<Song> artistsYear, HashMap<String, Integer> artCounter, HashMap<String,Song> songMap) {

        for (int j = 0; j < artistsYear.size();j++) {
            if (artistsYear.get(j).artistas != null) {
                for (int i = 0; i < artistsYear.get(j).artistas.length; i++) {
                    String art = artistsYear.get(j).artistas[i].nome;

                    if (artistsYear.get(j).artistas[i].nome.startsWith("'In The Heights'")) {
                        art = artistsYear.get(j).artistas[i].nome.replace("'","");
                    }
                    if (artCounter.containsKey(art)) {
                        int value = artCounter.get(art);
                        value = value + 1;
                        artCounter.put(art, value);
                    } else {
                        artCounter.put(art, 1);
                        songMap.put(art, artistsYear.get(j));
                    }
                }
            }
        }

    }

    public static String getArtistsOneSong(int firstYear, int lastYear) {
        if(firstYear >= lastYear){
            return "Invalid period";
        }

        ArrayList<Song> artistsYear = new ArrayList<>();
        HashMap<String,Integer> artCounter = new HashMap<>();
        HashMap<String,Song> songMap = new HashMap<>();
        ArrayList<Song> sort = new ArrayList<>();
        String res = "";

        for (Song song : Main.songs.values()){
            if(song.ano >= firstYear && song.ano <= lastYear){
                artistsYear.add(song);
            }
        }

        savingData(artistsYear, artCounter, songMap);

        for(String key : artCounter.keySet()){
            if(artCounter.get(key).equals(1)){
                Artista[] artistas = new Artista[1];
                artistas[0] = new Artista(null,key);
                Song songs = new Song(songMap.get(key).titulo, artistas, songMap.get(key).ano);
                sort.add(songs);
            }
        }

        sort.sort(Comparator.comparing(Song::getArtistName));
        for (Song value : sort) {
            res += "" + value.artistas[0] + " | " + value.titulo + " | " + value.ano + "\n";
        }

        return res;
    }

    public static String getTopArtistsWithSongsBetween(int n, int themeA, int themeB) {
        String res = "";
        int count = 0;

        List<Map.Entry<String,Integer>> list = new LinkedList<>(Main.artistCountSongs.entrySet());

        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        for (int i = 0, j = 0; i < list.size() && j < n; i++) {
            if(list.get(i).getValue() >= themeA && list.get(i).getValue() <= themeB){
                res += list.get(i).getKey() + " " + list.get(i).getValue() + "\n";
                j++;
                count++;
            }
        }

        if (count == 0) {
            return "No results";
        }

        return res;
    }

    public static String mostFrequentWordsInArtistName(int n, int m, int l) {
        return "No results\n";
    }

    public static String cleanup() {
        int ignoredSongs = 0;
        int ignoredArtists = 0;

        List<String> listOfSongsToBeRemoved = new ArrayList<>();
        List<String> listOfSongsWoutArtToBeRemoved = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();

        for (Song song : Main.songs.values()) {
            if (song.artistas == null && song.duracao == -1) {
                //nao tem artists nem details
                listOfSongsWoutArtToBeRemoved.add(song.id);
                ignoredSongs++;
            } else if (song.artistas == null) {
                //nao tem artists
                listOfSongsWoutArtToBeRemoved.add(song.id);
                ignoredSongs++;
            } else if (song.duracao == -1) {
                //nao tem details
                listOfSongsToBeRemoved.add(song.id);
                ignoredSongs++;

                String[] artists = new String[song.artistas.length];
                counter(song, map, artists);
            }
        }

        ignoredArtists = comparator(listOfSongsToBeRemoved, map);

        // Removed selected songs
        for (String key : listOfSongsToBeRemoved) {
            Main.songs.remove(key);
        }
        for (String key : listOfSongsWoutArtToBeRemoved) {
            Main.songs.remove(key);
        }

        return "Musicas removidas: " + ignoredSongs + "; Artistas removidos: " + ignoredArtists;
    }

    public static HashMap<String, Integer> counter(Song song, HashMap<String, Integer> map, String[] artists) {

        for (int i = 0; i < song.artistas.length; i++) {
            artists[i] = song.artistas[i].nome;
            if (map.containsKey(artists[i])) {
                int value = map.get(artists[i]);
                value = value + 1;
                map.put(artists[i], value);
            } else {
                map.put(artists[i], 1);
            }
        }

        return map;

    }

    public static int comparator(List<String> listOfSongsToBeRemoved, HashMap<String, Integer> map) {

        TreeSet<String> tree = new TreeSet<>();
        int ignoredArtists = 0;

        for (String id : listOfSongsToBeRemoved) {
            for (int i = 0; i < Main.songs.get(id).artistas.length; i++) {
                String name = Main.songs.get(id).artistas[i].nome;
                if (!(tree.contains(name))) {
                    tree.add(name);
                    int value = Main.artistCountSongs.get(Main.songs.get(id).artistas[i].nome);
                    int value2 = map.get(Main.songs.get(id).artistas[i].nome);
                    if (value == value2) {
                        ignoredArtists++;
                    }
                }
            }
        }

        return ignoredArtists;

    }

}
