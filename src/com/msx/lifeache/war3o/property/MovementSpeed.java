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
		this.ability = lib.copyOf("移动速度加强");
		ability.get("Imvb").setContent(speed);
		ability.get("ansf").setContent("（+" + speed +"）");
	}

	@Override
	public String text() {
		return "+" + speed + "点移动速度";
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
