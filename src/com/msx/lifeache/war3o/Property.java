package com.msx.lifeache.war3o;

import com.msx.lifeache.war3o.equipment.EquipmentBase;
import com.msx.lifeache.war3o.equipment.EquipmentStandard;

public abstract class Property implements Associable{
	
	protected Ability ability;
	
	protected Property() {
		
	}
	
	public abstract String text();
	
	public Ability getAbility() {
		return ability;
	}
	
	protected abstract void raiseTo(EquipmentBase base, EquipmentStandard standard);
	
	protected abstract boolean hasDps();
	protected abstract boolean hasTenacity();
}
