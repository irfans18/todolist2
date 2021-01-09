package com.irfans.todolist2.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.irfans.todolist2.R;
import com.irfans.todolist2.data.model.Task;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    Context context;
    ArrayList<Task> toDoLists;

    public TaskAdapter(Context context, ArrayList<Task> toDoLists){
        this.context = context;
        this.toDoLists = toDoLists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_task, viewGroup,false));
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.titledoes.setText(toDoLists.get(i).getTitle());
        myViewHolder.descdoes.setText(toDoLists.get(i).getDescription());
        myViewHolder.datedoes.setText(toDoLists.get(i).getDate());
    }

    @Override
    public int getItemCount() {
        return toDoLists.size();
    }

    class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView titledoes, descdoes, datedoes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titledoes = (TextView)itemView.findViewById(R.id.taskTitle_tv);
            descdoes = (TextView)itemView.findViewById(R.id.taskDesc_tv);
            datedoes = (TextView)itemView.findViewById(R.id.taskDate_tv);

        }
    }
}
