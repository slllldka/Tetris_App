package edu.skku.cs.pa3;

import java.util.ArrayList;

public abstract class Tetromino {
    protected ArrayList<String> items = GameModel.items;
    protected int x1, y1, x2, y2, x3, y3, x4, y4;
    protected String color;
    protected int status = 0; // 0 ~ 3, changes when tetromino rotates
    protected int type = -1;

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getX3() {
        return x3;
    }

    public void setX3(int x3) {
        this.x3 = x3;
    }

    public int getY3() {
        return y3;
    }

    public void setY3(int y3) {
        this.y3 = y3;
    }

    public int getX4() {
        return x4;
    }

    public void setX4(int x4) {
        this.x4 = x4;
    }

    public int getY4() {
        return y4;
    }

    public void setY4(int y4) {
        this.y4 = y4;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setColorColor(){
        items.set(x1*10+y1, color);
        items.set(x2*10+y2, color);
        items.set(x3*10+y3, color);
        items.set(x4*10+y4, color);
    }

    public void setColorBlack(){
        items.set(x1*10+y1, GameModel.black);
        items.set(x2*10+y2, GameModel.black);
        items.set(x3*10+y3, GameModel.black);
        items.set(x4*10+y4, GameModel.black);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void Left(){
        if(y1 < 1 || y2 < 1 || y3 < 1 || y4 < 1){
            return;
        }
        setColorBlack();

        y1--;
        y2--;
        y3--;
        y4--;
        String color1 = items.get(x1*10+y1);
        String color2 = items.get(x2*10+y2);
        String color3 = items.get(x3*10+y3);
        String color4 = items.get(x4*10+y4);
        boolean equal1 = color1.equals(GameModel.black);
        boolean equal2 = color2.equals(GameModel.black);
        boolean equal3 = color3.equals(GameModel.black);
        boolean equal4 = color4.equals(GameModel.black);

        if(equal1 && equal2 && equal3 && equal4){
            setColorColor();
        }
        else{
            y1++;
            y2++;
            y3++;
            y4++;
        }
        setColorColor();
    }
    public void Right(){
        if(y1 > 8 || y2 > 8 || y3 > 8 || y4 > 8){
            return;
        }
        setColorBlack();

        y1++;
        y2++;
        y3++;
        y4++;
        String color1 = items.get(x1*10+y1);
        String color2 = items.get(x2*10+y2);
        String color3 = items.get(x3*10+y3);
        String color4 = items.get(x4*10+y4);
        boolean equal1 = color1.equals(GameModel.black);
        boolean equal2 = color2.equals(GameModel.black);
        boolean equal3 = color3.equals(GameModel.black);
        boolean equal4 = color4.equals(GameModel.black);

        if(equal1 && equal2 && equal3 && equal4){
            setColorColor();
        }
        else {
            y1--;
            y2--;
            y3--;
            y4--;
            setColorColor();
        }
    }
    public boolean SoftDrop(boolean value){
        if(x1 > 20 || x2 > 20 || x3 > 20 || x4 > 20){
            return false;
        }

        setColorBlack();

        x1++;
        x2++;
        x3++;
        x4++;
        String color1 = items.get(x1*10+y1);
        String color2 = items.get(x2*10+y2);
        String color3 = items.get(x3*10+y3);
        String color4 = items.get(x4*10+y4);
        boolean equal1 = color1.equals(GameModel.black);
        boolean equal2 = color2.equals(GameModel.black);
        boolean equal3 = color3.equals(GameModel.black);
        boolean equal4 = color4.equals(GameModel.black);

        if(equal1 && equal2 && equal3 && equal4){
            if(!value) {
                x1--;
                x2--;
                x3--;
                x4--;
            }
            setColorColor();
            return true;
        }
        else{
            x1--;
            x2--;
            x3--;
            x4--;
            setColorColor();
            return false;
        }
    }
    public void HardDrop(){
        while(SoftDrop(true));
    }
    public abstract void Rotate();
}
