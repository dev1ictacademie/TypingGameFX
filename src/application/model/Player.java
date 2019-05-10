package application.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Player {
	static ArrayList<Player> list = new ArrayList<>();
	static String file = "file.txt";
	private String name;
	private int curLevel = 0;
	private HashMap<Character, Integer> mistakes = new HashMap<>();
	
//	public static void main(String[] args) {
//		Player p = Player.create("test");
//		p.setCurLevel(2);
//		HashMap testMap = new HashMap<>();
//		testMap.put("f", 4);
//		p.setMistakes(testMap);
//		System.out.println(list.size());
//		save();
//		load();
//		System.out.println(list.size());
//	}
	
	private Player(String name) {
		this.name = name;
	}
	
	public int getCurLevel() {
		return curLevel;
	}
	
	public void setCurLevel(int curLevel) {
		this.curLevel = curLevel;
	}
	
	public HashMap getMistakes() {
		return mistakes;
	}
	
	public void setMistakes(HashMap mistakes) {
		this.mistakes = mistakes;
	}
	
	public static Player create(String name) {
		if (!list.stream().anyMatch(p -> p.name.equals(name))) {
			Player p = new Player(name);
			list.add(p);
			return p;
		} else {return null;}
	}
	
	public static void load() {
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line = "";
		    list.clear();
	
		    while ((line = br.readLine())!= null) {
		        String[] lines = line.split(";");
		        
		        Player p = create(lines[0]);
		        if(p != null) {
		        	p.curLevel = Integer.valueOf(lines[1]);
		        	String[] map = lines[2].split(":");
		        	for(int i = 0; i < map.length; i=i+2) {
		        		p.mistakes.put(map[i].charAt(0), Integer.valueOf(map[i+1]));
		        	}
		        	System.out.println(p.toString());
		        }
		        //line = br.readLine();
		    }
		} catch(Exception e){
            e.printStackTrace();
        }
	}
	
	public static void save() {		
		try(BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
			for(Player p : list) {
				br.write(p.toString());
				br.newLine();
			}
		} catch(Exception e){
            e.printStackTrace();
        }
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(name + ";");
		sb.append(curLevel + ";");
		
		for (Map.Entry<Character, Integer> e : mistakes.entrySet()) {
			sb.append(e.getKey() + ":" + e.getValue() + ";");
		}
		
		return sb.toString();
	}

}
