package org.example.context;


import lombok.Getter;

/**
 * Space class represents the space where the rover is moving.
 * <li>It can be used to check if the rover is out of boundaries.</li>
 * <li>It is a singleton class,since all rovers move in the same space.</li>
 * author: ZIDANI El Mehdi
 */
public final class Space {


  @Getter
  private Boolean[][] spaceField;

  @Getter
  private  Integer xMax =0;

  @Getter

  private  Integer yMax =0;

  private static class LoadSpace {
    static final Space INSTANCE = new Space();
  }
  public static Space getInstance() {
    return LoadSpace.INSTANCE;
  }

  private Space() {
  }

   public void init() {
    validateSpaceBoundaries();
    //max coordinates are inclusive
    spaceField = new Boolean[xMax+1][yMax+1];
  }

  private void validateSpaceBoundaries() {
    if (xMax < 0 || yMax < 0) {
      throw new IllegalArgumentException("Space boundaries must be positive");
    }
  }

  public void setXMax(final Integer xMax) {
    if (xMax < 0) {
      throw new IllegalArgumentException("xMax must be positive");
    }
    this.xMax = xMax;
  }

  public void setYMax(final Integer yMax) {
    if (yMax < 0) {
      throw new IllegalArgumentException("yMax must be positive");
    }
    this.yMax = yMax;
  }

  public void occupySpace(final Integer x, final Integer y) {
    spaceField[x][y] = true;
  }

}
