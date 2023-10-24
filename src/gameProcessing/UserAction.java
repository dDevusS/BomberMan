package gameProcessing;

import java.util.Scanner;

public class UserAction {
	
	public static Scanner input = new Scanner(System.in);
	
	public static void makeFirstTurn(String command, GameSession game) {
		
	}
	
	public void makeTurn() {}
	
	public void pauseGame() {}
	
	private void saveGame() {}
	
	enum typeCommand {
		UNCORRECT, COORDINATE, EXIT;
	}
	
	private typeCommand checkUserCommand(String userCommand, GameSession game) {
		switch (userCommand) {
		case "exit" : return typeCommand.EXIT;
		}
		
		String[] command = userCommand.split("-");
		
		if (command.length == 2) {
			try {
				Integer.parseInt(command[0]);
				Integer.parseInt(command[1]);
				
				if (Integer.parseInt(command[0]) > 0 && Integer.parseInt(command[0]) <= game.getColumns() ||
						Integer.parseInt(command[0]) > 0 && Integer.parseInt(command[0]) <= game.getColumns()) {
					return typeCommand.COORDINATE;
				}
			} 
			catch (NumberFormatException e) {
				return typeCommand.UNCORRECT;
			}
		}
		return typeCommand.UNCORRECT;
	}
}
