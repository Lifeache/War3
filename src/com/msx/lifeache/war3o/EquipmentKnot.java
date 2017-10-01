package com.msx.lifeache.war3o;

import java.util.Vector;

public class EquipmentKnot{
	
	War3AbilityKnot abilityKnot;
	War3AbilityKnot itemKnot;
	Vector<Equipment> equipments;
	public EquipmentKnot() {
		this(new War3AbilityKnot(), new War3AbilityKnot());
	}
	
	public EquipmentKnot(War3AbilityKnot abilityKnot,War3AbilityKnot itemKnot) {
		this.abilityKnot = abilityKnot;
		this.itemKnot = itemKnot;
		this.equipments = new Vector<>();
	}
	
	public Equipment createEquipment(){
		Equipment equipment = new Equipment(this);
		equipments.add(equipment);
		return equipment;
	}

	public void associate(ItemAbilityLibrary alib,ItemAbilityLibrary elib) {
		for (Equipment equipment : equipments) {
			equipment.associate(alib,elib);
		}
	}
	
	/**
	 * @return abilityKnot
	 */
	public War3AbilityKnot getAbilityKnot() {
		return abilityKnot;
	}

	/**
	 * @param abilityKnot 要设置的 abilityKnot
	 */
	public void setAbilityKnot(War3AbilityKnot abilityKnot) {
		this.abilityKnot = abilityKnot;
	}

	/**
	 * @return itemKnot
	 */
	public War3AbilityKnot getItemKnot() {
		return itemKnot;
	}

	/**
	 * @param itemKnot 要设置的 itemKnot
	 */
	public void setItemKnot(War3AbilityKnot itemKnot) {
		this.itemKnot = itemKnot;
	}

	/**
	 * @return equipments
	 */
	public Vector<Equipment> getEquipments() {
		return equipments;
	}
}
