package maze;

import javax.swing.*;

import java.awt.*;
import java.util.Set;

public class MazeCanvas extends JComponent {
    private int startX = 0;
    private int startY = 0;
    private int endX;
    private int endY;

    final private int CanvasMargin = 100;
    final private int CanvasWidth = 1000;
    final private int CanvasHeight = 850;
    private int cellHeight;
    private int cellWidth;

    public int height;
    public int width;
    public Set<Wall> walls;

    public MazeCanvas(int width, int height, Set<Wall> walls){
        this.height = height;
        this.width = width;
        this.walls = walls;
        cellWidth = cellHeight = (CanvasHeight-2 * CanvasMargin) / height;
    }




    @Override
    public void paint(Graphics g) {
        //Printing borders of the board
        g.drawLine((CanvasMargin+cellWidth), CanvasMargin,(CanvasMargin+(cellHeight*width)), CanvasMargin);
        g.drawLine((CanvasMargin), (CanvasMargin),(CanvasMargin), CanvasMargin+((cellHeight*height)));
        g.drawLine((CanvasMargin), CanvasMargin+((cellHeight*height)),(CanvasMargin+(cellHeight*width)), CanvasMargin+((cellHeight*height)));
        g.drawLine((CanvasMargin+(cellWidth*width)), CanvasMargin, (CanvasMargin+(cellWidth*width)), CanvasMargin+((cellHeight*height)-cellHeight));

        for (Wall w : walls) {
            if (w.isHorizontal()) {
                drawHorizontal(g, w);
            } else {
                drawVerticalLine(g, w);
            }
        }
    }

    private void drawVerticalLine(Graphics g, Wall w){
        Cell x = w.getX();
        Cell y = w.getY();
        int row = (x.getId()/width)*cellWidth;
        int col = (x.getId()%width)*cellWidth;
        startX = CanvasMargin + col+ cellWidth;
        startY = CanvasMargin + row;
        endX = startX;
        endY = startY + cellHeight;

        g.drawLine(startX,startY,endX,endY);
    }

    private void drawHorizontal(Graphics g, Wall w){
        Cell x = w.getX();
        Cell y = w.getY();
        int row = ((x.getId()/width) * cellWidth);
        int col = (x.getId()%width)* cellWidth;
        startX = CanvasMargin + col;
        startY = CanvasMargin + row + cellWidth;
        endX = startX + cellWidth;
        endY = startY;
        g.drawLine(startX, startY, endX, endY);

    }
}
