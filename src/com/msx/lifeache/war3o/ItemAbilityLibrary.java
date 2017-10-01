package com.msx.lifeache.war3o;

import java.io.File;
import java.util.HashMap;

public class ItemAbilityLibrary {
	static String dataFile = "C:\\Users\\Administrator.USER-20160403KS\\Desktop\\魔兽地图制作\\物品技能\\all.w3a";
	
	War3AbilityKnot libKnot;
	HashMap<String, Ability> abilityMap;
	private ItemAbilityLibrary(){
		
	}
	
	public static ItemAbilityLibrary createAbilityLibrary(String libFile) throws ErrorWar3oFile, TooLongStringException, UnknownWar3oDataException{
		ItemAbilityLibrary itemAbilityLibrary = new ItemAbilityLibrary();
		War3OMerge war3oMerge = new War3OMerge();
		War3AbilityKnot libKnot = war3oMerge.listAbility(new File(libFile));
		HashMap<String, Ability> mapA = new HashMap<>();
		for (int i = 0; i < libKnot.getUdgCount(); i++) {
			Ability a3 = libKnot.getU(i);
			mapA.put(a3.get("anam").content.toString(), a3);
		}
		itemAbilityLibrary.libKnot = libKnot;
		itemAbilityLibrary.abilityMap = mapA;
		return itemAbilityLibrary;
	}
	
	public static ItemAbilityLibrary createWeaponLibrary(String libFile) throws ErrorWar3oFile, TooLongStringException, UnknownWar3oDataException{
		ItemAbilityLibrary itemAbilityLibrary = new ItemAbilityLibrary();
		War3OMerge war3oMerge = new War3OMerge();
		War3AbilityKnot libKnot = war3oMerge.listItem(new File(libFile));
		HashMap<String, Ability> mapA = new HashMap<>();
		for (int i = 0; i < libKnot.getUdgCount(); i++) {
			Ability a3 = libKnot.getU(i);
			mapA.put(a3.get("unam").content.toString(), a3);
		}
		itemAbilityLibrary.libKnot = libKnot;
		itemAbilityLibrary.abilityMap = mapA;
		//weaponLibExpend(itemAbilityLibrary,war3oMerge);
		return itemAbilityLibrary;
	}
	
	public Ability copyOf(String name){
		return this.abilityMap.get(name).createCopy();
	}
}
