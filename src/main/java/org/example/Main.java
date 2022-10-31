package org.example;

import org.example.context.Space;
import org.example.rover_logic.Rover;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import exceptions.CollisionAheadException;
import exceptions.OutOfSpaceBoundariesException;

public class Main {

  public static void main(String[] args) {


    final File file = new File(args[0]);
    if (!file.exists()) {
      throw new IllegalArgumentException("input file does not exist");
    }
    List<String> roverInitData = new ArrayList<>();
    List<String> roverCommands = new ArrayList<>();
    Space space = Space.getInstance();
    parseInputData(file, roverInitData, roverCommands);
    roverInitData.forEach(roverCoordinate -> {
      try {
        final Rover rover = initRover(roverCoordinate);
        rover.parseCommands(roverCommands.get(roverInitData.indexOf(roverCoordinate)));
        // flagging the rover position in the space for collision detection
        space.occupySpace(rover.getX(),rover.getY());
        System.out.println(rover.getX() + " " + rover.getY() + " " + rover.getDirection());
      } catch (CollisionAheadException | OutOfSpaceBoundariesException | IllegalArgumentException e) {
        System.out.println("An error occured while handling rover : " + e.getMessage());
      }
    });
  }

  private static void parseInputData(File file, List<String> roverInitData, List<String> roverCommands) {
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      initSpace(br);
      String line;

      while ((line = br.readLine()) != null) {
        roverInitData.add(line);
        roverCommands.add(br.readLine());
      }

      if (roverInitData.size() != roverCommands.size()) {
        throw new IllegalArgumentException();
      }
    } catch (IOException | IllegalArgumentException e) {
      throw new IllegalArgumentException("input file is not valid");
    }
  }

  private static void initSpace(final BufferedReader br) throws IOException {
    final String [] coordinates = br.readLine().split(" ");
    Space spaceInstance = Space.getInstance();
    spaceInstance.setXMax(Integer.parseInt(coordinates[0]));
    spaceInstance.setYMax(Integer.parseInt(coordinates[1]));
    spaceInstance.init();
  }

  private static Rover initRover(final String line) {
    final String[] roverCoordinates = line.split(" ");
    if (roverCoordinates.length != 3) {
      throw new IllegalArgumentException("not enough coordinates");
    }

    return new Rover(Integer.parseInt(roverCoordinates[0]),
        Integer.parseInt(roverCoordinates[1]), roverCoordinates[2]);
  }


}