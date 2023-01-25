package strategy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Save implements Serializable{
	
	private FileOutputStream fileOut;
	private ObjectOutputStream out;
	private Logger logger;
	
	public Save(Logger logger) {
		this.logger=logger;
	}
	
	public void saveLog() {
		try {
			fileOut = new FileOutputStream("neki.txt");
			out = new ObjectOutputStream(fileOut);
			out.writeChars(logger.getTextArea().getText());
			out.close();
			fileOut.close();
			System.out.println(logger.getTextArea().getText());
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
