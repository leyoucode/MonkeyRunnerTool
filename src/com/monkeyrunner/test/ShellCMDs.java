package com.monkeyrunner.test;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("ShellCMDs")
public class ShellCMDs {

	@XStreamImplicit(itemFieldName="cmd")
	private List<String> cmds;

	public List<String> getCmds() {
		return cmds;
	}

	public void setCmds(List<String> cmds) {
		this.cmds = cmds;
	}
	
}
