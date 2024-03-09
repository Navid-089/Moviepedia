package com.example.java_project;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Server implements Serializable {

    private static final String INPUT_FILE_NAME = "movies.txt";
    private static final String OUTPUT_FILE_NAME = "movies.txt";

    public static String prodName;

    static List<Movie> movieList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String[] tokens = line.split(",");
            Movie m = new Movie();
            m.setTitle(tokens[0]);
            m.setYear(Integer.parseInt(tokens[1]));
            m.setGenre1(tokens[2]);
            m.setGenre2(tokens[3]);
            m.setGenre3(tokens[4]);
            m.setTime(Integer.parseInt(tokens[5]));
            m.setProductionCompany(tokens[6]);
            m.setBudget(Integer.parseInt(tokens[7]));
            m.setRevenue(Integer.parseInt(tokens[8]));
            movieList.add(m);
        }
        ServerSocket Server = new ServerSocket(6777);
        System.out.println("Server started");
        while (true) {
            Socket clientSocket = Server.accept();
            System.out.println("Client Connected");
            SocketWrapper client = new SocketWrapper(clientSocket);
            new Thread(() -> {

                try {
                    while (true) {
                        Object ob = client.read();
                        DataWrapper dataWrapper = (DataWrapper) ob;
                        if (dataWrapper.command.equalsIgnoreCase("prodlist")) {
                            List<Movie> prodMovies = new ArrayList<>();
                            prodName = (String) dataWrapper.obj;
                            for (int i = 0; i < movieList.size(); i++) {
                                if (movieList.get(i).getProductionCompany().equalsIgnoreCase(prodName)) {
                                    prodMovies.add(movieList.get(i));
                                }
                            }
                            client.write(new DataWrapper("showAll", prodMovies));
                        } else if (dataWrapper.command.equalsIgnoreCase("Add")) {
                            Movie mov = (Movie) dataWrapper.obj;
                            movieList.add(mov);
                        } else if (dataWrapper.command.equalsIgnoreCase("transfer")) {
                            String data = (String) dataWrapper.obj;
                            String[] tokens = data.split(",");
                            String mov = tokens[0];
                            String prod = tokens[1];
                            Movie movie = new Movie();
                            for (Movie m : movieList) {
                                if (m.getTitle().equalsIgnoreCase(mov))
                                    movie = m;
                            }
                            movie.setProductionCompany(prod);
                        } else if (dataWrapper.command.equalsIgnoreCase("test")) {
                            String data = (String) dataWrapper.obj;
                            for (Movie mov : movieList) {
                                if (mov.getTitle().equalsIgnoreCase(data)) {
                                    DataWrapper dataWrapper1 = new DataWrapper("tested", "same");
                                    client.write(dataWrapper1);
                                    break;
                                }
                                client.write(new DataWrapper("tested", "notSame"));
                            }
                        }

                    }

                } catch (IOException e) {


                    try {
                        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
                        for (int i = 0; i < movieList.size(); i++) {
                            Movie m = movieList.get(i);
                            String text = m.getTitle() + "," + m.getYear() + "," + m.getGenre1() + "," + m.getGenre2()
                                    + "," + m.getGenre3() + "," + m.getTime() + "," + m.getProductionCompany() + "," +
                                    m.getBudget() + "," + m.getRevenue();
                            bw.write(text);
                            bw.write(System.lineSeparator());
                        }
                        System.out.println("All the data is saved!");
                        System.out.println("EXITING.");
                        bw.close();
                    } catch(IOException exception){

                        throw new RuntimeException(exception) ;
                    }
                    System.out.println("Unexpectedly, Client got disconnected.");


                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        client.closeConnection();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                }



            }).start();

        }




    }
}
