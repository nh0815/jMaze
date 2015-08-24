package maze;

import java.util.*;

/**
 * Created by nick on 8/23/15.
 */
public class Maze {

    private int width;
    private int height;
    private List<List<Cell>> maze;
    private Set<Wall> walls;
    private Cell start;
    private Cell end;
    private Random random;

    public Maze(int width, int height){
        this.width = width;
        this.height = height;
        this.maze = new ArrayList<List<Cell>>();
        this.walls = new HashSet<Wall>();
        this.start = null;
        this.end = null;
        this.random = new Random();
    }

    /**
     * Set up empty maze cells
     */
    private void init(){
        for(int i = 0; i < this.width; i++){
            List<Cell> row = new ArrayList<Cell>();
            for(int j = 0; j < this.height; j++){
                row.add(new Cell(i, j));
            }
            this.maze.add(row);
        }
        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.height; j++){
                Cell cell = this.maze.get(i).get(j);
                if(cell.getX() > 0){
                    Cell below = getCell(i-1, j);
                    cell.addNeighbor(below);
                    this.walls.add(new Wall(cell, below));
                }
                if(cell.getX() < this.width - 1){
                    Cell above = getCell(i+1, j);
                    cell.addNeighbor(above);
                    this.walls.add(new Wall(cell, above));
                }
                if(cell.getY() > 0){
                    Cell left = getCell(i, j-1);
                    cell.addNeighbor(left);
                    this.walls.add(new Wall(cell, left));
                }
                if(cell.getY() < this.height - 1){
                    Cell right = getCell(i, j+1);
                    cell.addNeighbor(right);
                    this.walls.add(new Wall(cell, right));
                }
            }
        }
    }

    private Cell getCell(int x, int y){
        return this.maze.get(x).get(y);
    }

    private Cell getRandomCell(){
        int x = this.random.nextInt(this.width);
        int y = this.random.nextInt(this.height);
        return getCell(x, y);
    }

    private Cell getRandomUnvisitedCell(){
        Cell cell = getRandomCell();
        while(cell.isVisited()){
            cell = getRandomCell();
        }
        return cell;
    }

    private boolean hasUnvisitedCells(){
        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.height; j++){
                if(!getCell(i, j).isVisited()){
                    return true;
                }
            }
        }
        return false;
    }

    private void generate(){
        Cell current = getCell(0, 0);
        this.start = current;
        current.setVisited(true);
        List<Cell> unvisited = new LinkedList<Cell>();
        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.height; j++){
                Cell cell = getCell(i, j);
               if(!cell.isVisited()){
                   unvisited.add(cell);
               }
            }
        }
        Stack<Cell> stack = new Stack<>();
        while(hasUnvisitedCells()){
            LinkedList<Cell> unvisitedNeighbors = new LinkedList<Cell>();
            for(Cell c : current.getNeighbors()){
                if(!c.isVisited()){
                    unvisitedNeighbors.add(c);
                }
            }
            if(unvisitedNeighbors.size() > 0){
                Cell randomNeighbor = getRandomNeighbor(unvisitedNeighbors);
                stack.push(current);
                Wall wall = new Wall(current, randomNeighbor);
                if(this.walls.contains(wall)){
                    this.walls.remove(wall);
                }
                current = randomNeighbor;
                current.setVisited(true);
            } else if(!stack.isEmpty()){
                current = stack.pop();
            } else {
                current = getRandomUnvisitedCell();
                current.setVisited(true);
            }
        }
        this.end = getCell(this.width-1, this.height-1);
    }

    private Cell getRandomNeighbor(List<Cell> neighbors){
        return neighbors.get(this.random.nextInt(neighbors.size()));
    }

}
