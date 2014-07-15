package com.monkeyrunner.test;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("Action")
public class Action {

	public Action() {
	}

	@XStreamAlias("Operation")
	private int operation;// 屏幕操作，对应枚举Operation.java文件 点击：0，长按：1，拖动：2，输入：3
	
	@XStreamAlias("ViewId")
	private String viewId;// 被点击的ViewID
	
	@XStreamAlias("PointX")
	private int pointX;// 被点击点的x坐标
	
	@XStreamAlias("PointY")
	private int pointY;// 被点击点的y坐标
	
	@XStreamAlias("StartPointX")
	private int startPointX;// 拖动起始点的x坐标
	
	@XStreamAlias("StartPointY")
	private int startPointY;// 拖动起始点的y坐标
	
	@XStreamAlias("EndPointX")
	private int endPointX;// 拖动结束点的x坐标
	
	@XStreamAlias("EndPointY")
	private int endPointY;// 拖动结束点的y坐标
	
	@XStreamAlias("Text")
	private int text;// 输入的文字 只限ASC
	
	@XStreamAlias("WaitTime")
	private int waitTime;// 执行动作等待时间
	
	@XStreamAlias("AppPackage")
	private String appPackage;// App 包名
	
	@XStreamAlias("MainActivity")
	private String mainActivity;// App 主Activity
	
	@XStreamAlias("ShellCMDs")
	private ShellCMDs shellCMDs;
	
	@XStreamAlias("PressKeyCode")
	private String pressKeyCode;
	
	@XStreamAlias("Describtion")
	private String describtion;// 描述

	@Override
	public String toString() {
		return "describtion:" + describtion + " operation:" + operation
				+ " viewId:" + viewId + " pointX:" + pointX + " pointY:"
				+ pointY + " startPointX:" + startPointX + " startPointY:"
				+ startPointY + " endPointX:" + endPointX + " endPointY:"
				+ endPointY + " text:" + text + " waitTime:" + waitTime
				+ " appPackage:" + appPackage + " mainActivity:" + mainActivity;
	}

	public int getOperation() {
		return operation;
	}

	public void setOperation(int operation) {
		this.operation = operation;
	}

	public String getViewId() {
		return viewId;
	}

	public void setViewId(String viewId) {
		this.viewId = viewId;
	}

	public int getPointX() {
		return pointX;
	}

	public void setPointX(int pointX) {
		this.pointX = pointX;
	}

	public int getPointY() {
		return pointY;
	}

	public void setPointY(int pointY) {
		this.pointY = pointY;
	}

	public int getStartPointX() {
		return startPointX;
	}

	public void setStartPointX(int startPointX) {
		this.startPointX = startPointX;
	}

	public int getStartPointY() {
		return startPointY;
	}

	public void setStartPointY(int startPointY) {
		this.startPointY = startPointY;
	}

	public int getEndPointX() {
		return endPointX;
	}

	public void setEndPointX(int endPointX) {
		this.endPointX = endPointX;
	}

	public int getEndPointY() {
		return endPointY;
	}

	public void setEndPointY(int endPointY) {
		this.endPointY = endPointY;
	}

	public int getText() {
		return text;
	}

	public void setText(int text) {
		this.text = text;
	}

	public String getDescribtion() {
		return describtion;
	}

	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public String getAppPackage() {
		return appPackage;
	}

	public void setAppPackage(String appPackage) {
		this.appPackage = appPackage;
	}

	public String getMainActivity() {
		return mainActivity;
	}

	public void setMainActivity(String mainActivity) {
		this.mainActivity = mainActivity;
	}

	public ShellCMDs getShellCMDs() {
		return shellCMDs;
	}

	public void setShellCMDs(ShellCMDs shellCMDs) {
		this.shellCMDs = shellCMDs;
	}

	public String getPressKeyCode() {
		return pressKeyCode;
	}

	public void setPressKeyCode(String pressKeyCode) {
		this.pressKeyCode = pressKeyCode;
	}

}
