package pt.ulusofona.aed.deisiRockstar2021;

public class Clean {

    public static String[] simpleArtists(String[] artists) {

        if (artists[0].contains("''")) {
            return null;
        }

        artists[0] = artists[0].replace("[","");
        artists[0] = artists[0].replace("]","");
        artists[0] = artists[0].replace("'","");

        return artists;

    }

    public static String[] complexArtists(String[] artists) {
        int i;

        for (i = 0; i < artists.length; i++) {

            artists[i] = artists[i].trim();

            if (artists[i].contains("''")) {
                return null;
            }

            if (!artists[i].contains("\"[")) {
                artists[i] = complexArtistsPt2(artists[i]);

            } else {
                artists[i] = artists[i].replace("\"[","");
                artists[i] = artists[i].replace("]\"","");

                if (artists[i].charAt(0) == '\"') {
                    artists[i] = artists[i].replace("\"\"","");

                } else if (artists[i].charAt(0) == '\'') {
                    artists[i] = artists[i].replace("\"\"\"","\"");
                    artists[i] = artists[i].replace("'","");

                }

            }
        }

        return artists;

    }

    public static String complexArtistsPt2(String artist) {
        if (artist.contains("]\"")) {

            artist = artist.replace("]\"","");
            artist = artist.replace("\"[","");

            if (artist.charAt(artist.length() - 1) == '\"') {
                artist = artist.replace("\"\"","");

            } else if (artist.charAt(artist.length() - 1) == '\'') {
                artist = clearAll(artist);

            }

        } else {

            if (artist.charAt(0) == '\"') {
                artist = artist.replace("\"\"","");

            } else if (artist.charAt(0) == '\'') {
                artist = clearAll(artist);

            } else if (artist.charAt(artist.length() - 1) == '\'') {
                artist = clearAll(artist);

            }

        }

        return artist;

    }

    public static String clearAll(String str) {
        str = str.replace("\"\"\"","\"");
        str = str.replace("\"\"","");
        str = str.replace("'","");

        return str;
    }

}
