package com.irfans.todolist2.modul.todolist;

import android.view.View;

import com.irfans.todolist2.base.BaseFragmentHolderActivity;
import com.irfans.todolist2.utils.SharedPreferences.TokenSessionRepository;
import com.irfans.todolist2.utils.SharedPreferences.TokenSharedUtil;
import com.irfans.todolist2.utils.SharedPreferences.UtilProvider;


public class TodoListActivity extends BaseFragmentHolderActivity {
    TodoListFragment todoListFragment;
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
        todoListFragment = new TodoListFragment(tokenSessionRepository);
        setCurrentFragment(todoListFragment, false);

    }



}
