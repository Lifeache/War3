package com.msx.lifeache.war3o;

import java.util.Vector;

public class War3AbilityKnot {
	
	Vector<Ability> udgAs;
	Vector<Ability> normalAs;
	Vector<Ability> newudgAs;
	int normalCount;
	int udgCount;
	public War3AbilityKnot() {
		udgAs = new Vector<>();
		normalAs = new Vector<>();
		newudgAs = new Vector<>();
	}
	
	public static int compare(Ability a1,Ability a2){
		String a = a1.name;
		String b = a2.name;
		return a.compareTo(b);
	}
	
	public void putNew(Ability ability){
		newudgAs.add(ability);
	}
	
	public void putU(Ability ability){
		for (int i = udgAs.size() - 1; i >= 0; i--){
			if (compare(ability, udgAs.get(i)) > 0){
				udgAs.insertElementAt(ability, i + 1);
				return ;
			}
		}
		udgAs.insertElementAt(ability, 0);
	}
	
	public Ability getU(int index){
		return udgAs.get(index);
	}

	public void putN(Ability ability){
		normalAs.add(ability);
	}
	
	public Ability getN(int index){
		return normalAs.get(index);
	}
	
	public int getNormalCount() {
		return normalCount;
	}

	
	public void setNormalCount(int normalCount) {
		this.normalCount = normalCount;
	}

	public int getUdgCount() {
		return udgCount;
	}

	public void setUdgCount(int udgCount) {
		this.udgCount = udgCount;
	}
	
	public String createVarSet(String varName){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i < newudgAs.size(); i++) {
			sBuilder.append("set " + varName + "[" + i + "] = '" + newudgAs.get(i).name + "'\n");
		}
		return sBuilder.toString();
	}
	
	public String createIdList(String varName){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("String[] " + varName + " = new String[" + newudgAs.size() +"];\n" );
		for (int i = 0; i < newudgAs.size(); i++) {
			sBuilder.append("" + varName + "[" + i + "] = \"" + newudgAs.get(i).name + "\";\n");
		}
		return sBuilder.toString();
	}
	
	public void removeById(String id){
		for (Ability ability : udgAs) {
			if (ability != null && ability.name.equals(id)){
				udgAs.remove(ability);
				udgCount--;
				break;
			}
		}
	}
	
	public void removeByIdList(String[] idList){
		for (String string : idList) {
			removeById(string);
		}
	}
}
