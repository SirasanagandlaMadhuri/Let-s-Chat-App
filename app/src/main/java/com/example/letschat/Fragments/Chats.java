package com.example.letschat.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.letschat.Adapters.UsersAdapter;
import com.example.letschat.Models.Users;
import com.example.letschat.R;
import com.example.letschat.databinding.FragmentChatsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Chats extends Fragment{
    public Chats() {
    }
    FragmentChatsBinding binding;
    ArrayList<Users> list = new ArrayList<>() ;
    FirebaseDatabase database;
    DatabaseReference chatsRef;
    FirebaseAuth firebaseAuth;
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {

        binding = FragmentChatsBinding.inflate(inflater, container,false);


        firebaseAuth=FirebaseAuth.getInstance();
        RecyclerView r=binding.recyclerview;

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        r.setLayoutManager(layoutManager);

        UsersAdapter adapter =new UsersAdapter(getContext(), list);
        binding.recyclerview.setAdapter(adapter);

        database=FirebaseDatabase.getInstance();
        chatsRef = database.getReference("Chats");
        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {

            private String generateChatId(String senderId, String receiverId) {
                return senderId + "_" + receiverId;
            }
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Users users = dataSnapshot.getValue(Users.class);
                    if (users != null) {
                        list.add(users);
                        if (currentUser != null) {
                            String chatId = generateChatId(currentUser.getUid(), users.getUserid());
                            DatabaseReference userChatRef = chatsRef.child(chatId);
                            userChatRef.child("senderId").setValue(currentUser.getUid());
                            userChatRef.child("receiverId").setValue(users.getUserid());
                            userChatRef.child("receiverName").setValue(users.getUserName());
                            userChatRef.child("receiverImage").setValue(users.getProfilepic());
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return binding.getRoot();}
}