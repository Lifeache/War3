package com.msx.lifeache.war3o.property;

import java.text.DecimalFormat;

import com.msx.lifeache.war3o.ItemAbilityLibrary;
import com.msx.lifeache.war3o.Property;
import com.msx.lifeache.war3o.equipment.EquipmentBase;
import com.msx.lifeache.war3o.equipment.EquipmentStandard;

public class MpRecoveryRate extends Property {
	
	float rate;
	
	public MpRecoveryRate(float rate) {
		this.rate = rate;
	}
	
	@Override
	public void associate(ItemAbilityLibrary lib) {
		this.ability = lib.copyOf("ħ���ظ���ǿ");
		ability.get("Imrp").setContent(rate);
		ability.get("ansf").setContent("��+" + new DecimalFormat("0.00").format(rate) +"��");
	}

	@Override
	public String text() {
		return "ÿ��ħ���ظ�����" + new DecimalFormat("0.00").format(rate) + "��";
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
