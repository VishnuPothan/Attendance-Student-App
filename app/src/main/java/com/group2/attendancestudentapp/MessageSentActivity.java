package com.group2.attendancestudentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.group2.attendancestudentapp.model.AttendanceSubjectModel;
import com.group2.attendancestudentapp.model.TeacherDetailsModel;

import java.util.List;
import java.util.Objects;


public class MessageSentActivity extends AppCompatActivity {

    TextView subjectNameText, timeText, attendanceText, dateText, teacherNameText;
    TextInputEditText messageTextInput;
    private DatabaseReference mDatabase;
    Button submitBtn;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_sent);
        Objects.requireNonNull(getSupportActionBar()).hide();

        // initialize views
        subjectNameText = findViewById(R.id.subjectNameText);
        timeText = findViewById(R.id.timeText);
        attendanceText = findViewById(R.id.attendanceText);
        messageTextInput = findViewById(R.id.messageTextInput);
        dateText = findViewById(R.id.dateText);
        teacherNameText = findViewById(R.id.teacherNameText);
        progressBar = findViewById(R.id.progressBar);
        submitBtn = findViewById(R.id.submitBtn);

        //Initialize Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();

        String hourDetailsStr = getIntent().getStringExtra("HOUR_DETAILS");
        String dateStr = getIntent().getStringExtra("DATE");

        Gson gson = new Gson();
        AttendanceSubjectModel attendanceSubjectModel = gson.fromJson(hourDetailsStr, AttendanceSubjectModel.class);

        dateText.setText(dateStr);
        subjectNameText.setText(attendanceSubjectModel.getSubject());
        attendanceText.setText(attendanceSubjectModel.getAttendanceMark().equals("P") ? "Present" : "Absent");
        if (attendanceSubjectModel.getAttendanceMark().equals("A")) {
            timeText.setVisibility(View.GONE);
        } else {
            timeText.setText(attendanceSubjectModel.getTime());
        }

        progressBar.setVisibility(View.VISIBLE);
        mDatabase.child("teacher").child(attendanceSubjectModel.getTeacherID()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    TeacherDetailsModel teacherDetailsModel =  snapshot.getValue(TeacherDetailsModel.class);
                    teacherNameText.setText(teacherDetailsModel.getName());
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
            }
        });

    }
}