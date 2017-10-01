package com.msx.lifeache.war3o.property;

import com.msx.lifeache.war3o.ItemAbilityLibrary;
import com.msx.lifeache.war3o.Property;
import com.msx.lifeache.war3o.equipment.DPS;
import com.msx.lifeache.war3o.equipment.EquipmentBase;
import com.msx.lifeache.war3o.equipment.EquipmentStandard;

public class Attack extends Property{
	
	int atk;
	
	public Attack(int atk) {
		this.atk = atk;
	}
	
	public void setAttack(int atk) {
		this.atk = atk;
		
	}
	
	public int getAttack() {
		return atk;
	}
	
	@Override
	public String text() {
		
		return "+" + atk + "���˺�";
	}

	@Override
	public void associate(ItemAbilityLibrary lib) {
		this.ability = lib.copyOf("��������ǿ");
		ability.get("Iatt").setContent(atk);
		ability.get("ansf").setContent("��+" + atk +"��");
	}

	@Override
	public void raiseTo(EquipmentBase base,  EquipmentStandard standard) {
		if (base.getDps() == null){
			return;
		}
		DPS dps = base.getDps();
		float baseDps = dps.dps();
		float increment = (standard.getDps() - baseDps) / baseDps;
		int dAtk= (int)((baseDps * increment) / dps.getFrequency());
		
		atk = dAtk;
		dps.setDamage(dps.getDamage() + dAtk);
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
