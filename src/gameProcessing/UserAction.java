package gameProcessing;

import java.util.Scanner;

import gameEntity.Coordinate;

public class UserAction {
	
	public static Scanner input = new Scanner(System.in);
	
	public static void makeTurn(GameSession game) {
		while (true) {
			String userCommand = input.nextLine();
			
			switch (userCommand) {
			case "exit" :
				ProcessingSaveGame.saveGame(game);
				System.exit(0);
				break;
			}
			
			Coordinate coordinate = convertToCoordinate(userCommand, game);
			
			if (coordinate != null) {
				
				if (game.getCell(coordinate).isVisible()) {
					System.out.println("This is visible cell. Chouse another cell, please.");
					continue;
				}
				
				processValidCoordinate(game, coordinate);
				break;
			}
			else {
				System.out.println("Uncorrect command. Please use examples for writing correct command.");
			}
		}
	}
	
	private static void openAllZeroCellClosedToChoese(Coordinate coordinate, GameSession game) {
		if (game.getCell(coordinate).getStepsFromBomb() != 0) {
			return;
		}
		for (int modificaterRow = -1; modificaterRow <= 1; modificaterRow++) {
			for (int modificaterColumn = -1; modificaterColumn <= 1; modificaterColumn++) {
				if (game.getCell(coordinate.shiftCoordinate(modificaterRow, modificaterColumn)) != null && 
						!game.getCell(coordinate.shiftCoordinate(modificaterRow, modificaterColumn)).isVisible()) {
					game.getCell(coordinate.shiftCoordinate(modificaterRow, modificaterColumn)).makeVisible(game);
					openAllZeroCellClosedToChoese(coordinate.shiftCoordinate(modificaterRow, modificaterColumn), game);
				}
			}
		}
	}
	
	private static void makeVisibleAreaForFirstTurn(Coordinate coordinate, GameSession game) {
		game.getCell(coordinate).setStepsFromBomb(0);
		
		for (int row = -1; row <= 1; row++) {
			for (int column = -1; column <= 1; column++) {
				if (game.getCell(coordinate.shiftCoordinate(row, column)) != null) {
					game.getCell(coordinate.shiftCoordinate(row, column)).makeBeginingCell();
				}
			}
		}
		game.getGameField().createBombs(game.getColumns(), game.getRows(), game.getQuantityOfBombs());
	}
	
	private static Coordinate convertToCoordinate(String userCommand, GameSession game) {
		String[] command = userCommand.split("-");
		
		if (command.length == 2) {
			try {
				int row = Integer.parseInt(command[0].trim());
				int column = Integer.parseInt(command[1].trim());
				
				if (row > 0 && row <= game.getRows() && column > 0 && column <= game.getColumns()) {
					return new Coordinate(row, column);
				}
			} 
			catch (NumberFormatException e) {
			}
		}
		return null;
	}
	
	private static void processValidCoordinate(GameSession game, Coordinate coordinate) {
		game.increaceCounterTurn();
		game.getCell(coordinate).makeVisible(game);
		
		if (game.getCounterTurns() == 1) {
			makeVisibleAreaForFirstTurn(coordinate, game);
			openAllZeroCellClosedToChoese(coordinate, game);
		}
		else if (game.getCell(coordinate).isBomb()){
			game.makeExplosion();
		}
		else {
			openAllZeroCellClosedToChoese(coordinate, game);
		}
	}
}
