package com.irfans.todolist2.modul.task;

import android.view.View;

import com.irfans.todolist2.base.BaseFragmentHolderActivity;
import com.irfans.todolist2.utils.SharedPreferencesUtil;

public class TaskActivity extends BaseFragmentHolderActivity {
    TaskFragment taskFragment;
    SharedPreferencesUtil sharedPreferencesUtil;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setImageResource(R.drawable.....);
        ivIcon.setVisibility(View.VISIBLE);

        taskFragment = new TaskFragment(sharedPreferencesUtil);
        setCurrentFragment(taskFragment, false);
    }

}
