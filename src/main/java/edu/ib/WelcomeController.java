package edu.ib;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController {

    private Stage stage;
    private Scene scene;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCheckNotes;

    @FXML
    private Button btnGoToSolver;

    @FXML
    void checkNotesClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/notes.fxml"));
            Parent root1 = (Parent)loader.load();
            Stage primaryStage = new Stage();
            Scene scene1 = new Scene(root1);
            primaryStage.setScene(scene1);
            primaryStage.show();
        } catch (Exception error) {
            error.printStackTrace();
            error.getCause();
        }
    }

    @FXML
    void goToSolverClicked(ActionEvent event) {
        try{
            Parent root=  FXMLLoader.load(getClass().getResource("/fxml/tvdemo.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene= new Scene(root,800,600);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    @FXML
    void initialize() {
        assert btnCheckNotes != null : "fx:id=\"btnCheckNotes\" was not injected: check your FXML file 'welcometoapp.fxml'.";
        assert btnGoToSolver != null : "fx:id=\"btnGoToSolver\" was not injected: check your FXML file 'welcometoapp.fxml'.";

    }

}

