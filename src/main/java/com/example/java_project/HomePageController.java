package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.java_project.LogInPageController.input;
import static com.example.java_project.Main.mList;
import static com.example.java_project.Main.server;

public class HomePageController {

    private Stage stage;
    private Parent root;
    private Scene scene;

    static String command;
    public Button MostRecentMovies;
    public Button MaxRev;
    public Button TotalProfit;
    public Button BackButton;

    public void onMostRecentMoviesClick(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MostRecentMovies.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setScene(scene);

        MostRecentMoviesController mostRecentMovies= fxmlLoader.getController();
        mostRecentMovies.setCompanyNameLabel(input.toUpperCase());

        List<Movie> temporaryList= new ArrayList<>();
        SearchByProductionCompany list= new SearchByProductionCompany(mList);
        temporaryList.addAll(list.mostRecentMovies());

        for(int i=0;i<temporaryList.size();i++)
            mostRecentMovies.Table.getItems().add(temporaryList.get(i));


    }

    public void onMaxRevClick(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MoviesWithMaximumRevenue.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setScene(scene);

        MoviesWithMaximumRevenueController moviesWithMaximumRevenueController= fxmlLoader.getController();
        moviesWithMaximumRevenueController.setCompanyNameLabel(input.toUpperCase());

        List<Movie> temporaryList= new ArrayList<>();
        SearchByProductionCompany list= new SearchByProductionCompany(mList);
        temporaryList.addAll(list.maxRevenueMovies());

       /* System.out.println("size :" + temporaryList.size());*/

        for(int i=0;i<temporaryList.size();i++)
            moviesWithMaximumRevenueController.Table.getItems().add(temporaryList.get(i));
    }

    public void onTotalProfitClick(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("TotalProfit.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setScene(scene);

        TotalProfitController totalProfitController=fxmlLoader.getController();
        totalProfitController.setCompanyNameLabelLabel(input.toUpperCase());

        SearchByProductionCompany list=new SearchByProductionCompany(mList);
        long profit=list.totalProfit();

        if(profit >=0 )
        {
            totalProfitController.setProfitLabel("Profit : "+profit+"$");
        }
        else
        {
            totalProfitController.setProfitLabel("Loss : "+profit+"$");
        }

    }


    public void onBackButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LogInPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setScene(scene);
       LogInPageController logInPageController=fxmlLoader.getController();
       logInPageController.LogInTextField.clear();

    }


    public void onAddMovieButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddMoviePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setScene(scene);
    }

    public void onTransferMovieButtonClick(ActionEvent actionEvent) throws IOException {

        Stage stage= (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("TransferMoviePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setScene(scene);

    }

    public void onProducedMoviesClick(ActionEvent actionEvent) throws IOException {
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
