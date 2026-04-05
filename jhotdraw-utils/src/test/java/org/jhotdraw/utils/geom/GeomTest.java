package org.jhotdraw.utils.geom;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import org.junit.jupiter.api.Test;

class GeomTest {
  private Point HorizontalLinePoint = new Point(0, 0);
  private Point HorizontalLineEndPoint = new Point(10, 0);

  private Point VerticalLinePoint = new Point(0, 0);
  private Point VerticalLineEndPoint = new Point(0, 10);

  @Test
  void lineContainsPoint_pointOnHorizontalLine_int_returnsTrue() {
    assertTrue(Geom.lineContainsPoint(0, 0, 10, 0, 5, 0));
  }

  @Test
  void lineContainsPoint_pointOnHorizontalLine_int_WithinTolerance_returnsTrue() {
    int tolerance = (int) Geom.getTolerance();
    assertTrue(Geom.lineContainsPoint(0, 0, 10, 0, 5, tolerance - 1));
  }

  @Test
  void lineContainsPoint_pointNotOnHorizontalLine_int_OutsideTolerance_returnsFalse() {
    int tolerance = (int) Geom.getTolerance();
    assertFalse(Geom.lineContainsPoint(0, 0, 10, 0, 5, tolerance));
  }

  @Test
  void lineContainsPoint_pointOnVerticalLine_int_returnsTrue() {
    assertTrue(Geom.lineContainsPoint(0, 0, 0, 10, 0, 5));
  }

  @Test
  void lineContainsPoint_pointOnVerticalLine_int_WithinTolerance_returnsTrue() {
    int tolerance = (int) Geom.getTolerance();
    assertTrue(Geom.lineContainsPoint(0, 0, 0, 10, tolerance - 1, 5));
  }

  @Test
  void lineContainsPoint_pointOnVerticalLine_int_OutsideTolerance_returnsFalse() {
    int tolerance = (int) Geom.getTolerance();
    assertFalse(Geom.lineContainsPoint(0, 0, 0, 10, tolerance, 5));
  }

  @Test
  void lineContainsPoint_pointOnHorizontalLine_point_returnsTrue() {
    Point point = new Point(5, 0);
    assertTrue(Geom.lineContainsPoint(HorizontalLinePoint, HorizontalLineEndPoint, point));
  }

  @Test
  void lineContainsPoint_pointOnHorizontalLine_point_WithinTolerance_returnsTrue() {
    int tolerance = (int) Geom.getTolerance();
    Point point = new Point(5, tolerance - 1);
    assertTrue(Geom.lineContainsPoint(HorizontalLinePoint, HorizontalLineEndPoint, point));
  }

  @Test
  void lineContainsPoint_pointNotOnHorizontalLine_point_OutsideTolerance_returnsFalse() {
    int tolerance = (int) Geom.getTolerance();
    Point point = new Point(5, tolerance);
    assertFalse(Geom.lineContainsPoint(HorizontalLinePoint, HorizontalLineEndPoint, point));
  }

  @Test
  void lineContainsPoint_pointOnVerticalLine_point_returnsTrue() {
    Point point = new Point(0, 5);
    assertTrue(Geom.lineContainsPoint(VerticalLinePoint, VerticalLineEndPoint, point));
  }

  @Test
  void lineContainsPoint_pointOnVerticalLine_point_WithinTolerance_returnsTrue() {
    int tolerance = (int) Geom.getTolerance();
    Point point = new Point(tolerance - 1, 5);
    assertTrue(Geom.lineContainsPoint(VerticalLinePoint, VerticalLineEndPoint, point));
  }

  @Test
  void lineContainsPoint_pointOnVerticalLine_point_OutsideTolerance_returnsFalse() {
    int tolerance = (int) Geom.getTolerance();
    Point point = new Point(tolerance, 5);
    assertFalse(Geom.lineContainsPoint(VerticalLinePoint, VerticalLineEndPoint, point));
  }
}
