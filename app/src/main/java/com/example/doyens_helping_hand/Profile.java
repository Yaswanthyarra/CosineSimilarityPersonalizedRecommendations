package com.example.doyens_helping_hand;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    private Button logout,find;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userid,mail,email;
    private static final String USERS="Youngster_info";
    private TextView nametv,emailtv,phonenotv,interesttv,experiencetv,professiontv,servicetv,locationtv;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        logout= (Button) findViewById(R.id.button3);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Profile.this,MainActivity.class));
            }
        });

        find =(Button) findViewById(R.id.button6);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profile.this,Cosine_Similarity.class));
            }
        });

        user=FirebaseAuth.getInstance().getCurrentUser();
        userid=user.getUid();
        mail=user.getEmail();





        Intent intent=getIntent();
        email = intent.getStringExtra("email");


        DatabaseReference rootRef=FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef=rootRef.child(USERS);
        Log.v("USERID", userRef.getKey());





        nametv=findViewById(R.id.textView7);
        emailtv= findViewById(R.id.textView9);
        phonenotv=findViewById(R.id.textView11);
        interesttv= findViewById(R.id.textView13);
        experiencetv= findViewById(R.id.textView16);
        professiontv=findViewById(R.id.textView17);
        servicetv= findViewById(R.id.textView19);
        locationtv=findViewById(R.id.textView21);

       userRef.addValueEventListener(new ValueEventListener() {

           String email,name,phone,interest,experience,profession,service,location;
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               for (DataSnapshot keyId: snapshot.getChildren()){
                   if(keyId.child("email").getValue().equals(mail)){
                       email=keyId.child("email").getValue(String.class);
                       name=keyId.child("name").getValue(String.class);
                       phone=keyId.child("phone").getValue(String.class);
                       interest=keyId.child("interest").getValue(String.class);
                       experience=keyId.child("experience").getValue(String.class);
                       profession=keyId.child("profession").getValue(String.class);
                       service=keyId.child("service").getValue(String.class);
                       location=keyId.child("location").getValue(String.class);
                       break;
                   }
               }
               nametv.setText(name);
               emailtv.setText(mail);
               emailtv.setText(email);
               phonenotv.setText(phone);
               experiencetv.setText(experience);
               professiontv.setText(profession);
               interesttv.setText(interest);
               servicetv.setText(service);
               locationtv.setText(location);

               Toast.makeText(Profile.this,"Your profile retrived!!",Toast.LENGTH_LONG).show();
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {
               Log.w(TAG,"Failed to read Value",error.toException());

               Toast.makeText(Profile.this,"Something Wrong Happened!",Toast.LENGTH_LONG).show();
           }
       });

    }
}