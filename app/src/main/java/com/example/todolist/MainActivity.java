package com.example.todolist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolist.Notification.AlertReceiver;
import com.example.todolist.adapter.TodoListAdapter;
import com.example.todolist.dialog.DialogAdd;
import com.example.todolist.dialog.DialogEdit;
import com.example.todolist.dialog.DialogLogin;
import com.example.todolist.model.Todo;
import com.example.todolist.model.User;
import com.example.todolist.swipemenulistview.SwipeMenu;
import com.example.todolist.swipemenulistview.SwipeMenuCreator;
import com.example.todolist.swipemenulistview.SwipeMenuItem;
import com.example.todolist.swipemenulistview.SwipeMenuListView;
import com.example.todolist.util.DPIUtils;
import com.example.todolist.util.DialogUtils;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private int userID;
    private String jwt;
    private Activity activity;
    private ImageButton ivAdd;
    private TextView tvTitle;
    private SwipeMenuListView listView;
    private TodoListAdapter adapter;
    private ArrayList<Todo> listTodo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;

        preferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        String username = preferences.getString("username", "");
        userID = preferences.getInt("id", -1);
        jwt = preferences.getString(Define.jwt, "");

        if (username.length() > 0) {
            initUI();
            initData();
            initClick();
        } else {
            if (DialogUtils.enableShowDialogFragment(getSupportFragmentManager(), DialogLogin.class.getSimpleName())) {
                new DialogLogin(new DialogLogin.LoginCallBack() {
                    @Override
                    public void onLoginSuccess(User user) {
                        userID = user.getId();
                        jwt = preferences.getString(Define.jwt, "");
                        initUI();
                        initData();
                        initClick();
                    }
                }).show(getSupportFragmentManager(), DialogLogin.class.getSimpleName());
            }
        }

    }

    private void initClick() {
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DialogUtils.enableShowDialogFragment(getSupportFragmentManager(), DialogAdd.class.getSimpleName())) {
                    new DialogAdd(userID, new DialogAdd.AddCallback() {
                        @Override
                        public void onSuccess(String time,String day) {
                            initData();
                            Todo todo = listTodo.get(listTodo.size() -1);
                            AlarmManager alarmManager = (AlarmManager) activity.getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                            final Intent intent = new Intent(activity.getApplicationContext(), AlertReceiver.class);
                            PendingIntent pending = PendingIntent.getBroadcast(activity.getApplicationContext(),Integer.parseInt(todo.getId()),intent,0);
                            Calendar calendar = Calendar.getInstance();
                            String[] cal = time.split(":");
                            int hours_alarm = Integer.parseInt(cal[0]);
                            int minute_alarm = Integer.parseInt(cal[1]);
                            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
                            calendar.set(Calendar.HOUR_OF_DAY,hours_alarm);
                            calendar.set(Calendar.MINUTE, minute_alarm);
                            calendar.set(Calendar.SECOND, 0);
                            calendar.set(Calendar.MILLISECOND, 0);
                            intent.putExtra("id",todo.getId());
                            startAlarm(calendar,pending,alarmManager);
                        }
                    }).show(getSupportFragmentManager(), DialogAdd.class.getSimpleName());
                }
            }
        });
    }

    private void initUI() {
        ivAdd = findViewById(R.id.ivRight);
        tvTitle = findViewById(R.id.tvTitle);
        listView = findViewById(R.id.lvToDoList);
        tvTitle.setText(activity.getString(R.string.app_name));

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // edit item
                SwipeMenuItem edit = new SwipeMenuItem(activity);
                edit.setBackgroundResource(R.drawable.bg_button_swipe);
                edit.setIconColor(ColorUtils.getColor(activity, R.color.blue));
                edit.setWidth(DPIUtils.dpToPx(activity, 65));
                edit.setIcon(R.mipmap.ic_edit);
                menu.addMenuItem(edit);

                // delete item
                SwipeMenuItem delete = new SwipeMenuItem(activity);
                delete.setBackgroundResource(R.drawable.bg_button_swipe);
                delete.setIconColor(ColorUtils.getColor(activity, R.color.red));
                delete.setWidth(DPIUtils.dpToPx(activity, 65));
                delete.setIcon(R.mipmap.ic_trash);
                menu.addMenuItem(delete);

            }
        };
        listView.setMenuCreator(creator);
    }

    private void initData() {
        if (jwt.length() > 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    APIConnector.get(Define.API_GET_LIST + userID, jwt, new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                            e.printStackTrace();
                        }

                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            if (response.isSuccessful()) {
                                parseData(response.body().string());
                            } else {
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
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void parseData(String response) {
        if (response != null){
            listTodo = new ArrayList<>();
            try {
                JSONArray array = new JSONArray(response);
                for (int i =0 ;i< array.length();i++){
                    JSONObject object = array.getJSONObject(i);
                    String id = object.getString("id");
                    String name = object.getString("name");
                    boolean status = object.getBoolean("status");
                    String timestamp = object.getString("time");
                    DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
                    final ZonedDateTime parsed = ZonedDateTime.parse(timestamp, formatter);
                    long offsetMillis = ZoneOffset.from(parsed).getTotalSeconds() * 1000;
                    long isoMillis = parsed.toInstant().toEpochMilli();
                    Date date = new Date(isoMillis + offsetMillis);
                    String pattern = "dd/MM/yyyy HH:mm:ss";
                    DateFormat df = new SimpleDateFormat(pattern);
                    String todayAsString = df.format(date);
                    long millis = date.getTime();

                    Todo todo = new Todo(name,status,null,millis,userID);
                    todo.setTimeStamp(todayAsString);
                    todo.setId(id);
                    listTodo.add(todo);
                }

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initListTodo();
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void initListTodo() {
        if (listTodo != null ){
            adapter = new TodoListAdapter(activity,listTodo);
            listView.setAdapter(adapter);
        }

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(final int position, SwipeMenu menu, int index) {
                switch (index){
                    case 0 :
                        if (DialogUtils.enableShowDialogFragment(getSupportFragmentManager(), DialogEdit.class.getSimpleName())){
                            new DialogEdit(listTodo.get(position), new DialogEdit.Callback() {
                                @Override
                                public void onSuccess(Todo todo) {
                                    initData();
                                }
                            }).show(getSupportFragmentManager(),DialogEdit.class.getSimpleName());
                        }
                        break;
                    case 1:
                        AlertDialog.Builder builder = new AlertDialog.Builder((activity));
                        builder.setMessage("Bạn có chắc chắn muốn xóa công việc ?");

                        // add a button
                        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DeleteTodo(position);
                            }
                        });

                        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.setNegativeButton(android.R.string.no, null).setIcon(android.R.drawable.ic_dialog_alert);
                        // create and show the alert dialog
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(activity.getResources().getColor(R.color.blue));
                        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(activity.getResources().getColor(R.color.gray));
                        break;
                }
                return false;
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                listView.smoothOpenMenu(position);
                return true;
            }
        });

    }

    private void DeleteTodo(final int position) {
        new Thread(new Runnable() {
            @Override
            public void run() {
              APIConnector.delete(Define.API_DELETE + listTodo.get(position).getId(), new Callback() {
                  @Override
                  public void onFailure(@NotNull Call call, @NotNull IOException e) {
                      e.printStackTrace();
                  }

                  @Override
                  public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if (response.isSuccessful()){
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    listTodo.remove(position);
                                    adapter.notifyDataSetChanged();
                                }
                            });
                        }
                        else {
                            Toast.makeText(activity, "Something wrong", Toast.LENGTH_SHORT).show();
                        }
                  }
              });
            }
        }).start();
    }


    private void startAlarm(Calendar calendar, PendingIntent pending, AlarmManager alarmManager) {
        Calendar now = Calendar.getInstance();
        Log.d("DayofWeek", now.get(Calendar.DAY_OF_WEEK) + " ");
        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DATE, 7);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pending);
        }
    }
    public void cancelAlarm(int id, Intent intent){
        PendingIntent pending = PendingIntent.getBroadcast(activity.getApplicationContext(),id,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager) activity.getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pending);

    }
    public void deleteAllAlarm(int id){
        Intent intent =  new Intent(activity.getApplicationContext(), AlertReceiver.class);
        PendingIntent pending = PendingIntent.getBroadcast(activity.getApplicationContext(),id,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager) activity.getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pending);
    }
}