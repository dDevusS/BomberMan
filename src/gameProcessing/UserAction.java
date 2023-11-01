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
				SaveLoadGameSession.saveGame(game);
				System.exit(0);
				break;
			}
		
			if (isCoordinate(userCommand, game)) {
				Coordinate coordinate = new Coordinate(userCommand.split("-"));
				
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
		for (int row = -1; row <= 1; row++) {
			for (int column = -1; column <= 1; column++) {
				if (game.getCell(coordinate.shiftCoordinate(row, column)) != null && 
						!game.getCell(coordinate.shiftCoordinate(row, column)).isVisible()) {
					game.getCell(coordinate.shiftCoordinate(row, column)).makeVisible(game);
					openAllZeroCellClosedToChoese(coordinate.shiftCoordinate(row, column), game);
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
	
	private static boolean isCoordinate(String userCommand, GameSession game) {
		String[] command = userCommand.split("-");
		
		if (command.length == 2) {
			try {
				int row = Integer.parseInt(command[0].trim());
				int column = Integer.parseInt(command[1].trim());
				
				if (row > 0 && row <= game.getRows() && column > 0 && column <= game.getColumns()) {
					return true;
				}
			} 
			catch (NumberFormatException e) {
			}
		}
		return false;
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
