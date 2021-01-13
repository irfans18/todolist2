package com.irfans.todolist2.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.irfans.todolist2.R;
import com.irfans.todolist2.data.model.Task;

public class RecyclerViewAdapterTodolist extends RecyclerView.Adapter<RecyclerViewAdapterTodolist.MyViewHolder> {
    private static List<Task> mDataset;
    private static MyClickListener myClickListener;

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvDate;
        TextView tvTitle;
        TextView tvDescription;
        CheckBox cbItem;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.taskTitle_tv);
            tvDescription = (TextView) itemView.findViewById(R.id.taskDesc_tv);
            tvDate = itemView.findViewById(R.id.taskDate_tv);
            cbItem = itemView.findViewById(R.id.item_check);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position =getAdapterPosition();
            myClickListener.onItemClick(position, view);
        }
    }

    public RecyclerViewAdapterTodolist(List<Task> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvTitle.setText(mDataset.get(position).getTitle());
        holder.tvDescription.setText(mDataset.get(position).getDescription());
        holder.tvDate.setText(mDataset.get(position).getDeadline());

        int check = mDataset.get(position).isChecked();
        if (check == 1){
            holder.cbItem.setChecked(true);
        }else {
            holder.cbItem.setChecked(false);
        }

        if (holder.cbItem.isChecked()){
            mDataset.get(position).setChecked(1);
        }else {
            mDataset.get(position).setChecked(0);
        }

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

}