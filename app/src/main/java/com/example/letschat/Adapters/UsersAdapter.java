package com.example.letschat.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letschat.ChatDetail;
import com.example.letschat.Models.Users;
import com.example.letschat.R;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    List<Users> list;
    Context context;
    FirebaseAuth firebaseAuth;
    String uid;

    public UsersAdapter(Context context, List<Users> list) {
        this.list = list;
        this.context = context;
        firebaseAuth = FirebaseAuth.getInstance();
        uid = firebaseAuth.getUid();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Users users = list.get(position);
        Picasso.get().load(users.getProfilepic()).placeholder(R.drawable.account__1_).into(holder.image);
        holder.userName.setText(users.getUserName());

        final String hisuid = list.get(position).getUserid();
        String userimage = list.get(position).getProfilepic();
        String username = list.get(position).getUserName();
        String usermail = list.get(position).getMail();
        String lastmsg = list.get(position).getLastMessage();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatDetail.class);
                intent.putExtra("userid", users.getUserid());
                intent.putExtra("profilepic", users.getProfilepic());
                intent.putExtra("userName", users.getUserName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

            return list.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView userName , lastMessage;
        public ViewHolder (@NonNull View itemView) {
            super (itemView);
            image = itemView. findViewById(R.id.profile_image);
            userName = itemView. findViewById(R. id.userNamelist);
            lastMessage = itemView. findViewById(R.id.LastMessage) ;
}}}
