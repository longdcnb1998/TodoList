package com.example.todolist.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.todolist.R;
import com.example.todolist.model.Todo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class TodoListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Todo> list;

    public TodoListAdapter(Context context, ArrayList<Todo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.todo_item, parent, false);

        }
        ConstraintLayout layout;
        TextView tv_icon, tv_title, tv_content;
        tv_icon = convertView.findViewById(R.id.textView_Icon);
        tv_title = convertView.findViewById(R.id.textView_titleCat);
        tv_content = convertView.findViewById(R.id.textView_contentCat);
        layout = convertView.findViewById(R.id.ltItem);

        Todo todo = (Todo) getItem(position);
        if (todo != null) {
            String title = todo.getName();
            String icon = title.toUpperCase().charAt(0) + "";
            tv_title.setText(title);
            tv_icon.setText(icon);
            tv_content.setText(todo.getTimeStamp());
            if (todo.isStatus()) {
                layout.setBackgroundColor(context.getResources().getColor(R.color.item_done));
            }
        }
        return convertView;
    }


}
