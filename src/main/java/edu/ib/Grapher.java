package edu.ib;

import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class Grapher {

    private XYChart graph;
//    private double leftBound;
//    private double rightBound;
//    private double h;
//
    public Grapher(XYChart graph) {
        this.graph = graph;
//        this.leftBound = leftBound;
//        this.rightBound = rightBound;
//        this.h = h;
    }

    public XYChart getGraph() {
        return graph;
    }

    public void setGraph(XYChart graph) {
        this.graph = graph;
    }



    public void makePlot(ArrayList<Double> tList, ArrayList<Double> xList){
        XYChart.Series series = new XYChart.Series<>();
        for(int i=0; i<tList.size(); i++) plotPoint(tList.get(i),xList.get(i),series);
        graph.getData().add(series);
    }

    private  void plotPoint(double  x, double y, XYChart.Series series){
        series.getData().add(new XYChart.Data(x,y));
    }

    public void clear(){
        graph.getData().clear();
    }
}
