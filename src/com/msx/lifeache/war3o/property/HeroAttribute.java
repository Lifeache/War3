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
		ability = lib.copyOf("Ӣ�����Լ�ǿ");
		ability.get("Iint").setContent(ite);
		ability.get("Iagi").setContent(agi);
		ability.get("Istr").setContent(str);
		ability.get("ansf").setContent("��+" + str + " +" + agi + " +" + ite +"��");

	}

	@Override
	public String text() {
		String t = "";
		if (str != 0){
			t += "+" + str + "������ֵ";
		}
		if (agi != 0){
			t += "+" + agi + "������ֵ";
		}
		if (ite != 0){
			t += "+" + ite + "������ֵ";
		}
		return t;
	}
	
	public String[] texts() {
		String[] t = new String[3];
		if (str != 0){
			t[0] = "+" + str + "������ֵ";
		}
		if (agi != 0){
			t[1] = "+" + agi + "������ֵ";
		}
		if (ite != 0){
			t[2] = "+" + ite + "������ֵ";
		}
		
		
		return t;
	}

	@Override
	protected void raiseTo(EquipmentBase base, EquipmentStandard standard) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	protected boolean hasDps() {
		// TODO �Զ����ɵķ������
		return false;
	}

	@Override
	protected boolean hasTenacity() {
		// TODO �Զ����ɵķ������
		return false;
	}

}
