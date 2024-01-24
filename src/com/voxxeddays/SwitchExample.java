package com.voxxeddays;

import com.voxxeddays.domain.Circle;
import com.voxxeddays.domain.Point;
import com.voxxeddays.domain.Rectangle;
import com.voxxeddays.domain.Shape;

/** Pattern matching for switch examples */
public class SwitchExample {

  /**
   * You can use this method to play around with the methods below
   */
  public static void main(String[] args) {
    var rectangle = new Rectangle(20, 10);
    var circle = new Circle(10);

    var point = new Point(2, 2);
  }

  /** This method shows a basic usage of pattern matching with switch */
  public static double basicPatternMatchingSwitch(Shape shape) {
    //  switch (shape) {
    //      case Rectangle r: return 2 * r.length() + 2 * r.width();
    //      case Circle c:  return 2 * c.radius() * Math.PI;
    //      default: throw new IllegalArgumentException("Unrecognized shape");
    //  }

    return switch (shape) {
      case Rectangle r -> 2 * r.length() + 2 * r.width();
      case Circle c -> 2 * c.radius() * Math.PI;
      default -> throw new IllegalArgumentException("Unrecognized shape");
    };
  }

  /**
   * This method shows the scope of the variables used. Be careful with fall through cases they will
   * produce a compilation error.
   */
  public static double scopeOfPatternVariables(Shape shape) {
    // Compilation error
    //    switch (shape) {
    //      case Rectangle r: {
    //        System.out.println("its a rectangle " + r.length());
    //      }
    //      case Circle s: {
    //        System.out.println("its a circle " + s.radius());
    //      }
    //      default: throw new IllegalArgumentException("Unrecognized shape");
    //    }

    return switch (shape) {
      case Rectangle s -> 2 * s.length() + 2 * s.width();
      case Circle s -> 2 * s.radius() * Math.PI;
      default -> throw new IllegalArgumentException("Unrecognized shape");
    };
  }

  /**
   * This method shows how we can use the pattern variable to do additional check to our checking
   */
  public static double useOfWhenClause(Shape shape) {
    return switch (shape) {
      case Rectangle r when r.length() > 6 -> 3 * r.length() + 3 * r.width();
      case Rectangle r -> 2 * r.length() + 2 * r.length();
      case Circle c -> 2 * c.radius() * Math.PI;
      default -> throw new IllegalArgumentException("Unrecognized shape");
    };
  }

  /**
   * This method shows that we can handle the null value in the switch cases and even cluster the
   * case with the default (it could be other case).
   */
  public static double nullHandling(Shape shape) {
    return switch (shape) {
      case Rectangle r -> 2 * r.length() + 2 * r.width();
      case Circle c -> 2 * c.radius() * Math.PI;
      case null, default -> throw new IllegalArgumentException("Unrecognized shape");
    };
  }


  /**
   * This method shows how the pattern label dominance works.
   */
  public static void patternLabelDominance(Object object) {
    // All String are CharSequence, so the second label is never reached causing a compilation error
    //  switch(object) {
    //    case CharSequence cs -> System.out.println("A sequence of length " + cs.length());
    //    case String s -> System.out.println("A string: " + s);
    //    default -> {}
    //  }

    switch(object) {
      case String s -> System.out.println("A string: " + s);
      case CharSequence cs -> System.out.println("A sequence of length " + cs.length());
      default -> {}
    }
  }

  /**
   * This method shows how the type coverage works in switch
   */
  public static int exhaustiveness(Object object) {
    // This switch doesn't cover all possible options, it shows a compilation error
    // return switch (object) {
    //  case String s  -> s.length();
    //  case Integer i -> i;
    // };

    return switch (object) {
      case String s  -> s.length();
      case Integer i -> i;
      default -> 0;
    };
  }

  /**
   * This method shows a new feature introduced in Java 21, record patterns. It works with generic
   * records, it has type inference, and it works with nested record.
   */
  public static double useOfRecordPatterns(Object object) {
    return switch (object) {
      case Point(var y, double x) -> Math.toDegrees(Math.atan2(y, x));
      default -> throw new IllegalArgumentException("Not a point");
    };
  }

  /**
   * This method shows a new feature introduced in Java 21 (only preview), unnamed variables.
   */
  public static double unnamedPatterns(Object object) {
    return switch (object) {
      case Point(var y, _) -> Math.toDegrees(Math.atan2(y, 10));
      case String _ -> 1.0;
      default -> throw new IllegalArgumentException("Not a point");
    };
  }
}
