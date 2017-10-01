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
		this.ability = lib.copyOf("生命回复加强");
		ability.get("Ihpr").setContent(rate);
		ability.get("ansf").setContent("（+" + rate +"）");

	}

	@Override
	public String text() {
		return "每秒生命回复增加" + rate + "点";
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
