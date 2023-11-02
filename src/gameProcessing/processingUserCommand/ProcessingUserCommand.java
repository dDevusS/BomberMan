package gameProcessing.processingUserCommand;

import gameEntity.Coordinate;
import gameProcessing.GameSession;

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
			} 
			catch (NumberFormatException e) {
			}
		}
		return null;
	}
	
}
