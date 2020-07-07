package com.example.todolist.dialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.todolist.APIConnector;
import com.example.todolist.Define;
import com.example.todolist.R;
import com.example.todolist.model.Todo;
import com.example.todolist.util.DialogUtils;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DialogEdit extends DialogFragment {

    private Activity activity;
    private Dialog dialog;
    private Callback callback;

    private EditText edtName,edtDes;
    private TextView tvSelectTime,tvSelectDate;
    private int id ;
    private ImageButton ivRight;
    private long time;
    private TextView tvTitle;
    private Todo todo;
    int year,month, day, hours, min;

    public DialogEdit(Todo todo,Callback callback) {
        this.todo = todo;
        this.callback = callback;
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        dialog = DialogUtils.getNewDialog(activity,false,activity.getResources().getColor(R.color.blue));
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_edit);
        intiUI();
        initData();
        return dialog;
    }

    private void intiUI() {
        ivRight = dialog.findViewById(R.id.ivRight);
        edtName = dialog.findViewById(R.id.edt_title);
        edtDes = dialog.findViewById(R.id.edt_info);
        tvSelectTime = dialog.findViewById(R.id.tv_timeStart);
        tvSelectDate = dialog.findViewById(R.id.tvDate);
        tvTitle = dialog.findViewById(R.id.tvTitle);
        tvTitle.setText("Sửa công việc");
        edtName.setText(todo.getName());
        String time[] = todo.getTimeStamp().split(" ");

        tvSelectTime.setText(time[1]);
        tvSelectDate.setText(time[0]);
    }

    private void initData() {
        ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                String des = edtDes.getText().toString().trim();
                String startDateString = tvSelectTime.getText().toString();
                if (name.length() > 0 && des.length() > 0 && tvSelectTime.getText().toString().length() > 0){
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = null;
                    try {
                        date = df.parse(startDateString);
                        time = date.getTime();
                        new Timestamp(time);
                        String newDateString = df.format(date);
                        System.out.println(newDateString);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    todo.setName(name);
                    todo.setTime(time);
                    todo.setStatus(true);
                    Gson gson = new Gson();
                    final String json = gson.toJson(todo);
                    Log.d("LongDinhAdd",json);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            APIConnector.put(Define.API_EDIT+todo.getId(), json, new okhttp3.Callback() {
                                @Override
                                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                                }

                                @Override
                                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                    if (response.isSuccessful()){
                                        if (callback != null){
                                            callback.onSuccess( year, month, day, hours, min);
                                            dismiss();
                                        }
                                    }
                                    else {
                                        activity.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(activity, "Something wrong", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                }
                            });
                        }
                    }).start();
                }
                else {
                    Toast.makeText(activity, "Thông tin chưa đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar mcurrentTime = Calendar.getInstance();
                int year = mcurrentTime.get(Calendar.YEAR);
                int month = mcurrentTime.get(Calendar.MONTH);
                int day = mcurrentTime.get(Calendar.DAY_OF_MONTH);
                tvSelectTime.setText(day + "/"+(month+1) +"/"+year);

                DatePickerDialog pickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tvSelectTime.setText(dayOfMonth + "/"+(month+1) +"/"+year);
                    }
                },year,month,day);

                pickerDialog.show();
            }
        });
    }
    public interface Callback{
        void onSuccess(int year, int month, int day, int hours, int min);
    }
}
