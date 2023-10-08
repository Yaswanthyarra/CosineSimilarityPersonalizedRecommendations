package com.example.doyens_helping_hand;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ExpandedCardView extends AppCompatActivity {
    private Button btn,btnrate;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expanded_card_view);

        String name=getIntent().getStringExtra("Name");
        String email=getIntent().getStringExtra("Email");
        String phone=getIntent().getStringExtra("Phone");





        TextView nametv=findViewById(R.id.textView7);
        TextView emailtv=findViewById(R.id.textView9);
        TextView phonetv=findViewById(R.id.textView11);

        nametv.setText(name);
        emailtv.setText(email);
        phonetv.setText(phone);

        btn=findViewById(R.id.btn);

        String num=getIntent().getStringExtra("Phone");

        String text="Hello Doyen Guru i want your help!!!";

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean installed=isAppInstalled("com.whatsapp");
                if(installed){
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+num+"&text="+text));
                    startActivity(intent);
                }
                else{
                    Toast.makeText(ExpandedCardView.this, "Whatsapp is not installed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnrate=findViewById(R.id.button9);
        String rate=getIntent().getStringExtra("rate");
        String uid=getIntent().getStringExtra("uid");
        btnrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent (ExpandedCardView.this,Rating.class);
                intent.putExtra("rate",rate);
                intent.putExtra("uid",uid);

                startActivity(intent);

            }
        });







    }
    private boolean isAppInstalled(String s){
        PackageManager packageManager=getPackageManager();
        boolean is_installed;

        try{
            packageManager.getPackageInfo(s,PackageManager.GET_ACTIVITIES);
            is_installed=true;

        }
        catch (PackageManager.NameNotFoundException e){
            is_installed=false;
            e.printStackTrace();
        }
        return is_installed;

    }
}