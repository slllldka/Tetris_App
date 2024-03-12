package edu.skku.cs.pa3;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GameModel {
    private boolean running = false;
    private boolean paused = false;
    private boolean current_exist = false;
    private boolean hold_pressed = false;

    private Tetromino current;
    private int timer = 0;
    private int idx = 0;
    private int order[] = new int[7];
    private int score = 0;

    private int hold_type = -1;
    private int current_type = -1;

    public static ArrayList<String> items;
    public final static String red = "#FF0000", orange = "#FFA500", yellow = "#FFFF00", green = "#00FF00"
            , skyblue = "#00FFFF", blue = "#7777FF", violet = "#EE82EE", black = "#000000";

    public GameModel(){
        items = new ArrayList<String>();
        for(int i=0;i<220;i++){
            items.add(black);
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isCurrent_exist() {
        return current_exist;
    }

    public void setCurrent_exist(boolean current_exist) {
        this.current_exist = current_exist;
    }

    public boolean isHold_pressed() {
        return hold_pressed;
    }

    public void setHold_pressed(boolean hold_pressed) {
        this.hold_pressed = hold_pressed;
    }

    public Tetromino getCurrent() {
        return current;
    }

    public void setCurrent(Tetromino current) {
        this.current = current;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int[] getOrder() {
        return order;
    }

    public void setOrder(int idx, int value) {
        order[idx] = value;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHold_type() {
        return hold_type;
    }

    public void setHold_type(int hold_type) {
        this.hold_type = hold_type;
    }

    public int getCurrent_type() {
        return current_type;
    }

    public void setCurrent_type(int current_type) {
        this.current_type = current_type;
    }

}
