package command;

public class RedoCmd implements CommandShape {

	private CommandShape command;
	
	 public RedoCmd(CommandShape command) {
		 this.command=command;
		 
	 }
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		command.execute();
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		command.unexecute();
	}

}
