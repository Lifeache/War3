package com.msx.lifeache.war3o;

public class Unit implements Associable {
	String name;
	
	int damage;
	
	float frequency;
	
	float armor;
	
	int hp;
	
	Ability ability;
	
	@Override
	public void associate(ItemAbilityLibrary lib) {
		ability = lib.copyOf("”„»À");
		ability.get("unam").setContent(name);
		ability.get("ua1b").setContent(damage);
		ability.get("ua1c").setContent(frequency);
		ability.get("uhpm").setContent(hp);
		ability.get("udef").setContent(armor);
	}

}
