package com.example.todolist.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
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

public class DialogRegister extends DialogFragment {

    private Activity activity;
    private Dialog dialog;
    private EditText edtUsername;
    private EditText edtPass;
    private EditText edtEmail;
    private Button btnRegister;
    private String username;
    private String password;
    private String email;
    private int[] role = {3};

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
        dialog.setContentView(R.layout.dialog_register);
        initUI();
        intiData();
        return dialog;
    }

    private void initUI() {
        btnRegister = dialog.findViewById(R.id.btnRegister);
        edtUsername = dialog.findViewById(R.id.edtUsername);
        edtPass = dialog.findViewById(R.id.edtPass);
        edtUsername = dialog.findViewById(R.id.edtUsername);
        edtEmail = dialog.findViewById(R.id.edtEmail);
    }

    private void intiData() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = edtUsername.getText().toString().trim();
                password = edtPass.getText().toString().trim();
                email = edtEmail.getText().toString().trim();
                if (username.length() > 0 && password.length() > 0 && email.length() > 0 && email.contains("@")){
                    final JSONObject object = createObjectRegister();
                    Log.d("LongDinh",object.toString());
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            APIConnector.post(Define.API_REGISTER, object.toString(), new Callback() {
                                @Override
                                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                    activity.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(activity, "Đã xảy ra lỗi kết nối, vui lòng kiểm tra lại kết nối Internet của bạn", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }

                                @Override
                                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                    if (response.isSuccessful()){
                                        parseData(response.body().string());
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
                    Toast.makeText(activity, "Thông tin đăng kí chưa đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void parseData(String response) {
        try {
            JSONObject object = new JSONObject(response);
            Gson gson = new Gson();
            User user = gson.fromJson(String.valueOf(object),User.class);
            if (user != null){
               activity.runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       Toast.makeText(activity, "Bạn đã đăng kí thành công", Toast.LENGTH_SHORT).show();
                       dismiss();
                   }
               });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject createObjectRegister() {
        JSONObject object = new JSONObject();
        try {
            object.put(Define.username,username);
            object.put(Define.password, password);
            object.put(Define.email,email);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object;
    }
}
