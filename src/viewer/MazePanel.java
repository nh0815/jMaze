package viewer;

import maze.Cell;
import maze.Maze;
import maze.Wall;

import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 * Created by nick on 8/23/15.
 */
public class MazePanel extends JPanel {

    private Maze maze;
    private int cellSize;
    private int gapSize;

    public MazePanel(Maze maze, int cellSize, int gapSize){
        this.maze = maze;
        this.cellSize = cellSize;
        this.gapSize = gapSize;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        drawBorders(this.cellSize, this.gapSize, g);

        for(List<Cell> row : this.maze.getCells()){
            for(Cell cell : row){
                drawCell(cell, this.cellSize, this.gapSize, g);
            }
        }

        for(Wall wall : this.maze.getWalls()){
            drawWall(wall, this.cellSize, this.gapSize, g);
        }
    }

    private void drawCell(Cell cell, int cellSize, int gapSize, Graphics g){
        int x1 = cell.getX() * (cellSize + gapSize) + gapSize;
        int y1 = cell.getY() * (cellSize + gapSize) + gapSize;
        int width = cellSize;
        int height = cellSize;
        g.setColor(Color.RED);
        g.fillRect(x1, y1, width, height);
    }

    private void drawWall(Wall wall, int cellSize, int gapSize, Graphics g){

    }

    private void drawBorders(int cellSize, int gapSize, Graphics g){
        g.setColor(Color.BLACK);
        int x1 = 0;
        int y1 = 0;
        int width = maze.getWidth() * (gapSize + cellSize) + gapSize;
        int height = gapSize;
        g.fillRect(x1, y1, width, height);

        width = gapSize;
        height = maze.getHeight() * (gapSize + cellSize) + gapSize;
        g.fillRect(x1, y1, width, height);

//        x1 = maze.getWidth() * (gapSize + cellSize);
        x1 = 0;
        y1 = maze.getHeight() * (gapSize + cellSize);
        width = maze.getWidth() * (cellSize + gapSize) + gapSize;
        height = gapSize;
        g.fillRect(x1, y1, width, height);

        x1 = maze.getWidth() * (gapSize + cellSize);
        y1 = 0;
        width = gapSize;
        height = maze.getHeight() * (cellSize + gapSize) + gapSize;
        g.fillRect(x1, y1, width, height);
    }
}
