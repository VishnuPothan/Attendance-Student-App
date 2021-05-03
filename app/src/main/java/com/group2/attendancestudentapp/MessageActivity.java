package com.group2.attendancestudentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.group2.attendancestudentapp.SharedPreference.SharedPreference;
import com.group2.attendancestudentapp.adapter.MessageAdapter;
import com.group2.attendancestudentapp.model.RequestDetailsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MessageActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    RecyclerView requestsRecyclerView;
    MessageAdapter messageAdapter;
    private DatabaseReference mDatabase;
    List<RequestDetailsModel> requestDetailsModelList;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Objects.requireNonNull(getSupportActionBar()).hide();

        //Initialize Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Bottom Navigation SetUp
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.message);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            switch (itemId) {
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.profile:
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.message:
                    startActivity(new Intent(getApplicationContext(), MessageActivity.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            finish();
            return false;
        });

        ShowAttendanceByDate();
    }

    private void ShowAttendanceByDate() {
        //initialize
        requestsRecyclerView = findViewById(R.id.requestsRecyclerView);

        // data fetch
        GetData();
    }

    private void GetData() {
        requestDetailsModelList = new ArrayList<>();

        mDatabase.child("request").orderByChild("studentID").equalTo(SharedPreference.getUserID(getApplicationContext())).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    RequestDetailsModel requestDetailsModel = ds.getValue(RequestDetailsModel.class);
                    requestDetailsModelList.add(requestDetailsModel);
                }
                // setting up the recycler view
                messageAdapter = new MessageAdapter(requestDetailsModelList);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                requestsRecyclerView.setLayoutManager(layoutManager);
                requestsRecyclerView.setAdapter(messageAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void ShowRequestDetails(int position) {
        Intent intent = new Intent(getApplicationContext(), MessageSentActivity.class);/*
        intent.putExtra("HOUR_DETAILS", hourDetailsStr);
        intent.putExtra("DATE", attendanceDateModelList.get(position).getDateStr());*/
        startActivity(intent);
    }
}