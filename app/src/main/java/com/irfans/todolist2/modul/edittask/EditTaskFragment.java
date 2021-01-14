package com.irfans.todolist2.modul.edittask;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.irfans.todolist2.R;
import com.irfans.todolist2.base.BaseFragment;
import com.irfans.todolist2.data.model.SuccessMessage;
import com.irfans.todolist2.data.model.Task;
import com.irfans.todolist2.databinding.FragmentEditTaskBinding;
import com.irfans.todolist2.modul.todolist.TodoListActivity;
import com.irfans.todolist2.utils.RequestCallback;
import com.irfans.todolist2.utils.SharedPreferences.TokenSessionRepository;
import com.irfans.todolist2.utils.myURL;

import java.util.Calendar;
import java.util.List;

import static android.content.ContentValues.TAG;


/**
 * Created by fahrul on 13/03/19.
 */

public class EditTaskFragment extends BaseFragment<EditTaskActivity, EditTaskContract.Presenter> implements EditTaskContract.View, View.OnClickListener {

    private FragmentEditTaskBinding binding;
    private Task task;
    String date = null;


    public EditTaskFragment(Task task) {
        this.task = task;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_task, container, false);
        fragmentView = binding.getRoot();
        mPresenter = new EditTaskPresenter(this, activity);
        mPresenter.loadData(task);
        initCalendar();
        setTitle("Edit Task");
        binding.itemShareIv.setOnClickListener(this);
        binding.updateTaskBtn.setOnClickListener(this);
        binding.deleteTaskBtn.setOnClickListener(this);
        binding.finishTaskBtn.setOnClickListener(this);

        return fragmentView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.updateTask_btn) setBtSaveClick();
        if (v.getId() == R.id.finishTask_btn) setBtFinishClick();
        if (v.getId() == R.id.deleteTask_btn) setBtDeleteClick();
        if (v.getId() == R.id.item_share_iv) shareTask();
    }

    private void shareTask(){
        String message = task.getTitle() + "\n" + task.getDescription() + "\n" + task.getDeadline();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(intent,"Share"));
    }

    private void setBtSaveClick(){
        Task newTask = new Task();
        newTask.setTitle(binding.editTaskTaskTitleEt2.getText().toString());
        newTask.setDescription(binding.editTaskTaskDescEt2.getText().toString());
        if (date != null) newTask.setDeadline(date);
        newTask.setDeadline(binding.editTaskTaskDateTv.getText().toString());
        mPresenter.saveData(newTask);
    }

    private void setBtDeleteClick() {
        mPresenter.delete(task);
    }

    private void setBtFinishClick() {
        mPresenter.finish(task);
    }


    public void initCalendar(){
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        binding.editTaskTaskDateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
//                        String date = day+"/"+month+"/"+year;
//                        String dates = date.toString();

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year-1);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, day);
                        date = year+"-"+month+"-"+day;
                        CharSequence date = DateFormat.format("EEE, d MMM yyyy", calendar);
                        binding.editTaskTaskDateTv.setText(date);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });


    }



    @Override
    public void setPresenter(EditTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void requestEditTask(Task ntask, RequestCallback<SuccessMessage> requestCallback) {
        AndroidNetworking.post(myURL.UPDATE_TASK_URL + task.getId())
                .addBodyParameter("title", ntask.getTitle())
                .addBodyParameter("description", ntask.getDescription())
                .addBodyParameter("deadline", ntask.getDeadline())
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
    public void finishTask(int id, RequestCallback<SuccessMessage> requestCallback) {
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
    public void deleteTask(int id, RequestCallback<SuccessMessage> requestCallback) {
        AndroidNetworking.post(myURL.DELETE_TASK_URL + id)
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
                        Log.e(TAG, anError.getMessage());
                        Log.e(TAG, String.valueOf(anError.getErrorCode()));

                    }
                });
    }

    @Override
    public void setResult(Task data) {
        binding.editTaskTaskTitleEt2.setText(data.getTitle());
        binding.editTaskTaskDescEt2.setText(data.getDescription());
        binding.editTaskTaskDateTv.setText(data.getDeadline());
    }

    @Override
    public void showSuccessMessage(SuccessMessage data) {
        Toast.makeText(activity, data.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void redirectToTaskList() {
            Intent intent = new Intent(activity, TodoListActivity.class);
            startActivity(intent);
            activity.finish();
    }



}
