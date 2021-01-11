package com.irfans.todolist2.modul.newtask;

import android.view.View;

import com.irfans.todolist2.base.BaseFragmentHolderActivity;
import com.irfans.todolist2.utils.SharedPreferences.TokenSessionRepository;
import com.irfans.todolist2.utils.SharedPreferences.TokenSharedUtil;
import com.irfans.todolist2.utils.SharedPreferences.UtilProvider;


public class NewTaskActivity extends BaseFragmentHolderActivity {
    NewTaskFragment newTaskFragment;
    TokenSharedUtil tokenSessionRepository;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setImageResource(R.drawable.....);
        ivIcon.setVisibility(View.VISIBLE);

        tokenSessionRepository = UtilProvider.getTokenSharedUtil();
        newTaskFragment = new NewTaskFragment(tokenSessionRepository);
        setCurrentFragment(newTaskFragment, false);
    }




}
