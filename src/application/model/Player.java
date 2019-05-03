package application.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Player {
	static ArrayList<Player> list = new ArrayList();
	private String name;
	private int curLevel = 0;
	private HashMap<Character, Integer> mistakes;
	
	private Player(String name) {
		this.name = name;
	}
	
	void create(String name) {
		if (list.stream().anyMatch(p -> p.name.equals(name))) {
			list.add(new Player(name));
		}
	}
	
	void load() {
		try(BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
	
		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    
		    String everything = sb.toString();
		} catch(Exception e){
            System.out.println(e);
        }
	}
	
	void save() {		
		try(BufferedWriter br = new BufferedWriter(new FileWriter("file.txt"))) {
		    StringBuilder sb = new StringBuilder();
		    String everything = sb.toString();
		    br.write(everything);
		} catch(Exception e){
            System.out.println(e);
        }
	}

}
