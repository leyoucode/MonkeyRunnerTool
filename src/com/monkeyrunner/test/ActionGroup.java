package com.monkeyrunner.test;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ActionGroup")
public class ActionGroup {
	
	@XStreamAlias("Count")
	private int count;//执行次数
	
	@XStreamAlias("WaitTime")
	private int waitTime;// 执行动作等待时间
	
	@XStreamAlias("Actions")
	private Actions actions;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public Actions getActions() {
		return actions;
	}

	public void setActions(Actions actions) {
		this.actions = actions;
	}

}
