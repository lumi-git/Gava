package Gava.utility;

import java.util.ArrayList;

public class FpsManager {
    private double fps;
    private double minFps = -1;
    private double maxFps = -1;

    private ArrayList<Double> fpsList;

    public FpsManager(){
        fpsList = new ArrayList<Double>();
    }

    public void addFps(double fps){
        if( fps < minFps || minFps == -1){
            minFps = fps;
        }
        if( fps > maxFps){
            maxFps = fps;
        }
        if( fpsList.size() > 20){
            fpsList.remove(0);
        }
        fpsList.add(fps);
    }

    public void mean(){
        double sum = 0;
        for (Double fps : fpsList) {
            sum += fps;
        }
        this.fps = sum / fpsList.size();
    }

    public double getFps(){
        return this.fps;
    }

    public double getMinFps(){
        return this.minFps;
    }

    public double getMaxFps(){
        return this.maxFps;
    }

    @Override
    public String toString() {
        return "FpsManager{" +
                "fpsMean=" + fps +
                ", minFps=" + minFps +
                ", maxFps=" + maxFps +
                '}';
    }
}
