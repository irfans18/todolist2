package com.irfans.todolist2.modul.edittask;

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

import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.irfans.todolist2.R;
import com.irfans.todolist2.base.BaseFragment;
import com.irfans.todolist2.data.model.SuccessMessage;
import com.irfans.todolist2.data.model.Task;
import com.irfans.todolist2.databinding.FragmentEditTaskBinding;
import com.irfans.todolist2.modul.todolist.TodoListActivity;
import com.irfans.todolist2.utils.RequestCallback;
import com.irfans.todolist2.utils.SharedPreferences.TokenSessionRepository;
import com.irfans.todolist2.utils.myURL;

import java.util.Calendar;


/**
 * Created by fahrul on 13/03/19.
 */

public class EditTaskFragment extends BaseFragment<EditTaskActivity, EditTaskContract.Presenter> implements EditTaskContract.View {

    private FragmentEditTaskBinding binding;
    private TokenSessionRepository tokenSessionRepository;
    private String task_id;
    String date;
    String privacy;


    public EditTaskFragment(int task_id, TokenSessionRepository tokenSessionRepository) {
        this.task_id = String.valueOf(task_id);
        this.tokenSessionRepository = tokenSessionRepository;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_edit_task, container, false);
        mPresenter = new EditTaskPresenter(this);
        mPresenter.start();
        mPresenter.loadData();
        initCalendar();

        setTitle("Edit Task");
        binding.updateTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtSaveClick();
            }
        });

        binding.editTaskPrivacyRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkPrivacy(checkedId);
            }
        });

        return fragmentView;
    }

    private void checkPrivacy(int checkedId) {
        switch (checkedId){
            case R.id.new_task_private_rb :
                privacy = "0";
                break;
            case R.id.new_task_public_rb:
                privacy = "1";
                break;
        }
    }

    public void initCalendar(){
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        binding.editTaskTaskDateTv.setOnClickListener(new View.OnClickListener() {
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
                        binding.editTaskTaskDateTv.setText(date);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });


    }

    public void setBtSaveClick(){
        String title = binding.editTaskTaskTitleEt2.getText().toString();
        String description = binding.editTaskTaskDescEt2.getText().toString();
        mPresenter.saveData(title,description);
    }

    @Override
    public void setPresenter(EditTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void requestEditTask(String title, String description, RequestCallback<SuccessMessage> requestCallback) {
        AndroidNetworking.post(myURL.CREATE_TASK_URL)
                .addHeaders("Authorization", "Bearer " + tokenSessionRepository.getToken())
                .addBodyParameter("title", title)
                .addBodyParameter("description", description)
                .addBodyParameter("deadline", date)
                .addBodyParameter("privacy", privacy)
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
    public void requestTaskDetail(final RequestCallback<EditTaskResponse> requestCallback) {
        AndroidNetworking.get(myURL.DETAIL_TASK_URL + task_id)
                .addHeaders("Authorization", "Bearer " + tokenSessionRepository.getToken())
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObjectList(EditTaskResponse.class, new ParsedRequestListener<EditTaskResponse>() {
                    @Override
                    public void onResponse(EditTaskResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                            Log.d("PUBLIC", "response null");
                        }else{
                            requestCallback.requestSuccess(response);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed(anError.getMessage());
                        Log.d("PUBLIC", "error gan " + anError.getMessage() + anError.getErrorCode());
                    }
                });
    }

    @Override
    public void setResult(Task data) {
        binding.editTaskTaskTitleEt2.setText(data.getTitle());
        binding.editTaskTaskDescEt2.setText(data.getDescription());
        binding.editTaskTaskDateTv.setText(data.getDeadline());

            if (data.isPrivacy()) {
                binding.editTaskPublicRb.setChecked(true);
            }else
                binding.editTaskPrivateRb.setChecked(true);
    }

    @Override
    public void redirectToTaskList() {
            Intent intent = new Intent(activity, TodoListActivity.class);
            startActivity(intent);
            activity.finish();
    }


}
