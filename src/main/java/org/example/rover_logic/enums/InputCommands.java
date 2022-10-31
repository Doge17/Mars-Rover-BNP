package org.example.rover_logic.enums;

/**
 * InputCommands enum is an enum that represents the commands that the rover can receive.
 * author: ZIDANI El Mehdi
 */
public enum InputCommands {
  L("L"),
  R("R"),
  M("M");

  private final String command;

  InputCommands(String command) {
    this.command = command;
  }

}
