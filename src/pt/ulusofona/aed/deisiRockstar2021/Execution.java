package pt.ulusofona.aed.deisiRockstar2021;

public class Execution {

    public static void illegalCommand() {
        System.out.println("Illegal command. Try again");
    }

    public static String getQuery(String command) {
        String[] input = command.split(" ");
        String res;

        switch (input[0]) {
            case "COUNT_SONGS_YEAR":{
                String str = command.replace("COUNT_SONGS_YEAR ", "");
                input = str.split(" ");
                if (input.length != 1) {
                    illegalCommand();
                    return null;
                } else {
                    int year = Integer.parseInt(input[0]);
                    res = SimpleQuery.countSongsYear(year);
                    return res;
                }

            } case "COUNT_DUPLICATE_SONGS_YEAR": {
                String str = command.replace("COUNT_DUPLICATE_SONGS_YEAR ", "");
                input = str.split(" ");
                if (input.length != 1) {
                    illegalCommand();
                    return null;
                } else {
                    int year = Integer.parseInt(input[0]);
                    res = SimpleQuery.countDuplicateSongsYear(year);
                    return res;
                }

            } case "GET_ARTISTS_FOR_TAG": {
                String str = command.replace("GET_ARTISTS_FOR_TAG ", "");
                input = str.split(" ");

                if (input.length != 1) {
                    illegalCommand();
                    return null;
                } else {
                    res = ComplexQuery.getArtistsForTag(input[0]);
                    return res;
                }

            } case "GET_MOST_DANCEABLE": {
                String str = command.replace("GET_MOST_DANCEABLE ", "");
                input = str.split(" ");

                if (input.length != 3) {
                    illegalCommand();
                    return null;
                } else {
                    int firstYear = Integer.parseInt(input[0]);
                    int lastYear = Integer.parseInt(input[1]);
                    int results = Integer.parseInt(input[2]);
                    res = SimpleQuery.getMostDanceable(firstYear, lastYear, results);
                    return res;
                }

            } default:
                res = getQuery2(command);
                return res;

        }

    }

    public static String getQuery2(String command) {
        String[] input = command.split(" ");
        String res;

        switch (input[0]) {
            case "GET_ARTISTS_ONE_SONG": {
                String str = command.replace("GET_ARTISTS_ONE_SONG ", "");
                input = str.split(" ");

                if (input.length != 2) {
                    illegalCommand();
                    return null;
                } else {
                    int firstYear = Integer.parseInt(input[0]);
                    int lastYear = Integer.parseInt(input[1]);
                    res = SimpleQuery.getArtistsOneSong(firstYear, lastYear);
                    return res;
                }

            } case "GET_TOP_ARTISTS_WITH_SONGS_BETWEEN": {
                String str = command.replace("GET_TOP_ARTISTS_WITH_SONGS_BETWEEN ", "");
                input = str.split(" ");

                if (input.length != 3) {
                    illegalCommand();
                    return null;
                } else {
                    int n = Integer.parseInt(input[0]);
                    int themeA = Integer.parseInt(input[1]);
                    int themeB = Integer.parseInt(input[2]);
                    res = SimpleQuery.getTopArtistsWithSongsBetween(n, themeA, themeB);
                    return res;
                }

            } case "MOST_FREQUENT_WORDS_IN_ARTIST_NAME": {
                String str = command.replace("MOST_FREQUENT_WORDS_IN_ARTIST_NAME ", "");
                input = str.split(" ");

                if (input.length != 3) {
                    illegalCommand();
                    return null;
                } else {
                    int n = Integer.parseInt(input[0]);
                    int m = Integer.parseInt(input[1]);
                    int l = Integer.parseInt(input[2]);
                    res = SimpleQuery.mostFrequentWordsInArtistName(n, m, l);
                    return res;
                }

            } default:
                res = getQuery3(command);
                return res;

        }

    }

    public static String getQuery3(String command) {
        String[] input = command.split(" ");
        String res;

        switch (input[0]) {
            case "GET_UNIQUE_TAGS": {

                res = ComplexQuery.getUniqueTags();
                break;

            } case "GET_UNIQUE_TAGS_IN_BETWEEN_YEARS": {
                String str = command.replace("GET_UNIQUE_TAGS_IN_BETWEEN_YEARS ", "");
                input = str.split(" ");

                if  (input.length != 2) {
                    illegalCommand();
                    return null;
                } else {
                    int year1 = Integer.parseInt(input[0]);
                    int year2 = Integer.parseInt(input[1]);
                    res = ComplexQuery.getUniqueTagsInBetweenYears(year1, year2);
                    return res;
                }

            } case "GET_RISING_STARS": {
                String str = command.replace("GET_RISING_STARS ", "");
                input = str.split(" ");

                if (input.length != 3) {
                    illegalCommand();
                    return null;
                } else {
                    int year1 = Integer.parseInt(input[0]);
                    int year2 = Integer.parseInt(input[1]);
                    String ordination = input[2];
                    res = ComplexQuery.getRisingStars(year1, year2, ordination);
                    return res;
                }

            } default:
                res = getQuery4(command);
                return res;

        }

        return res;

    }

    public static String getQuery4(String command) {
        String[] input = command.split(" ");
        String res;

        switch (input[0]) {
            case "ADD_TAGS": {
                String str = command.replace("ADD_TAGS ", "");
                input = str.split(";");

                res = ComplexQuery.addTags(input);
                return res;

            } case "REMOVE_TAGS": {
                String str = command.replace("REMOVE_TAGS ", "");
                input = str.split(";");

                res = ComplexQuery.removeTags(input);
                return res;

            } case "CLEANUP": {
                res = SimpleQuery.cleanup();
                return res;

            } case "GET_TOP_10_LIVELINESS_SONGS": {
                String str = command.replace("GET_TOP_10_LIVELINESS_SONGS ", "");
                input = str.split(" ");

                if (input.length != 2) {
                    illegalCommand();
                    return null;
                } else {
                    int year = Integer.parseInt(input[0]);
                    double liveliness = Double.parseDouble(input[1]);
                    res = SimpleQuery.getTop10LivelinessSongs(year, liveliness);
                    return res;
                }

            } default:
                System.out.println("Illegal command. Try again");
                return null;

        }

    }

}
