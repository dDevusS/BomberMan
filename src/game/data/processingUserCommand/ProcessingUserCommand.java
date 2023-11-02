package game.data.processingUserCommand;

import game.data.GameSession;
import game.data.gameEntity.Coordinate;

public class ProcessingUserCommand {

	public static Coordinate convertToCoordinate(String userCommand, GameSession game) {
		String[] command = userCommand.split("-");

		if (command.length == 2) {
			try {
				int row = Integer.parseInt(command[0].trim());
				int column = Integer.parseInt(command[1].trim());

				if (row > 0 && row <= game.getRows() && column > 0 && column <= game.getColumns()) {
					return new Coordinate(row, column);
				}
			} catch (NumberFormatException e) {
			}
		}
		return null;
	}

	public static ProcessedCommand processUserCommand(String userCommand, GameSession game) {
		String[] splitedCommand = userCommand.toLowerCase().split(" ", 2);
		if (splitedCommand.length == 2) {
			return new ProcessedCommand(returnCommand(splitedCommand[0]), convertToCoordinate(splitedCommand[1], game));
		} else
			return new ProcessedCommand(returnCommand(splitedCommand[0]), null);
	}

	private static Command returnCommand(String command) {
		switch (command) {
		case "open":
			return Command.OPEN_CELL;
		case "unsure":
			return Command.UNSURE_MARKER;
		case "bomb":
			return Command.BOMB_MARKER;
		case "delete":
			return Command.DELETE_MARKER;
		case "exit":
			return Command.EXIT;
		default:
			return Command.UNKNOWN;
		}
	}
}
