package edu.skku.cs.pa3;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GamePresenter implements GameContract.Presenter{

    private SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
    private Random random = new Random();

    private GameContract.View view;
    private GameModel model;
    private GridViewAdapter adapter;

    public GamePresenter(GameContract.View view, Context mcontext){
        this.view = view;
        model = new GameModel();
        adapter = new GridViewAdapter(mcontext);


        model.setScore(0);
        model.setRunning(true);

        view.UpdateGridview(adapter);
        view.UpdateScore(model.getScore());

        SetOrder();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Start();
            }
        });
        thread.start();
    }
    @Override
    public void SetOrder(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<7;i++){
            list.add(i);
        }

        for(int i=0;i<7;i++){
            int r = random.nextInt(7-i);
            model.setOrder(i, list.get(r));
            list.remove(r);
        }
    }

    @Override
    public void CreateNewTetromino(){
        int[] order = model.getOrder();
        int idx = model.getIdx();
        if (order[idx] == 0) {
            model.setCurrent(new IMino());
        } else if (order[idx] == 1) {
            model.setCurrent(new OMino());
        } else if (order[idx] == 2) {
            model.setCurrent(new JMino());
        } else if (order[idx] == 3) {
            model.setCurrent(new LMino());
        } else if (order[idx] == 4) {
            model.setCurrent(new TMino());
        } else if (order[idx] == 5) {
            model.setCurrent(new SMino());
        } else if (order[idx] == 6) {
            model.setCurrent(new ZMino());
        }

        model.setCurrent_exist(true);

        if (idx == 6) {
            SetOrder();
        }

        model.setIdx((idx + 1) % 7);
    }

    @Override
    public void HoldTetromino(){
        int hold_type = model.getHold_type();
        if(hold_type == -1){
            CreateNewTetromino();
        }
        else {
            if (hold_type == 0) {
                model.setCurrent(new IMino());
            } else if (hold_type == 1) {
                model.setCurrent(new OMino());
            } else if (hold_type == 2) {
                model.setCurrent(new JMino());
            } else if (hold_type == 3) {
                model.setCurrent(new LMino());
            } else if (hold_type == 4) {
                model.setCurrent(new TMino());
            } else if (hold_type == 5) {
                model.setCurrent(new SMino());
            } else if (hold_type == 6) {
                model.setCurrent(new ZMino());
            }
        }

        model.setHold_type(model.getCurrent_type());
        hold_type = model.getHold_type();
        if(hold_type == 0){
            view.UpdateHoldImage(R.drawable.imino);
        }
        else if(hold_type == 1){
            view.UpdateHoldImage(R.drawable.omino);
        }
        else if(hold_type == 2){
            view.UpdateHoldImage(R.drawable.jmino);
        }
        else if(hold_type == 3){
            view.UpdateHoldImage(R.drawable.lmino);
        }
        else if(hold_type == 4){
            view.UpdateHoldImage(R.drawable.tmino);
        }
        else if(hold_type == 5){
            view.UpdateHoldImage(R.drawable.smino);
        }
        else if(hold_type == 6){
            view.UpdateHoldImage(R.drawable.zmino);
        }
    }

    @Override
    public void Start(){
        while(model.isRunning()) {
            if(!model.isPaused()) {
                if (model.getCurrent() == null) {
                    if(model.isHold_pressed()){
                        HoldTetromino();
                    }
                    else {
                        CreateNewTetromino();
                    }
                }

                view.UpdateGridview(adapter);

                Tetromino current = model.getCurrent();
                synchronized (current) {
                    if(current != null) {
                        if (!current.SoftDrop(true)) {
                            if (current.x1 < 2 || current.x2 < 2 || current.x3 < 2 || current.x4 < 2) {
                                model.setRunning(false);
                                view.MakeToast("Finished", Toast.LENGTH_SHORT);

                            /*    OkHttpClient client = new OkHttpClient();

                                String date = format.format(System.currentTimeMillis());
                                RecordData data = new RecordData(LobbyModel.id, model.getScore(), date);

                                Gson gson = new Gson();
                                String json = gson.toJson(data, RecordData.class);

                                HttpUrl.Builder urlBuilder = HttpUrl.parse("https://sfbsl3u7sj.execute-api.ap-northeast-2.amazonaws.com/pa3/post/records").newBuilder();
                                String url = urlBuilder.build().toString();

                                Request req = new Request.Builder().url(url).post(RequestBody.create(MediaType.parse("application/json"), json)).build();

                                client.newCall(req).enqueue(new Callback() {
                                    @Override
                                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                        String myResponse = response.body().string();
                                        Gson gson = new GsonBuilder().create();
                                        SuccessFailData data = gson.fromJson(myResponse, SuccessFailData.class);
                                        boolean success = data.getSuccess();
                                        if (success) {
                                            view.MakeToast("Score Recording Success", Toast.LENGTH_SHORT);
                                        } else {
                                            view.MakeToast("Score Recording Fail", Toast.LENGTH_SHORT);
                                        }
                                    }

                                    @Override
                                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                        e.printStackTrace();
                                    }
                                });*/

                            }
                            model.setCurrent(null);
                            model.setCurrent_exist(false);
                            model.setHold_pressed(false);
                        }
                    }
                }

                current = model.getCurrent();
                if(current == null) {
                    ArrayList<Integer> idx_arr = CheckLines();
                    model.setScore(model.getScore() + idx_arr.size() * idx_arr.size());

                    view.UpdateScore(model.getScore());
                    view.UpdateGridview(adapter);

                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    PushDown(idx_arr);

                    view.UpdateGridview(adapter);

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    model.setTimer(0);
                    while (model.getTimer() < 700) {
                        model.setTimer(model.getTimer() + 10);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    @Override
    public ArrayList<Integer> CheckLines(){
        ArrayList<Integer> idx_arr = new ArrayList<Integer>();
        for(int i=2;i<22;i++){
            int j=0;
            for(j=0;j<10;j++){
                if(GameModel.items.get(i*10+j).equals(GameModel.black)){
                    break;
                }
            }
            if(j == 10){
                idx_arr.add(i);
            }
        }

        for(int i : idx_arr){
            for(int j=0;j<10;j++){
                GameModel.items.set(i*10+j, GameModel.black);
            }
        }

        return idx_arr;
    }

    @Override
    public void PushDown(ArrayList<Integer> idx_arr){
        for(int i : idx_arr){
            for(int j=i-1;j>=2;j--){
                for(int k=0;k<10;k++){
                    String color = GameModel.items.get(j*10+k);
                    GameModel.items.set((j+1)*10+k, color);
                }
            }
            for(int j=0;j<10;j++){
                GameModel.items.set(2*10+j, GameModel.black);
            }
        }
    }

    @Override
    public void ibtn_p_r_pressed() {
        if(model.isPaused() == false){
            model.setPaused(true);
            view.UpdatePauseResume(android.R.drawable.ic_media_play, "stop");
        }
        else{
            model.setPaused(false);
            view.UpdatePauseResume(android.R.drawable.ic_media_pause, "run");
        }
    }

    @Override
    public void btn_hold_pressed() {
        if(!model.isPaused()){
            Tetromino current = model.getCurrent();
            if(current != null){
                if(!model.isHold_pressed()){
                    model.setHold_pressed(true);
                    model.setCurrent_type(current.getType());
                    current.setColorBlack();
                    model.setCurrent(null);
                    view.UpdateGridview(adapter);
                }
            }
        }
    }

    @Override
    public void btn_left_pressed() {
        if(!model.isPaused()) {
            Tetromino current = model.getCurrent();
            if (current != null) {
                current.Left();
                view.UpdateGridview(adapter);
            }
        }
    }

    @Override
    public void btn_right_pressed() {
        if(!model.isPaused()) {
            Tetromino current = model.getCurrent();
            if (current != null) {
                current.Right();
                view.UpdateGridview(adapter);
            }
        }
    }

    @Override
    public void btn_rotate_pressed() {
        if(!model.isPaused()) {
            Tetromino current = model.getCurrent();
            if (current != null) {
                current.Rotate();
                view.UpdateGridview(adapter);
                if(model.isCurrent_exist()) {
                    if (!current.SoftDrop(false)) {
                        model.setTimer(model.getTimer() - 200);
                    }
                }
            }
        }
    }

    @Override
    public void btn_softdrop_pressed() {
        if(!model.isPaused()) {
            Tetromino current = model.getCurrent();
            if (current != null) {
                current.SoftDrop(true);
                view.UpdateGridview(adapter);
            }
        }
    }

    @Override
    public void btn_harddrop_pressed() {
        if(!model.isPaused()) {
            Tetromino current = model.getCurrent();
            if (current != null) {
                if(current.SoftDrop(false)) {
                    model.setTimer(0);
                }
                current.HardDrop();
                view.UpdateGridview(adapter);
            }
        }
    }

    @Override
    public void onStop() {
        model.setPaused(true);
    }

    @Override
    public void onResume() {
        model.setPaused(false);
    }

    @Override
    public void onDestroy() {
        model.setRunning(false);
    }
}
