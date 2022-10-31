package org.example.context;

import com.google.common.collect.ImmutableList;

import org.example.rover_logic.enums.Direction;

/**
 * Compass class is a singleton class that serves as a calculator for the next direction of the rover
 * based on the current direction and the command.
 * <ul>
 * <li>It is a singleton class because it is not necessary to have more than one instance of it</li>
 * <li>Directions are stored in an immutable list to avoid any modification of the list.</li>
 * </ul>
 * author: ZIDANI El Mehdi
 */
public final class Compass {

  private Compass() {
  }


  public static Compass getInstance() {
    return LoadCompass.INSTANCE;
  }

  private static class LoadCompass {
    static final Compass INSTANCE = new Compass();
  }

  // NORTH -> EAST -> SOUTH -> WEST -> NORTH
 private final ImmutableList<Direction> directions = ImmutableList.of(Direction.N, Direction.E, Direction.S, Direction.W);

  /**
   * getNextLeftDirection is a method that returns the next direction of the rover if
   * the input command is R
   * @param direction the current direction of the rover
   * @return the next direction of the rover
   */
  public Direction getNextRightDirection(final Direction direction) {
    final int index = directions.indexOf(direction);
    if (index == directions.size() - 1) {
      return directions.get(0);
    }
    return directions.get(index + 1);
  }

  /**
   * getNextLeftDirection is a method that returns the next direction of the rover if
   * the input command is L
   * @param direction the current direction of the rover
   * @return the next direction of the rover
   */
  public Direction getNextLeftDirection(final Direction direction) {
    final int index = directions.indexOf(direction);
    if (index == 0) {
      return directions.get(directions.size() - 1);
    }
    return directions.get(index - 1);
  }


}
