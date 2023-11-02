package gameProcessing.processingUserCommand;

import gameEntity.Coordinate;

public class ProcessedCommand {
	
	private Command userCommand;
	private Coordinate imputCoordinate;
	
	ProcessedCommand(Command userCommand, Coordinate inputCoordinate) {
		this.userCommand = userCommand;
		this.imputCoordinate = inputCoordinate;
	}

	public Command getUserCommand() {
		return userCommand;
	}

	public Coordinate getImputCoordinate() {
		return imputCoordinate;
	}
}
