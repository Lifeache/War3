package com.msx.lifeache.war3o.property;

import com.msx.lifeache.war3o.ItemAbilityLibrary;
import com.msx.lifeache.war3o.Property;
import com.msx.lifeache.war3o.equipment.EquipmentBase;
import com.msx.lifeache.war3o.equipment.EquipmentStandard;

public class HpRecoveryRate extends Property {
	
	int rate;
	
	public HpRecoveryRate(int rate) {
		this.rate = rate;
	}
	@Override
	public void associate(ItemAbilityLibrary lib) {
		this.ability = lib.copyOf("�����ظ���ǿ");
		ability.get("Ihpr").setContent(rate);
		ability.get("ansf").setContent("��+" + rate +"��");

	}

	@Override
	public String text() {
		return "ÿ�������ظ�����" + rate + "��";
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
