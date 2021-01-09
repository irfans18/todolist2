package com.irfans.todolist2.modul.newtask;

import android.view.View;

import com.irfans.todolist2.base.BaseFragmentHolderActivity;


public class NewTaskActivity extends BaseFragmentHolderActivity {
    NewTaskFragment newTaskFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setImageResource(R.drawable.....);
        ivIcon.setVisibility(View.VISIBLE);

        newTaskFragment = new NewTaskFragment();
        setCurrentFragment(newTaskFragment, false);
    }




}
