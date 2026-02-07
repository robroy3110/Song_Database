package pt.ulusofona.aed.deisiRockstar2021;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

public class Reader {

    public static void loadFilesSongs(String fileSongs) throws IOException {
        Main.songsLinesInfo.numLinhasOk = 0;
        Main.songsLinesInfo.numLinhasIgnored = 0;

        String[] dataSongs = new String[30];
        Arrays.fill(dataSongs,null);

        try {
            FileReader firstFile = new FileReader(fileSongs);
            BufferedReader reader = new BufferedReader(firstFile);
            String line;
            int countSongsOk = 0, countSongsIgnored = 0;

            while ((line = reader.readLine())!= null) {
                dataSongs = line.split("@");

                if (dataSongs.length == 3) {
                    String id = dataSongs[0].trim();
                    String nome = dataSongs[1].trim();
                    int ano = Integer.parseInt(dataSongs[2].trim());

                    if(Main.songs.containsKey(id)) {
                        countSongsIgnored++;
                    } else {
                        if (ano > 0 && ano <= 2021) {
                            Song song = new Song(id, nome, null, ano, -1, false, -1, -1, -1, -1);
                            Main.songs.put(id,song);
                            TreeSet<String> tree = new TreeSet<>();
                            Main.yearArtists.put(ano, tree);
                            countSongsOk++;
                        } else {
                            countSongsIgnored++;
                        }
                    }
                } else {
                    countSongsIgnored++;
                }

            }

            reader.close();
            Main.songsLinesInfo = new ParseInfo(countSongsOk, countSongsIgnored);
        } catch (FileNotFoundException exception) {
            String message = "Erro: o ficheiro " + fileSongs + " não foi encontrado.";
            System.out.println(message);
        }
    }

    public static void loadFilesArtists(String fileArtists) throws IOException {
        Main.artistsLinesInfo.numLinhasOk = 0;
        Main.artistsLinesInfo.numLinhasIgnored = 0;

        String[] dataArtists = new String[20];
        Arrays.fill(dataArtists,null);

        try {
            FileReader secondFile = new FileReader(fileArtists);
            BufferedReader secondFileScan = new BufferedReader(secondFile);
            String line2;
            int countArtistsOk = 0, countArtistsIgnored = 0, count;

            while ((line2 = secondFileScan.readLine()) != null) {

                dataArtists = line2.split("@");

                if (dataArtists.length == 2) {
                    String id = dataArtists[0].trim();
                    String nomes = dataArtists[1].trim();
                    String[] arrayArtists = nomes.split(",");

                    if (arrayArtists[0].charAt(0) == '\"') {
                        arrayArtists = Clean.complexArtists(arrayArtists);
                    } else {
                        arrayArtists = Clean.simpleArtists(arrayArtists);
                    }

                    if (arrayArtists != null) {

                        TreeSet<String> tree = new TreeSet<>();
                        Collections.addAll(tree, arrayArtists);
                        Artista[] artistas = new Artista[tree.size()];
                        count = 0;

                        if (Main.songs.containsKey(id)){
                            Main.songs.get(id).artistas = Main.savingData(tree, artistas, id);
                            for(String artista : tree){
                                if(Main.artistSongs.containsKey(artista)){
                                    Main.artistSongs.get(artista).add(Main.songs.get(id));
                                }else {
                                    ArrayList<Song> songs2 = new ArrayList<>();
                                    songs2.add(Main.songs.get(id));
                                    Main.artistSongs.put(artista,songs2);
                                }
                            }
                            count = 1;
                        }

                        if (count == 0) {
                            countArtistsIgnored++;
                        } else {
                            countArtistsOk++;
                        }

                    } else {
                        countArtistsIgnored++;
                    }
                } else {
                    countArtistsIgnored++;
                }

            }

            secondFileScan.close();
            Main.artistsLinesInfo = new ParseInfo (countArtistsOk, countArtistsIgnored);

        } catch (FileNotFoundException exception) {
            String message = "Erro: o ficheiro " + fileArtists + " não foi encontrado.";
            System.out.println(message);
        }
    }

    public static void loadFilesDetails(String fileDetails) throws IOException{
        Main.detailsLinesInfo.numLinhasOk = 0;
        Main.detailsLinesInfo.numLinhasIgnored = 0;

        boolean letra = false;
        String[] dataDetails = new String[70];
        Arrays.fill(dataDetails,null);

        try {
            FileReader thirdFile = new FileReader(fileDetails);
            BufferedReader thirdFileScan = new BufferedReader(thirdFile);
            String line3;
            int countDetailsOk = 0, countDetailsIgnored = 0, count;

            while ((line3 = thirdFileScan.readLine()) != null) {

                dataDetails = line3.split("@");

                if (dataDetails.length == 7) {
                    count = 0;
                    String id = dataDetails[0].trim();
                    int duracao = Integer.parseInt(dataDetails[1].trim());
                    String letraCheck = dataDetails[2].trim();
                    int popularidade = Integer.parseInt(dataDetails[3].trim());
                    double dancabilidade = Double.parseDouble(dataDetails[4].trim());
                    double vivacidade = Double.parseDouble(dataDetails[5].trim());
                    double volume = Double.parseDouble(dataDetails[6].trim());
                    if (letraCheck.equals("0")) {
                        letra = false;
                    } else if (letraCheck.equals("1")) {
                        letra = true;
                    } else {
                        count = 2;
                    }

                    if (count == 0) {
                        if (Main.songs.containsKey(id) && (Main.songs.get(id).duracao == -1)) {
                            Main.songs.get(id).dancabilidade = dancabilidade;
                            Main.songs.get(id).duracao = duracao;
                            Main.songs.get(id).letra = letra;
                            Main.songs.get(id).popular = popularidade;
                            Main.songs.get(id).vivacidade = vivacidade;
                            Main.songs.get(id).volume = volume;
                            count = 1;
                        }
                    }

                    if (count == 0 || count == 2) {
                        countDetailsIgnored++;
                    } else {
                        countDetailsOk++;
                    }

                } else {
                    countDetailsIgnored++;
                }
            }

            thirdFileScan.close();
            Main.detailsLinesInfo = new ParseInfo (countDetailsOk, countDetailsIgnored);

        } catch (FileNotFoundException exception) {
            String message = "Erro: o ficheiro " + fileDetails + " não foi encontrado.";
            System.out.println(message);
        }
    }

}
