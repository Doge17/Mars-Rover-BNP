package ContextTest;

import org.example.context.Space;
import org.junit.Assert;
import org.junit.Test;

public class SpaceTest {
  @Test
  public void space_Should_Be_Initialised_With_Correct_Values(){
    // given
    String [] coordinates = {"5", "5"};
    Space space = Space.getInstance();
    // when
    space.setXMax(Integer.parseInt(coordinates[0]));
    space.setYMax(Integer.parseInt(coordinates[1]));
    // then
    Assert.assertEquals(new Integer("5"), space.getXMax());
    Assert.assertEquals(new Integer("5"), space.getYMax());
  }
  @Test
  public void space_Should_Not_Be_Initialised_With_Incorrect_YAxis() {
    // given
    String[] coordinates = {"5", "-1"};
    //when
    Space space = Space.getInstance();
    Integer yMax = Integer.parseInt(coordinates[1]);
    // then
    Assert.assertThrows(IllegalArgumentException.class, () -> space.setYMax(yMax));
  }

    @Test
    public void space_Should_Not_Be_Initialised_With_Incorrect_XAxis(){
      // given
      String [] coordinates = {"-1", "5"};
      //when
      Space space = Space.getInstance();
      Integer xMax = Integer.parseInt(coordinates[0]);
      // then
      Assert.assertThrows(IllegalArgumentException.class, () -> space.setXMax(xMax));
    }

@Test
  public void isSingleton_Should_Return_Same_Instance() {
  // given
  Space space = Space.getInstance();
  // when
  Space space2 = Space.getInstance();
  // then
  Assert.assertEquals(space, space2);
}

  }



