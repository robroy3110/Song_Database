package pt.ulusofona.aed.deisiRockstar2021;

import java.util.*;

public class ComplexQuery {

    public static Collection<Song> song = Main.songs.values();
    public static ArrayList<Song> songs = new ArrayList<>(song);
    public static LinkedHashMap<String, TreeSet<String>> tagsHash = new LinkedHashMap<>();
    public static LinkedHashMap<String,TreeSet<String>> artistsHash = new LinkedHashMap<>();

    public static String getArtistsForTag(String tag) {
        String res = "";

        if(!(tagsHash.containsKey(tag.toUpperCase()))){
            return "No results";
        }

        res += tagsHash.get(tag.toUpperCase());

        res = res.replace("[","");
        res = res.replace("]","");
        res = res.replace(", ",";");

        return res;

    }

    public static String getUniqueTags() {
        if (tagsHash.isEmpty()) {
            return "No results";
        }

        String res = "";
        HashMap<String, Integer> tagsCount = new HashMap<>();

        for(String key : tagsHash.keySet()){
            tagsCount.put(key,tagsHash.get(key).size());
        }

        List<Map.Entry<String,Integer>> list = new LinkedList<>(tagsCount.entrySet());

        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        for(int i = 0; i < list.size(); i++){
            res += "" + list.get(i).getKey() + " " + list.get(i).getValue() + "\n";
        }

        return res;
    }

    public static String getUniqueTagsInBetweenYears(int year1, int year2) {
        String res = "";
        int count = 0;
        HashMap<String, Integer> tagsCount = new HashMap<>();
        ArrayList<String> artists = new ArrayList<>();

        for(String key : artistsHash.keySet()){
            for (int i = year1; i <= year2; i++) {
                if (Main.yearArtists.containsKey(i)) {
                    if (Main.yearArtists.get(i).contains(key)) {
                        artists.add(key);
                        count = 1;
                        break;
                    }
                }
            }
        }

        if (count == 0) {
            return "No results";
        }

        for (int i = 0; i < artists.size(); i++) {
            for (String tag: artistsHash.get(artists.get(i))) {
                if (tagsCount.containsKey(tag)) {
                    int value = tagsCount.get(tag);
                    value = value + 1;
                    tagsCount.put(tag, value);
                } else {
                    tagsCount.put(tag, 1);
                }
            }
        }

        List<Map.Entry<String,Integer>> list = new LinkedList<>(tagsCount.entrySet());

        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        for(int i = 0; i < list.size(); i++){
            res += "" + list.get(i).getKey() + " " + list.get(i).getValue() + "\n";
        }

        return res;
    }

    public static void mediaCalculator(TreeMap<Double,String> artistsMedia, Integer[] media, String artist) {
        double mediaGeral = 0;

        for (int i = 0; i < media.length; i++) {
            mediaGeral += media[i];
        }

        mediaGeral = mediaGeral / media.length;
        artistsMedia.put(mediaGeral, artist);
    }

    public static HashMap<Integer, ArrayList<Song>> savingData(HashMap<String,ArrayList<Song>> songsHash, HashMap<Integer,ArrayList<Song>> songsAno, String artist, int year1, int year2) {
        for(int i = 0; i < songsHash.get(artist).size(); i++) {

            if(songsHash.get(artist).get(i).ano >= year1 && songsHash.get(artist).get(i).ano <= year2) {
                if (songsAno.containsKey(songsHash.get(artist).get(i).ano)) {
                    songsAno.get(songsHash.get(artist).get(i).ano).add(songsHash.get(artist).get(i));
                } else {
                    ArrayList<Song> temp = new ArrayList<>();
                    temp.add(songsHash.get(artist).get(i));
                    songsAno.put(songsHash.get(artist).get(i).ano, temp);

                }
            }
        }

        return songsAno;

    }

    public static void popularityCalculator(HashMap<Integer,ArrayList<Song>> songsAno, Integer[] media, String artist, TreeMap<Double, String> artistsMedia) {
        int a = 0;
        int count = 0;

        for(Integer ano : songsAno.keySet()){
            int popularidade = 0;

            for(int i = 0; i < songsAno.get(ano).size();i++){
                popularidade += songsAno.get(ano).get(i).popular;
            }

            popularidade = popularidade / songsAno.get(ano).size();
            media[a] = popularidade;
            a++;
            count = 0;
            if (media[1] != null){
                if (media[a - 1] <= media[a - 2]){
                    count++;//se der mal no DP maybe esse = pode ser o erro
                    break;
                }
            }

        }
        if (count == 0) {
            mediaCalculator(artistsMedia, media, artist);
        }
    }

    public static String getRisingStars(int firstYear, int lastYear, String ordination) {
        HashMap<String,ArrayList<Song>> songsHash = new HashMap<>();
        HashMap<Integer,ArrayList<Song>> songsAno = new HashMap<>();
        TreeMap<Double, String> artistsMedia = new TreeMap<>();

        int count;
        String res = "";

        for(String artist : Main.artistYears.keySet()){
            count = 0;
            for (int i = firstYear; i <= lastYear;i++){
                if (!(Main.artistYears.get(artist).contains(i))) {
                    count++;
                    break;
                }
            }
            if (count == 0) {
                songsHash.put(artist,Main.artistSongs.get(artist));
            }
        }

        for(String artist : songsHash.keySet()) {
            songsAno.clear();
            savingData(songsHash, songsAno, artist, firstYear, lastYear);
            Integer[] media = new Integer[lastYear - firstYear + 1];
            popularityCalculator(songsAno, media, artist, artistsMedia);

        }

        if (ordination.equals("ASC")) {
            for (Double key : artistsMedia.keySet()){
                res += artistsMedia.get(key) + " <=> "  + key + "\n";
            }

        } else if (ordination.equals("DESC")) {
            for (Double key : artistsMedia.descendingKeySet()){
                res += artistsMedia.get(key) + " <=> " + key + "\n";
            }

        } else {
            res += "Illegal command. Try again";
        }

        return res;
    }

    public static String addTags(String[] artistAndTags) {
        String res = "";
        String temp = "";
        String artist = artistAndTags[0];

        if (!(Main.artistSongs.containsKey(artist))) {
            return "Inexistent artist";
        }

        for (int i = 1; i < artistAndTags.length; i++) {

            if(artistsHash.get(artist) != null){
                artistsHash.get(artist).add(artistAndTags[i].toUpperCase());
            } else {
                TreeSet<String> artistTree2 = new TreeSet<>();
                artistTree2.add(artistAndTags[i].toUpperCase());
                artistsHash.put(artist,artistTree2);
            }

            if (tagsHash.containsKey(artistAndTags[i].toUpperCase())) {
                tagsHash.get(artistAndTags[i].toUpperCase()).add(artist);
            } else {
                TreeSet<String> tagsTree2 = new TreeSet<>();
                tagsTree2.add(artist);
                tagsHash.put(artistAndTags[i].toUpperCase(),tagsTree2);
            }

        }

        res += artist + " | ";
        temp += artistsHash.get(artist);
        temp = temp.replace(" ","");
        temp = temp.replace("[","");
        temp = temp.replace("]","");
        res += temp;

        return res;
    }

    public static String removeTags(String[] artistAndTags) {
        String artist = artistAndTags[0];
        String res = "";
        String temp = "";

        if (!(Main.artistCountSongs.containsKey(artist))) {
            return "Inexistent artist";
        }

        if (!(artistsHash.containsKey(artist))) {
            return artist + " | No tags";
        }

        for (int i = 1; i < artistAndTags.length; i++) {
            if (!(artistsHash.get(artist).isEmpty())) {
                artistsHash.get(artist).remove(artistAndTags[i].toUpperCase());
            }
            if (tagsHash.containsKey(artistAndTags[i].toUpperCase())) {
                tagsHash.get(artistAndTags[i].toUpperCase()).remove(artist);
                if (tagsHash.get(artistAndTags[i].toUpperCase()) == null) {
                    tagsHash.remove(artistAndTags[i].toUpperCase());
                }
            }

            if (artistsHash.get(artist).isEmpty()) {
                artistsHash.remove(artist);
            }

        }

        if (!(artistsHash.containsKey(artist))) {
            return artist + " | No tags";
        }

        res += artist + " | ";
        temp += artistsHash.get(artist);
        temp = temp.replace(" ","");
        temp = temp.replace("[","");
        temp = temp.replace("]","");
        res += temp;

        return res;
    }

}
