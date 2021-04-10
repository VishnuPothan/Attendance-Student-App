package com.group2.attendancestudentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.group2.attendancestudentapp.adapter.AttendanceAdapter;
import com.group2.attendancestudentapp.model.AttendanceDetails;
import com.group2.attendancestudentapp.model.HourDetails;

import java.util.ArrayList;
import java.util.List;

public class AttendanceTableViewActivity extends AppCompatActivity {

    RecyclerView attendanceRecycler;
    AttendanceAdapter attendanceAdapter;
    List<AttendanceDetails> attendanceDetailsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_table_view);

        //initialize
        attendanceRecycler = findViewById(R.id.attendanceRecyclerView);

        // TODO data filling
        DummyDataFill();

        // setting up the recycler view
        attendanceAdapter = new AttendanceAdapter(attendanceDetailsList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        attendanceRecycler.setLayoutManager(layoutManager);
        attendanceRecycler.setAdapter(attendanceAdapter);
    }

    private void DummyDataFill() {
        attendanceDetailsList = new ArrayList<>();
        List<HourDetails> hourDetailsArrayList = new ArrayList<>();
        hourDetailsArrayList.add(new HourDetails("1", "Sumit Narendar", "Computer Graphics", "11:50:12", true));
        hourDetailsArrayList.add(new HourDetails("2", "Sumit Narendar", "Computer Graphics", "11:50:12", false));
        hourDetailsArrayList.add(new HourDetails("3", "Sumit Narendar", "Computer Graphics", "11:50:12", true));
        hourDetailsArrayList.add(new HourDetails("4", "Sumit Narendar", "Computer Graphics", "11:50:12", true));
        hourDetailsArrayList.add(new HourDetails("5", "Sumit Narendar", "Computer Graphics", "11:50:12", false));
        hourDetailsArrayList.add(new HourDetails("6", "Sumit Narendar", "Computer Graphics", "11:50:12", true));
        hourDetailsArrayList.add(new HourDetails("7", "Sumit Narendar", "Computer Graphics", "11:50:12", true));

        attendanceDetailsList.add(new AttendanceDetails("20-30-2021", hourDetailsArrayList));
        attendanceDetailsList.add(new AttendanceDetails("21-30-2021", hourDetailsArrayList));
        attendanceDetailsList.add(new AttendanceDetails("22-30-2021", hourDetailsArrayList));
        attendanceDetailsList.add(new AttendanceDetails("23-30-2021", hourDetailsArrayList));
        attendanceDetailsList.add(new AttendanceDetails("24-30-2021", hourDetailsArrayList));
        attendanceDetailsList.add(new AttendanceDetails("25-30-2021", hourDetailsArrayList));
    }
}