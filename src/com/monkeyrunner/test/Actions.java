package com.monkeyrunner.test;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("Actions")
public class Actions {
	
	@XStreamImplicit(itemFieldName="ActionWarp")
	private List<ActionWarp> actionWarps;
	
//	@XStreamAlias("ActionGroup")
//	private List actionGroup;
	
	public Actions() {
	}

	public List<ActionWarp> getActionWarps() {
		return actionWarps;
	}

	public void setActionWarps(List<ActionWarp> actionWarps) {
		this.actionWarps = actionWarps;
	}


//	public ActionGroup getActionGroup() {
//		return actionGroup;
//	}
//
//	public void setActionGroup(ActionGroup actionGroup) {
//		this.actionGroup = actionGroup;
//	}
	
	
}
