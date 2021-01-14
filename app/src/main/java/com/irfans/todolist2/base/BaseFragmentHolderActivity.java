package com.irfans.todolist2.base;

import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.irfans.todolist2.R;

public abstract class BaseFragmentHolderActivity extends BaseActivity {
    protected TextView tvToolbarTitle;
    protected FrameLayout flFragmentContainer;
    protected ImageButton btOptionMenu;
    protected ImageView ivIcon;
    protected ImageButton btBack;
    protected View vMenuBarShadow;
    protected RelativeLayout rlActivityFragmentHolder;
    protected ConstraintLayout loading;


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
        loading = findViewById(R.id.loading_screen);


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

    public void startLoading(){
        loading.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void stopLoading(){
        loading.setVisibility(View.GONE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
}
