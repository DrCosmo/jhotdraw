package org.jhotdraw.utils.io;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import org.jhotdraw.utils.geom.path.BezierPath;

public class VectorPathParser {
  private VectorPathParser() {}

  public static BezierPath[] toPath(String str) throws IOException {
    LinkedList<BezierPath> paths = new LinkedList<>();
    BezierPath path = null;
    Point2D.Double p = new Point2D.Double();
    Point2D.Double c1 = new Point2D.Double();
    Point2D.Double c2 = new Point2D.Double();

    StreamPosTokenizer tt = new StreamPosTokenizer(new StringReader(str));
    tt.resetSyntax();
    tt.parseNumbers();
    tt.parseExponents();
    tt.parsePlusAsNumber();
    tt.whitespaceChars(0, ' ');
    tt.whitespaceChars(',', ',');

    char nextCommand = 'M';
    char command = 'M';
    Commands:
    while (tt.nextToken() != StreamPosTokenizer.TT_EOF) {
      if (tt.ttype > 0) {
        command = (char) tt.ttype;
      } else {
        command = nextCommand;
        tt.pushBack();
      }
      BezierPath.Node node;
      switch (command) {
        case 'M':
          if (path != null) paths.add(path);
          path = new BezierPath();
          if (tt.nextToken() != StreamPosTokenizer.TT_NUMBER)
            throw new IOException(
                "x coordinate missing for 'M' at position " + tt.getStartPosition() + " in " + str);
          p.x = tt.nval;
          if (tt.nextToken() != StreamPosTokenizer.TT_NUMBER)
            throw new IOException(
                "y coordinate missing for 'M' at position " + tt.getStartPosition() + " in " + str);
          p.y = tt.nval;
          path.moveTo(p.x, p.y);
          nextCommand = 'L';
          break;
        case 'm':
          if (path != null) paths.add(path);
          path = new BezierPath();
          if (tt.nextToken() != StreamPosTokenizer.TT_NUMBER)
            throw new IOException("dx coordinate missing for 'm' at position "
                + tt.getStartPosition() + " in " + str);
          p.x += tt.nval;
          if (tt.nextToken() != StreamPosTokenizer.TT_NUMBER)
            throw new IOException("dy coordinate missing for 'm' at position "
                + tt.getStartPosition() + " in " + str);
          p.y += tt.nval;
          path.moveTo(p.x, p.y);
          nextCommand = 'l';
          break;
        case 'Z':
        case 'z':
          p.x = path.nodes().get(0).x[0];
          p.y = path.nodes().get(0).y[0];
          if (path.size() > 1) {
            BezierPath.Node first = path.nodes().get(0);
            BezierPath.Node last = path.nodes().get(path.size() - 1);
            if (first.x[0] == last.x[0] && first.y[0] == last.y[0]) {
              if ((last.mask & BezierPath.C1_MASK) != 0) {
                first.mask |= BezierPath.C1_MASK;
                first.x[1] = last.x[1];
                first.y[1] = last.y[1];
              }
              path.remove(path.size() - 1);
            }
          }
          path.setClosed(true);
          break;
          // ... tous les autres cas identiques aux deux classes ...
        default:
          break Commands;
      }
    }
    if (path != null) paths.add(path);
    return paths.toArray(new BezierPath[0]);
  }
}
