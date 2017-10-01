package com.msx.lifeache.war3o.property;

import java.text.DecimalFormat;

import com.msx.lifeache.war3o.ItemAbilityLibrary;
import com.msx.lifeache.war3o.Property;
import com.msx.lifeache.war3o.equipment.EquipmentBase;
import com.msx.lifeache.war3o.equipment.EquipmentStandard;
import com.msx.lifeache.war3o.equipment.Tenacity;

public class Evasion extends Property {
	
	float chance;
	
	public Evasion(Float chance) {
		this.chance = chance;
	}
	
	@Override
	public void associate(ItemAbilityLibrary lib) {
		this.ability = lib.copyOf("���ܼ�ǿ");
		ability.get("Eev1").setContent(chance);
		ability.get("ansf").setContent("��+" + new DecimalFormat(".0").format(chance) +"%��");

	}

	@Override
	public String text() {
		return "+" + new DecimalFormat(".0").format(chance) + "%�����ܼ���";
	}

	@Override
	protected void raiseTo(EquipmentBase base, EquipmentStandard standard) {
		if (base.getTenacity() == null){
			return;
		}
		Tenacity tenacity = base.getTenacity();
		float baseTenacity = tenacity.tenacity();
		float increment = (standard.getTenacity() - baseTenacity) / baseTenacity;
		
		chance = increment / (1 + increment) * 100.0f;
		tenacity.getEvs().add(this);
	}

	/**
	 * @return chance
	 */
	public float getChance() {
		return chance;
	}

	/**
	 * @param chance Ҫ���õ� chance
	 */
	public void setChance(float chance) {
		this.chance = chance;
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
