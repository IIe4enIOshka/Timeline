package iie4enioshka.example.timeline;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;

import static iie4enioshka.example.timeline.SplashScreen.list;


public class Game extends AppCompatActivity{

    private Window w;
    private Button btn_back;
    private TextView t_time;
    private ImageView img_left;
    private ImageView img_right;
    private static boolean stop;
    private int timer;
    private int WinOrLost;
    private TextView t_score;
    private int fail;
    private int score;
    private Dialog dialog;
    private Dialog dialog_loss;
    private Dialog dialog_finish;

    //private InterfaceGame interfaceGame;
    private TextView btn_close;
    private Button btn_continue;

    private ProgressBar progressbar;
    private int progressStatus;
    private boolean Exit;

    private boolean cancel;
    private static final String TAG = "LOG";
    private int image;

    private TextView t_score_finish;
    private Button btn_rest;
    private Button btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Collections.shuffle(list);
        cancel = false;
        timer = 10;
        fail = 0;
        score = 0;
        image = 0;
        stop = false;

        //new Timer().execute();
        //interfaceGame = new InterfaceGame();

        dialog = new Dialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_preview);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog_loss = new Dialog(this);
        dialog_loss.setCanceledOnTouchOutside(false);
        dialog_loss.setContentView(R.layout.dialog_loss);
        dialog_loss.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog_finish = new Dialog(this);
        dialog_finish.setCanceledOnTouchOutside(false);
        dialog_finish.setContentView(R.layout.dialog_finish);
        dialog_finish.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        btn_close = (TextView)dialog.findViewById(R.id.btn_close);
        btn_continue = (Button)dialog.findViewById(R.id.btn_continue);

        progressbar = (ProgressBar)dialog_loss.findViewById(R.id.progressBar123);
        progressStatus = 100;

        btn_home = (Button)dialog_finish.findViewById(R.id.btn_home);
        btn_rest = (Button)dialog_finish.findViewById(R.id.btn_rest);
        t_score_finish = (TextView)dialog_finish.findViewById(R.id.t_score_finish);

        t_score = (TextView)findViewById(R.id.t_score);
        t_time = (TextView)findViewById(R.id.t_time);
        img_left = (ImageView)findViewById(R.id.img_left);
        img_right = (ImageView)findViewById(R.id.img_right);

        t_time.setText("" + timer);
        t_score.setText("" + score);

        btn_back = (Button)findViewById(R.id.btn_back);

        w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        dialog_finish.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        dialog.show();
        dialog.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Game.this, Menu.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startTimerThread();
                Cards();
            }
        });

        progressbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fail++;
                stop = true;
                cancel = false;
                dialog_loss.dismiss();
                startTimerThread();
                Cards();
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_finish.dismiss();
                try {
                    Intent intent = new Intent(Game.this, Menu.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

//        list = new ArrayList<>();
//        list.add(new Cards(0, 1492, R.drawable.card_1_1, R.drawable.card_1_2));
//        list.add(new Cards(0, -4236, R.drawable.card_2_1, R.drawable.card_2_2));
//        list.add(new Cards(0, -150, R.drawable.card_3_1, R.drawable.card_3_2));
//        list.add(new Cards(0, 1996, R.drawable.card_4_1, R.drawable.card_4_2));
//        list.add(new Cards(0, 1910, R.drawable.card_5_1, R.drawable.card_5_2));
//        Collections.shuffle(list);

        //map.putAll(new SplashScreen().getMap());
//        map = new HashMap<>();
//        map.put(new Cards(0, 1492, R.drawable.card_1_1), new Cards(1, 1492, R.drawable.card_1_2));
//        map.put(new Cards(2, -4236, R.drawable.card_2_1), new Cards(3, -4236, R.drawable.card_2_2));
//        map.put(new Cards(4, -150, R.drawable.card_3_1), new Cards(5, -150, R.drawable.card_3_2));
//        map.put(new Cards(6, 1996, R.drawable.card_4_1), new Cards(7, 1996, R.drawable.card_4_2));
//        map.put(new Cards(8, 1910, R.drawable.card_5_1), new Cards(9, 1910, R.drawable.card_5_2));
//
//        list1 = new ArrayList<>(map.keySet());
//        Collections.shuffle(list1);

//        img_left.setImageResource(list.get(0).getImage1());
//        img_right.setImageResource(list.get(1).getImage1());

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    //interfaceGame.cancel(false);
                    cancel = true;
                    Intent intent = new Intent(Game.this, Menu.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e){
                    e.getStackTrace();
                }
            }
        });

        img_left.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cancel = true;
                Win(0);
            }
        });
        img_right.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cancel = true;
                Win(1);
            }
        });
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    private void startProgressbar() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                while(progressStatus > 0){
                    try {
                        if(stop)
                            break;
                        timer = 10;
                        progressStatus--;
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                progressbar.setProgress(progressStatus);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    });
                }
                if(progressStatus == 0 && fail == 0) {
                    dialog_loss.dismiss();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            t_score_finish.setText("" + score);
                            dialog_finish.show();
                        }
                    });

                }
            }
        };
        new Thread(runnable).start();
    }

    private void startTimerThread() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                for (int i = 10; i >= 0; i--) {
                    if (cancel == true)
                        break;
                    Log.d(TAG, "time: " + i);
                    final int finalI = i;
                    handler.post(new Runnable() {
                        public void run() {
                            t_time.setText("" + finalI);
                            if (finalI < 6)
                                t_time.setTextColor(Color.RED);
                            if (finalI > 5)
                                t_time.setTextColor(Color.BLACK);
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(runnable).start();
    }

    private void Cards() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Collections.shuffle(list);
                if (image == list.size()-1) {
                    image = 0;
                }
                final int finalImage = image;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loadImage(list.get(finalImage).getImage1(), list.get(finalImage + 1).getImage1());
                    }
                });
            }
        };
        new Thread(runnable).start();
    }

//    public class InterfaceGame extends AsyncTask<Void, Integer, Void>{
//        @Override
//        protected Void doInBackground(Void... voids) {
//            int image;
//            while(true){
//                if(isCancelled())
//                    break;
//                Collections.shuffle(list);
//                for(image = 0; image <= list.size()-1; image += 2) {
//                    if(Exit == true)
//                        break;
//                    if(fail == 3) {
//                            publishProgress(6);
//                        while(!dialogloss.isShowing()){
//                            System.out.println("Диалог открыт");
//                        }
//                    }
//                    publishProgress(1, image);
//                    for (timer = 10; timer >= 0; timer--) {
//                        if (stop == true)
//                            break;
//                        try {
//                            publishProgress(0, timer);
//                            Thread.sleep(1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if(WinOrLost != 3 && WinOrLost != 4) {
//                        try {
//                            fail++;
//                            if (list.get(image).compare(list.get(image + 1))) {
//                                publishProgress(3);
//                                Thread.sleep(500);
//                                publishProgress(2, image);
//                                Thread.sleep(500);
//                                WinOrLost = 0;
//                                stop = false;
//                            } else {
//                                publishProgress(4);
//                                Thread.sleep(500);
//                                publishProgress(2, image);
//                                Thread.sleep(500);
//                                WinOrLost = 0;
//                                stop = false;
//                            }
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if (WinOrLost == 3){
//                        try {
//                            if(list.get(image).compare(list.get(image + 1))){
//                                score++;
//                                publishProgress(5, score);
//                                publishProgress(3);
//                                Thread.sleep(500);
//                                publishProgress(2, image);
//                                Thread.sleep(500);
//                                WinOrLost = 0;
//                                stop = false;
//                            }
//                            else{
//                                fail++;
//                                publishProgress(4);
//                                Thread.sleep(500);
//                                publishProgress(2, image);
//                                Thread.sleep(500);
//                                WinOrLost = 0;
//                                stop = false;
//                            }
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if (WinOrLost == 4){
//                        try {
//                            if(list.get(image + 1).compare(list.get(image))){
//                                score++;
//                                publishProgress(5, score);
//                                publishProgress(4);
//                                Thread.sleep(500);
//                                publishProgress(2, image);
//                                Thread.sleep(500);
//                                WinOrLost = 0;
//                                stop = false;
//                            }
//                            else{
//                                fail++;
//                                publishProgress(3);
//                                Thread.sleep(500);
//                                publishProgress(2, image);
//                                Thread.sleep(500);
//                                WinOrLost = 0;
//                                stop = false;
//                            }
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//            return null;
//        }
//
//        @Override
//        public void onProgressUpdate(Integer... progress) {
//            if(WinOrLost == 0){
//                img_left.setColorFilter(Color.TRANSPARENT);
//                img_right.setColorFilter(Color.TRANSPARENT);
//            }
//            if(progress[0] == 0) {
//                if (progress[1] < 6)
//                    t_time.setTextColor(Color.RED);
//                if(progress[1] > 5)
//                    t_time.setTextColor(Color.BLACK);
//                t_time.setText("" + progress[1]);
//            }
//            if(progress[0] == 1) {
//                loadImage(list.get(progress[1]).getImage1(), list.get(progress[1] + 1).getImage1());
//                System.out.println("Единица " + progress[1]);
//            }
//            if(progress[0] == 2) {
//                loadImage(list.get(progress[1]).getImage2(), list.get(progress[1] + 1).getImage2());
//                System.out.println("Двойка " + progress[1]);
//            }
//            if(progress[0] == 3) {
//                img_left.setColorFilter(Color.argb(50, 0, 255, 0));
//            }
//            if(progress[0] == 4) {
//                img_right.setColorFilter(Color.argb(50, 0, 255, 0));
//            }
//            if(progress[0] == 5)
//                t_score.setText("" + progress[1]);
//            if(progress[0] == 6) {
//                dialogloss.show();
//                startProgressbar();
//            }
//        }
//    }

    private synchronized void loadImage(int image1, int image2){
        img_left.setImageResource(image1);
        img_right.setImageResource(image2);
    }

    private void Win(final int Win){
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (Win == 0){
                    if(list.get(image).compare(list.get(image + 1))) {
                        score++;
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                img_left.setColorFilter(Color.argb(50, 0, 255, 0));
                                t_score.setText("" + score);
                            }
                        });
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                loadImage(list.get(image).getImage2(), list.get(image + 1).getImage2());
                            }
                        });
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                img_left.setColorFilter(Color.TRANSPARENT);
                            }
                        });
                        cancel = false;
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                startTimerThread();
                                Cards();
                            }
                        });
                        image++;
                    }
                    else{
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                img_left.setColorFilter(Color.argb(50, 255, 0, 0));
                            }
                        });
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                loadImage(list.get(image).getImage2(), list.get(image + 1).getImage2());
                            }
                        });
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                img_left.setColorFilter(Color.TRANSPARENT);
                                if(fail <= 1) {
                                    dialog_loss.show();
                                    startProgressbar();
                                }
                            }
                        });
                    }
                }
                else if(Win == 1){
                    if(list.get(image + 1).compare(list.get(image))) {
                        score++;
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                img_right.setColorFilter(Color.argb(50, 0, 255, 0));
                                t_score.setText("" + score);
                            }
                        });
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                loadImage(list.get(image).getImage2(), list.get(image + 1).getImage2());
                            }
                        });
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                img_right.setColorFilter(Color.TRANSPARENT);
                            }
                        });
                        cancel = false;
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                startTimerThread();
                                Cards();
                            }
                        });
                        image++;
                    }
                    else{
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                img_right.setColorFilter(Color.argb(50, 255, 0, 0));
                            }
                        });
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                loadImage(list.get(image).getImage2(), list.get(image + 1).getImage2());
                            }
                        });
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                img_right.setColorFilter(Color.TRANSPARENT);
                                if(fail == 0) {
                                    dialog_loss.show();
                                    startProgressbar();
                                }
                                if(fail >= 1) {
                                    cancel = true;
                                    dialog_finish.show();
                                    t_score_finish.setText("" + score);
                                }
                            }
                        });
                    }
                }
            }
        };
        new Thread(runnable).start();
    }
}


