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
		this.ability = lib.copyOf("魔法回复加强");
		ability.get("Imrp").setContent(rate);
		ability.get("ansf").setContent("（+" + new DecimalFormat("0.00").format(rate) +"）");
	}

	@Override
	public String text() {
		return "每秒魔法回复增加" + new DecimalFormat("0.00").format(rate) + "点";
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
