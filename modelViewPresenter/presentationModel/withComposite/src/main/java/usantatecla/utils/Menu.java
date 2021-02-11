package usantatecla.utils;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu {

	private static final String OPTION = "----- Choose one option -----"; // TODO Vista
	private ArrayList<Command> commandList;

	public Menu() {
		this.commandList = new ArrayList<Command>();
	}

	public void execute() {
		List<Command> commands = new ArrayList<Command>();
		for (int i = 0; i < this.commandList.size(); i++) {
			if (this.commandList.get(i).isActive()) {
				commands.add(this.commandList.get(i));
			}
		}
		commands.get(this.read(commands)).execute();
	}

	private int read(List<Command> commands) { // TODO Vista
		boolean error;
		int option;
		do {
			error = false;
			Console.getInstance().writeln();
			Console.getInstance().writeln(Menu.OPTION);
			for (int i = 0; i < commands.size(); i++) {
				Console.getInstance().writeln((i + 1) + ") " + commands.get(i).getTitle());
			}
			option = Console.getInstance().readInt("") - 1;
			if (!new ClosedInterval(0, commands.size()-1).includes(option)) {
				error = true;
			}
		} while (error);
	}

	protected void addCommand(Command command) {
		this.commandList.add(command);
	}

}
