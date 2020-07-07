package com.example.todolist.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.todolist.APIConnector;
import com.example.todolist.Define;
import com.example.todolist.R;
import com.example.todolist.model.Todo;
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

public class DialogLogin extends DialogFragment {

    private Activity activity;
    private Dialog dialog;
    private EditText  edtUsername;
    private EditText  edtPass;
    private EditText edtEmail;
    private LoginCallBack loginCallBack;

    private Button btnRegister, btnLogin;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    public DialogLogin(LoginCallBack loginCallBack) {
        this.loginCallBack = loginCallBack;
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        dialog = DialogUtils.getNewDialog(activity);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_login);
        initUI();
        initData();
        return dialog;
    }

    private void initUI() {
        btnLogin = dialog.findViewById(R.id.btnLogin);
        btnRegister = dialog.findViewById(R.id.btnRegister);
        edtUsername = dialog.findViewById(R.id.edtUsername);
        edtPass = dialog.findViewById(R.id.edtPass);
        preferences = activity.getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    private void initData() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DialogUtils.enableShowDialogFragment(getChildFragmentManager(),DialogRegister.class.getSimpleName())){
                    new DialogRegister().show(getChildFragmentManager(),DialogRegister.class.getSimpleName());
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
                                            editor.commit();
                                            activity.runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    if (loginCallBack != null){
                                                        loginCallBack.onLoginSuccess(user);
                                                    }
                                                }
                                            });
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        dismiss();
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

    public interface LoginCallBack {
        void onLoginSuccess(User user);
    }
}
