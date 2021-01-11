package com.irfans.todolist2.modul.edittask;

import android.view.View;

import com.irfans.todolist2.base.BaseFragmentHolderActivity;
import com.irfans.todolist2.utils.SharedPreferences.TokenSessionRepository;


public class EditTaskActivity extends BaseFragmentHolderActivity {
    EditTaskFragment editTaskFragment;
    TokenSessionRepository tokenSessionRepository;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setImageResource(R.drawable.....);
        ivIcon.setVisibility(View.VISIBLE);

        int id = getIntent().getIntExtra("task_id", 0);
        editTaskFragment = new EditTaskFragment(id, tokenSessionRepository);
//        editTaskFragment.setId(id);
        setCurrentFragment(editTaskFragment, false);

    }




}
