package edu.ib;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

    public class TVDemoController {

        private ODEIntegrator integrator;
        private ObservableList<PointTX> list= FXCollections.observableArrayList();
        private Grapher grapher;

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private Button btnCalculateEuler;

        @FXML
        private Button btnCalculateEulerModified;

        @FXML
        private Button btnClear;

        @FXML
        private Button btnSave;


        @FXML
        private LineChart<?, ?> plot;

        @FXML
        private NumberAxis tAxis;

        @FXML
        private TableView<PointTX> table;

        @FXML
        private TextField tfEquation;

        @FXML
        private TextField tfH;

        @FXML
        private TextField tfMax;

        @FXML
        private TextField tfMin;

        @FXML
        private TextField tfXZero;

        @FXML
        private Text tvError;

        @FXML
        private TableColumn<PointTX, Double> time;

        @FXML
        private Text tvFinalResult;

        @FXML
        private Text tvH;

        @FXML
        private TableColumn<PointTX, Double> x;

        @FXML
        private NumberAxis xValAxis;

        @FXML
        void btnCalculateEulerClicked(ActionEvent event) {
            table.getItems().clear(); // remove all previous values

            if(!(tfMin.getText().isEmpty()) && !(tfMax.getText().isEmpty()) && !(tfH.getText().isEmpty()) &&!(tfXZero.getText().isEmpty())&&!(tfEquation.getText().isEmpty())){
                tvError.setText("");
                double a=Double.parseDouble(changeComma(tfMin.getText().toString()));
                double b=Double.parseDouble(changeComma(tfMax.getText().toString()));
                double h=Double.parseDouble(changeComma(tfH.getText().toString()));
                double x0=Double.parseDouble(changeComma(tfXZero.getText().toString()));

                String equation = changeComma(tfEquation.getText().toString());
            ConsoleHandler consoleHandler = new ConsoleHandler();
            integrator = new ODEIntegrator(a,b,x0,equation, new Euler(), consoleHandler);
            integrator.integrate(h);
            list.addAll(PointTX.getPointsTX(consoleHandler.gettList(),consoleHandler.getxList()));
            grapher.makePlot(consoleHandler.gettList(),consoleHandler.getxList());
            String value = String.valueOf(consoleHandler.getXValue(consoleHandler.getNumberOfPoints()-1));
            tvFinalResult.setText(value);}
            else{
                tvError.setText("Please enter all parameters");
            }
        }

        @FXML
        void btnCalculateEulerModifiedClicked(ActionEvent event) {
            table.getItems().clear(); // remove all previous values

            if(!(tfMin.getText().isEmpty()) && !(tfMax.getText().isEmpty()) && !(tfH.getText().isEmpty()) &&!(tfXZero.getText().isEmpty())&&!(tfEquation.getText().isEmpty())){
                tvError.setText("");
                double a=Double.parseDouble(changeComma(tfMin.getText().toString()));
                double b=Double.parseDouble(changeComma(tfMax.getText().toString()));
                double h=Double.parseDouble(changeComma(tfH.getText().toString()));
                double x0=Double.parseDouble(changeComma(tfXZero.getText().toString()));

            String equation = changeComma(tfEquation.getText().toString());
            ConsoleHandler consoleHandler = new ConsoleHandler();
            integrator = new ODEIntegrator(a,b,x0,equation, new EulerModified(), consoleHandler);
            integrator.integrate(h);
            list.addAll(PointTX.getPointsTX(consoleHandler.gettList(),consoleHandler.getxList()));
            grapher.makePlot(consoleHandler.gettList(),consoleHandler.getxList());
            String value = String.valueOf(consoleHandler.getXValue(consoleHandler.getNumberOfPoints()-1));
            tvFinalResult.setText(value);}
            else{
                tvError.setText("Please enter all parameters");
            }


        }

        @FXML
        void btnClearClicked(ActionEvent event) {
            grapher.clear();
        }

        @FXML
        void saveToFile(ActionEvent event) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/savetofile.fxml"));
                Parent root = (Parent)loader.load();
                SaveController saveController = loader.getController();
                saveController.getResultList(list);
                Stage primaryStage = new Stage();
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);

                primaryStage.show();
            } catch (Exception error) {
                error.printStackTrace();
                error.getCause();
            }
        }

        public TVDemoController() {
        }

        public List<PointTX> getList() {
            List<PointTX> resultList = new ArrayList<>();
            for (PointTX pointTX : list) {
                resultList.add(pointTX);

            }
            return resultList;
        }

        @FXML
        void initialize() {
            assert btnCalculateEuler != null : "fx:id=\"btnCalculateEuler\" was not injected: check your FXML file 'tvdemo.fxml'.";
            assert btnCalculateEulerModified != null : "fx:id=\"btnCalculateEulerModified\" was not injected: check your FXML file 'tvdemo.fxml'.";
            assert plot != null : "fx:id=\"plot\" was not injected: check your FXML file 'tvdemo.fxml'.";
            assert tAxis != null : "fx:id=\"tAxis\" was not injected: check your FXML file 'tvdemo.fxml'.";
            assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'tvdemo.fxml'.";
            assert tfEquation != null : "fx:id=\"tfEquation\" was not injected: check your FXML file 'tvdemo.fxml'.";
            assert tfH != null : "fx:id=\"tfH\" was not injected: check your FXML file 'tvdemo.fxml'.";
            assert tfMax != null : "fx:id=\"tfMax\" was not injected: check your FXML file 'tvdemo.fxml'.";
            assert tfMin != null : "fx:id=\"tfMin\" was not injected: check your FXML file 'tvdemo.fxml'.";
            assert tfXZero != null : "fx:id=\"tfXZero\" was not injected: check your FXML file 'tvdemo.fxml'.";
            assert time != null : "fx:id=\"time\" was not injected: check your FXML file 'tvdemo.fxml'.";
            assert tvFinalResult != null : "fx:id=\"tvFinalResult\" was not injected: check your FXML file 'tvdemo.fxml'.";
            assert tvH != null : "fx:id=\"tvH\" was not injected: check your FXML file 'tvdemo.fxml'.";
            assert x != null : "fx:id=\"x\" was not injected: check your FXML file 'tvdemo.fxml'.";
            assert xValAxis != null : "fx:id=\"xValAxis\" was not injected: check your FXML file 'tvdemo.fxml'.";
            assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'tvdemo.fxml'.";
            assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'tvdemo.fxml'.";
            assert tvError != null : "fx:id=\"tvError\" was not injected: check your FXML file 'tvdemo.fxml'.";

            grapher = new Grapher(plot);
            time.setCellValueFactory(new PropertyValueFactory<PointTX,Double>("time"));
            x.setCellValueFactory(new PropertyValueFactory<PointTX,Double>("x"));
            table.setItems(list);

        }

        public String changeComma(String s){
            if(s.contains(",")){
                s = s.replace(",",".");
            }
            return s;
        }

    }


