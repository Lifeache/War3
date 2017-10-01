package com.msx.lifeache.war3o.property;

import com.msx.lifeache.war3o.ItemAbilityLibrary;
import com.msx.lifeache.war3o.Property;
import com.msx.lifeache.war3o.equipment.EquipmentBase;
import com.msx.lifeache.war3o.equipment.EquipmentStandard;

public class HeroAttribute extends Property {
	
	int ite;
	int agi;
	int str;
	
	public HeroAttribute(int ite,int agi,int str) {
		this.ite = ite;
		this.agi = agi;
		this.str = str;
	}
	
	@Override
	public void associate(ItemAbilityLibrary lib) {
		ability = lib.copyOf("英雄属性加强");
		ability.get("Iint").setContent(ite);
		ability.get("Iagi").setContent(agi);
		ability.get("Istr").setContent(str);
		ability.get("ansf").setContent("（+" + str + " +" + agi + " +" + ite +"）");

	}

	@Override
	public String text() {
		String t = "";
		if (str != 0){
			t += "+" + str + "点力量值";
		}
		if (agi != 0){
			t += "+" + agi + "点敏捷值";
		}
		if (ite != 0){
			t += "+" + ite + "点智力值";
		}
		return t;
	}
	
	public String[] texts() {
		String[] t = new String[3];
		if (str != 0){
			t[0] = "+" + str + "点力量值";
		}
		if (agi != 0){
			t[1] = "+" + agi + "点敏捷值";
		}
		if (ite != 0){
			t[2] = "+" + ite + "点智力值";
		}
		
		
		return t;
	}

	@Override
	protected void raiseTo(EquipmentBase base, EquipmentStandard standard) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	protected boolean hasDps() {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	protected boolean hasTenacity() {
		// TODO 自动生成的方法存根
		return false;
	}

}
