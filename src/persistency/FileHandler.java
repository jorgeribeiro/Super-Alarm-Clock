package persistency;

import java.io.*;

public class FileHandler {
	public void writeFile(String fileName, String info, boolean update) {
	    FileWriter fileWriter;  
	    BufferedWriter bufferedWriter; 
		try {
			fileWriter = new FileWriter("data/" + fileName,update);
			bufferedWriter = new BufferedWriter(fileWriter); 
			bufferedWriter.write(info);
			bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file");
            ex.printStackTrace();
        }
	}
	
	public String readFile(String fileName) {
		String toReturn = "";
		String info = "";
	        try {
	            FileReader fileReader = new FileReader("data/"+ fileName);            
	            BufferedReader bufferedReader = new BufferedReader(fileReader);//Wrap FileReader in BufferedReader.
	            while((info= bufferedReader.readLine()) != null) {
	            	toReturn += info + "\n";
	            } 
	            bufferedReader.close();
	            return toReturn;
	        }
	        catch(IOException ex) {
	            System.out.println("Error reading file"); 
	            ex.printStackTrace();                  
	        }
	        return toReturn;
	}
}
