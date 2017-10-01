package com.msx.lifeache.war3o.property;

import com.msx.lifeache.war3o.ItemAbilityLibrary;
import com.msx.lifeache.war3o.Property;
import com.msx.lifeache.war3o.equipment.EquipmentBase;
import com.msx.lifeache.war3o.equipment.EquipmentStandard;

public class MovementSpeed extends Property {
	
	int speed;
	
	public MovementSpeed(int speed) {
		this.speed = speed;
	}
	
	@Override
	public void associate(ItemAbilityLibrary lib) {
		this.ability = lib.copyOf("�ƶ��ٶȼ�ǿ");
		ability.get("Imvb").setContent(speed);
		ability.get("ansf").setContent("��+" + speed +"��");
	}

	@Override
	public String text() {
		return "+" + speed + "���ƶ��ٶ�";
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
