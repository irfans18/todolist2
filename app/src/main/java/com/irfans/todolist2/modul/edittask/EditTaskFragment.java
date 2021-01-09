package com.irfans.todolist2.modul.edittask;

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
import com.irfans.todolist2.data.model.Task;
import com.irfans.todolist2.modul.todolist.TodoListActivity;


/**
 * Created by fahrul on 13/03/19.
 */

public class EditTaskFragment extends BaseFragment<EditTaskActivity, EditTaskContract.Presenter> implements EditTaskContract.View {

    EditText etTaskTitle;
    EditText etTaskDescription;
    EditText etTaskDate;
    Button btnSave;
    String id;
    EditText btnDelete;


    public EditTaskFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_edit_task, container, false);
        mPresenter = new EditTaskPresenter(this);
        mPresenter.start();

        etTaskTitle = fragmentView.findViewById(R.id.taskTitle_et2);
        etTaskDescription = fragmentView.findViewById(R.id.taskDesc_et2);
        etTaskDate = fragmentView.findViewById(R.id.taskDate_et2);
        btnSave = fragmentView.findViewById(R.id.updateTask_btn);
        btnDelete = fragmentView.findViewById(R.id.deleteTask_btn);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtSaveClick();
            }
        });

        setTitle("Edit Task");
        mPresenter.loadData(this.id);

        return fragmentView;
    }

    public void setBtSaveClick(){
        String title = etTaskTitle.getText().toString();
        String description = etTaskDescription.getText().toString();
        String date = etTaskDate.getText().toString();
        mPresenter.saveData(title,description, date);
    }

    @Override
    public void setPresenter(EditTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToTaskList() {
            Intent intent = new Intent(activity, TodoListActivity.class);
            startActivity(intent);
            activity.finish();
    }

    @Override
    public void showData(Task task) {
        this.etTaskTitle.setText(task.getTitle());
        this.etTaskDescription.setText(task.getDescription());
        this.etTaskDate.setText(task.getDate());
    }

    @Override
    public void setId(String id) {
        this.id=id;
    }

}
