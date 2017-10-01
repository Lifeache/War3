package com.msx.lifeache.war3o.equipment;

import java.awt.Color;
import java.io.File;

import com.msx.lifeache.war3o.Ability;
import com.msx.lifeache.war3o.Equipment;
import com.msx.lifeache.war3o.EquipmentKnot;
import com.msx.lifeache.war3o.ErrorWar3oFile;
import com.msx.lifeache.war3o.ItemAbilityLibrary;
import com.msx.lifeache.war3o.Property;
import com.msx.lifeache.war3o.TooLongStringException;
import com.msx.lifeache.war3o.UnknownWar3oDataException;
import com.msx.lifeache.war3o.War3AbilityKnot;
import com.msx.lifeache.war3o.War3OMerge;
import com.msx.lifeache.war3o.property.Armor;
import com.msx.lifeache.war3o.property.Attack;
import com.msx.lifeache.war3o.property.AttackSpeed;
import com.msx.lifeache.war3o.property.CriticalDamage;
import com.msx.lifeache.war3o.property.Evasion;
import com.msx.lifeache.war3o.property.HeroAttribute;
import com.msx.lifeache.war3o.property.Hp;
import com.msx.lifeache.war3o.property.HpRecoveryRate;
import com.msx.lifeache.war3o.property.MovementSpeed;
import com.msx.lifeache.war3o.property.Mp;
import com.msx.lifeache.war3o.property.MpRecoveryRate;

import msx.names.NameFactory;

public class EquipmentCreator {
	
	private int randomInt(int from,int to) {
		return from + (int)(Math.random() * (to - from + 1));
	}
	
	public Property obtainRandomProperty(){
		int i = (int)(Math.random() * 6);
		switch (i) {
		case 0:
			return new Attack(randomInt(0, 1000));
		case 1:
			return new Armor(randomInt(0, 750));
		case 2:
			return new AttackSpeed((float)(Math.random() * 4));
		case 3:
			return new CriticalDamage((float)(Math.random() * 25), (float)(Math.random() * 6));
		case 4:
			return new Evasion((float)(Math.random() * 75));
		case 5:
			return new Hp(randomInt(0, 10000));
		case 6:
			return new MovementSpeed(randomInt(0, 200));
		case 7:
			return new MpRecoveryRate((float)(Math.random() * 10));
		case 8:
			return new Mp(randomInt(0, 200));
		case 9:
			return new HpRecoveryRate(randomInt(0, 100));
		case 10:
			return new HeroAttribute(randomInt(0, 1000), 0, 0);
		case 11:
			return new HeroAttribute(0,randomInt(0, 1000), 0);
		case 12:	
			return new HeroAttribute(0,0,randomInt(0, 1000));
		default :
			return null;
		}
	}
	
	public Equipment obtainRandomEquipment(EquipmentKnot equipmentKnot){
		int i = (int)(Math.random() * 7);
		Equipment e = equipmentKnot.createEquipment();
		switch (i) {
		case 0:
			e.setColor(Color.GRAY);
			e.setQuality(Equipment.QUALITY_COMMOM);
			e.addProperty(obtainRandomProperty());
			//e.raiseDpsTo(new EquipmentBase(25, 1.0f, 0, 0),50.0f);
			break;
		case 1:
			e.setQuality(Equipment.QUALITY_COMMOM);
			e.addProperty(obtainRandomProperty());
			//e.raiseDpsTo(new EquipmentBase(50, 1.0f, 0, 0),100.0f);
			break;
		case 2:
			e.setColor(Color.GREEN);
			e.setQuality(Equipment.QUALITY_RARE);
			e.addProperty(obtainRandomProperty());
			e.addProperty(obtainRandomProperty());
			//e.raiseDpsTo(new EquipmentBase(100, 1.0f, 0, 0),300.0f);
			break;
		case 3:
			e.setColor(Color.BLUE);
			e.setQuality(Equipment.QUALITY_RARE);
			e.addProperty(obtainRandomProperty());
			e.addProperty(obtainRandomProperty());
			//e.raiseDpsTo(new EquipmentBase(300, 1.0f, 0, 0),600.0f);
			break;
		case 4:
			e.setColor(new Color(0xff800080));
			e.setQuality(Equipment.QUALITY_EPIC);
			e.addProperty(obtainRandomProperty());
			e.addProperty(obtainRandomProperty());
			e.addProperty(obtainRandomProperty());
			//e.raiseDpsTo(new EquipmentBase(600, 1.0f, 0, 0),1200.0f);
			break;
		case 5:
			e.setColor(Color.ORANGE);
			e.setQuality(Equipment.QUALITY_LEGENDARY);
			e.addProperty(obtainRandomProperty());
			e.addProperty(obtainRandomProperty());
			e.addProperty(obtainRandomProperty());
			e.addProperty(obtainRandomProperty());
			//e.raiseDpsTo(new EquipmentBase(1200, 1.0f, 0, 0),2400.0f);
			break;
		case 6:
			e.setColor(Color.YELLOW);
			e.setQuality(Equipment.QUALITY_LEGENDARY);
			e.addProperty(obtainRandomProperty());
			e.addProperty(obtainRandomProperty());
			e.addProperty(obtainRandomProperty());
			e.addProperty(obtainRandomProperty());
			//e.raiseDpsTo(new EquipmentBase(2400, 1.0f, 0, 0),4800.0f);
			break;
		default:
			break;
		}
		i = (int)(Math.random() * 6);
		switch (i) {
		case 0 :
			e.setType(Equipment.TYPE_WEAPON_1);
			break;
		case 1 :
			e.setType(Equipment.TYPE_WEAPON_2);
			break;
		case 2 :
			e.setType(Equipment.TYPE_AMULET);
			break;
		case 3 :
			e.setType(Equipment.TYPE_BOOTS);
			break;
		case 4 :
			e.setType(Equipment.TYPE_CHESTARMOR);
			break;
		case 5 :
			e.setType(Equipment.TYPE_HELM);
			break;
		default:
			break;
		}
		return e;
	}
	
	public static void main(String[] args) {
		File eFile = new File("C:\\Users\\Administrator.USER-20160403KS\\Desktop\\魔兽地图制作\\装备物品\\装备.w3t");
		File aFile = new File("C:\\Users\\Administrator.USER-20160403KS\\Desktop\\魔兽地图制作\\物品技能\\all.w3a");
		File uFile = new File("C:\\Users\\Administrator.USER-20160403KS\\Desktop\\魔兽地图制作\\单位\\单位.w3u");
		ItemAbilityLibrary eLib = null;
		ItemAbilityLibrary aLib = null;
		ItemAbilityLibrary uLib = null;
		try {
			eLib = ItemAbilityLibrary.createWeaponLibrary(eFile.getPath());
			aLib = ItemAbilityLibrary.createAbilityLibrary(aFile.getPath());
			uLib = ItemAbilityLibrary.createWeaponLibrary(uFile.getPath());
		} catch (ErrorWar3oFile | TooLongStringException | UnknownWar3oDataException e) {
			e.printStackTrace();
		}
		EquipmentKnot equipmentKnot = new EquipmentKnot();
		//Equipment equipment = equipmentKnot.createEquipment();

		EquipmentCreator creator = new EquipmentCreator();
		NameFactory nameFactory = new NameFactory("E:\\JavaHome\\我的游戏\\");
//		for (int i = 0; i < 100; i++){
//			equipment = creator.obtainRandomEquipment(equipmentKnot);
//			equipment.setName(nameFactory.getRandomItemName());
//		}
		creator.recruitSet(equipmentKnot);
		equipmentKnot.associate(aLib,eLib);
		
		
		
		File oout = new File("C:\\Users\\Administrator.USER-20160403KS\\Desktop\\魔兽地图制作\\new.w3o");
		
		War3OMerge war3oMerge = new War3OMerge();
		war3oMerge.setW3aKnot(equipmentKnot.getAbilityKnot());
		war3oMerge.setW3tKnot(equipmentKnot.getItemKnot());
		War3AbilityKnot uKont = new War3AbilityKnot();
		Ability aUnit = uLib.copyOf("鱼人");
		aUnit.get("unam").setContent("鱼人袭击者");
		aUnit.get("ua1b").setContent(100);
		war3oMerge.addAbilityU(uKont, aUnit);
		war3oMerge.setW3uKnot(uKont);
		war3oMerge.writeW3O(oout);
		
		System.out.println(equipmentKnot.getItemKnot().createVarSet("udg_Sys_Equipment"));
		System.out.println(equipmentKnot.getItemKnot().createIdList("udg_Sys_Equipment"));
	}
	
	public EquipmentKnot recruitSet(EquipmentKnot knot){
		EquipmentKnot newKnot = new EquipmentKnot();
		NameFactory nameFactory = new NameFactory("E:\\JavaHome\\我的游戏\\");
		
		DPS dps = new DPS(26, 1.0f);
		Tenacity tenacity = new Tenacity(600, 5);
		EquipmentBase base = new EquipmentBase(dps, tenacity);
		EquipmentStandard standard = new EquipmentStandard(70, 900);
		Equipment equipment = knot.createEquipment();
		equipment.setName(nameFactory.getRandomItemName());
		equipment.setType(Equipment.TYPE_WEAPON_1);
		equipment.setQuality(Equipment.QUALITY_COMMOM);
		equipment.addProperty(new Attack(0));
		equipment.raiseDpsTo(base, standard);
		newKnot.getEquipments().add(equipment);
		
		dps = new DPS(26, 1.0f);
		tenacity = new Tenacity(600, 5);
		base = new EquipmentBase(dps, tenacity);
		equipment = knot.createEquipment();
		equipment.setName(nameFactory.getRandomItemName());
		equipment.setType(Equipment.TYPE_WEAPON_2);
		equipment.setQuality(Equipment.QUALITY_COMMOM);
		equipment.addProperty(new Attack(0));
		equipment.raiseDpsTo(base, standard);
		newKnot.getEquipments().add(equipment);
		
		dps = new DPS(26, 1.0f);
		tenacity = new Tenacity(600, 5);
		base = new EquipmentBase(dps, tenacity);
		equipment = knot.createEquipment();
		equipment.setName(nameFactory.getRandomItemName());
		equipment.setType(Equipment.TYPE_AMULET);
		equipment.setQuality(Equipment.QUALITY_COMMOM);
		equipment.addProperty(new CriticalDamage(0, 0));
		equipment.raiseDpsTo(base, standard);
		newKnot.getEquipments().add(equipment);
		
		dps = new DPS(26, 1.0f);
		tenacity = new Tenacity(600, 5);
		base = new EquipmentBase(dps, tenacity);
		equipment = knot.createEquipment();
		equipment.setName(nameFactory.getRandomItemName());
		equipment.setType(Equipment.TYPE_CHESTARMOR);
		equipment.setQuality(Equipment.QUALITY_COMMOM);
		equipment.addProperty(new Hp(0));
		equipment.raiseDpsTo(base, standard);
		newKnot.getEquipments().add(equipment);
		
		dps = new DPS(26, 1.0f);
		tenacity = new Tenacity(600, 5);
		base = new EquipmentBase(dps, tenacity);
		equipment = knot.createEquipment();
		equipment.setName(nameFactory.getRandomItemName());
		equipment.setType(Equipment.TYPE_BOOTS);
		equipment.setQuality(Equipment.QUALITY_COMMOM);
		equipment.addProperty(new Evasion(0f));
		equipment.raiseDpsTo(base, standard);
		newKnot.getEquipments().add(equipment);
		
		dps = new DPS(26, 1.0f);
		tenacity = new Tenacity(600, 5);
		base = new EquipmentBase(dps, tenacity);
		equipment = knot.createEquipment();
		equipment.setName(nameFactory.getRandomItemName());
		equipment.setType(Equipment.TYPE_HELM);
		equipment.setQuality(Equipment.QUALITY_COMMOM);
		equipment.addProperty(new Armor(0));
		equipment.raiseDpsTo(base, standard);
		newKnot.getEquipments().add(equipment);
		
		dps = new DPS(26, 1.0f);
		tenacity = new Tenacity(600, 5);
		base = new EquipmentBase(dps, tenacity);
		equipment = knot.createEquipment();
		equipment.setName(nameFactory.getRandomItemName());
		equipment.setType(Equipment.TYPE_GLOVES);
		equipment.setQuality(Equipment.QUALITY_COMMOM);
		equipment.addProperty(new AttackSpeed(0f));
		equipment.raiseDpsTo(base, standard);
		newKnot.getEquipments().add(equipment);
		
		return newKnot;
	}
}
