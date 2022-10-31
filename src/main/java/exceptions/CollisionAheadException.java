package exceptions;

public class CollisionAheadException extends RuntimeException {
  public CollisionAheadException() {
    super("Rover will collide with another rover");
  }
}


