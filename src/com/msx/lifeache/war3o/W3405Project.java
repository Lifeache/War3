package com.msx.lifeache.war3o;

import java.io.File;

import com.msx.lifeache.war3o.equipment.EquipmentCreator;

public class W3405Project {
	static String projectPath = "C:\\Users\\Administrator.USER-20160403KS\\Desktop\\";
	static String w3aPath = "魔兽地图制作\\405\\405技能.w3a";
	static String w3tPath = "魔兽地图制作\\405\\405物品.w3t";
	
	static String newW3aPath = "魔兽地图制作\\405\\new_405技能.w3a";
	static String newW3tPath = "魔兽地图制作\\405\\new_405物品.w3t";

	static String w3aLibPath = "魔兽地图制作\\物品技能\\all.w3a";
	static String w3tLibPath = "魔兽地图制作\\装备物品\\装备.w3t";
	public static void main(String[] args) throws ErrorWar3oFile, TooLongStringException, UnknownWar3oDataException {
		W3405Project project = new W3405Project();
		War3OMerge merge = new War3OMerge();
		War3AbilityKnot w3a = merge.listAbility(new File(projectPath,w3aPath));
		War3AbilityKnot w3t = merge.listItem(new File(projectPath,w3tPath));
		
		ItemAbilityLibrary w3aLib = ItemAbilityLibrary.createAbilityLibrary(projectPath + w3aLibPath);
		ItemAbilityLibrary w3tLib = ItemAbilityLibrary.createWeaponLibrary(projectPath + w3tLibPath);
		
		//EquipmentKnot equipmentKnot = new EquipmentKnot(w3a,w3t);
		//EquipmentCreator creator = new EquipmentCreator();
		//creator.recruitSet(equipmentKnot);
		
		//equipmentKnot.associate(w3aLib, w3tLib);
		
		//merge.merge(w3a, equipmentKnot.getAbilityKnot());
		//merge.merge(w3t, equipmentKnot.getItemKnot());
		
		//System.out.println(equipmentKnot.getAbilityKnot().createIdList("aId"));
		//System.out.println(equipmentKnot.getItemKnot().createIdList("eId"));
		
		
		String[] aId = new String[7];
		aId[0] = "S001";
		aId[1] = "S002";
		aId[2] = "S003";
		aId[3] = "S004";
		aId[4] = "S005";
		aId[5] = "S006";
		aId[6] = "S007";

		String[] eId = new String[7];
		eId[0] = "I02F";
		eId[1] = "I02G";
		eId[2] = "I02H";
		eId[3] = "I02I";
		eId[4] = "I02J";
		eId[5] = "I02K";
		eId[6] = "I02L";
		
		w3a.removeByIdList(aId);
		w3t.removeByIdList(eId);
		
		merge.writeAbility(new File(projectPath + newW3aPath), w3a);
		merge.writeItem(new File(projectPath + newW3tPath), w3t);
	}
	
}
