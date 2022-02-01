package edu.ib;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveController implements Initializable {

    private List<PointTX> pointsList = new ArrayList<>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> fileExtension;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField tfDirectory;

    @FXML
    private TextField tfFileName;


    @FXML
    private Text tvComment;

    @FXML
    void changeDirectoryBtnClick(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);
        if (selectedDirectory != null) tfDirectory.setText(selectedDirectory.getAbsolutePath());
    }

    @FXML
    void saveButtonClicked(ActionEvent event) {
        Path file =  Paths.get(tfDirectory.getText(), tfFileName.getText().trim() + fileExtension.getSelectionModel().getSelectedItem());
        if(!Files.exists(file)) { {
            try {
                Files.createFile((file).toAbsolutePath());
                saveTextToFile(file);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }}

    public SaveController() {
    }
    public void getResultList(ObservableList<PointTX> list) {
        for (PointTX pointTX : list) {
            pointsList.add(pointTX);
        }
    }


    @FXML
    void initialize() {
        assert fileExtension != null : "fx:id=\"fileExtension\" was not injected: check your FXML file 'savetofile.fxml'.";
        assert saveBtn != null : "fx:id=\"saveBtn\" was not injected: check your FXML file 'savetofile.fxml'.";
        assert tfDirectory != null : "fx:id=\"tfDirectory\" was not injected: check your FXML file 'savetofile.fxml'.";
        assert tfFileName != null : "fx:id=\"tfFileName\" was not injected: check your FXML file 'savetofile.fxml'.";
        assert tvComment != null : "fx:id=\"tvComment\" was not injected: check your FXML file 'savetofile.fxml'.";

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileExtension.getItems().addAll(".txt", ".csv");

        saveBtn.disableProperty().bind(tfFileName.textProperty().isEmpty()
                .or(fileExtension.getSelectionModel().selectedItemProperty().isNull()
                        .or(tfDirectory.textProperty().isEmpty())));
    }

    private void saveTextToFile(Path path) {
        try {
            List<String> stringList = new ArrayList<>();
            for (PointTX pointTX : pointsList) {
                stringList.add(pointTX.toString());

            }
            boolean exists = Files.exists(path);
            if (exists) {
                boolean empty = Files.size(path) == 0;
                tvComment.setText("File with that name is saved");
            }
            Files.write(path, stringList, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(SaveController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
