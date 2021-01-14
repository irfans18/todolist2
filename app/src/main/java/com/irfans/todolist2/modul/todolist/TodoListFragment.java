package com.irfans.todolist2.modul.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.irfans.todolist2.R;
import com.irfans.todolist2.base.BaseFragment;
import com.irfans.todolist2.model.Task;
import com.irfans.todolist2.databinding.FragmentHomeBinding;
import com.irfans.todolist2.modul.edittask.EditTaskActivity;
import com.irfans.todolist2.modul.newtask.NewTaskActivity;
import com.irfans.todolist2.utils.RecyclerViewAdapterTodolist;
import com.irfans.todolist2.utils.RequestCallback;
import com.irfans.todolist2.utils.myURL;

import static android.content.ContentValues.TAG;


/**
 * Created by fahrul on 13/03/19.
 */

public class TodoListFragment extends BaseFragment<TodoListActivity, TodoListContract.Presenter> implements TodoListContract.View, View.OnClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    FragmentHomeBinding binding;


    public TodoListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        fragmentView = binding.getRoot();
        mPresenter = new TodoListPresenter(this);
        mPresenter.start();


        mRecyclerView = fragmentView.findViewById(R.id.home_tasks_rv);
        mRecyclerView.setHasFixedSize(true);
        binding.homeTasksRv.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        binding.homeTasksRv.setLayoutManager(mLayoutManager);
        mPresenter.getData();
        setTitle("Todo List");


        binding.homeAddTaskBtn.setOnClickListener(this);


        return fragmentView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.home_addTask_btn) gotoNewTask();
    }


    @Override
    public void setPresenter(TodoListContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void gotoNewTask() {
        Intent intent = new Intent(activity, NewTaskActivity.class);
        startActivity(intent);
    }

    @Override
    public void requestPrivateTasks(final RequestCallback<List<Task>> requestCallback ) {
        AndroidNetworking.get(myURL.SHOW_TASK_URL)
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObjectList(Task.class, new ParsedRequestListener<List<Task>>() {
                    @Override
                    public void onResponse(List<Task> data) {
                        // do anything with response
                        requestCallback.requestSuccess(data);
                    }
                    @Override
                    public void onError(ANError anError) {
                        // handle error
                    }
                });
    }


    @Override
    public void showFailedMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
    }

    @Override
    public void setResult(final List<Task> data){
        mAdapter = new RecyclerViewAdapterTodolist(data, getContext());
        binding.homeTasksRv.setAdapter(mAdapter);
//        mRecyclerView.setAdapter(mAdapter);

        Log.e(TAG, data.get(0).getTitle());

        ((RecyclerViewAdapterTodolist) mAdapter).setOnItemClickListener(new RecyclerViewAdapterTodolist.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Task task = new Task();
                task.setId(data.get(position).getId());
                task.setTitle(data.get(position).getTitle());
                task.setDeadline(data.get(position).getDeadline());
                task.setDescription(data.get(position).getDescription());
                redirectToEdit(task);
            }
        });

    }

    @Override
    public void redirectToEdit(Task task) {
        Intent intent = new Intent(activity, EditTaskActivity.class);
        intent.putExtra("id", task.getId());
        intent.putExtra("title", task.getTitle());
        intent.putExtra("desc", task.getDescription());
        intent.putExtra("date", task.getDeadline());
        startActivity(intent);
    }



}
