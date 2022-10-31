package org.example.rover_logic.enums;

/**
 * Direction enum represents the direction of the rover.
 * author: ZIDANI El Mehdi
 */
public enum Direction {
  N("N"),
  E("E"),
  S("S"),
  W("W");

  private final String directionCode;

  Direction(String direction) {
    this.directionCode = direction;
  }

}
