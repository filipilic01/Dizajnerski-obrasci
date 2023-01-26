package strategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFileChooser;

public class SaveLoad implements Serializable, Strategy{
	
	private FileOutputStream fileOut;
	private ObjectOutputStream out;
	private Logger logger;
	private File fileToSave;
	private File fileToLoad;
	
	public SaveLoad(Logger logger) {
		this.logger=logger;
	}
	
	public void saveLog() {
		JFileChooser j = new JFileChooser();
		
		int i=j.showSaveDialog(j);
		if(i==JFileChooser.APPROVE_OPTION) {
			fileToSave = j.getSelectedFile();
			System.out.println(fileToSave.getAbsolutePath());
		
		
		try {
			fileOut = new FileOutputStream(fileToSave.getAbsolutePath());
			out = new ObjectOutputStream(fileOut);
			out.writeObject(logger.getTextArea().getText());
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

	@Override
	public void loadFile(){
		JFileChooser j = new JFileChooser();
		
		int i=j.showSaveDialog(j);
		if(i==JFileChooser.APPROVE_OPTION) {
			fileToLoad= j.getSelectedFile();
		
			try {
				FileInputStream fileIn = new FileInputStream(fileToLoad.getAbsolutePath());
				try {
					ObjectInputStream in = new ObjectInputStream(fileIn);
					String s = (String)in.readObject();
					
					System.out.println(s);
					logger.getTextArea().setText(s);
					in.close();
					fileIn.close();
					System.out.println("Successfull loaded");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}

}
