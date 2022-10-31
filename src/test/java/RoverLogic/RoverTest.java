package RoverLogic;

import org.example.context.Space;
import org.example.rover_logic.Rover;
import org.junit.Assert;
import org.junit.Test;

import exceptions.CollisionAheadException;
import exceptions.OutOfSpaceBoundariesException;

public class RoverTest {

  @Test
  public void rover_Should_Be_Initialised_With_Correct_Values() {
    // given
    String[] coordinates = {"1", "2", "N"};
    // when
    Rover rover = new Rover(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]), coordinates[2]);
    // then
    Assert.assertEquals(new Integer("1"), rover.getX());
    Assert.assertEquals(new Integer("2"), rover.getY());
    Assert.assertEquals("N", rover.getDirection().toString());
  }
  @Test
  public void rover_Should_Not_Be_Initialised_With_Incorrect_XAxis() {
    // given
    String[] coordinates = {"-1", "2", "N"};
    // when
    Integer x = Integer.parseInt(coordinates[0]);
    Integer y = Integer.parseInt(coordinates[1]);
    // then
    Assert.assertThrows(IllegalArgumentException.class, () -> new Rover(x,y, coordinates[2]));
  }

  @Test
  public void rover_Should_Not_Be_Initialised_With_Incorrect_YAxis() {
    // given
    String[] coordinates = {"1", "-1", "N"};
    // when
    Integer x = Integer.parseInt(coordinates[0]);
    Integer y = Integer.parseInt(coordinates[1]);
    // then
    Assert.assertThrows(IllegalArgumentException.class, () -> new Rover(x, y, coordinates[2]));
  }

  @Test
  public void rover_Should_Not_Be_Initialised_With_Incorrect_Direction() {
    // given
    String[] coordinates = {"1", "2", "Z"};
    // when
    Integer x = Integer.parseInt(coordinates[0]);
    Integer y = Integer.parseInt(coordinates[1]);
    String direction = coordinates[2];

    // then
    Assert.assertThrows(IllegalArgumentException.class, () -> new Rover(x,y, direction));
  }

  @Test
  public void parseCommand_should_move_rover_with_correct_values() {
    // given
    String commandQueue = "LMLMLMLMM";
    String commandQueue2 = "MMRMMRMRRM";
    Space.getInstance().setXMax(5);
    Space.getInstance().setYMax(5);
    Space.getInstance().init();
    // when
    Rover rover  = new Rover(1, 2, "N");
    Rover rover2 = new Rover(3, 3, "E");
    rover.parseCommands(commandQueue);
    rover2.parseCommands(commandQueue2);
    // then
    //Rover 1
    Assert.assertEquals(1, rover.getX().intValue());
    Assert.assertEquals(3, rover.getY().intValue());
    Assert.assertEquals("N", rover.getDirection().toString());
    //Rover 2
    Assert.assertEquals(5, rover2.getX().intValue());
    Assert.assertEquals(1, rover2.getY().intValue());
    Assert.assertEquals("E", rover2.getDirection().toString());
  }
  @Test
  public void parseCommand_should_throw_if_incorrect_string() {
    // given
    String emptyCommandQueue = "";
    String nullCommandQueue = null;
    String blankCommandQueue = " ";
    // when
    Rover rover  = new Rover(1, 2, "N");
    // then
    Assert.assertThrows(IllegalArgumentException.class, () -> rover.parseCommands(emptyCommandQueue));
    Assert.assertThrows(NullPointerException.class, () -> rover.parseCommands(nullCommandQueue));
    Assert.assertThrows(IllegalArgumentException.class, () -> rover.parseCommands(blankCommandQueue));
  }

  @Test
  public void parseCommand_should_throw_if_string_is_invalid() {
    // given
    String commandQueue = "LMLMLMLMMX";
    // when
    Rover rover  = new Rover(1, 2, "N");
    // then
    Assert.assertThrows(IllegalArgumentException.class, () -> rover.parseCommands(commandQueue));
  }

  @Test
  public void executeCommands_should_change_direction_accordingly() {
    // given
    String commandQueueL = "L";
    String commandQueueR = "R";

    Rover rover  = new Rover(1, 2, "N");
    Rover rover2 = new Rover(1, 2, "N");
    // when
    rover.parseCommands(commandQueueL);
    rover2.parseCommands(commandQueueR);
    // then
    Assert.assertEquals("W", rover.getDirection().toString());
    Assert.assertEquals("E", rover2.getDirection().toString());

  }

  @Test
  public void executeCommands_should_move_rover() {
    // given
    String commandQueue = "M";
    Rover rover  = new Rover(1, 2, "N");
    Space.getInstance().setXMax(5);
    Space.getInstance().setYMax(5);
    Space.getInstance().init();
    // when
    rover.parseCommands(commandQueue);
    // then
    Assert.assertEquals(3, rover.getY().intValue());
  }

  @Test
  public void rover_should_stop_if_out_space_boudaries(){
    //given
    String commandQueue = "M";
    Rover rover  = new Rover(5, 5, "N");
    Space.getInstance().setXMax(5);
    Space.getInstance().setYMax(5);
    Space.getInstance().init();
    //then
    Assert.assertThrows(OutOfSpaceBoundariesException.class, () -> rover.parseCommands(commandQueue));
  }

  @Test
  public void rover_should_stop_if_will_collide(){
    //given
    String commandQueue = "M";
    Rover rover  = new Rover(1, 2, "N");
    Space.getInstance().setXMax(5);
    Space.getInstance().setYMax(5);
    Space.getInstance().init();
    //when
    Space.getInstance().occupySpace(1, 3);
    //then
    Assert.assertThrows(CollisionAheadException.class, () -> rover.parseCommands(commandQueue));
  }



}
