package com.example.doyens_helping_hand;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class cosine_similarity_doyen extends AppCompatActivity implements RecyclerViewInterface{

    RecyclerView recyclerView;

    MyAdapter myAdapter;
    ArrayList<User> list;
    DatabaseReference database;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userid, mail, email;
    private String[] young = new String[8];
    private String[] retired = new String[8];
    private double[] retired_rating=new double[10];
    private String domain;
    private double rank[]= new double[10];
    private int ind;


    private List <DataSnapshot> retired_keys = new ArrayList<DataSnapshot>();






    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cosine_similarity_doyen);

        recyclerView= findViewById(R.id.userList);
        database= FirebaseDatabase.getInstance().getReference("Youngster_info");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        list=new ArrayList<>();

        myAdapter = new MyAdapter(this,list,this);
        recyclerView.setAdapter(myAdapter);

        user = FirebaseAuth.getInstance().getCurrentUser();
        userid = user.getUid();
        mail = user.getEmail();

        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        DatabaseReference rootRefYoung = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRefYoung = rootRefYoung.child("RetiredPeople_info");
        Log.v("USERID", userRefYoung.getKey());



        userRefYoung.addValueEventListener(new ValueEventListener() {


            String email, name, phone, interest, experience, profession, service, location;

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot keyId : snapshot.getChildren()) {
                    if (keyId.child("email").getValue().equals(mail)) {
                        young[0] = keyId.child("email").getValue(String.class);
                        young[1] = keyId.child("name").getValue(String.class);
                        young[2] = keyId.child("phone").getValue(String.class);
                        young[3] = keyId.child("interest").getValue(String.class);
                        young[4] = keyId.child("experience").getValue(String.class);
                        young[5] = keyId.child("profession").getValue(String.class);
                        young[6] = keyId.child("service").getValue(String.class);
                        young[7] = keyId.child("location").getValue(String.class);
                        domain=keyId.child("domain").getValue(String.class);
                        break;
                    }

                }
                myAdapter.notifyDataSetChanged();




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        database.addValueEventListener(new ValueEventListener() {

            String emailR, nameR, phoneR, interestR, experienceR, professionR, serviceR, locationR;
            HashSet<String> set = new HashSet<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ind=0;

                for (DataSnapshot keyId : snapshot.getChildren()) {
                    if (keyId.child("domain").getValue().equals(domain)) {
                    retired[0] = keyId.child("email").getValue(String.class);
                    retired[1] = keyId.child("name").getValue(String.class);
                    retired[2] = keyId.child("phone").getValue(String.class);
                    retired[3] = keyId.child("interest").getValue(String.class);
                    retired[4]= keyId.child("experience").getValue(String.class);
                    retired[5] = keyId.child("profession").getValue(String.class);
                    retired[6] = keyId.child("service").getValue(String.class);
                    retired[7] = keyId.child("location").getValue(String.class);

                    retired_keys.add(keyId);









                    set.addAll(Arrays.asList(young));
                    set.addAll(Arrays.asList(retired));
                    String[] str = new String[set.size()];
                    int k=0;
                    int i;

                    for (String ii: set) {
                        str[k++] = ii;

                    }
                    int s1Cos[]= new int[set.size()];
                    int s2Cos[]= new int[set.size()];
                    for(i=0;i<set.size();i++){
                        s1Cos[i]=0;
                        s2Cos[i]=0;
                    }
                    for(int j=0;j<str.length;j++){
                        for(i=0;i<young.length;i++){

                            if(str[j].equals(young[i])){
                                s1Cos[j]=1;
                            }
                        }

                        for(i=0;i<retired.length;i++){

                            if(str[j].equals(retired[i])){
                                s2Cos[j]=1;
                            }
                        }
                    }
                    int c=0,s1Sum=0,s2Sum=0;

                    //cosine formula
                    for(i=0;i<s1Cos.length;i++){
                        c+=s1Cos[i]*s2Cos[i];
                        s1Sum+=s1Cos[i];
                        s2Sum+=s2Cos[i];

                    }

                    double cosine=c/(Math.pow((s1Sum*s2Sum),0.5));
                    rank[ind]=cosine;
                    retired_rating[ind]=Double.parseDouble(keyId.child("rate").getValue(String.class));
                    ind++;

                }}

                for(int i = 0; i < ind; i++) {
                    double tempValue;
                    DataSnapshot tempId;
                    for (int j = i + 1; j < ind; j++) {



                        if ((rank[i]/5)*retired_rating[i] < (rank[j]/5)*retired_rating[j]) {
                            tempValue = rank[i];
                            rank[i] = rank[j];
                            rank[j] = tempValue;
                            tempValue = retired_rating[i];
                            retired_rating[i] = retired_rating[j];
                            retired_rating[j] = tempValue;
                            tempId=retired_keys.get(i);
                            retired_keys.set(i,retired_keys.get(j));
                            retired_keys.set(j,tempId);
                        }
                    }
                }

                for(DataSnapshot dataSnapshot : retired_keys){
                    User user= dataSnapshot.getValue(User.class);
                    list.add(user);
                }
                myAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public void onItemClick(int position) {
        Intent intent= new Intent (cosine_similarity_doyen.this,expanded_card_view_for_retired.class);
        intent.putExtra("Name",list.get(position).getName());
        intent.putExtra("Email",list.get(position).getEmail());
        intent.putExtra("Phone",list.get(position).getPhone());
        intent.putExtra("rate",list.get(position).getRate());
        intent.putExtra("uid",list.get(position).getUid());
        startActivity(intent);
    }
}
