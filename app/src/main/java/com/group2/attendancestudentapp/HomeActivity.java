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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.group2.attendancestudentapp.SharedPreference.SharedPreference;
import com.group2.attendancestudentapp.adapter.AttendanceViewDateAdapter;
import com.group2.attendancestudentapp.adapter.AttendanceViewSubjectAdapter;
import com.group2.attendancestudentapp.model.AttendanceDateModel;
import com.group2.attendancestudentapp.model.AttendanceStudentModel;
import com.group2.attendancestudentapp.model.AttendanceSubjectModel;
import com.group2.attendancestudentapp.model.AttendanceSubjectViewModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    AutoCompleteTextView editTextFilledExposedDropdown;
    LinearLayout viewByDateLayout, viewByPercentageLayout, viewBySubjectLayout;
    RecyclerView attendanceRecycler, attendanceSubjectRecyclerView;
    AttendanceViewDateAdapter attendanceViewDateAdapter;
    AttendanceViewSubjectAdapter attendanceViewSubjectAdapter;
    List<AttendanceDateModel> attendanceDateModelList;
    List<AttendanceSubjectViewModel> attendanceSubjectViewModelList;
    private DatabaseReference mDatabase;
    TextView percentageText, totalHourText, attendedHoursText;
    String selectedStr;
    ProgressBar progressBar;
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

        findViewById(R.id.applyBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedStr = String.valueOf(editTextFilledExposedDropdown.getText());
                if (selectedStr.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Select a option!!!", Toast.LENGTH_SHORT).show();
                } else if (selectedStr.equals("By Percentage")) {
                    ShowAttendanceByPercentage();
                } else if (selectedStr.equals("By Date")) {
                    ShowAttendanceByDate();
                } else if (selectedStr.equals("By Subject")) {
                    ShowAttendanceBySubject();
                }
            }
        });

        // TODO Badge code for icon
        /*BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.home);
        badgeDrawable.isVisible();
        badgeDrawable.setNumber(5);*/
    }

    private void ShowAttendanceBySubject() {
        // making layout visible
        viewBySubjectLayout = findViewById(R.id.viewBySubjectLayout);
        viewBySubjectLayout.setVisibility(View.VISIBLE);

        // making layout invisible
        viewByPercentageLayout = findViewById(R.id.viewByPercentageLayout);
        viewByPercentageLayout.setVisibility(View.GONE);

        viewByDateLayout = findViewById(R.id.viewByDateLayout);
        viewByDateLayout.setVisibility(View.GONE);

        // initialize
        attendanceSubjectRecyclerView = findViewById(R.id.attendanceSubjectRecyclerView);

        GetAttendanceData();
    }

    private void ShowAttendanceByDate() {
        // making layout visible
        viewByDateLayout = findViewById(R.id.viewByDateLayout);
        viewByDateLayout.setVisibility(View.VISIBLE);

        // making layout invisible
        viewByPercentageLayout = findViewById(R.id.viewByPercentageLayout);
        viewByPercentageLayout.setVisibility(View.GONE);

        viewBySubjectLayout = findViewById(R.id.viewBySubjectLayout);
        viewBySubjectLayout.setVisibility(View.GONE);

        //initialize
        attendanceRecycler = findViewById(R.id.attendanceRecyclerView);

        GetAttendanceData();
    }

    private void ShowAttendanceByPercentage() {
        // making layout visible
        viewByPercentageLayout = findViewById(R.id.viewByPercentageLayout);
        viewByPercentageLayout.setVisibility(View.VISIBLE);

        // making layout invisible
        viewByDateLayout = findViewById(R.id.viewByDateLayout);
        viewByDateLayout.setVisibility(View.GONE);

        viewBySubjectLayout = findViewById(R.id.viewBySubjectLayout);
        viewBySubjectLayout.setVisibility(View.GONE);

        // initialize views
        attendedHoursText = findViewById(R.id.attendedHoursText);
        percentageText = findViewById(R.id.percentageText);
        totalHourText = findViewById(R.id.totalHourText);

        GetAttendanceData();
    }

    private void GetAttendanceData() {
        // progressBar spin
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

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

                // rest of the view setup
                if (selectedStr.equals("By Date")) {
                    // setting up the view By Date
                    attendanceViewDateAdapter = new AttendanceViewDateAdapter(attendanceDateModelList);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    attendanceRecycler.setLayoutManager(layoutManager);
                    attendanceRecycler.setAdapter(attendanceViewDateAdapter);
                } else if (selectedStr.equals("By Percentage")) {
                    // setting up the view By Percentage
                    int totalHour = 0;
                    int attendedHour = 0;
                    for (int i = 0; i < attendanceDateModelList.size(); i++) {
                        for (int j = 0; j < attendanceDateModelList.get(i).getAttendanceSubjectModelList().size(); j++) {
                            totalHour++;
                            if (attendanceDateModelList.get(0).getAttendanceSubjectModelList().get(j).getAttendanceMark().equals("P")) {
                                attendedHour++;
                            }
                        }
                    }

                    attendedHoursText.setText(String.valueOf(attendedHour));
                    totalHourText.setText(String.valueOf(totalHour));
                    double percentageDouble = ((double) attendedHour / totalHour) * 100;
                    double percentageDoubleRound = (double) Math.round(percentageDouble * 100) / 100;
                    percentageText.setText(String.valueOf(percentageDoubleRound));
                } else if (selectedStr.equals("By Subject")) {
                    // subject data listing
                    List<String> subjectList = new ArrayList<>();
                    attendanceSubjectViewModelList = new ArrayList<>();

                    for (int i = 0; i < attendanceDateModelList.size(); i++) {
                        AttendanceSubjectViewModel attendanceSubjectViewModel = new AttendanceSubjectViewModel();
                        for (int j = 0; j < attendanceDateModelList.get(i).getAttendanceSubjectModelList().size(); j++) {
                            AttendanceSubjectModel attendanceSubjectModel = attendanceDateModelList.get(i).getAttendanceSubjectModelList().get(j);
                            if (!subjectList.contains(attendanceSubjectModel.getSubject())) {
                                subjectList.add(attendanceSubjectModel.getSubject());
                                if (attendanceSubjectModel.getAttendanceMark().equals("P")) {
                                    attendanceSubjectViewModel = new AttendanceSubjectViewModel(attendanceSubjectModel.getSubject(), 1, 1, 100);
                                } else if (attendanceSubjectModel.getAttendanceMark().equals("A") || attendanceSubjectModel.getAttendanceMark().equals("L")) {
                                    attendanceSubjectViewModel = new AttendanceSubjectViewModel(attendanceSubjectModel.getSubject(), 0, 1, 0);
                                }
                                attendanceSubjectViewModelList.add(attendanceSubjectViewModel);
                            } else {
                                for (int k = 0; k < attendanceSubjectViewModelList.size(); k++) {
                                    if(attendanceSubjectViewModelList.get(k).getSubjectName().equals(attendanceSubjectModel.getSubject())){
                                        attendanceSubjectViewModel = attendanceSubjectViewModelList.get(k);
                                        int attendedHour = attendanceSubjectViewModel.getAttendedHours();
                                        int totalHour = attendanceSubjectViewModel.getTotalHour() + 1;
                                        double percentage = attendanceSubjectViewModel.getPercentage();
                                        if (attendanceSubjectModel.getAttendanceMark().equals("P")) {
                                            attendedHour = attendedHour + 1;
                                            percentage =  ((double) attendedHour / totalHour) * 100;
                                            attendanceSubjectViewModelList.get(k).setAttendedHours(attendedHour);
                                            attendanceSubjectViewModelList.get(k).setTotalHour(totalHour);
                                            attendanceSubjectViewModelList.get(k).setPercentage(percentage);
                                        } else if (attendanceSubjectModel.getAttendanceMark().equals("A") || attendanceSubjectModel.getAttendanceMark().equals("L")) {
                                            percentage =  ((double) attendedHour / totalHour) * 100;
                                            attendanceSubjectViewModelList.get(k).setAttendedHours(attendedHour);
                                            attendanceSubjectViewModelList.get(k).setTotalHour(totalHour);
                                            attendanceSubjectViewModelList.get(k).setPercentage(percentage);
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    // setting up the view By Subject
                    attendanceViewSubjectAdapter = new AttendanceViewSubjectAdapter(attendanceSubjectViewModelList);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    attendanceSubjectRecyclerView.setLayoutManager(layoutManager);
                    attendanceSubjectRecyclerView.setAdapter(attendanceViewSubjectAdapter);
                }

                // progressBar hide
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("here Attendance", String.valueOf(error));

                // progressBar hide
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    public void ShowAttendanceByDateDetails(int subjectPosition, int position) {
        Gson gson = new Gson();
        String hourDetailsStr = gson.toJson(attendanceDateModelList.get(position).getAttendanceSubjectModelList().get(subjectPosition));

        Intent intent = new Intent(getBaseContext(), MessageSentActivity.class);
        intent.putExtra("HOUR_DETAILS", hourDetailsStr);
        intent.putExtra("DATE", attendanceDateModelList.get(position).getDateStr());
        startActivity(intent);
    }
}