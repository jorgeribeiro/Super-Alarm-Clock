package persistency;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import model.Contact;

public class FileHandler {
	public void writeFile(String fileName,String info) {
	    FileWriter fileWriter;  
	    BufferedWriter bufferedWriter; 
		try {
			fileWriter = new FileWriter(fileName);
			bufferedWriter = new BufferedWriter(fileWriter); 
			//Once writing objects are instantiated, the existing content of the file would be wiped out...
			bufferedWriter.write(info);
			bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file");
            ex.printStackTrace();
        }
	}
	
	public void readFile(String fileName) {
		
	}
}
