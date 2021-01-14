package com.irfans.todolist2.modul.newtask;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.irfans.todolist2.R;
import com.irfans.todolist2.base.BaseFragment;
import com.irfans.todolist2.data.model.SuccessMessage;
import com.irfans.todolist2.databinding.FragmentNewTaskBinding;
import com.irfans.todolist2.modul.todolist.TodoListActivity;
import com.irfans.todolist2.utils.RequestCallback;
import com.irfans.todolist2.utils.SharedPreferences.TokenSessionRepository;
import com.irfans.todolist2.utils.SharedPreferences.TokenSharedUtil;
import com.irfans.todolist2.utils.myURL;

import java.util.Calendar;

import static android.content.ContentValues.TAG;


/**
 * Created by fahrul on 13/03/19.
 */

public class NewTaskFragment extends BaseFragment<NewTaskActivity, NewTaskContract.Presenter> implements NewTaskContract.View {

    private FragmentNewTaskBinding binding;
    private final TokenSharedUtil tokenSessionRepository;
    String date;
    String privacy;

    public NewTaskFragment(TokenSharedUtil tokenSessionRepository) {
        this.tokenSessionRepository = tokenSessionRepository;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_task, container, false);
        fragmentView = binding.getRoot();
        mPresenter = new NewTaskPresenter(this);
        mPresenter.start();


        initCalendar();
        binding.newTaskSaveTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtSaveClick();
            }
        });

        setTitle("New Task");

        return fragmentView;
    }

    public void initCalendar(){
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        binding.newTaskTaskDateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
//                        String date = day+"/"+month+"/"+year;
//                        String dates = date.toString();

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year-1);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, day);
                        date = year+"-"+month+"-"+day;
                        CharSequence date = DateFormat.format("EEE, d MMM yyyy", calendar);
                        binding.newTaskTaskDateTv.setText(date);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });


    }

    public void setBtSaveClick(){
        String title = binding.newTaskTaskTitleEt.getText().toString();
        String description = binding.newTaskTaskDescEt.getText().toString();
        mPresenter.saveData(title,description);
    }

    @Override
    public void requestNewTask(String title, String description, RequestCallback<SuccessMessage> requestCallback) {
        Log.e(TAG, date );
        AndroidNetworking.post(myURL.CREATE_TASK_URL)
                .addBodyParameter("title", title)
                .addBodyParameter("description", description)
                .addBodyParameter("deadline", date)
                .setTag(this)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(SuccessMessage.class, new ParsedRequestListener<SuccessMessage>() {

                    @Override
                    public void onResponse(SuccessMessage response) {
                        if (response == null) {
                            requestCallback.requestFailed("Null Response");
                            Log.d("tag", "response null");
                        } else if (response.isSuccess() == false) {
                            requestCallback.requestFailed("Cancel Failed");
                        } else {
                            requestCallback.requestSuccess(response);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                                            }
                });
    }

    @Override
    public void setPresenter(NewTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showSuccessMessage(SuccessMessage data) {
        Toast.makeText(activity, data.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void redirectToTaskList() {
            Intent intent = new Intent(activity, TodoListActivity.class);
            startActivity(intent);
            activity.finish();
    }

}
