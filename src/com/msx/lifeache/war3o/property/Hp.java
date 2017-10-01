package com.msx.lifeache.war3o.property;

import com.msx.lifeache.war3o.ItemAbilityLibrary;
import com.msx.lifeache.war3o.Property;
import com.msx.lifeache.war3o.equipment.EquipmentBase;
import com.msx.lifeache.war3o.equipment.EquipmentStandard;
import com.msx.lifeache.war3o.equipment.Tenacity;

public class Hp extends Property {
	
	int hp;
	
	public Hp(int hp) {
		this.hp = hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	@Override
	public String text() {
		
		return "+" + hp + "点生命值";
	}
	
	@Override
	public void associate(ItemAbilityLibrary lib) {
		this.ability = lib.copyOf("生命值加强");
		ability.get("Ilif").setContent(hp);
		ability.get("ansf").setContent("（+" + hp +"）");
	}
	
	@Override
	protected void raiseTo(EquipmentBase base, EquipmentStandard standard) {
		if (base.getTenacity() == null){
			return;
		}
		Tenacity tenacity = base.getTenacity();
		float baseTenacity = tenacity.tenacity();
		float increment = (standard.getTenacity() - baseTenacity) / baseTenacity;
		System.out.println("原坚韧=" + baseTenacity);
		hp = (int)(increment * tenacity.getHp());
		tenacity.setHp(tenacity.getHp() + hp);
		System.out.println("现坚韧=" + tenacity.tenacity());
	}
	@Override
	protected boolean hasDps() {
		// TODO 自动生成的方法存根
		return false;
	}
	@Override
	protected boolean hasTenacity() {
		// TODO 自动生成的方法存根
		return true;
	}

}
