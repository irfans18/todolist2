package com.irfans.todolist2.base;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.irfans.todolist2.R;
import com.irfans.todolist2.modul.login.LoginActivity;
import com.irfans.todolist2.utils.SharedPreferences.TokenSessionRepository;
import com.irfans.todolist2.utils.SharedPreferencesUtil;

public abstract class BaseFragmentHolderActivity extends BaseActivity {
    protected TextView tvToolbarTitle;
    protected FrameLayout flFragmentContainer;
    protected ImageButton btOptionMenu;
    protected ImageView ivIcon;
    protected ImageButton btBack;
    protected View vMenuBarShadow;
    protected RelativeLayout rlActivityFragmentHolder;
    protected TokenSessionRepository sessionRepository;

    @Override
    protected void initializeView() {
        setContentView(R.layout.activity_base);
        tvToolbarTitle = findViewById(R.id.tvToolbarTitle);
        flFragmentContainer = findViewById(R.id.flFragmentContainer);
        btOptionMenu = findViewById(R.id.btOptionMenu);
        ivIcon = findViewById(R.id.ivIcon);
        btBack = findViewById(R.id.btBack);
        vMenuBarShadow = findViewById(R.id.vMenuBarShadow);
        rlActivityFragmentHolder = findViewById(R.id.rlActivityFragmentHolder);

//        btBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                logOut();
////                onBackPressed();
//            }
//
//            private void logOut() {
//                sessionRepository.clear();
//                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public void setTitle(String title) {
        this.tvToolbarTitle.setText(title);
    }
}
