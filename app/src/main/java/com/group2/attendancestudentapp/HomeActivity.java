package com.group2.attendancestudentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.group2.attendancestudentapp.SharedPreference.SharedPreference;
import com.group2.attendancestudentapp.adapter.AttendanceAdapter;
import com.group2.attendancestudentapp.model.AttendanceDateModel;
import com.group2.attendancestudentapp.model.AttendanceStudentModel;
import com.group2.attendancestudentapp.model.AttendanceSubjectModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    AutoCompleteTextView editTextFilledExposedDropdown;
    LinearLayout viewByDateLayout;
    RecyclerView attendanceRecycler;
    AttendanceAdapter attendanceAdapter;
    List<AttendanceDateModel> attendanceDateModelList;
    private DatabaseReference mDatabase;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Objects.requireNonNull(getSupportActionBar()).hide();

        //Initialize Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Bottom Navigation SetUp
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            switch (itemId) {
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.profile:
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.message:
                    startActivity(new Intent(getApplicationContext(), MessageActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            finish();
            return false;
        });

        // Attendance View Spinner initialize
        String[] attendanceViewType = new String[]{"By Percentage", "By Date", "By Subject"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dropdown_menu_popup_item, attendanceViewType);
        editTextFilledExposedDropdown = findViewById(R.id.attendanceViewText);
        editTextFilledExposedDropdown.setAdapter(adapter);

        findViewById(R.id.applyBtn).setOnClickListener(view -> ShowAttendanceByDate());

        // TODO Badge code for icon
        /*BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.home);
        badgeDrawable.isVisible();
        badgeDrawable.setNumber(5);*/
    }

    private void ShowAttendanceByDate() {
        // making layout visible
        viewByDateLayout = findViewById(R.id.viewByDateLayout);
        viewByDateLayout.setVisibility(View.VISIBLE);

        //initialize
        attendanceRecycler = findViewById(R.id.attendanceRecyclerView);

        GetAttendanceData();

    }

    private void GetAttendanceData() {
        attendanceDateModelList = new ArrayList<>();

        mDatabase.child("attendance").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String dateStr = ds.getKey();

                    HashMap<String, Objects> hMap = (HashMap<String, Objects>) snapshot.getValue();
                    assert hMap != null;
                    Object pair = hMap.get(dateStr);
                    HashMap<String, Objects> subjectMap = (HashMap<String, Objects>) pair;

                    List<AttendanceSubjectModel> attendanceSubjectModelList = new ArrayList<>();
                    assert subjectMap != null;
                    for (Map.Entry mapElement : subjectMap.entrySet()) {
                        String uniqueKeyStr = (String) mapElement.getKey();

                        Gson gson = new Gson();
                        String json = gson.toJson(mapElement.getValue());

                        Type collectionType = new TypeToken<List<AttendanceStudentModel>>() {
                        }.getType();
                        List<AttendanceStudentModel> attendanceStudentModelList = gson.fromJson(json, collectionType);

                        // filter for this user
                        AttendanceStudentModel attendanceStudentModelUser = new AttendanceStudentModel();
                        for (int i = 0; i < attendanceStudentModelList.size(); i++) {
                            if (attendanceStudentModelList.get(i).getID().equals(SharedPreference.getUserID(getApplicationContext()))) {
                                Log.i("here json ", attendanceStudentModelList.get(i).getID());
                                attendanceStudentModelUser = attendanceStudentModelList.get(i);
                            }
                        }
                        AttendanceSubjectModel attendanceSubjectModel = new AttendanceSubjectModel(uniqueKeyStr, attendanceStudentModelUser.getID(), attendanceStudentModelUser.getName(), attendanceStudentModelUser.getSubject(), attendanceStudentModelUser.getTime(), attendanceStudentModelUser.getTeacherID(), attendanceStudentModelUser.getAttendanceMark());
                        attendanceSubjectModelList.add(attendanceSubjectModel);
                    }
                    if (attendanceSubjectModelList.size() != 0) {
                        AttendanceDateModel attendanceDateModel = new AttendanceDateModel(dateStr, attendanceSubjectModelList);
                        attendanceDateModelList.add(attendanceDateModel);
                    }
                }

                // setting up the recycler view
                attendanceAdapter = new AttendanceAdapter(attendanceDateModelList);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                attendanceRecycler.setLayoutManager(layoutManager);
                attendanceRecycler.setAdapter(attendanceAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("here Attendance", String.valueOf(error));
            }
        });
    }
}