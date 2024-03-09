package com.example.java_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.java_project.LogInPageController.input;
import static com.example.java_project.Main.mList;
import static com.example.java_project.Main.server;

public class TransferMoviePageController {
    public Button TransferButton;
    public ChoiceBox CompanyList;
    public TextField MovieNameField;
    public Label ErrorLabel;
    public Label SuccessLabel;

    public TextField tryTextField;

    /*ObservableList<String> ProductionCompanies = FXCollections.observableArrayList("No Name","Universal Pictures",
            "Columbia Pictures","Winkler Films","Twentieth Century Fox Film Corporation","Walt Disney Pictures","Walt Disney Productions",
            "Davis-Panzer Productions",
            "A Band Apart","Touchstone Pictures","Pixar Animation Studios","United Artists","Paramount Pictures","Icon Entertainment International",
            "Davis-Films","TriStar Pictures","Regency Enterprises","Lightstorm Entertainment","Lucasfilm","Blue Parrot Productions",
            "Keith Barish Productions","Gramercy Pictures","Act III Communications","American Zoetrope","Eon Productions",
            "Amblin Entertainment","Gaumont","RKO Radio Pictures","Imagine Entertainment","Stanley Kubrick Productions",
            "Caravan Pictures","New Line Cinema","Morgan Creek Productions","Miramax Films","Orion Pictures","PolyGram Filmed Entertainment",
            "Shaw Brothers","Castle Rock Entertainment","Tim Burton Productions","Hawk Films");
*/

    ObservableList<String> ProductionCompanies = FXCollections.observableArrayList("A Band Apart",
            "Act III Communications","Amblin Entertainment","American Zoetrope","Blue Parrot Productions",
            "Caravan Pictures","Castle Rock Entertainment","Columbia Pictures","Davis-Films","Davis-Panzer Productions",
            "Eon Productions","Gaumont","Gramercy Pictures","Hawk Films","Icon Entertainment International",
            "Imagine Entertainment","Keith Barish Productions","Lightstorm Entertainment","Lucasfilm","Miramax Films",
            "Morgan Creek Productions","New Line Cinema","No Name","Orion Pictures","Paramount Pictures","Pixar Animation Studios",
            "PolyGram Filmed Entertainment","Regency Enterprises","RKO Radio Pictures","Shaw Brothers","Stanley Kubrick Productions",
            "Tim Burton Productions","Touchstone Pictures","TriStar Pictures","Twentieth Century Fox Film Corporation","United Artists",
            "Universal Pictures","Walt Disney Pictures","Walt Disney Productions","Winkler Films");

    public void onTransferButtonClick(ActionEvent actionEvent) throws IOException {
        boolean b=true;
        for(int i=0;i<mList.size();i++)
        {
            if(mList.get(i).getTitle().equalsIgnoreCase(MovieNameField.getText()))
                b=false;
        }
        if(b)
        {
            ErrorLabel.setText("Movie not found!");
        }
        else {
            String str=MovieNameField.getText()+","+(String) CompanyList.getValue();
            server.write(new DataWrapper("transfer",str));
            SuccessLabel.setTextFill(Color.GREEN);
            SuccessLabel.setText("Transfer Successful!");
            server.write(new DataWrapper("prodlist",input));
        }
        MovieNameField.clear();
        CompanyList.setValue("");
    }

    @FXML
    public void initialize()
    {
        CompanyList.setValue("");
        CompanyList.setItems(ProductionCompanies);
    }

    public void onBackButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setScene(scene);
    }
}
