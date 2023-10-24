package gameProcessing;

import java.util.Scanner;

import gameEntity.Coordinate;

public class UserAction {
	
	public static Scanner input = new Scanner(System.in);
	
	public void makeTurn() {}
	
	private void saveGame() {}
	
	private static void makeVisibleAreaForFirstTurn(Coordinate coordinate, GameSession game) {
		for (int y = -1; y <= 1; y++) {
			for (int x = -1; x <= 1; x++) {
				if (game.getGameField().get(coordinate.shiftCoordinate(y, x)) != null) {
					game.getGameField().get(coordinate.shiftCoordinate(y, x)).makeBeginingCell(true);
					game.getGameField().get(coordinate.shiftCoordinate(y, x)).makeVisible();
				}
			}
		}
	}
	
	enum typeCommand {
		UNCORRECT, COORDINATE, EXIT;
	}
	
	private static typeCommand checkUserCommand(String userCommand, GameSession game) {
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
