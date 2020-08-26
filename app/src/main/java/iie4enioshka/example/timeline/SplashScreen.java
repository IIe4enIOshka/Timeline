package iie4enioshka.example.timeline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;

public class SplashScreen extends AppCompatActivity {
    public static ArrayList<Cards> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        list = new ArrayList<>();
        list.add(new Cards(0, 1492, R.drawable.card_1_1, R.drawable.card_1_2));
        list.add(new Cards(1, -4236, R.drawable.card_2_1, R.drawable.card_2_2));
        list.add(new Cards(2, -150, R.drawable.card_3_1, R.drawable.card_3_2));
        list.add(new Cards(3, 1996, R.drawable.card_4_1, R.drawable.card_4_2));
        list.add(new Cards(4, 1910, R.drawable.card_5_1, R.drawable.card_5_2));
        list.add(new Cards(5, -200000, R.drawable.card_6_1, R.drawable.card_6_2));

        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
        finish();
    }
}
