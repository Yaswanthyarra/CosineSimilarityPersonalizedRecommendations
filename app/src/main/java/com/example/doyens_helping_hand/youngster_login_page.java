package com.example.doyens_helping_hand;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class youngster_login_page extends AppCompatActivity {
    private TextView btn,forgotPassword;
    Button btnRegister;
    String email,password;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private EditText pbEmail,pbPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youngster_login_page);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        pbEmail = findViewById(R.id.editTextTextPersonName);
        pbPwd = findViewById(R.id.editTextTextPersonName2);

        btn = findViewById(R.id.signUp);
        btnRegister=findViewById(R.id.button2);

        forgotPassword= (TextView) findViewById(R.id.textView);



        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(youngster_login_page.this,Domains_youngster.class));
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = pbEmail.getText().toString();
                password = pbPwd.getText().toString();
                if(TextUtils.isEmpty((email))){
                    pbEmail.setError("Please enter your email id");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    pbPwd.setError("Please enter valid Password");
                    return;
                }
                SignIn(email,password);


            }
        });



        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(youngster_login_page.this,ForgotPassword.class));
            }
        });
    }







    private void SignIn(String email, String password){
        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(youngster_login_page.this,"Login Success",Toast.LENGTH_SHORT).show();//THis msg came aa while loging??
                            startActivity(new Intent(youngster_login_page.this,Profile.class));
                            finish();

                        }
                        else
                        {
                            Toast.makeText(youngster_login_page.this,"Login Failed "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }

                    }


                });
    }

}