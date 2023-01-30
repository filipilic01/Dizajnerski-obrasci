package command;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class UndoCmd implements CommandShape{

	 private CommandShape command;
	 
	 public UndoCmd(CommandShape command) {
		 this.command=command;
		 
	 }
	@Override
	public void execute() {
		
		command.unexecute();
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		command.execute();
	}

}
