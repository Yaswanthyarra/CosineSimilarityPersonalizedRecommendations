package com.example.doyens_helping_hand;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class young_registration_it extends AppCompatActivity {
    private EditText inputEmail,inputPassword,inputName,inputPhoneNo;
    Button btnRegister;


    ProgressDialog progressDialog;
    private Spinner spinner_field_of_interest,spinner_experience,spinner_Retired_from_profession,spinner_serve,spinner_location;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private  FirebaseDatabase db=FirebaseDatabase.getInstance();
    private  DatabaseReference root=db.getReference().child("Youngster_info");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_young_registration_it);

        //spinner objects feild of interest
        spinner_field_of_interest=findViewById(R.id.editTextTextPersonName6);
        List<String> categories = new ArrayList<>();
        categories.add(0,"Choose your sub stream:");
        categories.add("Application Developer");
        categories.add("Front-end Developer");
        categories.add("Back-end Developer");
        categories.add("Data Scientist");
        categories.add("Game Developer");
        categories.add("Quality Analyst");
        categories.add("Mobile Test Engineer");
        categories.add("DevOps Engineer");
        categories.add("Cloud Engineer");

        //style spinner
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, R.layout.my_selected_item,categories);

        //drop dowm layout  tyle
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //attatching data adapter to spinner
        spinner_field_of_interest.setAdapter(dataAdapter);
        spinner_field_of_interest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Choose Category")){
                    //do nothing
                }
                else{

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //spinner expereincece
        spinner_experience=findViewById(R.id.editTextTextPersonName5);
        List<String> categoriesE = new ArrayList<>();
        categoriesE.add(0,"Expected duration of help:");
        categoriesE.add("daily");
        categoriesE.add("Once a week");
        categoriesE.add("once a month");
        categoriesE.add("whenever required");

        //style spinner
        ArrayAdapter<String> dataAdapterE;
        dataAdapterE = new ArrayAdapter(this, R.layout.my_selected_item,categoriesE);

        //drop dowm layout  tyle
        dataAdapterE.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //attatching data adapter to spinner
        spinner_experience.setAdapter(dataAdapterE);
        spinner_experience.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Experience")){
                    //do nothing
                }
                else{

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //spinner Retired from profession
        spinner_Retired_from_profession =findViewById(R.id.editTextTextPersonName4);
        List<String> categoriesP = new ArrayList<>();
        categoriesP.add(0,"Minimum fees willing to Pay:");
        categoriesP.add("below 1000 per month");
        categoriesP.add("1000+ per month");
        categoriesP.add("10k+ per month");
        categoriesP.add("Don't want to disclose");


        //style spinner
        ArrayAdapter<String> dataAdapterP;
        dataAdapterP = new ArrayAdapter(this, R.layout.my_selected_item,categoriesP);

        //drop dowm layout  tyle
        dataAdapterP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //attatching data adapter to spinner
        spinner_Retired_from_profession.setAdapter(dataAdapterP);
        spinner_Retired_from_profession.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Retired from profession")){
                    //do nothing
                }
                else{

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //spinner serve
        spinner_serve =findViewById(R.id.editTextTextPersonName8);
        List<String> categoriesS = new ArrayList<>();
        categoriesS.add(0,"Kind of service expected");
        categoriesS.add("Offline");
        categoriesS.add("Online");


        //style spinner
        ArrayAdapter<String> dataAdapterS;
        dataAdapterS = new ArrayAdapter(this, R.layout.my_selected_item,categoriesS);

        //drop dowm layout  tyle
        dataAdapterS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //attatching data adapter to spinner
        spinner_serve.setAdapter(dataAdapterS);
        spinner_serve.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Retired from profession")){
                    //do nothing
                }
                else{

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //spinner location
        spinner_location=findViewById(R.id.editTextTextPersonName7);
        List<String> categoriesL = new ArrayList<>();
        categoriesL.add(0,"Choose nearby Location");
        categoriesL.add("Anantapur");
        categoriesL.add("Guntur");
        categoriesL.add("Rajahmundry");
        categoriesL.add("Tirupati");
        categoriesL.add("Vijayawada");
        categoriesL.add("Visakhapatnam");


        //style spinner
        ArrayAdapter<String> dataAdapterL;
        dataAdapterL = new ArrayAdapter(this, R.layout.my_selected_item,categoriesL);

        //drop dowm layout  tyle
        dataAdapterL.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //attatching data adapter to spinner
        spinner_location.setAdapter(dataAdapterL);
        spinner_location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Choose Category")){
                    //do nothing
                }
                else{

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        TextView btn=findViewById(R.id.textView3);


        inputEmail=findViewById(R.id.editTextTextPersonName2);
        inputPassword=findViewById(R.id.editTextTextPersonName9);
        inputName=findViewById(R.id.editTextTextPersonName);
        inputPhoneNo=findViewById(R.id.editTextTextPersonName3);


        btnRegister=findViewById(R.id.button);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser= mAuth.getCurrentUser();




        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(young_registration_it.this,youngster_login_page.class));
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
        String interest=spinner_field_of_interest.getSelectedItem().toString();
        String profession=spinner_Retired_from_profession.getSelectedItem().toString();
        String experience=spinner_experience.getSelectedItem().toString();
        String location=spinner_location.getSelectedItem().toString();
        String service=spinner_serve.getSelectedItem().toString();

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
            ((TextView)spinner_field_of_interest.getSelectedView()).setError("Error message");
            return;
        }
        if(profession.isEmpty()){
            ((TextView)spinner_Retired_from_profession.getSelectedView()).setError("Error message");
            return;
        }
        if(experience.isEmpty()){
            ((TextView)spinner_experience.getSelectedView()).setError("Error message");
            return;
        }
        if(location.isEmpty()){
            ((TextView)spinner_location.getSelectedView()).setError("Error message");
            return;
        }
        if(service.isEmpty()){
            ((TextView)spinner_serve.getSelectedView()).setError("Error message");
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
                        Toast.makeText(young_registration_it.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        //Intent intent=new Intent(registration_page.this,retired_display_page.class);
                        // startActivity(intent);

                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(young_registration_it.this, "Registration Failed"+task.getException(), Toast.LENGTH_SHORT).show();
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
        map.put("interest",spinner_field_of_interest.getSelectedItem().toString());
        map.put("profession",spinner_Retired_from_profession.getSelectedItem().toString());
        map.put("experience",spinner_experience.getSelectedItem().toString());
        map.put("location",spinner_location.getSelectedItem().toString());
        map.put("service",spinner_serve.getSelectedItem().toString());
        map.put("rate","5");
        map.put("domain","it");
        map.put("uid",FirebaseAuth.getInstance().getCurrentUser().getUid());
        //root.push().setValue(map);
        root.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(map);
    }
    private void sendUserToNextActivity() {
        Intent intent=new Intent(young_registration_it.this,youngster_login_page.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}