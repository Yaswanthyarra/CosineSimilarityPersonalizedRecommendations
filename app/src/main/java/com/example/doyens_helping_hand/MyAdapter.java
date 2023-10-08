package com.example.doyens_helping_hand;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<User> list;

    public MyAdapter(Context context, ArrayList<User> list,RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.list = list;
        this.recyclerViewInterface=recyclerViewInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v,recyclerViewInterface);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user=list.get(position);

        holder.name.setText(user.getName());
        holder.email.setText(user.getEmail());
        holder.phone.setText(user.getPhone());
        holder.interest.setText(user.getInterest());
        holder.profession.setText(user.getProfession());
        holder.experience.setText(user.getExperience());
        holder.location.setText(user.getLocation());
        holder.service.setText(user.getService());
        holder.rate.setText(user.getRate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,phone,interest,profession,experience,location,service,email,rate;
        public MyViewHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            name=itemView.findViewById(R.id.tvName);
            email=itemView.findViewById(R.id.tvemail);
            phone=itemView.findViewById(R.id.tvPhoneNo);
            interest=itemView.findViewById(R.id.tvinterest);
            profession=itemView.findViewById(R.id.tvprofessionName);
            experience=itemView.findViewById(R.id.tvexperience);
            location=itemView.findViewById(R.id.tvlocationName);
            service=itemView.findViewById(R.id.tvserveName);
            rate=itemView.findViewById(R.id.tvrating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null){
                        int pos=getAdapterPosition();
                        if(pos!=RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

        }
    }

}
