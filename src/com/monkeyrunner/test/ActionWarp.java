package com.monkeyrunner.test;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ActionWarp")
public class ActionWarp {

	@XStreamAlias("ActionType")
	private int actionType;
	
	@XStreamAlias("Action")
	private Action action;
	
	@XStreamAlias("ActionGroup")
	private ActionGroup actionGroup;

	public int getActionType() {
		return actionType;
	}

	public void setActionType(int actionType) {
		this.actionType = actionType;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public ActionGroup getActionGroup() {
		return actionGroup;
	}

	public void setActionGroup(ActionGroup actionGroup) {
		this.actionGroup = actionGroup;
	}

	
}
