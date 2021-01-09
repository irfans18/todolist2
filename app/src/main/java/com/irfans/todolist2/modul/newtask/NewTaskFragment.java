package com.irfans.todolist2.modul.newtask;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.irfans.todolist2.R;
import com.irfans.todolist2.base.BaseFragment;
import com.irfans.todolist2.modul.todolist.TodoListActivity;


/**
 * Created by fahrul on 13/03/19.
 */

public class NewTaskFragment extends BaseFragment<NewTaskActivity, NewTaskContract.Presenter> implements NewTaskContract.View {

    EditText etTaskTitle;
    EditText etTaskDescription;
    Button btnSave;
    EditText etTaskDate;
    Button btnCancel;


    public NewTaskFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_new_task, container, false);
        mPresenter = new NewTaskPresenter(this);
        mPresenter.start();

        etTaskTitle = fragmentView.findViewById(R.id.taskTitle_et);
        etTaskDescription = fragmentView.findViewById(R.id.taskDesc_et);
        etTaskDate = fragmentView.findViewById(R.id.taskDate_et);
        btnSave = fragmentView.findViewById(R.id.addTask_btn);
        btnCancel = fragmentView.findViewById(R.id.cancelTask_btn);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtSaveClick();
            }
        });

        setTitle("Add New Task");

        return fragmentView;
    }

    public void setBtSaveClick(){
        String title = etTaskTitle.getText().toString();
        String description = etTaskDescription.getText().toString();
        String date = etTaskDate.getText().toString();
        mPresenter.saveData(title,description, date);
    }

    @Override
    public void setPresenter(NewTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToTaskList() {
            Intent intent = new Intent(activity, TodoListActivity.class);
            startActivity(intent);
            activity.finish();
    }


}
