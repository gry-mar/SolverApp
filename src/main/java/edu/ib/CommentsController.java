package edu.ib;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CommentsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lbComments;


    @FXML
    void initialize() {
        assert lbComments != null : "fx:id=\"lbComments\" was not injected: check your FXML file 'notes.fxml'.";

        String words = "Application was created to solve differential equations using Euler\n" +
                " and modified Euler method.\n" +
                "Enter your equation in proper field and specify\n" +
                " compartment [a, b],start value x0 and step h.\n" +
                "Elementary operations:\n"+
                "- subtraction;\n"+
                "+ addition;\n"+
                "* multiplication;\n"+
                "/ division;\n"+
                "^ expotentiation.";

       lbComments.setText(words);

    }

}
