package com.irfans.todolist2.modul.edittask;

import android.view.View;

import com.irfans.todolist2.base.BaseFragmentHolderActivity;


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

        editTaskFragment = new EditTaskFragment();
        String id = getIntent().getExtras().getString("TaskId");
        editTaskFragment.setId(id);
        setCurrentFragment(editTaskFragment, false);

    }




}
