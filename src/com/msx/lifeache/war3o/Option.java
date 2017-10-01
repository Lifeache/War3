package com.msx.lifeache.war3o;

public class Option {
	String editorName;
	String dataName;
	int type;
	Object content;
	int level;
	int unknownOnes;
	public Option(String dataName,int type,Object content) {
		this(dataName, 0, type, content);
	}
	
	public Option(String dataName,int level,int type,Object content) {
		this.dataName = dataName;
		this.level = level;
		this.type = type;
		this.content = content;
	}
	
	public Option createCopy(){
		Option copy = new Option(dataName, level,type, content);
		copy.unknownOnes = unknownOnes;
		return copy;
	}
	
	public void setContent(Object content) {
		this.content = content;
	}
}
