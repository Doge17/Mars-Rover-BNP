package ContextTest;

import org.example.context.Compass;
import org.example.rover_logic.enums.Direction;
import org.junit.Assert;
import org.junit.Test;

public class CompassTest {
  @Test
  public void getNextRightDirection_Should_Cycle_Over_List() {
  //given
    Compass compass = Compass.getInstance();
  //when
    compass.getNextRightDirection(Direction.W);
  //then
    Assert.assertEquals(Direction.N, compass.getNextRightDirection(Direction.W));
  }

  @Test
  public void getNextLeftDirection_Should_Cycle_Backward_Over_List() {
  //given
    Compass compass = Compass.getInstance();
  //when
    compass.getNextLeftDirection(Direction.N);
  //then
    Assert.assertEquals(Direction.W, compass.getNextLeftDirection(Direction.N));
  }

  @Test
  public void isSingleton_Should_Return_Same_Instance() {
  //given
    Compass compass = Compass.getInstance();
  //when
    Compass compass2 = Compass.getInstance();
  //then
    Assert.assertEquals(compass, compass2);
  }

  }


