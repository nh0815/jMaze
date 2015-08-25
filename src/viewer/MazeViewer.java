package viewer;

import maze.Maze;

import javax.swing.*;
import java.awt.*;

/**
 * Created by nick on 8/23/15.
 */
public class MazeViewer extends JFrame {

    private JPanel panel;

    public MazeViewer(Maze maze, int cellSize){
        this(maze, cellSize, cellSize / 4);
    }

    public MazeViewer(Maze maze, int cellSize, int gapSize){
        this.panel = new MazePanel(maze, cellSize, gapSize);
        setContentPane(this.panel);
        int width = (maze.getWidth() + 1) * (cellSize + gapSize) + gapSize;
        int height = (maze.getHeight() + 1) * (cellSize + gapSize) + gapSize;
        this.setPreferredSize(new Dimension(width, height));
        this.pack();
        this.setVisible(true);
    }
}
