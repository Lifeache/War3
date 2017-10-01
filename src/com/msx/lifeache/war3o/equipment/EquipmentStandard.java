package com.msx.lifeache.war3o.equipment;

public class EquipmentStandard {
	float dps;
	float tenacity;
	public EquipmentStandard(float dps, float tenacity) {
		super();
		this.dps = dps;
		this.tenacity = tenacity;
	}
	/**
	 * @return dps
	 */
	public float getDps() {
		return dps;
	}
	/**
	 * @param dps 要设置的 dps
	 */
	public void setDps(int dps) {
		this.dps = dps;
	}
	/**
	 * @return tenacity
	 */
	public float getTenacity() {
		return tenacity;
	}
	/**
	 * @param tenacity 要设置的 tenacity
	 */
	public void setTenacity(int tenacity) {
		this.tenacity = tenacity;
	}
	
}
