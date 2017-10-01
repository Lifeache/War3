package com.msx.lifeache.war3o.equipment;


public class EquipmentBase {
	
	DPS dps;
	
	Tenacity tenacity;
	
	public EquipmentBase(DPS dps,Tenacity tenacity){
		this.dps = dps;
		this.tenacity = tenacity;
	}
	
	/**
	 * @return dps
	 */
	public DPS getDps() {
		return dps;
	}

	/**
	 * @param dps 要设置的 dps
	 */
	public void setDps(DPS dps) {
		this.dps = dps;
	}

	/**
	 * @return tenacity
	 */
	public Tenacity getTenacity() {
		return tenacity;
	}

	/**
	 * @param tenacity 要设置的 tenacity
	 */
	public void setTenacity(Tenacity tenacity) {
		this.tenacity = tenacity;
	}
	
	public EquipmentBase copy(){
		return new EquipmentBase(dps.clone(), tenacity.clone());
	}
}
