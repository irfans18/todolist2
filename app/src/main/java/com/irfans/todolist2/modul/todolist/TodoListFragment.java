package com.irfans.todolist2.modul.todolist;

import android.content.Intent;
import android.graphics.Color;
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
import com.irfans.todolist2.data.model.Task;
import com.irfans.todolist2.databinding.FragmentHomeBinding;
import com.irfans.todolist2.modul.edittask.EditTaskActivity;
import com.irfans.todolist2.modul.newtask.NewTaskActivity;
import com.irfans.todolist2.utils.RecyclerViewAdapterTodolist;
import com.irfans.todolist2.utils.RequestCallback;
import com.irfans.todolist2.utils.SharedPreferences.TokenSessionRepository;
import com.irfans.todolist2.utils.SharedPreferences.TokenSharedUtil;
import com.irfans.todolist2.utils.myURL;


/**
 * Created by fahrul on 13/03/19.
 */

public class TodoListFragment extends BaseFragment<TodoListActivity, TodoListContract.Presenter> implements TodoListContract.View, View.OnClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private final TokenSharedUtil tokenSessionRepository;


    FragmentHomeBinding binding;


    public TodoListFragment(TokenSharedUtil tokenSessionRepository) {
        this.tokenSessionRepository = tokenSessionRepository;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        fragmentView = binding.getRoot();
        mPresenter = new TodoListPresenter(this);
        mPresenter.start();


        binding.homeTasksRv.setHasFixedSize(true);
//        mRecyclerView = fragmentView.findViewById(R.id.home_tasks_rv);
//        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(activity);
        binding.homeTasksRv.setLayoutManager(mLayoutManager);
        mPresenter.getData(1);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        final ArrayList<Task> data = mPresenter.getDataSet();
//        mAdapter = new RecyclerViewAdapterTodolist(data);
//        mRecyclerView.setAdapter(mAdapter);
        setTitle("Todo List");


        binding.homePrivateBtn.setOnClickListener(this);
        binding.homePublicBtn.setOnClickListener(this);
        binding.homeAddTaskBtn.setOnClickListener(this);


        return fragmentView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.home_addTask_btn) gotoNewTask();
        if (v.getId() == R.id.home_private_btn) setPrivateBtn();
        if (v.getId() == R.id.home_public_btn) setPublicBtn();


    }

    private void setPublicBtn() {
        binding.homePrivateBtn.setTextColor(Color.DKGRAY);
        binding.homePublicBtn.setTextColor(Color.BLACK);
        mPresenter.getData(1);
    }

    private void setPrivateBtn() {
        binding.homePrivateBtn.setTextColor(Color.BLACK);
        binding.homePublicBtn.setTextColor(Color.DKGRAY);
        mPresenter.getData(0);
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
        AndroidNetworking.get(myURL.PRIVATE_TASK_URL)
                .addHeaders("Authorization", "Bearer " + tokenSessionRepository.getToken())
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObjectList(Task.class, new ParsedRequestListener<List<Task>>() {
                    @Override
                    public void onResponse(List<Task> response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                            Log.d("PRIVATE", "response null");
                        }else{
                            requestCallback.requestSuccess(response);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                        Log.d("PRIVATE", "error gan " + anError.getMessage() + anError.getErrorCode());
                    }
                });
    }

    @Override
    public void requestPublicTasks(RequestCallback<List<Task>> requestCallback) {
        AndroidNetworking.get(myURL.PUBLIC_TASK_URL)
                .addHeaders("Authorization", "Bearer " + tokenSessionRepository.getToken())
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObjectList(Task.class, new ParsedRequestListener<List<Task>>() {
                    @Override
                    public void onResponse(List<Task> response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                            Log.d("PUBLIC", "response null");
                        }else{
                            requestCallback.requestSuccess(response);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                        Log.d("PUBLIC", "error gan " + anError.getMessage() + anError.getErrorCode());
                    }
                });
    }

    @Override
    public void showFailedMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
    }

    @Override
    public void setResult(final List<Task> data){
        mAdapter = new RecyclerViewAdapterTodolist(data);
        mRecyclerView.setAdapter(mAdapter);
        ((RecyclerViewAdapterTodolist) mAdapter).setOnItemClickListener(new RecyclerViewAdapterTodolist.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                int task_id = data.get(position).getId();
                redirectToEdit(task_id);
            }
        });

    }

    private void redirectToEdit(int task_id) {
        Intent intent = new Intent(activity, EditTaskActivity.class);
        intent.putExtra("task_id", task_id);
        startActivity(intent);
    }



}
