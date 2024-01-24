package com.voxxeddays;

import com.voxxeddays.domain.Circle;
import com.voxxeddays.domain.Point;
import com.voxxeddays.domain.Rectangle;
import com.voxxeddays.domain.Shape;

/**
 * Pattern matching for instanceof examples
 */
public class InstanceOfExample {

  private static final String s = "Variable";

  /** You can use this method to play around with the methods below */
  public static void main(String[] args) {
    var rectangle = new Rectangle(20, 10);
    var circle = new Circle(10);

    var point = new Point(2, 2);
  }

  /** This method shows the basic use of instanceof operator */
  public static double withoutPatternVariables(Shape shape) {
    if (shape instanceof Rectangle) {
      var rectangle = (Rectangle) shape;
      return 2 * rectangle.length() + 2 * rectangle.width();
    } else if (shape instanceof Circle) {
      var circle = (Circle) shape;
      return 2 * circle.radius() * Math.PI;
    } else {
      throw new IllegalArgumentException("Unrecognized shape");
    }
  }

  /**
   * This method improves the previous version {@link #withoutPatternVariables(Shape)} by using
   * pattern variables, so we can avoid the cast
   */
  public static double withPatternVariables(Shape shape) {
    if (shape instanceof Rectangle rectangle) {
      return 2 * rectangle.length() + 2 * rectangle.width();
    } else if (shape instanceof Circle circle) {
      return 2 * circle.radius() * Math.PI;
    } else {
      throw new IllegalArgumentException("Unrecognized shape");
    }
  }

  /**
   * This method shows how you can use the pattern variable to do further checks (and how you can't
   * use it).
   */
  public static double furtherUseOfPatternVariables(Shape shape) {
    // Compilation error, the variable `rectangle` might not have been initialized
    //    if (shape instanceof Rectangle rectangle || s.length() > 6) {
    //      return 2 * rectangle.length() + 2 * rectangle.width();
    //    }

    // Here we can use the pattern variable `rectangle` because we are sure it's initialized
    if (shape instanceof Rectangle rectangle && rectangle.length() > 6) {
      return 2 * rectangle.length() + 2 * rectangle.width();
    } else if (shape instanceof Circle circle) {
      return 2 * circle.radius() * Math.PI;
    } else {
      throw new IllegalArgumentException("Unrecognized shape");
    }
  }

  /**
   * This method shows the scope of the variables used.
   *
   * <p>If one pattern is not matched you can use the same variable name on the next pattern check
   * (in our case the `s`). Pay attention to possible flow scoping.
   */
  public static double scopeOfPatternVariables(Shape shape) {
    if (shape instanceof Rectangle s) {
      return 2 * s.length() + 2 * s.width();
    } else if (shape instanceof Circle s) {
      return 2 * s.radius() * Math.PI;
    }

    return s.length() * 2.0;
  }

  /**
   * This method shows a new feature introduced in Java 21, record patterns. It works with generic
   * records, it has type inference, and it works with nested records.
   */
  public static double useOfRecordPatterns(Object object) {
    // Using a record with pattern matching
    //  if (object instanceof Point p) {
    //    return Math.toDegrees(Math.atan2(p.y(), p.x()));
    //  }

    // Using a record with pattern matching getting values directly and using type inference
    if (object instanceof Point(var y, double x)) {
      return Math.toDegrees(Math.atan2(y, x));
    } else {
      throw new IllegalArgumentException("Not a point");
    }
  }

  /**
   * This method shows a new feature introduced in Java 21 (only preview), unnamed patterns.
   */
  public static double unnamedPatterns(Object object) {
    if (object instanceof Point(var x, _)) {
      return Math.toDegrees(Math.atan2(x, 10));
    } else {
      throw new IllegalArgumentException("Not a point");
    }
  }
}
