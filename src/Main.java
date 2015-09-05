import maze.Cell;
import maze.Maze;
import viewer.MazeViewer;

import java.util.List;

/**
 * Created by nick on 8/23/15.
 */
public class Main {

    public static void main(String[] args){
        Maze maze = new Maze(20, 20);
        maze.generate();
        MazeViewer viewer = new MazeViewer(maze, 20);
        List<Cell> solution = maze.solve();
    }
}
