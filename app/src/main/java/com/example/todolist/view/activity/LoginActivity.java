package com.example.todolist.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todolist.APIConnector;
import com.example.todolist.Define;
import com.example.todolist.R;
import com.example.todolist.dialog.DialogRegister;
import com.example.todolist.model.User;
import com.example.todolist.util.DialogUtils;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private Activity activity;
    private EditText edtUsername;
    private EditText  edtPass;
    private EditText edtEmail;

    private Button btnRegister, btnLogin;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        String username = preferences.getString("username", "");
        activity = this;
        if (username.length() == 0) {
            initUI();
            initData();
        } else {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

    }

    private void initUI() {
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        edtUsername = findViewById(R.id.edtUsername);
        edtPass = findViewById(R.id.edtPass);
        preferences = activity.getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    private void initData() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DialogUtils.enableShowDialogFragment(getSupportFragmentManager(), DialogRegister.class.getSimpleName())){
                    new DialogRegister().show(getSupportFragmentManager(),DialogRegister.class.getSimpleName());
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final JSONObject object = createDataLogin();
                if (object != null){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            APIConnector.post(Define.API_LOGIN, object.toString(), new Callback() {
                                @Override
                                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                    if (response.isSuccessful()){
                                        try {
                                            JSONObject result = new JSONObject(response.body().string());
                                            String jwt = result.getString(Define.jwt);
                                            String data = result.getString("user");
                                            Gson gson = new Gson();
                                            final User user = gson.fromJson(data, User.class);
                                            editor.putString(Define.jwt,jwt);
                                            editor.putString("username",user.getUsername());
                                            editor.putString("password",user.getPassword());
                                            editor.putInt("id",user.getId());
                                            editor.putString(Define.jwt,jwt);
                                            editor.commit();
                                            activity.runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                                    startActivity(intent);
                                                }
                                            });
                                        } catch (JSONException e) {
                                            e.printStackTrace();
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
            }
        });
    }

    private JSONObject createDataLogin() {
        JSONObject object = new JSONObject();
        String identifier = edtUsername.getText().toString().trim();
        String password = edtPass.getText().toString().trim();
        if (identifier.length() > 0 && password.length() > 0){
            try {
                object.put(Define.identifier,identifier);
                object.put(Define.password, password);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        else {
            Toast.makeText(activity, "Thông tin chưa đúng", Toast.LENGTH_SHORT).show();
        }
        return object;
    }
}