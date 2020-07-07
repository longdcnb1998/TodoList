package com.example.todolist.swipemenulistview;


import android.content.Context;

public class SwipeMenuItem {

	private int id;
	private Context mContext;
	private String title;
	private int titleColor;
	private int titleSize;
	private int width;
	private int backgroundColor = 0;
	private int backgroundResource = 0;
	private int iconColor = 0;
	private int icon = 0;

	public SwipeMenuItem(Context context) {
		mContext = context;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTitleColor() {
		return titleColor;
	}

	public int getTitleSize() {
		return titleSize;
	}

	public void setTitleSize(int titleSize) {
		this.titleSize = titleSize;
	}

	public void setTitleColor(int titleColor) {
		this.titleColor = titleColor;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTitle(int resId) {
		setTitle(mContext.getString(resId));
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setBackgroundColor(int color) {
		this.backgroundColor = color;
	}
	public int getBackgroundColor() {
		return this.backgroundColor;
	}
	
	public void setBackgroundResource(int resourceId) {
		this.backgroundResource = resourceId;
	}
	public int getBackgroundResource() {
		return this.backgroundResource;
	}
	
	public void setIconColor(int color) {
		this.iconColor = color;
	}
	public int getIconColor() {
		return this.iconColor;
	}
	
	public void setIcon(int icon) {
		this.icon = icon;
	}
	public int getIcon() {
		return this.icon;
	}
}