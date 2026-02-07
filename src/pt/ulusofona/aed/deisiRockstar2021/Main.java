package pt.ulusofona.aed.deisiRockstar2021;

import java.io.IOException;
import java.util.*;

public class Main {

    public static LinkedHashMap<String,Song> songs = new LinkedHashMap<>();
    public static HashMap<String,Integer> artistCountSongs = new HashMap<>();
    public static HashMap<String,ArrayList<Song>> artistSongs = new LinkedHashMap<>();
    public static HashMap<Integer,TreeSet<String>> yearArtists = new HashMap<>();
    public static HashMap<String,TreeSet<Integer>> artistYears = new HashMap<>();
    public static ParseInfo songsLinesInfo = new ParseInfo();
    public static ParseInfo artistsLinesInfo = new ParseInfo();
    public static ParseInfo detailsLinesInfo = new ParseInfo();

    public static String getCreativeQuery() { return "GET_TOP_10_LIVELINESS_SONGS"; }
    public static int getTypeOfSecondParameter() { return 3; }
    public static String getVideoUrl() { return "https://youtu.be/_4-MUfzWC4k"; }

    public static String execute(String command) {
        return Execution.getQuery(command);
    }
    public static Artista[] savingData(TreeSet<String> tree, Artista[] artistas, String id) {
        int i = 0;
        for (String item : tree) {
            yearArtists.get(songs.get(id).ano).add(item);
            if (artistCountSongs.containsKey(item)) {
                int value = artistCountSongs.get(item);
                value = value + 1;
                artistCountSongs.put(item, value);
            } else {
                artistCountSongs.put(item, 1);
            }
            if (artistYears.containsKey(item)) {
                artistYears.get(item).add(songs.get(id).ano);
            } else {
                TreeSet<Integer> tree2 = new TreeSet<>();
                artistYears.put(item, tree2);
                artistYears.get(item).add(songs.get(id).ano);
            }
            artistas[i] = new Artista(id,item);
            i++;
        }
        if (songs.get(id).artistas != null) {
            for(int it = 0; it < songs.get(id).artistas.length; it++) {
                for (String item : tree) {
                    if (songs.get(id).artistas[it].nome.equals(item)) {
                        int value = artistCountSongs.get(item);
                        value = value - 1;
                        artistCountSongs.put(item, value);
                    }
                }
            }
        }
        return artistas;
    }

    static void loadFiles(String fileSongs, String fileArtists, String fileDetails) throws IOException {
        songs.clear();
        artistCountSongs.clear();
        artistSongs.clear();
        yearArtists.clear();
        artistYears.clear();
        Reader.loadFilesSongs(fileSongs);
        Reader.loadFilesArtists(fileArtists);
        Reader.loadFilesDetails(fileDetails);
    }

    public static void loadFiles() throws IOException {
        loadFiles("songs.txt", "song_artists.txt", "song_details.txt");
    }

    public static ArrayList<Song> getSongs() {
        Collection<Song> song = songs.values();
        return new ArrayList<>(song);
    }

    public static ParseInfo getParseInfo (String fileName){
        switch (fileName) {
            case "songs.txt": return songsLinesInfo;
            case "song_artists.txt": return artistsLinesInfo;
            case "song_details.txt": return detailsLinesInfo;
            default: return null;
        }
    }

    public static void main(String[] args) throws IOException {
        try {
            loadFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Welcome to DEISI Rockstar!");
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (line != null && !line.equals("KTHXBYE")) {
            long start = System.currentTimeMillis();
            String result = execute(line);
            long end = System.currentTimeMillis();
            if (result != null) {
                System.out.println(result);
            }
            System.out.println("(took " + (end - start) + " ms)");
            line = in.nextLine();
            if (line.equals("KTHXBYE")) {
                System.out.println("Adeus.");
            }
        }
    }

}