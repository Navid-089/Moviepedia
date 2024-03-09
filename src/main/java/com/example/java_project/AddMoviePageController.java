package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.java_project.LogInPageController.input;
import static com.example.java_project.Main.flag;
import static com.example.java_project.Main.server;

public class AddMoviePageController {
    public TextField TitleField;
    public TextField ReleaseYearField;
    public TextField GenresField;
    public TextField BudgetField;
    public TextField RevenueField;
    public Label onSameTitleField;
    public TextField DurationField;
    public Button AddButton;
    public Label SucessLabel;


    public void onAddButtonClick(ActionEvent actionEvent) throws IOException {
        Movie mov = null;

        String title = TitleField.getText();
        if (title.equalsIgnoreCase(""))
            title = "No given name";

        int year;
        if (ReleaseYearField.getText().equalsIgnoreCase(""))
            year = 0;
        else
            year = Integer.parseInt(ReleaseYearField.getText());

        int duration;
        if (DurationField.getText().equalsIgnoreCase(""))
            duration = 0;
        else
            duration = Integer.parseInt(DurationField.getText());


        String genre = GenresField.getText();

        int budget;
        if (BudgetField.getText().equalsIgnoreCase(""))
            budget = 0;
        else
            budget = Integer.parseInt(BudgetField.getText());


        int revenue;
        if (RevenueField.getText().equalsIgnoreCase(""))
            revenue = 0;
        else
           revenue = Integer.parseInt(RevenueField.getText());


        mov = new Movie();
        mov.setTitle(title);
        mov.setYear(year);
        mov.setBudget(budget);
        mov.setTime(duration);

        mov.setRevenue(revenue);
        mov.setProductionCompany(input);
        String[] tokens = genre.split(",");
        if (tokens.length == 3) {
            mov.setGenre1(tokens[0]);
            mov.setGenre2(tokens[1]);
            mov.setGenre3(tokens[2]);
        } else if (tokens.length == 2) {
            mov.setGenre1(tokens[0]);
            mov.setGenre2(tokens[1]);
            mov.setGenre3("");
        } else if (tokens.length == 1) {
            mov.setGenre1(tokens[0]);
            mov.setGenre2("");
            mov.setGenre3("");
        } else if (tokens.length == 0) {
            mov.setGenre1("");
            mov.setGenre2("");
            mov.setGenre2("");
        }


        DataWrapper d1 = new DataWrapper("Add", mov);
        DataWrapper d2 = new DataWrapper("prodlist", input);

        server.write(d1);
        server.write(d2);
        SucessLabel.setTextFill(Color.GREEN);
        SucessLabel.setText("Movie Added Successfully");
        TitleField.clear();
        ReleaseYearField.clear();
        DurationField.clear();
        GenresField.clear();
        BudgetField.clear();
        RevenueField.clear();

        /* for(Movie m: mList)
        {
            if(m.getTitle().equalsIgnoreCase(title)) {
                onSameTitleField.setText("There is already a movie with the same name.");
                TitleField.clear();
                ReleaseYearField.clear();
                GenresField.clear();
                BudgetField.clear();
                RevenueField.clear();
            }
            else
            {
                DataWrapper dw=new DataWrapper("Add",mov);
                server.write(dw);
            }*/


    }


    public void onBackButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setScene(scene);
    }

    public void onCheckButtonClick(ActionEvent actionEvent) throws IOException {
        DataWrapper d1 = new DataWrapper("test", TitleField.getText());
        server.write(d1);

        if (flag) {
            ReleaseYearField.setDisable(true);
            GenresField.setDisable(true);
            DurationField.setDisable(true);
            BudgetField.setDisable(true);
            RevenueField.setDisable(true);
            AddButton.setDisable(true);
            onSameTitleField.setTextFill(Color.RED);
            onSameTitleField.setText("Movie with same title already exists");
        } else if (!flag) {
            ReleaseYearField.setDisable(false);
            GenresField.setDisable(false);
            DurationField.setDisable(false);
            BudgetField.setDisable(false);
            RevenueField.setDisable(false);
            AddButton.setDisable(false);

            /*onSameTitleField.setTextFill(Color.GREEN);
            onSameTitleField.setText("Good to go");*/
        }


    }
}
