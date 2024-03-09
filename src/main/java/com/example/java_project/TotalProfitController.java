package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class TotalProfitController {

    public Button BackButton;
    public Label ProfitLabel;
    public Label CompanyNameLabel;

    public void setCompanyNameLabelLabel(String str)
    {
        CompanyNameLabel.setText(str);
    }

    public void setProfitLabel(String str)
    {
        ProfitLabel.setText(str);
    }

    public void onBackButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setScene(scene);
    }
}
