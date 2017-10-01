package com.msx.lifeache.war3o.property;

import java.text.DecimalFormat;

import com.msx.lifeache.war3o.ItemAbilityLibrary;
import com.msx.lifeache.war3o.Property;
import com.msx.lifeache.war3o.equipment.DPS;
import com.msx.lifeache.war3o.equipment.EquipmentBase;
import com.msx.lifeache.war3o.equipment.EquipmentStandard;

public class AttackSpeed extends Property {
	
	float speed;
	
	public AttackSpeed(Float speed) {
		this.speed = speed;
	}
	@Override
	public void associate(ItemAbilityLibrary lib) {
		this.ability = lib.copyOf("�����ٶȼ�ǿ");
		ability.get("Isx1").setContent(speed);
		ability.get("ansf").setContent("��+" + new DecimalFormat(".0").format(speed * 100.0) +"%��");
	}

	@Override
	public String text() {
		// TODO �Զ����ɵķ������
		return "+" + new DecimalFormat(".0").format(speed * 100.0) + "%�Ĺ����ٶ�";
	}
	
	public void raiseTo(EquipmentBase base,  EquipmentStandard standard) {
		if (base.getDps() == null){
			return;
		}
		DPS dps = base.getDps();
		float baseDps = dps.dps();
		float increment = (standard.getDps() - baseDps) / baseDps;
		float dFreq= (baseDps * increment) / dps.getDamage();
		
		speed = dFreq > 0.5f ? 0.5f : dFreq;
		dps.setFrequency(dps.getFrequency() + speed);
	}
	@Override
	protected boolean hasDps() {
		// TODO �Զ����ɵķ������
		return true;
	}
	@Override
	protected boolean hasTenacity() {
		// TODO �Զ����ɵķ������
		return false;
	}
}
