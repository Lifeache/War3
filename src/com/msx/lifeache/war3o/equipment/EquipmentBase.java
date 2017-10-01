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
	 * @param dps Ҫ���õ� dps
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
	 * @param tenacity Ҫ���õ� tenacity
	 */
	public void setTenacity(Tenacity tenacity) {
		this.tenacity = tenacity;
	}
	
	public EquipmentBase copy(){
		return new EquipmentBase(dps.clone(), tenacity.clone());
	}
}
