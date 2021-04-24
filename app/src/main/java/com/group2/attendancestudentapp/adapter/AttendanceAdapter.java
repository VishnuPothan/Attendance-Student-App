package com.group2.attendancestudentapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group2.attendancestudentapp.R;
import com.group2.attendancestudentapp.model.AttendanceDateModel;

import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.ViewHolder> {
    List<AttendanceDateModel> attendanceDateModelLists;
    Context context;

    public AttendanceAdapter(List<AttendanceDateModel> attendanceDateModelLists) {
        this.attendanceDateModelLists = attendanceDateModelLists;
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
        final AttendanceDateModel attendanceDateModel = attendanceDateModelLists.get(position);

        holder.dayBtn.setText(attendanceDateModel.getDateStr());

        // table set
        holder.hour1Btn.setText(attendanceDateModel.getAttendanceSubjectModelList().get(0).getAttendanceMark());
        holder.hour2Btn.setText(attendanceDateModel.getAttendanceSubjectModelList().get(1).getAttendanceMark());
        holder.hour3Btn.setText(attendanceDateModel.getAttendanceSubjectModelList().get(2).getAttendanceMark());
        holder.hour4Btn.setText(attendanceDateModel.getAttendanceSubjectModelList().get(3).getAttendanceMark());
        holder.hour5Btn.setText(attendanceDateModel.getAttendanceSubjectModelList().get(4).getAttendanceMark());
        holder.hour6Btn.setText(attendanceDateModel.getAttendanceSubjectModelList().get(5).getAttendanceMark());
        holder.hour7Btn.setText(attendanceDateModel.getAttendanceSubjectModelList().get(6).getAttendanceMark());

        holder.hour1Btn.setBackgroundColor(!attendanceDateModel.getAttendanceSubjectModelList().get(0).getAttendanceMark().equals("P") ? context.getResources().getColor(R.color.red) : context.getResources().getColor(R.color.green));
        holder.hour2Btn.setBackgroundColor(!attendanceDateModel.getAttendanceSubjectModelList().get(1).getAttendanceMark().equals("P") ? context.getResources().getColor(R.color.red) : context.getResources().getColor(R.color.green));
        holder.hour3Btn.setBackgroundColor(!attendanceDateModel.getAttendanceSubjectModelList().get(2).getAttendanceMark().equals("P") ? context.getResources().getColor(R.color.red) : context.getResources().getColor(R.color.green));
        holder.hour4Btn.setBackgroundColor(!attendanceDateModel.getAttendanceSubjectModelList().get(3).getAttendanceMark().equals("P") ? context.getResources().getColor(R.color.red) : context.getResources().getColor(R.color.green));
        holder.hour5Btn.setBackgroundColor(!attendanceDateModel.getAttendanceSubjectModelList().get(4).getAttendanceMark().equals("P") ? context.getResources().getColor(R.color.red) : context.getResources().getColor(R.color.green));
        holder.hour6Btn.setBackgroundColor(!attendanceDateModel.getAttendanceSubjectModelList().get(5).getAttendanceMark().equals("P") ? context.getResources().getColor(R.color.red) : context.getResources().getColor(R.color.green));
        holder.hour7Btn.setBackgroundColor(!attendanceDateModel.getAttendanceSubjectModelList().get(6).getAttendanceMark().equals("P") ? context.getResources().getColor(R.color.red) : context.getResources().getColor(R.color.green));
    }

    @Override
    public int getItemCount() {
        return attendanceDateModelLists.size();
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
