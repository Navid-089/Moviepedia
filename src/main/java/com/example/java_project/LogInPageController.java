package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;

import static com.example.java_project.Main.server;

public class LogInPageController implements Serializable {


    public TextField LogInTextField;
    public Label LogInFailureText;

    public Parent r;

    public static String input;

    private Stage stage;

    public void onLogInButtonClick(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
            input=LogInTextField.getText();

            if(!input.equalsIgnoreCase("")) {
                server.write(new DataWrapper("prodlist", input));

                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomePage.fxml"));
                r = fxmlLoader.load();

        /* ProducedMoviesController producedMoviesController= fxmlLoader.getController();
         producedMoviesController.setCompanyNameLabel(input);

         for(int i=0;i<mList.size();i++)
             producedMoviesController.Table.getItems().add(mList.get(i));*/

                Scene scene;
                scene = new Scene(r, 800, 600);
                stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                stage.setScene(scene);
                stage.show();
            }



        }
    }




    /*public void connect(Main main) {
        main.connect(this);
    }*/

    /*public static void handleServerResponse(List<Movie> mL) {
        movList.addAll(mL);
    }*/



