package com.msx.lifeache.war3o.equipment;

import java.util.Vector;

import com.msx.lifeache.war3o.property.CriticalDamage;

public class DPS implements Cloneable{
	int damage;
	
	float frequency;
	
	Vector<CriticalDamage> cds;
	
	public DPS(int damage, float frequency){
		this.damage = damage;
		this.frequency = frequency;
		this.cds = new Vector<>();
	}
	
	public float dps(){
		float dps = damage * frequency;
		for (CriticalDamage criticalDamage : cds) {
			dps += (1 - criticalDamage.getChance() / 100.0f) * dps + damage * frequency * (criticalDamage.getChance() / 100.0f) * criticalDamage.getDamage();
		}
		return dps;
	}

	/**
	 * @return damage
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * @param damage 要设置的 damage
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * @return frequency
	 */
	public float getFrequency() {
		return frequency;
	}

	/**
	 * @param frequency 要设置的 frequency
	 */
	public void setFrequency(float frequency) {
		this.frequency = frequency;
	}

	/**
	 * @return cds
	 */
	public Vector<CriticalDamage> getCriticalDamageVector() {
		return cds;
	}
	
	@Override
	protected DPS clone() {
		try {
			return (DPS)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}