package com.msx.lifeache.war3o;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class War3OMerge {
	//数据项类型：整型
	static final int TYPE_INT = 0x00; 
	
	//数据项类型：浮点?
	static final int TYPE_FLOAT_ = 0x01;
	
	//数据项类型：浮点
	static final int TYPE_FLOAT = 0x02;
	
	//数据项类型：字符串
	static final int TYPE_STRING = 0x03;
	
	//单位
	War3AbilityKnot w3uKnot;
	
	//物品
	War3AbilityKnot w3tKnot;
	
	//可破坏物
	War3AbilityKnot w3bKnot;
	
	//地形装饰物
	War3AbilityKnot w3dKnot;
	
	//技能
	War3AbilityKnot w3aKnot;
	
	//魔法效果
	War3AbilityKnot w3hKnot;
	
	//科技
	War3AbilityKnot w3qKnot;
		
	
	public static byte[] float2Bits(float f){
		int i = Float.floatToIntBits(f);
		return int2Bits(i);
	}
	
	public static byte[] int2Bits(int i){
		byte[] bits = new byte[4];
		bits[0] = (byte)(i & 0xff);
		bits[1] = (byte)((i >>> 8) & 0xff);
		bits[2] = (byte)((i >>> 16) & 0xff);
		bits[3] = (byte)((i >>> 24) & 0xff);
		return bits;
	}
	
	public static byte[] string2Bits(String str){
		try {
			return str.getBytes("utf8");
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		JFileChooser chooser = new JFileChooser("C:\\Users\\Administrator.USER-20160403KS\\Desktop");
		JFrame jFrame = new JFrame("测试");
		String[] strings = new String[]{"查看技能","查看物品","合并技能","制造"};
		JComboBox<String> box = new JComboBox<>(strings);
		JTextArea area = new JTextArea();
		area.setPreferredSize(new Dimension(180, 300));
		JButton button = new JButton("do");
		button.addActionListener(l -> {
			chooser.showOpenDialog(jFrame);
			File file1 = chooser.getSelectedFile();
			if (file1 == null || !file1.isFile()) {
				return;
			}
			War3OMerge war3oMerge = new War3OMerge();
			int it = box.getSelectedIndex();
			switch (it) {
			case 0:
				War3AbilityKnot knot1 = null;
				try {
					knot1 = war3oMerge.listAbility(file1);
				} catch (ErrorWar3oFile | TooLongStringException | UnknownWar3oDataException e) {
					e.printStackTrace();
				}
				Ability a = knot1.getU(0);
				if (a != null){;
					area.setText(war3oMerge.createTable(a));
				}
				break;
			case 1:
				War3AbilityKnot knot2 = null;
				try {
					knot2 = war3oMerge.listItem(file1);
				} catch (ErrorWar3oFile | TooLongStringException | UnknownWar3oDataException e) {
					e.printStackTrace();
				}
				Ability b = knot2.getU(0);
				if (b != null){;
					area.setText(war3oMerge.createTable(b));
				}
				break;
			case 2:
				File[] form = file1.getParentFile().listFiles(new FilenameFilter() {
					
					@Override
					public boolean accept(File dir, String name) {
						if (name.endsWith(".w3a")){
							return true;
						}
						return false;
					}
				});
				War3AbilityKnot[] knots = new War3AbilityKnot[form.length];
				for (int i = 0; i < form.length; i++) {
					try {
						knots[i] = war3oMerge.listAbility(form[i]);
					} catch (ErrorWar3oFile | TooLongStringException | UnknownWar3oDataException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					} 
				}
				War3AbilityKnot sub = war3oMerge.merge(knots);
				File out = new File(form[0].getParent(), "all.w3a");
				war3oMerge.writeAbility(out, sub);
				break;
			case 3:
				ItemAbilityLibrary abilityLibrary = null;
				ItemAbilityLibrary itemLibrary = null;
				try {
					abilityLibrary = ItemAbilityLibrary.createAbilityLibrary(file1.getPath());
					itemLibrary = ItemAbilityLibrary
							.createWeaponLibrary("C:\\Users\\Administrator.USER-20160403KS\\Desktop\\魔兽地图制作\\装备物品\\装备.w3t");
					
				} catch (ErrorWar3oFile | TooLongStringException | UnknownWar3oDataException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				War3AbilityKnot abKnot = new War3AbilityKnot();
//				try {
//					knot3 = war3oMerge.listAbility(file1);
//				} catch (ErrorWar3oFile | TooLongStringException e) {
//					e.printStackTrace();
//				}
//				HashMap<String, Ability> mapA = new HashMap<>();
//				for (int i = 0; i < knot3.getUdgCount(); i++) {
//					Ability a3 = knot3.getU(i);
//					mapA.put(a3.get("anam").content.toString(), a3);
//				}
//				System.out.println(mapA.get("攻击力加强").get("Iatt").content);
				
				Ability atk = abilityLibrary.copyOf("攻击力加强");//mapA.get("攻击力加强").createCopy();
				war3oMerge.addAbilityU(abKnot, atk);
				atk.get("Iatt").content = 100;
				atk.get("ansf").content = "(+100)";
				Ability atsp = abilityLibrary.copyOf("攻击速度加强");//mapA.get("攻击速度加强").createCopy();;
				war3oMerge.addAbilityU(abKnot, atsp);
				atsp.get("Isx1").content = 0.8f;
				atsp.get("ansf").content = "(+80%)";
				
				File out3 = new File(file1.getParent(), "all_1.w3a");
				war3oMerge.writeAbility(out3,abKnot);
				
				//out3 = new File("C:\\Users\\Administrator.USER-20160403KS\\Desktop\\魔兽地图制作\\装备物品\\装备.w3t");
				War3AbilityKnot wKnot = new War3AbilityKnot();
//				try {
//					wKnot = war3oMerge.listItem(out3);
//				} catch (ErrorWar3oFile | TooLongStringException e) {
//					// TODO 自动生成的 catch 块
//					e.printStackTrace();
//				}
				Ability weapon = itemLibrary.copyOf("隼矛之爪");
				war3oMerge.addAbilityU(wKnot,weapon);
				weapon.get("iabi").content = atk.name + "," + atsp.name;
				weapon.get("unam").content = "伤痕使者";
				
				Ability chestArmor = itemLibrary.copyOf("钢铁之心");
				war3oMerge.addAbilityU(wKnot,chestArmor);
				//chestArmor.get("iabi").content = atk.name + "," + atsp.name;
				chestArmor.get("unam").content = "永恒统治";
				
				out3 = new File(out3.getParent(),"wz.w3t");
				war3oMerge.writeItem(out3, wKnot);
				break;
				
			default:
				break;
			}
			
		}
		);
		jFrame.setLayout(new BorderLayout());
		jFrame.add(button,BorderLayout.NORTH);
		jFrame.add(area,BorderLayout.CENTER);
		jFrame.add(box, BorderLayout.SOUTH);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setBounds(500, 200, 500, 500);
		jFrame.setVisible(true);
	}
	
	public static void maiin(String[] args) {
		File file1 = new File("C:\\Users\\Administrator.USER-20160403KS\\Desktop\\信长技能.w3a");
		File file2 = new File("C:\\Users\\Administrator.USER-20160403KS\\Desktop\\新405.w3a");
		
	    War3OMerge war3oMerge = new War3OMerge();
		War3AbilityKnot knot1 = null;
		War3AbilityKnot knot2 = null;
		try {
			knot1 = war3oMerge.listAbility(file2);
			knot2 = war3oMerge.listAbility(file1);
			war3oMerge.merge(knot1, knot2);
		} catch (ErrorWar3oFile | TooLongStringException | UnknownWar3oDataException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		File file3 = new File("C:\\Users\\Administrator.USER-20160403KS\\Desktop\\这可不是风暴之雪.w3a");
		war3oMerge.writeAbility(file3, knot1);
		System.out.println("标准技能：" + knot1.getNormalCount());
		for(int i = 0; i < knot1.getNormalCount(); i++){
			Ability ability = knot1.getN(i);
			//System.out.println("->" + ability.name + ":" + ability.optionCnt);
			for (int j = 0; j < ability.getOptionCnt(); j++){
				Option option = ability.get(j);
				//System.out.println(" ->" + option.dataName + ":" + option.level +"\t" + option.content);
			}
		}
		System.out.println("自定义技能：" + knot1.getUdgCount());
		for(int i = 0; i < knot1.getUdgCount(); i++){
			Ability ability = knot1.getU(i);
			//System.out.println("->" + ability.name + ":" + ability.optionCnt);
			for (int j = 0; j < ability.getOptionCnt(); j++){
				Option option = ability.get(j);
				//System.out.println(" ->" + option.dataName + ":" + option.level +"\t" + option.content);
			}
		}
	}
	
	public String createTable(Ability a){
		StringBuilder sb = new StringBuilder();
		sb.append("id=" + a.name + "\tmodel=" + a.model + "\n");
		for (int j = 0; j < a.getOptionCnt(); j++){
			Option option = a.get(j);
			sb.append(option.dataName + "\t");
			sb.append(option.level + "\t");
			sb.append(option.content + "\t");
			sb.append(option.unknownOnes + "\t->\n");
			//System.out.println(" ->" + option.dataName + ":" + option.level +"\t" + option.content);
		}
		return sb.toString();
	}
	
	public War3AbilityKnot listAbility(File file) throws ErrorWar3oFile, TooLongStringException, UnknownWar3oDataException{
		War3AbilityKnot knot = null;
		FileInputStream input = null;
		try {
			input = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		byte[] buff = new byte[1024];
		
		try {
			readFirm(input, buff, 0, 8);
			knot = new War3AbilityKnot();
			int normalCnt = bits2Int(buff, 4);
			knot.setNormalCount(normalCnt);
			//System.out.println("********************************");
			//System.out.println("共有标准技能 " + normalCnt + "个");
		
			for (int i = 0; i < normalCnt; i++) {
				readFirm(input, buff, 0, 12);
				int optionCnt = bits2Int(buff, 8);
				String abilityId = bits2String(buff, 0, 4);
				Ability ability = new Ability(abilityId);
				knot.putN(ability);
				ability.setOptionCnt(optionCnt);
				//System.out.println(abilityId + "技能修改了 " + optionCnt + "项");
				for (int j = 0; j < optionCnt; j++){
					readFirm(input, buff, 0, 16);
					String optionId = bits2String(buff, 0, 4);
					//System.out.println("  ->" + optionId);
					int level = bits2Int(buff, 8);
					int type = bits2Int(buff, 4);
					int unknownOnes = bits2Int(buff, 12);
					if (type == TYPE_INT){
						readFirm(input, buff, 0, 4);
						int c = bits2Int(buff, 0);
						Option option = new Option(optionId, level, type, c);
						option.unknownOnes = unknownOnes;
						ability.put(option);
					//	System.out.println("    ->" + c);
					} else if (type == TYPE_FLOAT || type == TYPE_FLOAT_){
						readFirm(input, buff, 0, 4);
						float c = bits2Float(buff, 0);
						Option option = new Option(optionId, level, type, c);
						option.unknownOnes = unknownOnes;
						ability.put(option);
					//	System.out.println("    ->" + c);
					} else if (type == TYPE_STRING) {
						int n = 0;
						while (true){
							readFirm(input, buff, n, 1);
							
							if (buff[n] == 0){
								break;
							}
							n ++;
							if (n > 1024){
								throw new TooLongStringException();
							}
						}
						String c = bits2String(buff, 0, n);
						Option option = new Option(optionId, level, type, c);
						option.unknownOnes = unknownOnes;
						ability.put(option);
						//System.out.println("    ->" + c);
						
					} else {
						throw new UnknownWar3oDataException();
					}
					readFirm(input, buff, 0, 4);
				}
			}
			
			readFirm(input, buff, 0, 4);
			int udgCnt = bits2Int(buff, 0);
			knot.setUdgCount(udgCnt);
			//System.out.println("********************************");
			//System.out.println("共有自定义技能 " + udgCnt + "个");

			for (int i = 0; i < udgCnt; i++) {
				readFirm(input, buff, 0, 12);
				int optionCnt = bits2Int(buff, 8);
				String abilityModel = bits2String(buff, 0, 4);
				String abilityId = bits2String(buff, 4, 4);
				Ability ability = new Ability(abilityModel,abilityId);
				knot.putU(ability);
				ability.setOptionCnt(optionCnt);
				//System.out.println(abilityId + "技能修改了 " + optionCnt + "项");
				for (int j = 0; j < optionCnt; j++){
					readFirm(input, buff, 0, 16);
					String optionId = bits2String(buff, 0, 4);
					//System.out.println("  ->" + optionId);
					int level = bits2Int(buff, 8);
					int type = bits2Int(buff, 4);
					int unknownOnes = bits2Int(buff, 12);
					if (type == TYPE_INT){
						readFirm(input, buff, 0, 4);
						int c = bits2Int(buff, 0);
						Option option = new Option(optionId, level, type, c);
						option.unknownOnes = unknownOnes;
						ability.put(option);
						//System.out.println("    ->" + c);
					} else if (type == TYPE_FLOAT || type == TYPE_FLOAT_){
						readFirm(input, buff, 0, 4);
						float c = bits2Float(buff, 0);
						Option option = new Option(optionId, level, type, c);
						option.unknownOnes = unknownOnes;
						ability.put(option);
						//System.out.println("    ->" + c);
					} else if (type == TYPE_STRING) {
						int n = 0;
						while (true){
							readFirm(input, buff, n, 1);
							
							if (buff[n] == 0){
								break;
							}
							n ++;
							if (n > 1024){
								throw new TooLongStringException();
							}
						}
						String c = bits2String(buff, 0, n);
						Option option = new Option(optionId, level, type, c);
						option.unknownOnes = unknownOnes;
						ability.put(option);
						//System.out.println("    ->" + c);
						
					} else {
						throw new UnknownWar3oDataException();
					}
					readFirm(input, buff, 0, 4);
				}
			}
			
			
			input.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return knot;
	}
	
	public static int bits2Int(byte[] bits,int off){
		int i = bits[off] & 0xff;
		i += (bits[off + 1] & 0xff) << 8;
		i += (bits[off + 2] & 0xff) << 16;
		i += (bits[off + 3] & 0xff) << 24;
		return i;
	}
	
	public static String bits2String(byte[] bits,int off,int len){
		
		try {
			return new String(bits, off, len, "utf8");
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}
	
	public static float bits2Float(byte[] bits,int off){
		return Float.intBitsToFloat(bits2Int(bits, off));
	}
	
	public int read(FileInputStream in,byte[] buff, int offset, int count) throws IOException{  
		 
		int readCount = 0; // 已经成功读取的字节的个数  
		while (readCount < count) {  
		   int c = in.read(buff, offset + readCount, count - readCount);
		   if (c == -1) {
			   if (readCount == 0){
				   return -1;
			   } else {
				   return readCount;
			   }
		   } else {
			   readCount += c;
		   }  
		}
		return readCount;
	}
	
	public int readFirm(FileInputStream input,byte[] buff, int offset, int count) throws IOException, ErrorWar3oFile{
		int c = 0;
		if ((c = read(input, buff, offset, count)) != -1){
		
			if (c < count){
				throw new ErrorWar3oFile();
			}
		} else {
			throw new ErrorWar3oFile();
		}
		return c;
	}
	
	public void merge(War3AbilityKnot rKnot,War3AbilityKnot tKnot){
		//System.out.println("主有标准技能" + rKnot.getNormalCount() + "个");
		//System.out.println("从有标准技能" + tKnot.getNormalCount() + "个");
		System.out.println("主有自定义技能" + rKnot.getUdgCount() + "个");
		System.out.println("从有自定义技能" + tKnot.getUdgCount() + "个");
		for(int i = 0; i < tKnot.getUdgCount(); i++){
			//System.out.println("添加" + tKnot.getU(i).name);
			addAbilityU(rKnot, tKnot.getU(i));
		}
	}
	
	public void addAbilityU(War3AbilityKnot rKnot,Ability ability) {
		if ("".equals(ability.model)){
			return ;
		}
		for (int i = 0; i < rKnot.udgAs.size(); i++){
			if (War3AbilityKnot.compare(rKnot.getU(i),ability) == 0){
				char[] lastC = rKnot.udgAs.lastElement().name.toCharArray(); 	
				if (lastC[lastC.length - 1] == 'Z'){
					lastC[lastC.length - 1] = '0';
					
					if (lastC[lastC.length - 2] == 'Z'){
						lastC[lastC.length - 2] = '0';
						
						if (lastC[lastC.length - 3] == 'Z'){
							lastC[lastC.length - 3] = '0';
						} else if (lastC[lastC.length - 3] == '9'){
							lastC[lastC.length - 3] = 'A';
						} else {
							lastC[lastC.length - 3] += 1;
						}
						
					} else if (lastC[lastC.length - 2] == '9'){
						lastC[lastC.length - 2] = 'A';
					} else {
						lastC[lastC.length - 2] += 1;
					}
					
					
				} else if (lastC[lastC.length - 1] == '9'){
					lastC[lastC.length - 1] = 'A';
				} else {
					lastC[lastC.length - 1] += 1;
				}
				ability.name = new String(lastC);	
				break;
			}
		}
		rKnot.putU(ability);
		rKnot.putNew(ability);
		rKnot.setUdgCount(rKnot.getUdgCount() + 1);
		
	}
	
	public void writeAbility(File file,War3AbilityKnot knot){
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		writeAbility(out, knot);
		try {
			out.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	private void writeAbility(FileOutputStream out,War3AbilityKnot knot){
		try {
			byte[] zerobuff = new byte[]{0,0,0,0};
			out.write(int2Bits(2));
			out.write(int2Bits(knot.normalCount));
			for(int i = 0; i < knot.getNormalCount(); i++){
				Ability a = knot.getN(i);
				byte[] abName = string2Bits(a.name);
				out.write(abName);
				out.write(zerobuff);
				int cnt = a.optionCnt;
				out.write(int2Bits(cnt));
				for (int j = 0; j < cnt; j++){
					Option op = a.get(j);
					out.write(string2Bits(op.dataName));
					int type = op.type;
					out.write(int2Bits(type));
					out.write(int2Bits(op.level));
					out.write(int2Bits(op.unknownOnes));
					switch (type) {
					case TYPE_INT :
						out.write(int2Bits((int)op.content));
						break;
					case TYPE_FLOAT_ :
					case TYPE_FLOAT :
						out.write(float2Bits((float)op.content));
						break;
					case TYPE_STRING :
						byte[] b = string2Bits(op.content.toString());
						byte[] as = new byte[b.length + 1];
						System.arraycopy(b, 0, as, 0, b.length);
						as[as.length - 1] = 0;
						out.write(as);
						break;
					default:
						break;
					}
					out.write(abName);
				}
			}
			
			out.write(int2Bits(knot.getUdgCount()));
			for(int i = 0; i < knot.getUdgCount(); i++){
				Ability a = knot.getU(i);
				byte[] abName = string2Bits(a.name);
				out.write(string2Bits(a.model));
				out.write(abName);
				int cnt = a.optionCnt;
				out.write(int2Bits(cnt));
				for (int j = 0; j < cnt; j++){
					Option op = a.get(j);
					out.write(string2Bits(op.dataName));
					int type = op.type;
					out.write(int2Bits(type));
					out.write(int2Bits(op.level));
					out.write(int2Bits(op.unknownOnes));
					switch (type) {
					case TYPE_INT :
						out.write(int2Bits((int)op.content));
						break;
					case TYPE_FLOAT_ :
					case TYPE_FLOAT :
						out.write(float2Bits((float)op.content));
						break;
					case TYPE_STRING :
						byte[] b = string2Bits(op.content.toString());
						byte[] as = new byte[b.length + 1];
						System.arraycopy(b, 0, as, 0, b.length);
						as[as.length - 1] = 0;
						out.write(as);
						break;
					default:
						break;
					}
					out.write(zerobuff);
				}
		
			}
			
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public War3AbilityKnot listItem(File file) throws ErrorWar3oFile, TooLongStringException, UnknownWar3oDataException{
		War3AbilityKnot knot = null;
		FileInputStream input = null;
		try {
			input = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		byte[] buff = new byte[1024];
		
		try {
			readFirm(input, buff, 0, 8);
			knot = new War3AbilityKnot();
			int normalCnt = bits2Int(buff, 4);
			knot.setNormalCount(normalCnt);
			//System.out.println("********************************");
			//System.out.println("共有标准技能 " + normalCnt + "个");
		
			for (int i = 0; i < normalCnt; i++) {
				readFirm(input, buff, 0, 12);
				int optionCnt = bits2Int(buff, 8);
				String abilityId = bits2String(buff, 0, 4);
				Ability ability = new Ability(abilityId);
				knot.putN(ability);
				ability.setOptionCnt(optionCnt);
				//System.out.println(abilityId + "技能修改了 " + optionCnt + "项");
				for (int j = 0; j < optionCnt; j++){
					readFirm(input, buff, 0, 8);
					String optionId = bits2String(buff, 0, 4);
					//System.out.println("  ->" + optionId);
					int type = bits2Int(buff, 4);
					if (type == TYPE_INT){
						readFirm(input, buff, 0, 4);
						int c = bits2Int(buff, 0);
						Option option = new Option(optionId, -1, type, c);
						ability.put(option);
					//	System.out.println("    ->" + c);
					} else if (type == TYPE_FLOAT || type == TYPE_FLOAT_){
						readFirm(input, buff, 0, 4);
						float c = bits2Float(buff, 0);
						Option option = new Option(optionId, -1, type, c);
						ability.put(option);
					//	System.out.println("    ->" + c);
					} else if (type == TYPE_STRING) {
						int n = 0;
						while (true){
							readFirm(input, buff, n, 1);
							
							if (buff[n] == 0){
								break;
							}
							n ++;
							if (n > 1024){
								throw new TooLongStringException();
							}
						}
						String c = bits2String(buff, 0, n);
						Option option = new Option(optionId, -1, type, c);
						ability.put(option);
						//System.out.println("    ->" + c);
						
					} else {
						throw new UnknownWar3oDataException();
					}
					readFirm(input, buff, 0, 4);
				}
			}
			
			readFirm(input, buff, 0, 4);
			int udgCnt = bits2Int(buff, 0);
			knot.setUdgCount(udgCnt);
			//System.out.println("********************************");
			//System.out.println("共有自定义技能 " + udgCnt + "个");

			for (int i = 0; i < udgCnt; i++) {
				readFirm(input, buff, 0, 12);
				int optionCnt = bits2Int(buff, 8);
				String abilityModel = bits2String(buff, 0, 4);
				String abilityId = bits2String(buff, 4, 4);
				Ability ability = new Ability(abilityModel,abilityId);
				knot.putU(ability);
				ability.setOptionCnt(optionCnt);
				//System.out.println(abilityId + "技能修改了 " + optionCnt + "项");
				for (int j = 0; j < optionCnt; j++){
					readFirm(input, buff, 0, 8);
					String optionId = bits2String(buff, 0, 4);
					int type = bits2Int(buff, 4);
					if (type == TYPE_INT){
						readFirm(input, buff, 0, 4);
						int c = bits2Int(buff, 0);
						Option option = new Option(optionId, -1, type, c);
						ability.put(option);
						//System.out.println("    ->" + c);
					} else if (type == TYPE_FLOAT || type == TYPE_FLOAT_){
						readFirm(input, buff, 0, 4);
						float c = bits2Float(buff, 0);
						Option option = new Option(optionId, -1, type, c);
						ability.put(option);
						//System.out.println("    ->" + c);
					} else if (type == TYPE_STRING) {
						int n = 0;
						while (true){
							readFirm(input, buff, n, 1);
							
							if (buff[n] == 0){
								break;
							}
							n ++;
							if (n > 1024){
								throw new TooLongStringException();
							}
						}
						String c = bits2String(buff, 0, n);
						Option option = new Option(optionId, -1, type, c);
						ability.put(option);
						//System.out.println("    ->" + c);
						
					} else {
						throw new UnknownWar3oDataException();
					}
					readFirm(input, buff, 0, 4);
				}
			}
			
			
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return knot;
	}
	
	private void writeItem(FileOutputStream out,War3AbilityKnot knot){
		try {
			byte[] zerobuff = new byte[]{0,0,0,0};
			out.write(int2Bits(2));
			out.write(int2Bits(knot.normalCount));
			for(int i = 0; i < knot.getNormalCount(); i++){
				Ability a = knot.getN(i);
				byte[] abName = string2Bits(a.name);
				out.write(abName);
				out.write(zerobuff);
				int cnt = a.optionCnt;
				out.write(int2Bits(cnt));
				for (int j = 0; j < cnt; j++){
					Option op = a.get(j);
					out.write(string2Bits(op.dataName));
					int type = op.type;
					out.write(int2Bits(type));
					switch (type) {
					case TYPE_INT :
						out.write(int2Bits((int)op.content));
						break;
					case TYPE_FLOAT_ :
					case TYPE_FLOAT :
						out.write(float2Bits((float)op.content));
						break;
					case TYPE_STRING :
						byte[] b = string2Bits(op.content.toString());
						byte[] as = new byte[b.length + 1];
						System.arraycopy(b, 0, as, 0, b.length);
						as[as.length - 1] = 0;
						out.write(as);
						break;
					default:
						break;
					}
					out.write(abName);
				}
			}
			
			out.write(int2Bits(knot.getUdgCount()));
			for(int i = 0; i < knot.getUdgCount(); i++){
				Ability a = knot.getU(i);
				byte[] abName = string2Bits(a.name);
				out.write(string2Bits(a.model));
				out.write(abName);
				int cnt = a.optionCnt;
				out.write(int2Bits(cnt));
				for (int j = 0; j < cnt; j++){
					Option op = a.get(j);
					out.write(string2Bits(op.dataName));
					int type = op.type;
					out.write(int2Bits(type));
					switch (type) {
					case TYPE_INT :
						out.write(int2Bits((int)op.content));
						break;
					case TYPE_FLOAT_ :
					case TYPE_FLOAT :
						out.write(float2Bits((float)op.content));
						break;
					case TYPE_STRING :
						byte[] b = string2Bits(op.content.toString());
						byte[] as = new byte[b.length + 1];
						System.arraycopy(b, 0, as, 0, b.length);
						as[as.length - 1] = 0;
						out.write(as);
						break;
					default:
						break;
					}
					out.write(abName);
				}
		
			}
			
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public void writeItem(File file,War3AbilityKnot knot){
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		writeItem(out, knot);
		try {
			out.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public War3AbilityKnot merge(War3AbilityKnot[] knots){
		if (knots == null || knots.length < 1){
			return null;
		}
		War3AbilityKnot sub = knots[0];
		for (int i = 1 ; i < knots.length; i++){
			merge(sub,knots[i]);
		}
		return sub;
	}
	
	public void clearKnot(){
		w3uKnot = null;
		w3tKnot = null;
		w3bKnot = null;
		w3dKnot = null;
		w3aKnot = null;
		w3hKnot = null;
		w3qKnot = null;
	}
	
	public void writeW3O(File file){
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			out.write(int2Bits(1));
			
			if (w3uKnot != null){
				out.write(int2Bits(1));
				writeItem(out, w3uKnot);
			} else {
				out.write(int2Bits(0));
			}
			
			if (w3tKnot != null){
				out.write(int2Bits(1));
				writeItem(out, w3tKnot);
			} else {
				out.write(int2Bits(0));
			}
			
			if (w3bKnot != null){
				out.write(int2Bits(1));
				writeItem(out, w3bKnot);
			} else {
				out.write(int2Bits(0));
			}
			
			if (w3dKnot != null){
				out.write(int2Bits(1));
				writeItem(out, w3dKnot);
			} else {
				out.write(int2Bits(0));
			}
			
			if (w3aKnot != null){
				out.write(int2Bits(1));
				writeAbility(out, w3aKnot);
			} else {
				out.write(int2Bits(0));
			}
			
			if (w3hKnot != null){
				out.write(int2Bits(1));
				writeItem(out, w3hKnot);
			} else {
				out.write(int2Bits(0));
			}
			
			if (w3qKnot != null){
				out.write(int2Bits(1));
				writeItem(out, w3qKnot);
			} else {
				out.write(int2Bits(0));
			}
			
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @return w3uKnot
	 */
	public War3AbilityKnot getW3uKnot() {
		return w3uKnot;
	}

	/**
	 * @param w3uKnot 要设置的 w3uKnot
	 */
	public void setW3uKnot(War3AbilityKnot w3uKnot) {
		this.w3uKnot = w3uKnot;
	}

	/**
	 * @return w3tKnot
	 */
	public War3AbilityKnot getW3tKnot() {
		return w3tKnot;
	}

	/**
	 * @param w3tKnot 要设置的 w3tKnot
	 */
	public void setW3tKnot(War3AbilityKnot w3tKnot) {
		this.w3tKnot = w3tKnot;
	}

	/**
	 * @return w3bKnot
	 */
	public War3AbilityKnot getW3bKnot() {
		return w3bKnot;
	}

	/**
	 * @param w3bKnot 要设置的 w3bKnot
	 */
	public void setW3bKnot(War3AbilityKnot w3bKnot) {
		this.w3bKnot = w3bKnot;
	}

	/**
	 * @return w3dKnot
	 */
	public War3AbilityKnot getW3dKnot() {
		return w3dKnot;
	}

	/**
	 * @param w3dKnot 要设置的 w3dKnot
	 */
	public void setW3dKnot(War3AbilityKnot w3dKnot) {
		this.w3dKnot = w3dKnot;
	}

	/**
	 * @return w3aKnot
	 */
	public War3AbilityKnot getW3aKnot() {
		return w3aKnot;
	}

	/**
	 * @param w3aKnot 要设置的 w3aKnot
	 */
	public void setW3aKnot(War3AbilityKnot w3aKnot) {
		this.w3aKnot = w3aKnot;
	}

	/**
	 * @return w3hKnot
	 */
	public War3AbilityKnot getW3hKnot() {
		return w3hKnot;
	}

	/**
	 * @param w3hKnot 要设置的 w3hKnot
	 */
	public void setW3hKnot(War3AbilityKnot w3hKnot) {
		this.w3hKnot = w3hKnot;
	}

	/**
	 * @return w3qKnot
	 */
	public War3AbilityKnot getW3qKnot() {
		return w3qKnot;
	}

	/**
	 * @param w3qKnot 要设置的 w3qKnot
	 */
	public void setW3qKnot(War3AbilityKnot w3qKnot) {
		this.w3qKnot = w3qKnot;
	}
}