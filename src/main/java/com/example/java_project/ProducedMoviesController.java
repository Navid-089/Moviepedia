package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.java_project.LogInPageController.input;
import static com.example.java_project.Main.mList;
import static com.example.java_project.Main.server;

public class ProducedMoviesController {

    public TextField TrailerSearchField;
    public Button SearchButton;
    public Button RefreshButton;
    private Stage stage;
    private Parent r;
    private Scene scene;

    @FXML
    public Button BackButton;
    @FXML
    public TableView Table;
    @FXML
    public TableColumn TitleCol;
    @FXML
    public TableColumn YearCol;
    @FXML
    public TableColumn GenresCol;
    @FXML
    public TableColumn DurationCol;
    @FXML
    public TableColumn ProdComCol;
    @FXML
    public TableColumn BudgetCol;
    @FXML
    public TableColumn RevenueCol;
    @FXML
    public TableColumn ProfitCol;
    @FXML
    public Label CompanyNameLabel;

    @FXML
    public void setCompanyNameLabel(String str) {
        CompanyNameLabel.setText(str);
    }

    @FXML
    public void initialize() {

        /*FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ProducedMovies.fxml"));
        ProducedMoviesController producedMoviesController= fxmlLoader.getController();
        producedMoviesController.setCompanyNameLabel(input.toUpperCase());

        for(int i=0;i<mList.size();i++)
            producedMoviesController.Table.getItems().add(mList.get(i));*/

        TitleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        YearCol.setCellValueFactory(new PropertyValueFactory<>("Year"));
        GenresCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        DurationCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        ProdComCol.setCellValueFactory(new PropertyValueFactory<>("ProductionCompany"));
        BudgetCol.setCellValueFactory(new PropertyValueFactory<>("Budget"));
        RevenueCol.setCellValueFactory(new PropertyValueFactory<>("Revenue"));
        ProfitCol.setCellValueFactory(new PropertyValueFactory<>("Profit"));
    }

    @FXML
    public void onBackButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setScene(scene);
    }

    public void onRefreshButtonClick(ActionEvent actionEvent) throws IOException {
        /*server.write(new DataWrapper("prodlist",input));
        Stage stage= (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ProducedMovies.fxml"));

        ProducedMoviesController producedMoviesController = fxmlLoader.getController();
        producedMoviesController.setCompanyNameLabel(input);

        for(int i=0;i<mList.size();i++)
            producedMoviesController.Table.getItems().add(mList.get(i));

        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setScene(scene);*/

        server.write(new DataWrapper("prodlist", input));
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ProducedMovies.fxml"));
        r = fxmlLoader.load();

        ProducedMoviesController producedMoviesController = fxmlLoader.getController();
        producedMoviesController.setCompanyNameLabel(input.toUpperCase());

        for (int i = 0; i < mList.size(); i++)
            producedMoviesController.Table.getItems().add(mList.get(i));

        scene = new Scene(r, 800, 600);
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);


    }

    public void onSearchButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShowTrailer.fxml"));
        r = fxmlLoader.load();

        String str1 = TrailerSearchField.getText();
        String str2 = str1.replace(' ', '+');


        String str3 = "https://www.youtube.com/results?search_query=" + str2 +"+trailer";
        /*System.out.println(str3);*/

        ShowTrailerController showTrailerController = fxmlLoader.getController();
        showTrailerController.SetLink(str3);

        scene = new Scene(r, 800, 600);
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
