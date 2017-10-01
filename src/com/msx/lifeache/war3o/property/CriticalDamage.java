package com.msx.lifeache.war3o.property;

import java.text.DecimalFormat;

import com.msx.lifeache.war3o.ItemAbilityLibrary;
import com.msx.lifeache.war3o.Property;
import com.msx.lifeache.war3o.equipment.DPS;
import com.msx.lifeache.war3o.equipment.EquipmentBase;
import com.msx.lifeache.war3o.equipment.EquipmentStandard;

public class CriticalDamage extends Property {
	
	float chance;
	
	float damage;
	
	public CriticalDamage(float chance,float damage) {
		this.chance = chance;
		this.damage = damage;
	}
	
	@Override
	public void associate(ItemAbilityLibrary lib) {
		this.ability = lib.copyOf("暴击加强");
		ability.get("Ocr1").setContent(chance);
		ability.get("Ocr2").setContent(damage);
		ability.get("ansf").setContent("（+" + new DecimalFormat(".0").format(chance) +"% +"
				+ new DecimalFormat(".0").format(damage * 100.0) + "%）");
	}

	@Override
	public String text() {
		return "攻击时有" + new DecimalFormat(".0").format(chance)
				+ "%的几率造成" + new DecimalFormat(".0").format(damage * 100.0)
				+ "%的伤害";
	}

	public void raiseTo(EquipmentBase base,  EquipmentStandard standard) {
		if (base.getDps() == null){
			return;
		}
		DPS dps = base.getDps();
		float baseDps = dps.dps();
		float increment = (standard.getDps() - baseDps) / baseDps;
		chance = 20.0f;
		float dAtk= (baseDps * (1 + increment) - (1 - chance / 100.0f) * baseDps) / ((chance / 100.0f) * dps.getDamage() * dps.getFrequency());
		
		damage = dAtk;
		dps.getCriticalDamageVector().add(this);
	}

	/**
	 * @return chance
	 */
	public float getChance() {
		return chance;
	}

	/**
	 * @param chance 要设置的 chance
	 */
	public void setChance(float chance) {
		this.chance = chance;
	}

	/**
	 * @return damage
	 */
	public float getDamage() {
		return damage;
	}

	/**
	 * @param damage 要设置的 damage
	 */
	public void setDamage(float damage) {
		this.damage = damage;
	}

	@Override
	protected boolean hasDps() {
		// TODO 自动生成的方法存根
		return true;
	}

	@Override
	protected boolean hasTenacity() {
		// TODO 自动生成的方法存根
		return false;
	}

}
