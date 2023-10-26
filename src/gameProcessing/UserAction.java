package gameProcessing;

import java.util.Scanner;

import gameEntity.Coordinate;

public class UserAction {
	
	public static Scanner input = new Scanner(System.in);
	
	public static void makeTurn(GameSession game) {
		while (true) {
			String userCommand = input.nextLine();
			
			switch (userCommand) {
			case "exit" : System.exit(0); break;
			}
		
			if (isCoordinate(userCommand, game)) {
				String[] arrayCoordinate = userCommand.split("-");
				
				if (game.getCounterTurns() == 0) {
					makeVisibleAreaForFirstTurn(new Coordinate(Integer.parseInt(arrayCoordinate[0]), Integer.parseInt(arrayCoordinate[1])), game);
					game.increaceCounterTurn();
					game.getGameField().createBombs(game.getColumns(), game.getRows(), game.getQuantityOfBombs());
					return;
				}
				
				
			}
		}
	}
	
	private void saveGame() {}
	
	private static void makeVisibleAreaForFirstTurn(Coordinate coordinate, GameSession game) {
		for (int y = -1; y <= 1; y++) {
			for (int x = -1; x <= 1; x++) {
				if (game.getGameField().getGameField().get(coordinate.shiftCoordinate(y, x)) != null) {
					game.getGameField().getGameField().get(coordinate.shiftCoordinate(y, x)).makeBeginingCell(true);
				}
			}
		}
	}
	
	private static boolean isCoordinate(String userCommand, GameSession game) {	
		String[] command = userCommand.split("-");
		
		if (command.length == 2) {
			try {
				Integer.parseInt(command[0]);
				Integer.parseInt(command[1]);
				
				if (Integer.parseInt(command[0]) > 0 && Integer.parseInt(command[0]) <= game.getColumns() ||
						Integer.parseInt(command[0]) > 0 && Integer.parseInt(command[0]) <= game.getColumns()) {
					return true;
				}
			} 
			catch (NumberFormatException e) {
				return false;
			}
		}
		return false;
	}
}
