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
	 * @param dps Ҫ���õ� dps
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
	 * @param tenacity Ҫ���õ� tenacity
	 */
	public void setTenacity(int tenacity) {
		this.tenacity = tenacity;
	}
	
}
