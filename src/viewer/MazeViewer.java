package viewer;

import maze.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Nick Hirakawa
 */
public class MazeViewer extends JFrame {

    private MazePanel panel;
    private Maze maze;
    private int mazeWidth;
    private int mazeHeight;

    public MazeViewer(int width, int height){
        this(new Maze(width, height), 20);
    }

    public MazeViewer(Maze maze, int cellSize){
        this(maze, cellSize, cellSize / 4);
    }

    public MazeViewer(Maze maze, int cellSize, int gapSize){
        this.maze = maze;
        this.mazeWidth = maze.getWidth();
        this.mazeHeight = maze.getHeight();
        setTitle("jMaze");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.panel = new MazePanel(maze, cellSize, gapSize);
        setContentPane(this.panel);
        int width = (maze.getWidth() + 1) * (cellSize + gapSize) + gapSize;
        int height = (maze.getHeight() + 2) * (cellSize + gapSize) + gapSize;
        setJMenuBar(getMenu());
        this.setPreferredSize(new Dimension(width, height));
        this.pack();
        this.setVisible(true);
    }

    private JMenuBar getMenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem newMaze = new JMenuItem("New");
        JMenuItem solve = new JMenuItem("Solve");
        newMaze.addActionListener(getNewMazeActionListener());
        solve.addActionListener(getSolveActionListener());
        menu.add(newMaze);
        menu.add(solve);
        menuBar.add(menu);
        return menuBar;
    }

    private ActionListener getNewMazeActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                maze = new Maze(20, 20);
                maze.generate();
                panel.setMaze(maze);
            }
        };
    }

    private ActionListener getSolveActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                maze.solve();
                panel.revalidate();
            }
        };
    }
}
