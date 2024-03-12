package edu.skku.cs.pa3;

public class OMino extends Tetromino{

    public OMino(){
        int sx = 1, sy = 5;
        x1 = sx-1;
        y1 = sy;
        x2 = sx-1;
        y2 = sy+1;
        x3 = sx;
        y3 = sy+1;
        x4 = sx;
        y4 = sy;
        status = 0;
        type = 1;

        color = GameModel.yellow;

        setColorColor();
    }

    @Override
    public void Rotate() {
    }
}
