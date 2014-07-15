package com.monkeyrunner.test;

public enum Operation {

	Click("点击",0), 
	LongPress("长按",1),
	Drag("拖动",2),
	Input("输入",3),
	StartApp("启动App",4),
	StopApp("结束App",5),
	ShellCMD("Shell命令",6),
	Press("按下",7);
	
	private int index;
	
	private String name;
	
	private Operation(String name, int index) {  
        this.name = name;  
        this.index = index;  
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	} 
	
	
}
