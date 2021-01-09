package com.irfans.todolist2.modul.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.irfans.todolist2.R;
import com.irfans.todolist2.base.BaseFragment;
import com.irfans.todolist2.data.model.Task;
import com.irfans.todolist2.databinding.FragmentHomeBinding;
import com.irfans.todolist2.modul.edittask.EditTaskActivity;
import com.irfans.todolist2.modul.newtask.NewTaskActivity;
import com.irfans.todolist2.utils.TaskAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by fahrul on 13/03/19.
 */

public class TodoListFragment extends BaseFragment<TodoListActivity, TodoListContract.Presenter> implements TodoListContract.View {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    DatabaseReference reference;
    ArrayList<Task> lists;
    TaskAdapter taskAdapter;
//    @BindView(R.id.addTask_btn)
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

//        mRecyclerView = fragmentView.findViewById(R.id.home_tasks_rv);
//        mRecyclerView.setHasFixedSize(true);
//        mLayoutManager = new LinearLayoutManager(activity);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        final ArrayList<Task> data = mPresenter.getDataSet();
//        mAdapter = new RecyclerViewAdapterTodolist(data);
//        mRecyclerView.setAdapter(mAdapter);
        setTitle("Todo List");


        binding.homeAddTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoNewTask();
            }
        });

//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                gotoNewTask();
//            }
//        });

//        buttonAdd = fragmentView.findViewById(R.id.buttonAdd);
//        buttonAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                gotoNewTask();
//            }
//        });


//        ((RecyclerViewAdapterTodolist) mAdapter).setOnItemClickListener(new RecyclerViewAdapterTodolist.MyClickListener() {
//            @Override
//            public void onItemClick(int position, View v) {
//                String id = data.get(position).getId();
//                Log.d("BELAJAR ACTIVITY",">>>>>"+ position);
//                editTask(id);
//            }
//        });


        return fragmentView;
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
    public void getDataFromDatabase() {
        //working with database
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.homeTasksRv.setLayoutManager(new LinearLayoutManager(getContext()));
        lists = new ArrayList<Task>();

        //get data from firebase
        reference = FirebaseDatabase.getInstance().getReference().child("todolist");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //retrieve data
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Task toDoList = dataSnapshot1.getValue(Task.class);
                    lists.add(toDoList);
                }
                taskAdapter = new TaskAdapter(getActivity(), lists);
//                mRecyclerView.setAdapter(taskAdapter);
                binding.homeTasksRv.setAdapter(taskAdapter);
                taskAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //show an error
                Toast.makeText(getContext(), "No Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void editTask(String id) {
        Intent intent = new Intent(activity, EditTaskActivity.class);
        intent.putExtra("TaskId", id);
        startActivity(intent);
    }



}
