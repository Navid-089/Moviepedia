package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.awt.Desktop;

import java.io.IOException;

import static com.example.java_project.LogInPageController.input;
import static com.example.java_project.Main.mList;
import static com.example.java_project.Main.server;

public class ShowTrailerController {
    public WebView WebViewBox;
    public Button WatchButton;
    public Button BackButton;

    public String Link;

    public void SetLink(String str)
    {
        Link=str;
    }

    public String getLink()
    {
        return Link;
    }


    public void onWatchButton(ActionEvent actionEvent) {
       WebViewBox.getEngine().load(getLink());

    }

    public void onWatchOnYourBrowserButton(ActionEvent actionEvent) throws IOException {
        Desktop desktop=Desktop.getDesktop();
        desktop.browse(java.net.URI.create(getLink()));
    }

    public void onBackButtonClick(ActionEvent actionEvent) throws IOException {
        server.write(new DataWrapper("prodlist", input));
        Stage stage= (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ProducedMovies.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setScene(scene);

        ProducedMoviesController producedMoviesController= fxmlLoader.getController();
        producedMoviesController.setCompanyNameLabel(input.toUpperCase());

        for(int i=0;i<mList.size();i++)
            producedMoviesController.Table.getItems().add(mList.get(i));


    }


}
