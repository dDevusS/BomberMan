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
				Coordinate coordinate = new Coordinate(userCommand.split("-"));
				
				if (game.getGameField().getGameField().get(coordinate).isVisible()) {
					System.out.println("This is visible cell. Chouse another cell, please.");
					continue;
				}
				
				game.increaceCounterTurn();
				game.getGameField().getGameField().get(coordinate).makeVisible();
				
				if (game.getCounterTurns() == 1) {
					makeVisibleAreaForFirstTurn(coordinate, game);
					openAllZeroCellClosedToChoese(coordinate, game);
					return;
				}
				else {
					if (game.getGameField().getGameField().get(coordinate).isBomb()) {
						game.makeExplosion();
						return;
					}
					else {
						openAllZeroCellClosedToChoese(coordinate, game);
						return;
					}
				}
			}
			else {
				System.out.println("Uncorrect command. Please use examples for writing correct command.");
			}
		}
	}
	
	private void saveGame() {}
	
	private static void openAllZeroCellClosedToChoese(Coordinate coordinate, GameSession game) {
		if (game.getGameField().getGameField().get(coordinate).getStepsFromBomb() != 0) {
			return;
		}
		for (int row = -1; row <= 1; row++) {
			for (int column = -1; column <= 1; column++) {
				if (game.getGameField().getGameField().get(coordinate.shiftCoordinate(row, column)) != null &&
						!game.getGameField().getGameField().get(coordinate.shiftCoordinate(row, column)).isVisible()) {
					game.getGameField().getGameField().get(coordinate.shiftCoordinate(row, column)).makeVisible();
					openAllZeroCellClosedToChoese(coordinate.shiftCoordinate(row, column), game);
				}
			}
		}
	}
	
	private static void makeVisibleAreaForFirstTurn(Coordinate coordinate, GameSession game) {
		game.getGameField().getGameField().get(coordinate).setStepsFromBomb(0);
		
		for (int row = -1; row <= 1; row++) {
			for (int column = -1; column <= 1; column++) {
				if (game.getGameField().getGameField().get(coordinate.shiftCoordinate(row, column)) != null) {
					game.getGameField().getGameField().get(coordinate.shiftCoordinate(row, column)).makeBeginingCell();
				}
			}
		}
		game.getGameField().createBombs(game.getColumns(), game.getRows(), game.getQuantityOfBombs());
	}
	
	private static boolean isCoordinate(String userCommand, GameSession game) {
		String[] command = userCommand.split("-");
		
		if (command.length == 2) {
			try {
				Integer.parseInt(command[0].replaceAll("\\s", ""));
				Integer.parseInt(command[1].replaceAll("\\s", ""));
				
				if (Integer.parseInt(command[0].replaceAll("\\s", "")) > 0 && Integer.parseInt(command[0].replaceAll("\\s", "")) <= game.getColumns() ||
						Integer.parseInt(command[0].replaceAll("\\s", "")) > 0 && Integer.parseInt(command[0].replaceAll("\\s", "")) <= game.getColumns()) {
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
