package com.example.doyens_helping_hand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Domains_youngster extends AppCompatActivity {
    private ImageButton btnacademic,btnit,btnsport,btncareer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domains_youngster);
        btnacademic=findViewById(R.id.imageButton11);

        btnacademic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Domains_youngster.this,young_registration_academic.class);
                startActivity(intent);

            }
        });
        btnit=findViewById(R.id.imageButton13);
        btnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Domains_youngster.this,young_registration_it.class);
                startActivity(intent);
            }
        });

        btnsport=findViewById(R.id.imageButton12);
        btnsport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Domains_youngster.this,young_registration_sport.class);
                startActivity(intent);
            }
        });

        btncareer=findViewById(R.id.imageButton9);
        btncareer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Domains_youngster.this,young_registration_career.class);
                startActivity(intent);
            }
        });

    }
}