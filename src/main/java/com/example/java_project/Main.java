package com.example.java_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application implements Serializable {

    public static SocketWrapper server;
    public static boolean flag = false;

    public static List<Movie> mList = new ArrayList<>();

    public Object ob;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LogInPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Moviepedia");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) throws IOException {
        server = new SocketWrapper("127.0.0.1", 6777);
        new Thread(() -> {
            try {
                while (true) {
                    Object ob = server.read();
                    DataWrapper dataWrapper = (DataWrapper) ob;
                    if (dataWrapper.command.equalsIgnoreCase("showAll")) {
                        mList.clear();
                        List<Movie> expectedMovies = new ArrayList<>();
                        expectedMovies = (List<Movie>) ((DataWrapper) ob).obj;
                        /*System.out.println("nooo");*/
                        mList.addAll(expectedMovies);
                    } else if (dataWrapper.command.equalsIgnoreCase("tested")) {
                        String str = (String) dataWrapper.obj;
                        if (str.equalsIgnoreCase("notSame"))
                            flag = false;
                        else
                            flag = true;
                    }
                }
            } catch (IOException e) {
                System.out.println("Server disconnected.");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                server.closeConnection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
        launch();
    }
}
