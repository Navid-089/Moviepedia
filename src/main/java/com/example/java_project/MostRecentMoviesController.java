package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.java_project.LogInPageController.input;
import static com.example.java_project.Main.mList;
import static com.example.java_project.Main.server;



/*import com.example.java_project.Movie;*/

public class MostRecentMoviesController {

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
    public void setCompanyNameLabel(String str)
    {
        CompanyNameLabel.setText(str);
    }

    @FXML
    public void initialize()
    {
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
        Stage stage= (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setScene(scene);
    }

    public void onRefreshButtonClick(ActionEvent actionEvent) throws IOException {
        server.write(new DataWrapper("prodlist",input));
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MostRecentMovies.fxml"));
        r = fxmlLoader.load();

        MostRecentMoviesController mostRecentMoviesController=fxmlLoader.getController();
        mostRecentMoviesController.setCompanyNameLabel(input.toUpperCase());

        List<Movie> temporaryList= new ArrayList<>();
        SearchByProductionCompany list= new SearchByProductionCompany(mList);
        temporaryList.addAll(list.mostRecentMovies());

        for(int i=0;i<temporaryList.size();i++)
            mostRecentMoviesController.Table.getItems().add(temporaryList.get(i));

        scene = new Scene(r, 800, 600);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);

    }
}
