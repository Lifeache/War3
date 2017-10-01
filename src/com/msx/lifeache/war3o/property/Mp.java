package com.msx.lifeache.war3o.property;

import com.msx.lifeache.war3o.ItemAbilityLibrary;
import com.msx.lifeache.war3o.Property;
import com.msx.lifeache.war3o.equipment.EquipmentBase;
import com.msx.lifeache.war3o.equipment.EquipmentStandard;

public class Mp extends Property {
	
	int mp;
	
	public Mp(int mp) {
		this.mp = mp;
	}
	
	@Override
	public void associate(ItemAbilityLibrary lib) {
		this.ability = lib.copyOf("ħ��ֵ��ǿ");
		ability.get("Iman").setContent(mp);
		ability.get("ansf").setContent("��+" + mp +"��");
	}

	@Override
	public String text() {
		// TODO �Զ����ɵķ������
		return "+" + mp + "��ħ��ֵ";
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
