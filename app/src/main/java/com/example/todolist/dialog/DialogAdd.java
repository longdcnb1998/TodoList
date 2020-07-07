package com.example.todolist.dialog;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.example.todolist.APIConnector;
import com.example.todolist.Define;
import com.example.todolist.R;
import com.example.todolist.model.Todo;
import com.example.todolist.util.DialogUtils;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DialogAdd extends DialogFragment {

    private Activity activity;
    private Dialog dialog;
    private EditText edtName, edtDes;
    private TextView tvSelectDate, tvSelectHours;
    private int id;
    private ImageButton ivRight;
    private TextView tvTitle;
    private AddCallback addCallback;
    private int year, month, day, hours, min;

    public DialogAdd(int id, AddCallback addCallback) {
        this.id = id;
        this.addCallback = addCallback;
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        dialog = DialogUtils.getNewDialog(activity, false, activity.getResources().getColor(R.color.blue));
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_add);
        intiUI();
        initData();
        return dialog;
    }

    private void intiUI() {
        ivRight = dialog.findViewById(R.id.ivRight);
        edtName = dialog.findViewById(R.id.edt_title);
        edtDes = dialog.findViewById(R.id.edt_info);
        tvSelectDate = dialog.findViewById(R.id.tvDate);
        tvSelectHours = dialog.findViewById(R.id.tvHours);
        tvTitle = dialog.findViewById(R.id.tvTitle);
        tvTitle.setText("Thêm công việc");
        final Calendar mcurrentTime = Calendar.getInstance();
         year = mcurrentTime.get(Calendar.YEAR);
         month = mcurrentTime.get(Calendar.MONTH);
         day = mcurrentTime.get(Calendar.DAY_OF_MONTH);
         hours = mcurrentTime.get(Calendar.HOUR_OF_DAY);
         min = mcurrentTime.get(Calendar.MINUTE);
        tvSelectDate.setText(day + "/" + (month + 1) + "/" + year);
        if (min < 10) {
            tvSelectHours.setText(hours + ":0" + min);
        } else tvSelectHours.setText(hours + ":" + min);
    }

    private void initData() {
        tvSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar mcurrentTime = Calendar.getInstance();
                year = mcurrentTime.get(Calendar.YEAR);
                month = mcurrentTime.get(Calendar.MONTH);
                day = mcurrentTime.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog pickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year1, int month1, int dayOfMonth) {
                        year = year1;
                        month = month1 + 1;
                        day = dayOfMonth;
                        tvSelectDate.setText(dayOfMonth + "/" + (month1 + 1) + "/" + year1);
                    }
                }, year, month, day);

                pickerDialog.show();
            }
        });
        tvSelectHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar mcurrentTime = Calendar.getInstance();
                hours = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                min = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog pickerDialog = new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hours = hourOfDay; min = minute;
                        if (minute < 10) {
                            tvSelectHours.setText(hourOfDay + ":0" + minute);
                        } else tvSelectHours.setText(hourOfDay + ":" + minute);
                    }
                }, hours, min, true);

                pickerDialog.show();
            }
        });

        ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                String des = edtDes.getText().toString().trim();
                String startDateString = tvSelectDate.getText().toString() + " " + tvSelectHours.getText().toString();
                if (name.length() > 0 && des.length() > 0 && tvSelectDate.getText().toString().length() > 0) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    long millis = 0;
                    Date date = null;
                    try {
                        date = sdf.parse(startDateString);
                        millis = date.getTime();
                        ;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    final Todo todo = new Todo(name, false, null, millis, id);
                    Gson gson = new Gson();
                    final String json = gson.toJson(todo);
                    todo.setTimeStamp(tvSelectHours.getText().toString().trim());
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            APIConnector.post(Define.API_ADD, json, new Callback() {
                                @Override
                                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                                }

                                @Override
                                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                    if (response.isSuccessful()) {
                                        activity.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (addCallback != null) {
                                                    addCallback.onSuccess(year,month,day,hours,min);
                                                    dismiss();
                                                }
                                            }
                                        });
                                    }
                                }
                            });
                        }
                    }).start();
                } else {
                    Toast.makeText(activity, "Thông tin chưa đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public interface AddCallback {
        void onSuccess(int year, int month, int day, int hours, int min);
    }
}
