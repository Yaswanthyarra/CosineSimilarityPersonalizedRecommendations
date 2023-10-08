package com.example.doyens_helping_hand;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Rating_for_youngster extends AppCompatActivity {
    private float k;
    public DatabaseReference def;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_for_youngster);
        Button btn;
        RatingBar r1, r2, r3, r4;
        btn = (Button) findViewById(R.id.Button);
        r1 = (RatingBar) findViewById(R.id.ratingbar1);
        r2 = (RatingBar) findViewById(R.id.ratingbar2);
        r3 = (RatingBar) findViewById(R.id.ratingbar3);
        r4 = (RatingBar) findViewById(R.id.ratingbar4);

        def = FirebaseDatabase.getInstance().getReference("Youngster_info");

        Intent i2 = this.getIntent();
        if (i2 != null) {

            String rate = i2.getStringExtra("rate");
            String uid = i2.getStringExtra("uid");

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int d = Integer.parseInt(rate);
                    k = (r1.getRating() + r2.getRating() + r3.getRating() + r4.getRating()) / 4;
                    k = (k + d) / 2;

                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("rate", Integer.toString(Math.round(k)));

                    def.child(uid).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(Rating_for_youngster.this, "successfully updated thank you for feedback Doyen!" + Math.round(k), Toast.LENGTH_LONG).show();
                            Intent i = new Intent(Rating_for_youngster.this, Profile_Retired.class);
                            startActivity(i);

                        }
                    });

                }
            });
        }
    }
}