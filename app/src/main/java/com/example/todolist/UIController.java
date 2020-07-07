package com.example.todolist;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * @author hoangcaomobile
 * @since Monday, April 13, 2015
 */
public class UIController {

    public static void setupHeader(Activity activity, View headerView, int iconLeft, OnClickListener onClickLeft) {
        ImageView ivLeft = (ImageView) headerView.findViewById(R.id.ivLeft);
        ImageView ivRight = (ImageView) headerView.findViewById(R.id.ivRight);
        TextView tvTitle = (TextView) headerView.findViewById(R.id.tvTitle);

        if (AppSettings.isLightTheme(activity)) {
            ColorController.setTintColor(activity, ivLeft);
            ColorController.setTintColor(activity, ivRight);
            tvTitle.setTextColor(ColorUtils.getColor(activity, R.color.text));
            headerView.findViewById(R.id.viewLine).setVisibility(View.VISIBLE);
        } else {
            ColorController.setBackgroundColor(activity, headerView);
        }

        ivLeft.setImageResource(iconLeft);
        ivLeft.setOnClickListener(onClickLeft);
        tvTitle.setText(AppUtils.getAppName(activity));
    }

    public static void setupHeader(Activity activity, View headerView, int iconLeft, OnClickListener onClickLeft, String title) {
        ImageView ivLeft = (ImageView) headerView.findViewById(R.id.ivLeft);
        ImageView ivRight = (ImageView) headerView.findViewById(R.id.ivRight);
        TextView tvTitle = (TextView) headerView.findViewById(R.id.tvTitle);

        if (AppSettings.isLightTheme(activity)) {
            ColorController.setTintColor(activity, ivLeft);
            ColorController.setTintColor(activity, ivRight);
            tvTitle.setTextColor(ColorUtils.getColor(activity, R.color.text));
            headerView.findViewById(R.id.viewLine).setVisibility(View.VISIBLE);
        } else {
            ColorController.setBackgroundColor(activity, headerView);
        }

        ivLeft.setImageResource(iconLeft);
        ivLeft.setOnClickListener(onClickLeft);
        tvTitle.setText(title);
    }

    public static void setupHeader(Activity activity, View headerView, int iconLeft, OnClickListener onClickleft, int iconRight, OnClickListener onClickRight) {
        ImageView ivLeft = (ImageView) headerView.findViewById(R.id.ivLeft);
        ImageView ivRight = (ImageView) headerView.findViewById(R.id.ivRight);
        TextView tvTitle = (TextView) headerView.findViewById(R.id.tvTitle);

        if (AppSettings.isLightTheme(activity)) {
            ColorController.setTintColor(activity, ivLeft);
            ColorController.setTintColor(activity, ivRight);
            tvTitle.setTextColor(ColorUtils.getColor(activity, R.color.text));
            headerView.findViewById(R.id.viewLine).setVisibility(View.VISIBLE);
        } else {
            ColorController.setBackgroundColor(activity, headerView);
        }

        ivLeft.setImageResource(iconLeft);
        ivLeft.setOnClickListener(onClickleft);
        ivRight.setImageResource(iconRight);
        ivRight.setOnClickListener(onClickRight);
        tvTitle.setText(AppUtils.getAppName(activity));
    }

    public static void setupHeader(Activity activity, View headerView, int iconLeft, OnClickListener onClickleft, int iconRight, OnClickListener onClickRight, String title) {
        ImageView ivLeft = (ImageView) headerView.findViewById(R.id.ivLeft);
        ImageView ivRight = (ImageView) headerView.findViewById(R.id.ivRight);
        TextView tvTitle = (TextView) headerView.findViewById(R.id.tvTitle);

        if (AppSettings.isLightTheme(activity)) {
            ColorController.setTintColor(activity, ivLeft);
            ColorController.setTintColor(activity, ivRight);
            tvTitle.setTextColor(ColorUtils.getColor(activity, R.color.text));
            headerView.findViewById(R.id.viewLine).setVisibility(View.VISIBLE);
        } else {
            ColorController.setBackgroundColor(activity, headerView);
        }

        ivLeft.setImageResource(iconLeft);
        ivLeft.setOnClickListener(onClickleft);
        ivRight.setImageResource(iconRight);
        ivRight.setOnClickListener(onClickRight);
        tvTitle.setText(title);
    }

    public static void updateUIHeader(Activity activity, View headerView) {
        ImageView ivLeft = (ImageView) headerView.findViewById(R.id.ivLeft);
        ImageView ivRight = (ImageView) headerView.findViewById(R.id.ivRight);
        TextView tvTitle = (TextView) headerView.findViewById(R.id.tvTitle);
        View viewLine = (View) headerView.findViewById(R.id.viewLine);

        if (AppSettings.isLightTheme(activity)) {
            ColorController.setBackgroundColor(headerView, Color.WHITE);
            ColorController.setTintColor(activity, ivLeft);
            ColorController.setTintColor(activity, ivRight);
            tvTitle.setTextColor(ColorUtils.getColor(activity, R.color.text));
            viewLine.setVisibility(View.VISIBLE);
        } else {
            ColorController.setBackgroundColor(activity, headerView);
            ColorController.setTintColorWhite(activity, ivLeft);
            ColorController.setTintColorWhite(activity, ivRight);
            tvTitle.setTextColor(Color.WHITE);
            viewLine.setVisibility(View.GONE);
        }
    }

    public static void updateUIHeader(Activity activity, View headerView, int color) {
        ImageView ivLeft = (ImageView) headerView.findViewById(R.id.ivLeft);
        ImageView ivRight = (ImageView) headerView.findViewById(R.id.ivRight);
        TextView tvTitle = (TextView) headerView.findViewById(R.id.tvTitle);
        View viewLine = (View) headerView.findViewById(R.id.viewLine);

        if (AppSettings.isLightTheme(activity)) {
            ColorController.setBackgroundColor(headerView, Color.WHITE);
            ColorController.setTintColor(ivLeft, color);
            ColorController.setTintColor(ivRight, color);
            tvTitle.setTextColor(ColorUtils.getColor(activity, R.color.text));
            viewLine.setVisibility(View.VISIBLE);
        } else {
            ColorController.setBackgroundColor(headerView, color);
            ColorController.setTintColorWhite(activity, ivLeft);
            ColorController.setTintColorWhite(activity, ivRight);
            tvTitle.setTextColor(Color.WHITE);
            viewLine.setVisibility(View.GONE);
        }
    }
}