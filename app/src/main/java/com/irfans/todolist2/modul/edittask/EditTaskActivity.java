package com.irfans.todolist2.modul.edittask;

import android.view.View;

import com.irfans.todolist2.base.BaseFragmentHolderActivity;
import com.irfans.todolist2.data.model.Task;
import com.irfans.todolist2.utils.SharedPreferences.TokenSessionRepository;


public class EditTaskActivity extends BaseFragmentHolderActivity {
    EditTaskFragment editTaskFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setImageResource(R.drawable.....);
        ivIcon.setVisibility(View.VISIBLE);

        Task task = new Task();
        task.setId(getIntent().getIntExtra("id", 0));
        task.setTitle(getIntent().getStringExtra("title"));
        task.setDescription(getIntent().getStringExtra("desc"));
        task.setDeadline(getIntent().getStringExtra("date"));
        editTaskFragment = new EditTaskFragment(task);
        setCurrentFragment(editTaskFragment, false);

    }




}
