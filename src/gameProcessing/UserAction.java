package gameProcessing;

import java.util.Scanner;

import gameEntity.Coordinate;
import gameProcessing.processingUserCommand.Command;
import gameProcessing.processingUserCommand.ProcessedCommand;
import gameProcessing.processingUserCommand.ProcessingUserCommand;

public class UserAction {
	
	public static Scanner input = new Scanner(System.in);
	
	public static void alterMakeTurn(GameSession game) {
		while (true) {
			String inputUserCommand = input.nextLine();
			ProcessedCommand processedCommand = ProcessingUserCommand.processUserCommand(inputUserCommand, game);
			
			if (processedCommand.getUserCommand() == Command.EXIT) {
				ProcessingSaveGame.saveGame(game);
				System.exit(0);
				return;
			}
			
			if (processedCommand.getImputCoordinate() == null || processedCommand.getUserCommand() == null) {
				System.out.println("Uncorrect command. Please use examples for writing correct command.");
			}
			else {
				switch (processedCommand.getUserCommand()) {
				case OPEN_CELL :
					if (openCell(game, processedCommand.getImputCoordinate())) {
						return;
					}
					break;
				case BOMB_MARKER :
					if (alterMakeUserMarker(game, processedCommand)) {
						return;
					}
					break;
				case UNSURE_MARKER :
					if (alterMakeUserMarker(game, processedCommand)) {
						return;
					}
					break;
				case DELETE_MARKER :
					if (alterMakeUserMarker(game, processedCommand)) {
						return;
					}
					break;
				default:
					System.out.println("Uncorrect command. Please use examples for writing correct command.");
					break;
				}
			}
		}
	}
	
	private static boolean openCell(GameSession game, Coordinate coordinate) {		
			if (game.getCell(coordinate).isVisible()) {
				System.out.println("This is visible cell. Chouse another cell, please.");
				return false;
			}
			else if (game.getCell(coordinate).toString() == "X") {
				System.out.println("Be careful! You marked this like bomb.");
				return false;
			}
			
			processValidCoordinate(game, coordinate);
			return true;
	}
	
	private static boolean alterMakeUserMarker(GameSession game, ProcessedCommand processedCommand) {
		if (!game.getCell(processedCommand.getImputCoordinate()).isVisible()) {
			switch (processedCommand.getUserCommand()) {
			case BOMB_MARKER :
				game.getCell(processedCommand.getImputCoordinate()).makeMark("X");
				break;
			case UNSURE_MARKER :
				game.getCell(processedCommand.getImputCoordinate()).makeMark("?");
				break;
			case DELETE_MARKER :
				game.getCell(processedCommand.getImputCoordinate()).deleteMark();
				break;
			}
			return true;
		}
		else {
			System.out.println("This is visible cell. Chouse another cell, please.");
			return false;
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
