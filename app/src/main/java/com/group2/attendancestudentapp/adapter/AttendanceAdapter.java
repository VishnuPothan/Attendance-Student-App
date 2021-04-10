package com.group2.attendancestudentapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group2.attendancestudentapp.R;
import com.group2.attendancestudentapp.model.AttendanceDetails;

import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.ViewHolder> {
    List<AttendanceDetails> attendanceDetailsList;
    Context context;

    public AttendanceAdapter(List<AttendanceDetails> attendanceDetailsList) {
        this.attendanceDetailsList = attendanceDetailsList;
    }

    @NonNull
    @Override
    public AttendanceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendace_table_row, parent, false);
        return new AttendanceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceAdapter.ViewHolder holder, int position) {
        final AttendanceDetails attendanceDetails = attendanceDetailsList.get(position);

        holder.dayBtn.setText(attendanceDetails.getDateStr());

        // table set
        holder.hour1Btn.setText(attendanceDetails.getHourDetailsList().get(0).getPresent() ? "P" : "A");
        holder.hour2Btn.setText(attendanceDetails.getHourDetailsList().get(1).getPresent() ? "P" : "A");
        holder.hour3Btn.setText(attendanceDetails.getHourDetailsList().get(2).getPresent() ? "P" : "A");
        holder.hour4Btn.setText(attendanceDetails.getHourDetailsList().get(3).getPresent() ? "P" : "A");
        holder.hour5Btn.setText(attendanceDetails.getHourDetailsList().get(4).getPresent() ? "P" : "A");
        holder.hour6Btn.setText(attendanceDetails.getHourDetailsList().get(5).getPresent() ? "P" : "A");
        holder.hour7Btn.setText(attendanceDetails.getHourDetailsList().get(6).getPresent() ? "P" : "A");
    }

    @Override
    public int getItemCount() {
        return attendanceDetailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button dayBtn;
        Button hour1Btn, hour2Btn, hour3Btn, hour4Btn, hour5Btn, hour6Btn, hour7Btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayBtn = itemView.findViewById(R.id.dayBtn);
            hour1Btn = itemView.findViewById(R.id.hour1);
            hour2Btn = itemView.findViewById(R.id.hour2);
            hour3Btn = itemView.findViewById(R.id.hour3);
            hour4Btn = itemView.findViewById(R.id.hour4);
            hour5Btn = itemView.findViewById(R.id.hour5);
            hour6Btn = itemView.findViewById(R.id.hour6);
            hour7Btn = itemView.findViewById(R.id.hour7);
        }
    }
}
