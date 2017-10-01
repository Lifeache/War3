package com.msx.lifeache.war3o.equipment;

import java.util.Vector;

import com.msx.lifeache.war3o.property.Evasion;

public class Tenacity implements Cloneable{
	float armor;

	int hp;
	
	Vector<Evasion> evs;
	
	public Tenacity(int hp, float armor) {
		this.hp = hp;
		this.armor = armor;
		this.evs = new Vector<>();
	}

	/**
	 * @return armor
	 */
	public float getArmor() {
		return armor;
	}

	/**
	 * @param armor 要设置的 armor
	 */
	public void setArmor(float armor) {
		this.armor = armor;
	}

	/**
	 * @return hp
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * @param hp 要设置的 hp
	 */
	public void setHp(int hp) {
		this.hp = hp;
	}

	/**
	 * @return evs
	 */
	public Vector<Evasion> getEvs() {
		return evs;
	}
	
	public float tenacity(){
		float dr = (1 - (0.01f * armor / (0.01f * armor + 1)));
		for (Evasion evasion : evs) {
			dr *= 1 - evasion.getChance() / 100.0f;
		}
		return hp / dr;
	}
	
	@Override
	protected Tenacity clone() {
		try {
			return (Tenacity)super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}
}