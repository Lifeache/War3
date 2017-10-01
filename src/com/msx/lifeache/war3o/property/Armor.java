package com.msx.lifeache.war3o.property;

import com.msx.lifeache.war3o.ItemAbilityLibrary;
import com.msx.lifeache.war3o.Property;
import com.msx.lifeache.war3o.equipment.EquipmentBase;
import com.msx.lifeache.war3o.equipment.EquipmentStandard;
import com.msx.lifeache.war3o.equipment.Tenacity;

public class Armor extends Property {
	
	int armor;
	
	public Armor(int armor) {
		this.armor = armor;
	}
	
	@Override
	public void associate(ItemAbilityLibrary lib) {
		this.ability = lib.copyOf("���׼�ǿ");
		ability.get("Idef").setContent(armor);
		ability.get("ansf").setContent("��+" + armor +"��");
	}

	@Override
	public String text() {
		return "+" + armor + "�㻤��ֵ";
	}

	@Override
	protected void raiseTo(EquipmentBase base, EquipmentStandard standard) {
		if (base.getTenacity() == null){
			return;
		}
		Tenacity tenacity = base.getTenacity();
		float baseTenacity = tenacity.tenacity();
		float increment = (standard.getTenacity() - baseTenacity) / baseTenacity;
		float armor = tenacity.getArmor();
		float dr = (1 - (0.01f * armor / (0.01f * armor + 1)));
		System.out.println("ԭ����=" + ( 1 - dr));
		float c = 1 - dr / (1 + increment);//(increment + dr) / (increment + 1);
		System.out.println("�ּ���=" + c);
		armor =(c / (0.01f * (1 - c)) - armor);
		this.armor =  (int)armor;
		tenacity.setArmor(tenacity.getArmor() + this.armor);
	}

	@Override
	protected boolean hasDps() {
		// TODO �Զ����ɵķ������
		return false;
	}

	@Override
	protected boolean hasTenacity() {
		// TODO �Զ����ɵķ������
		return true;
	}
	

}
