package com.msx.lifeache.war3o;

import java.awt.Color;

import com.msx.lifeache.war3o.equipment.DPS;
import com.msx.lifeache.war3o.equipment.EquipmentBase;
import com.msx.lifeache.war3o.equipment.EquipmentCreator;
import com.msx.lifeache.war3o.equipment.EquipmentStandard;
import com.msx.lifeache.war3o.equipment.Tenacity;
import com.msx.lifeache.war3o.property.Armor;
import com.msx.lifeache.war3o.property.Attack;
import com.msx.lifeache.war3o.property.AttackSpeed;
import com.msx.lifeache.war3o.property.HeroAttribute;
import com.msx.lifeache.war3o.property.Hp;

public class Equipment{
	
	public final static String QUALITY_COMMOM = "普通";
	public final static String QUALITY_RARE = "稀有";
	public final static String QUALITY_EPIC = "史诗";
	public final static String QUALITY_LEGENDARY = "传说";
	
	public final static String TYPE_WEAPON_1 = "单手武器";
	public final static String TYPE_WEAPON_2 = "双手武器";
	public final static String TYPE_HELM = "头盔";
	public final static String TYPE_CHESTARMOR = "铠甲";
	public final static String TYPE_BOOTS = "护靴";
	public final static String TYPE_AMULET = "护符";
	public final static String TYPE_GLOVES = "护手";
	public final static String TYPE_BELT = "腰带";
	
	EquipmentKnot equipmentKnot;
	String name;
	Property[] properties;
	int abCnt;
	Color color;
	String quality;
	String type;
	
	Equipment(EquipmentKnot equipmentKnot) {
		this.equipmentKnot = equipmentKnot;
		this.properties = new Property[4];
		this.color = Color.WHITE;
		this.quality = QUALITY_COMMOM;
		this.type = TYPE_WEAPON_1;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void addProperty(Property ability){
		if (ability == null || abCnt >= 4){
			return;
		}
		properties[abCnt++] = ability;
	}

	public void associate(ItemAbilityLibrary alib,ItemAbilityLibrary elib) {
		String exStr = "";
		String ab = "";
		int damage = 0;
		War3OMerge war3oMerge = new War3OMerge();
		
		for (int i = 0; i < abCnt; i++) {
			Property property = properties[i];
			property.associate(alib);
			Ability ability = property.getAbility();
			war3oMerge.addAbilityU(equipmentKnot.abilityKnot, ability);
			
			if (property instanceof HeroAttribute){
				String[] ts = ((HeroAttribute)property).texts();
				for (String string : ts) {
					if (string != null){
						exStr += string + "\n";
					}
				}
			} else {
				exStr += property.text() + "\n";
			}
		
			if (i != 0){
				ab += ",";
			}
			
			ab += ability.name;
			if (property instanceof Attack){
				damage += ((Attack)property).getAttack();
			}
		}
		if (damage == 0){
			damage = 100;
		}
		Ability item = elib.copyOf(type);
		war3oMerge.addAbilityU(equipmentKnot.itemKnot, item);
		item.get("iabi").setContent(ab);
		item.get("ihtp").setContent(damage);
		item.get("iusa").setContent(0);
		item.get("ides").setContent(quality + type);
		item.get("utub").setContent(dye(new Color(0xffff8c00), quality + type) + "\n\n" + exStr);
		item.get("unam").setContent(dye(color, name));
		item.get("utip").setContent("购买" + dye(color, name));
		item.get("iclr").setContent(color.getRed());
		item.get("iclg").setContent(color.getGreen());
		item.get("iclb").setContent(color.getBlue());
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public static String dye(Color color,String text){
		if (color == null || color.getRGB() == Color.WHITE.getRGB()){
			return text;
		} else {
			return "|c" + Integer.toHexString(color.getRGB()) + text + "|r";
		
		}
		
	}
	
	public void setQuality(String quality) {
		this.quality = quality;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void raiseDpsTo(EquipmentBase base, EquipmentStandard standard){
		//System.out.println("targetDps=" + standard.getDps() + " ->targetTenacity=" + standard.getTenacity());
		//System.out.println("baseDps=" + base.getDps().dps() + " ->baseTen=" + base.getTenacity().tenacity());
		int dpsCnt = 0;
		int tenCnt = 0;
		for(int i = 0; i < abCnt; i++){
			if (properties[i].hasDps()){
				dpsCnt++;
			}
			if (properties[i].hasTenacity()){
				tenCnt++;
			}
		}
		int dpsIndex = 0;
		int tenIndex = 0;
		for(int i = 0; i < abCnt; i++){
			Property property = properties[i];
			float bd = base.getDps().dps();
			float bt = base.getTenacity().tenacity();
			float dd = 0.0f;
			if (property.hasDps()){
				dd = (standard.getDps() - bd) / (dpsCnt - dpsIndex);
				dpsIndex++;
			}
			float dt = 0.0f;
			if (property.hasTenacity()){
				dt = (standard.getTenacity() - bt) / (tenCnt - tenIndex);
				tenIndex++;
			}
			property.raiseTo(base, new EquipmentStandard(bd + dd, bt + dt));
			//System.out.print("dd=" + dd + ",dt=" + dt + "\t");
			//System.out.println("afterDps=" + base.getDps().dps() + " ->afterTens=" + base.getTenacity().tenacity());
		}
	}
	
	@Override
	public String toString() {
		String str = "";
		for(int i = 0; i < abCnt; i++){
			str += properties[i].text() + "\n";
		}
		return str;
	}
	
	public static void main(String[] args) {
		EquipmentCreator creator = new EquipmentCreator();
		Equipment equipment = new EquipmentKnot().createEquipment();//creator.obtainRandomEquipment(new EquipmentKnot());

		equipment.addProperty(new AttackSpeed(0.0f));
		equipment.addProperty(new Attack(0));
		
		equipment.addProperty(new Hp(0));
		equipment.addProperty(new Armor(0));
		DPS dps = new DPS(500, 1.0f);
		Tenacity tenacity = new Tenacity(1500, 15);
		EquipmentBase base = new EquipmentBase(dps, tenacity);
		equipment.raiseDpsTo(base, new EquipmentStandard(5000, 100000));
		System.out.println(equipment);
	}
}
