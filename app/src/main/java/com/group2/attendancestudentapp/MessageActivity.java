package com.group2.attendancestudentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.group2.attendancestudentapp.adapter.AttendanceAdapter;
import com.group2.attendancestudentapp.adapter.MessageAdapter;
import com.group2.attendancestudentapp.model.AttendanceDetails;
import com.group2.attendancestudentapp.model.HourDetails;
import com.group2.attendancestudentapp.model.MessageDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MessageActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    RecyclerView requestsRecyclerView;
    MessageAdapter messageAdapter;
    List<MessageDetails> messageDetailsList;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Objects.requireNonNull(getSupportActionBar()).hide();

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

        // TODO data filling
        DummyDataFill();

        // setting up the recycler view
        messageAdapter = new MessageAdapter(messageDetailsList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        requestsRecyclerView.setLayoutManager(layoutManager);
        requestsRecyclerView.setAdapter(messageAdapter);
    }

    private void DummyDataFill() {
        messageDetailsList = new ArrayList<>();

        messageDetailsList.add(new MessageDetails("RE01", "20-03-2021", "Pending", "Sir I was present this hour but it is marked as not present.", "Programing Paradigms", "4", "TE02","ST04"));

        messageDetailsList.add(new MessageDetails("RE01", "20-03-2021", "Pending", "Sir I was present this hour but it is marked as not present.", "Programing Paradigms", "4", "TE02","ST04"));

        messageDetailsList.add(new MessageDetails("RE01", "20-03-2021", "Pending", "Sir I was present this hour but it is marked as not present.", "Programing Paradigms", "4", "TE02","ST04"));

    }
}