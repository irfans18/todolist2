package com.irfans.todolist2.utils;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.irfans.todolist2.R;
import com.irfans.todolist2.model.SuccessMessage;
import com.irfans.todolist2.model.Task;

public class RecyclerViewAdapterTodolist extends RecyclerView.Adapter<RecyclerViewAdapterTodolist.MyViewHolder> {
    private static List<Task> mDataset;
    private static MyClickListener myClickListener;
    private Context context;


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvDate;
        TextView tvTitle;
        TextView tvDescription;
        CheckBox cbItemBox;
        ImageView ivShare;


        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.taskTitle_tv);
            tvDescription = itemView.findViewById(R.id.taskDesc_tv);
            tvDate = itemView.findViewById(R.id.taskDate_tv);
            cbItemBox = itemView.findViewById(R.id.item_check_box_cb);
            ivShare = itemView.findViewById(R.id.item_share_iv);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            myClickListener.onItemClick(position, view);
        }
    }

    public RecyclerViewAdapterTodolist(List<Task> myDataset, Context context) {
        mDataset = myDataset;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CharSequence date = mDataset.get(position).getDeadline();
        int id = mDataset.get(position).getId();
        int check = mDataset.get(position).isChecked();
//
        if (check == 1) {
            holder.cbItemBox.setChecked(true);
        } else {
            holder.cbItemBox.setChecked(false);
        }

        try {
            Date Ddate = new SimpleDateFormat("yyyy-MM-dd").parse(date.toString());
            date = DateFormat.format("EEEE, dd MMMM yyyy", Ddate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tvTitle.setText(mDataset.get(position).getTitle());
        holder.tvDescription.setText(mDataset.get(position).getDescription());
        holder.tvDate.setText(date);

        holder.cbItemBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateStatus(id, new RequestCallback<SuccessMessage>() {
                    @Override
                    public void requestSuccess(SuccessMessage data) {
                    }

                    @Override
                    public void requestFailed(String errorMessage) {

                    }
                });
            }
        });


        String finalDate = date.toString();
        holder.ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Title : " + mDataset.get(position).getTitle() + "\n"
                        + "Description : " + mDataset.get(position).getDescription() + "\n"
                        + "Deadline " + "\n" + finalDate;
                shareTask(message);
            }
        });

    }

    private void shareTask(String message) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        context.startActivity(Intent.createChooser(intent, "Share"));
    }

    private void updateStatus(int id, RequestCallback<SuccessMessage> requestCallback) {
        AndroidNetworking.post(myURL.FINISH_TASK_URL + id)
                .setTag(this)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(SuccessMessage.class, new ParsedRequestListener<SuccessMessage>() {
                    @Override
                    public void onResponse(SuccessMessage response) {
                        if (response == null) {
                            requestCallback.requestFailed("Null Response");
                            Log.d("tag", "response null");
                        } else if (response.isSuccess() == false) {
                            requestCallback.requestFailed("Cancel Failed");
                        } else {
                            requestCallback.requestSuccess(response);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                    }
                });
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