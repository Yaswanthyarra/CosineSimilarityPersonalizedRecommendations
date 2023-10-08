package com.example.doyens_helping_hand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class youngster_registration_page extends AppCompatActivity {
    private EditText inputEmail,inputPassword,inputName,inputPhoneNo,inputInterest,inputProfession,inputExperience,inputLocation,inputService;
    Button btnRegister;

    ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private  FirebaseDatabase db=FirebaseDatabase.getInstance();
    private  DatabaseReference root=db.getReference().child("Youngster_info");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        TextView btn=findViewById(R.id.textView3);


        inputEmail=findViewById(R.id.editTextTextPersonName2);
        inputPassword=findViewById(R.id.editTextTextPersonName9);
        inputName=findViewById(R.id.editTextTextPersonName);
        inputPhoneNo=findViewById(R.id.editTextTextPersonName3);
        inputInterest=findViewById(R.id.editTextTextPersonName6);
        inputProfession=findViewById(R.id.editTextTextPersonName4);
        inputExperience=findViewById(R.id.editTextTextPersonName5);
        inputLocation=findViewById(R.id.editTextTextPersonName7);
        inputService=findViewById(R.id.editTextTextPersonName8);

        btnRegister=findViewById(R.id.button);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser= mAuth.getCurrentUser();




        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(youngster_registration_page.this,doyen_login_page.class));
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerforAuth();

            }
        });
    }
    private void PerforAuth(){

        String email=inputEmail.getText().toString();

        String password=inputPassword.getText().toString();
        String name=inputName.getText().toString();
        String phone=inputPhoneNo.getText().toString();
        String interest=inputInterest.getText().toString();
        String profession=inputProfession.getText().toString();
        String experience=inputExperience.getText().toString();
        String location=inputLocation.getText().toString();
        String service=inputService.getText().toString();

        if(name.isEmpty()){
            inputName.setError("Name Needed");
            return;
        }
        if(email.isEmpty()){
            inputEmail.setError("Enter ID");
            return;
        }
        if(password.isEmpty()){
            inputPassword.setError("Please Enter password");
            return;
        }
        if(phone.isEmpty()){
            inputPhoneNo.setError("Number Needed");
            return;
        }
        if(interest.isEmpty()){
            inputInterest.setError("Enter Interest");
            return;
        }
        if(profession.isEmpty()){
            inputProfession.setError("Enter Profession");
            return;
        }
        if(experience.isEmpty()){
            inputExperience.setError("Enter Experience");
            return;
        }
        if(location.isEmpty()){
            inputLocation.setError("Please Enter location");
            return;
        }
        if(service.isEmpty()){
            inputPhoneNo.setError("Please enter mode of Service");
            return;

        }
        else{
            progressDialog.setMessage("Please wait while Registration");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        updateUi(mUser,email);
                        Toast.makeText(youngster_registration_page.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                       // Intent intent=new Intent(youngster_registration_page.this,youngster_display_page.class);
                        //startActivity(intent);

                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(youngster_registration_page.this, "Registration Failed"+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }


    }

    private void updateUi(FirebaseUser mUser, String email) {
        HashMap<String,Object> map =new HashMap<>();
        map.put("name",inputName.getText().toString());
        map.put("email",inputEmail.getText().toString());
        map.put("phone",inputPhoneNo.getText().toString());
        map.put("interest",inputInterest.getText().toString());
        map.put("profession",inputProfession.getText().toString());
        map.put("experience",inputExperience.getText().toString());
        map.put("location",inputLocation.getText().toString());
        map.put("service",inputService.getText().toString());
        root.push().setValue(map);
    }
    private void sendUserToNextActivity() {
        Intent intent=new Intent(youngster_registration_page.this,youngster_display_page.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}