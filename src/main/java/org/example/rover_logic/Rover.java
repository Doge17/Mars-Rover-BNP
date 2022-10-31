package org.example.rover_logic;


import org.example.context.Compass;
import org.example.context.Space;
import org.example.rover_logic.enums.Direction;
import org.example.rover_logic.enums.InputCommands;

import java.util.Objects;

import exceptions.CollisionAheadException;
import exceptions.InvalidDirectionException;
import exceptions.OutOfSpaceBoundariesException;
import lombok.Data;

/**
 * Rover class represents the rover.
 * <li>Each rover has a position (x,y),a direction and a compass.</li>
 * <li>A rover can only move with parseCommands method.</li>
 * author: ZIDANI El Mehdi
 */
@Data
public class Rover {

  Integer x;
  Integer y;
  Direction direction;
  Compass compass;

  public Rover(final Integer x, final Integer y, final String direction) {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("Rover coordinates must be positive");
    }
    this.x = x;
    this.y = y;
    this.direction = Direction.valueOf(direction);
    this.compass = Compass.getInstance();
  }

  /**
   * parseCommands method parses the commands by splitting input string.
   * getting the command from the input string and executing it by calling executeCommands method.
   *
   * @param commands input string of commands.
   */
  public void parseCommands(final String commands) {
    validateInputCommand(commands);
    for (final String command : commands.split("")) {
      executeCommands(InputCommands.valueOf(command));
    }
  }

  /**
   * checks if the input command is valid.
   *
   * @param commands input string of commands.
   */
  private static void validateInputCommand(final String commands) {
    Objects.requireNonNull(commands, "Empty Command String");
    if (commands.isEmpty() || commands.trim().isEmpty()) {
      throw new IllegalArgumentException("Empty Command String");
    }
  }

  /**
   * executeCommands method executes the command by calling the corresponding method.
   * <li>if L or R is called, compass is called to get the new direction then set it to the rover.</li>
   * <li>if M is called, move method is called to move the rover.</li>
   *
   * @param inputCommands input command.
   * @throws IllegalArgumentException if the command is not valid.
   */
  private void executeCommands(final InputCommands inputCommands) {
    switch (inputCommands) {
      case L:
        this.setDirection(compass.getNextLeftDirection(this.getDirection()));
        break;
      case R:
        this.setDirection(compass.getNextRightDirection(this.getDirection()));
        break;
      case M:
        move();
        break;
      default:
        throw new IllegalArgumentException("invalid command");
    }
  }


  /**
   * move method moves the rover by incrementing or decrementing x or y according to current direction.
   * @throws OutOfSpaceBoundariesException if the rover is out of space boundaries.
   * @throws CollisionAheadException       if the rover will collide with another rover on next move.
   */
  private void move() {
    if (!canMoveSafely()) throw new CollisionAheadException();

    switch (this.getDirection()) {
      case N:
        this.setY(this.getY() + 1);
        break;
      case E:
        this.setX(this.getX() + 1);
        break;
      case S:
        this.setY(this.getY() - 1);
        break;
      case W:
        this.setX(this.getX() - 1);
        break;
      default:
        throw new IllegalArgumentException("invalid direction");
    }
  }

  /**
   * canMoveSafely method checks for 2 things :
   * <li>if the rover will collide with another rover on next move</li>
   * <li>if the rover is out of space boundaries on next move</li>
   * @return true if the rover will collide with another rover on next move.
   * @throws OutOfSpaceBoundariesException if the rover will be out of space boundaries.
   * @throws IllegalArgumentException if the direction is not valid.
   */
  private boolean canMoveSafely() {
    Space space = Space.getInstance();
    try {
      switch (this.direction) {
        case N:
          return Objects.isNull(space.getSpaceField()[this.x][this.y + 1]);
        case E:
          return Objects.isNull(space.getSpaceField()[this.x + 1][this.y]);
        case S:
          return Objects.isNull(space.getSpaceField()[this.x][this.y - 1]);
        case W:
          return Objects.isNull(space.getSpaceField()[this.x - 1][this.y]);
        default:
          break;
      }
    }catch (ArrayIndexOutOfBoundsException e){
      throw new OutOfSpaceBoundariesException();
    }
    throw new InvalidDirectionException();
  }
}
