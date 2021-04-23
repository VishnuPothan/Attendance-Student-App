package com.group2.attendancestudentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.group2.attendancestudentapp.adapter.AttendanceAdapter;
import com.group2.attendancestudentapp.model.AttendanceDetails;
import com.group2.attendancestudentapp.model.HourDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    AutoCompleteTextView editTextFilledExposedDropdown;
    LinearLayout viewByDateLayout;
    RecyclerView attendanceRecycler;
    AttendanceAdapter attendanceAdapter;
    List<AttendanceDetails> attendanceDetailsList;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Objects.requireNonNull(getSupportActionBar()).hide();

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

        // findViewById(R.id.applyBtn).setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), AttendanceTableViewActivity.class)));

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

        viewByDateLayout.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = viewByDateLayout.getScrollY(); // For ScrollView
                int scrollX = viewByDateLayout.getScrollX(); // For HorizontalScrollView
                // DO SOMETHING WITH THE SCROLL COORDINATES
                Log.d("ScrollView","scrollX_"+scrollX+"_scrollY_"+scrollY+"_oldScrollX_");
            }
        });

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
        attendanceDetailsList.add(new AttendanceDetails("20-30-2021", hourDetailsArrayList));
        attendanceDetailsList.add(new AttendanceDetails("21-30-2021", hourDetailsArrayList));
        attendanceDetailsList.add(new AttendanceDetails("22-30-2021", hourDetailsArrayList));
        attendanceDetailsList.add(new AttendanceDetails("23-30-2021", hourDetailsArrayList));
        attendanceDetailsList.add(new AttendanceDetails("24-30-2021", hourDetailsArrayList));
        attendanceDetailsList.add(new AttendanceDetails("25-30-2021", hourDetailsArrayList));
        attendanceDetailsList.add(new AttendanceDetails("20-30-2021", hourDetailsArrayList));
        attendanceDetailsList.add(new AttendanceDetails("21-30-2021", hourDetailsArrayList));
        attendanceDetailsList.add(new AttendanceDetails("22-30-2021", hourDetailsArrayList));
        attendanceDetailsList.add(new AttendanceDetails("23-30-2021", hourDetailsArrayList));
        attendanceDetailsList.add(new AttendanceDetails("24-30-2021", hourDetailsArrayList));
        attendanceDetailsList.add(new AttendanceDetails("25-30-2021", hourDetailsArrayList));
        attendanceDetailsList.add(new AttendanceDetails("20-30-2021", hourDetailsArrayList));
        attendanceDetailsList.add(new AttendanceDetails("21-30-2021", hourDetailsArrayList));
        attendanceDetailsList.add(new AttendanceDetails("22-30-2021", hourDetailsArrayList));
        attendanceDetailsList.add(new AttendanceDetails("23-30-2021", hourDetailsArrayList));
        attendanceDetailsList.add(new AttendanceDetails("24-30-2021", hourDetailsArrayList));
        attendanceDetailsList.add(new AttendanceDetails("25-30-2021", hourDetailsArrayList));
    }
}