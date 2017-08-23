package GUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListCollab {

	
	ArrayList<String> collaborators;
	public ListCollab() {
		this.collaborators = new ArrayList<String>();
	}
	public ListCollab(String filePath) {
		this.collaborators = new ArrayList<String>();

        File file = new File(filePath);
        FileReader fr;
        
		try {
			fr = new FileReader(file);
		
        BufferedReader br = new BufferedReader(fr);

        for (String line = br.readLine(); line != null; line = br.readLine()) {
        		collaborators.add(line);
        }
        
        br.close();
        fr.close();
        
		} catch (FileNotFoundException e) {
			System.out.println("File not found exception");
		} catch (IOException e) {
			System.out.println("IO exception");
		}
       

	}
	
	public ArrayList<String> getCollab(){
		return this.collaborators;
	}
	
	public void afficheListCollab() {
		for (String string : collaborators) {
			System.out.println(string);
		}
	}
	
	public void add(String s) {
		this.collaborators.add(s);
	}
	
	public void remove(String s) {
		this.collaborators.remove(s);
	}

}
