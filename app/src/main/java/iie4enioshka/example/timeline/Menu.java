package iie4enioshka.example.timeline;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import io.reactivex.Observable;

public class Menu extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private int counter = 0;
    private static final String TAG = "MainActivity";
    private TextView textView;
    private ImageButton btn_prop;
    private Button btn_start;
    private LinearLayout linearLayout;
    private Observable<Long> intervalObservable;
    private Button btndlg1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btn_prop = (ImageButton)findViewById(R.id.btn_prop);
        btn_start = (Button)findViewById(R.id.btn_start);
        linearLayout = (LinearLayout)findViewById(R.id.prop_layout);

        btndlg1 = (Button)findViewById(R.id.btnDlg1);

        btndlg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullScreenDialog dialog = new FullScreenDialog();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                dialog.show(ft, FullScreenDialog.TAG);
            }
        });
//        textView.setText("0");
//
//        intervalObservable = Observable
//                .interval(1, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .takeWhile(new Predicate<Long>(){
//                    @Override
//                    public boolean test(Long aLong) throws Exception {
//                        return aLong <= 15;
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread());
//
//
//
//        intervalObservable.subscribe(new Observer<Long>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void  onNext(Long aLong) {
//                Log.d(TAG, "" + aLong);
//                //textView.setText("" + aLong);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "Таймер завершен");
//            }
//        });


        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    //onDestroy();
                    Intent intent = new Intent(Menu.this, Game.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e){
                    e.getStackTrace();
                }
            }
        });

        btn_prop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    //showPopup(v);
                    if(counter == 0) {
                        linearLayout.setVisibility(View.VISIBLE);
                        counter = 1;
                    }
                    else{
                        linearLayout.setVisibility(View.INVISIBLE);
                        counter = 0;
                    }
                }
                catch (Exception e){
                    e.getStackTrace();
                }
            }
        });
    }

//    public void showPopup(View v){
//        PopupMenu popupMenu = new PopupMenu(this, v);
//        popupMenu.setOnMenuItemClickListener(this);
//        popupMenu.inflate(R.menu.poupup_menu);
//        popupMenu.show();
//        getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sound:
                Toast.makeText(this,"sound", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.vibration:
                Toast.makeText(this,"vibration", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}
