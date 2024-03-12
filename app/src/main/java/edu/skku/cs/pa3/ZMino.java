package edu.skku.cs.pa3;

public class ZMino extends Tetromino{

    public ZMino(){
        int sx = 1, sy = 5;
        x1 = sx;
        y1 = sy+1;
        x2 = sx;
        y2 = sy;
        x3 = sx-1;
        y3 = sy;
        x4 = sx-1;
        y4 = sy-1;
        status = 0;
        type = 6;

        color = GameModel.red;

        setColorColor();
    }

    @Override
    public void Rotate() {
        int x[] = {x1, x2, x3, x4};
        int y[] = {y1, y2, y3, y4};

        setColorBlack();
        if(status == 0){
            x1 = x2+1;
            y1 = y2;
            x3 = x2;
            y3 = y2+1;
            x4 = x2-1;
            y4 = y2+1;
        }
        else if(status == 1){
            x1 = x2;
            y1 = y2-1;
            x3 = x2+1;
            y3 = y2;
            x4 = x2+1;
            y4 = y2+1;
        }
        else if(status == 2){
            x1 = x2-1;
            y1 = y2;
            x3 = x2;
            y3 = y2-1;
            x4 = x2+1;
            y4 = y2-1;
        }
        else if(status == 3){
            x1 = x2;
            y1 = y2+1;
            x3 = x2-1;
            y3 = y2;
            x4 = x2-1;
            y4 = y2-1;
        }

        if(x1>=0 && x1<22 && x2>=0 && x2<22 && x3>=0 && x3<22 && x4>=0 && x4<22
                && y1>=0 && y1<10 && y2>=0 && y2<10 && y3>=0 && y3<10 && y4>=0 && y4<10) {
            String color1 = items.get(x1*10+y1);
            String color2 = items.get(x2*10+y2);
            String color3 = items.get(x3*10+y3);
            String color4 = items.get(x4*10+y4);
            boolean equal1 = color1.equals(GameModel.black);
            boolean equal2 = color2.equals(GameModel.black);
            boolean equal3 = color3.equals(GameModel.black);
            boolean equal4 = color4.equals(GameModel.black);
            if(equal1 && equal2 && equal3 && equal4) {
                status = (status + 1) % 4;
            }
            else{
                x1 = x[0];
                x2 = x[1];
                x3 = x[2];
                x4 = x[3];

                y1 = y[0];
                y2 = y[1];
                y3 = y[2];
                y4 = y[3];
            }
        }
        else{
            x1 = x[0];
            x2 = x[1];
            x3 = x[2];
            x4 = x[3];

            y1 = y[0];
            y2 = y[1];
            y3 = y[2];
            y4 = y[3];
        }
        setColorColor();
    }
}
