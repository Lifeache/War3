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
		this.ability = lib.copyOf("魔法值加强");
		ability.get("Iman").setContent(mp);
		ability.get("ansf").setContent("（+" + mp +"）");
	}

	@Override
	public String text() {
		// TODO 自动生成的方法存根
		return "+" + mp + "点魔法值";
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
