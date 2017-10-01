package com.msx.lifeache.war3o;

import java.util.Vector;

public class Ability {
	String model;
	String name;
	Vector<Option> options;
	int optionCnt;
	
	public Ability(String model,String name) {
		this.model = model;
		this.name = name;
		options = new Vector<>();
	}
	
	public Ability(String name) {
		this("", name);
	}
	
	public void put(Option option){
		options.add(option);
	}
	
	public Option get(int index){
		return options.get(index);
	}

	
	public int getOptionCnt() {
		return optionCnt;
	}

	public void setOptionCnt(int optionCnt) {
		this.optionCnt = optionCnt;
	}
	
	public Ability createCopy(){
		Ability copy = new Ability(name);
		copy.model = model;
		copy.optionCnt = optionCnt;
		copy.options = new Vector<>();
		for (Option option : options) {
			copy.options.add(option.createCopy());
		}
		return copy;
	}
	
	public Option get(String dataName){
		for (Option option : options) {
			if (option.dataName.equals(dataName)){
				return option;
			}
		}
		return null;
	}

}
