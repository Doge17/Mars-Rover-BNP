package exceptions;

public class OutOfSpaceBoundariesException extends RuntimeException {
  public OutOfSpaceBoundariesException() {
    super("Rover is out of space boundaries");
  }
}


