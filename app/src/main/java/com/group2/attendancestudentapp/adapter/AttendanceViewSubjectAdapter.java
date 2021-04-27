package com.group2.attendancestudentapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.group2.attendancestudentapp.R;
import com.group2.attendancestudentapp.model.AttendanceSubjectViewModel;

import java.util.List;

public class AttendanceViewSubjectAdapter extends RecyclerView.Adapter<AttendanceViewSubjectAdapter.ViewHolder>{
    List<AttendanceSubjectViewModel> attendanceSubjectViewModelList;
    Context context;
    public AttendanceViewSubjectAdapter(List<AttendanceSubjectViewModel> attendanceSubjectViewModelList) {
        this.attendanceSubjectViewModelList = attendanceSubjectViewModelList;
    }

    @NonNull
    @Override
    public AttendanceViewSubjectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_subject_table_row, parent, false);
        return new AttendanceViewSubjectAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceViewSubjectAdapter.ViewHolder holder, int position) {
        AttendanceSubjectViewModel attendanceSubjectViewModel = attendanceSubjectViewModelList.get(position);
        holder.subjectNameBtn.setText(attendanceSubjectViewModel.getSubjectName());
        holder.attendedHourBtn.setText(String.valueOf(attendanceSubjectViewModel.getAttendedHours()));
        holder.totalHourBtn.setText(String.valueOf(attendanceSubjectViewModel.getTotalHour()));
        holder.percentageBtn.setText(String.format("%.2f", attendanceSubjectViewModel.getPercentage()));

    }

    @Override
    public int getItemCount() {
        return attendanceSubjectViewModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button subjectNameBtn, attendedHourBtn, totalHourBtn, percentageBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectNameBtn = itemView.findViewById(R.id.subjectNameBtn);
            attendedHourBtn = itemView.findViewById(R.id.attendedHourBtn);
            totalHourBtn = itemView.findViewById(R.id.totalHourBtn);
            percentageBtn = itemView.findViewById(R.id.percentageBtn);
        }
    }
}
