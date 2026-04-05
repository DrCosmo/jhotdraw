package org.jhotdraw.utils.geom;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GeomTest {

  @Test
  void lineContainsPoint_pointOnHorizontalLine_returnsTrue() {
    assertTrue(Geom.lineContainsPoint(0, 0, 10, 0, 5, 0));
  }

  @Test
  void lineContainsPoint_pointOnHorizontalLine_WithinTolerance_returnsTrue() {
    int tolerance = (int) Geom.getTolerance();
    assertTrue(Geom.lineContainsPoint(0, 0, 10, 0, 5, tolerance - 1));
  }

  @Test
  void lineContainsPoint_pointNotOnHorizontalLine_OutsideTolerance_returnsFalse() {
    int tolerance = (int) Geom.getTolerance();
    assertFalse(Geom.lineContainsPoint(0, 0, 10, 0, 5, tolerance));
  }

  @Test
  void lineContainsPoint_pointOnVerticalLine_returnsTrue() {
    assertTrue(Geom.lineContainsPoint(0, 0, 0, 10, 0, 5));
  }

  @Test
  void lineContainsPoint_pointOnVerticalLine_WithinTolerance_returnsTrue() {
    int tolerance = (int) Geom.getTolerance();
    assertTrue(Geom.lineContainsPoint(0, 0, 0, 10, tolerance - 1, 5));
  }

  @Test
  void lineContainsPoint_pointOnVerticalLine_OutsideTolerance_returnsFalse() {
    int tolerance = (int) Geom.getTolerance();
    assertFalse(Geom.lineContainsPoint(0, 0, 0, 10, tolerance, 5));
  }
}
