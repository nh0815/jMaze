import maze.Maze;
import viewer.MazeViewer;

/**
 * Created by nick on 8/23/15.
 */
public class Main {

    public static void main(String[] args){
        Maze maze = new Maze(20, 20);
        maze.generate();
        MazeViewer viewer = new MazeViewer(maze, 20);
    }
}
