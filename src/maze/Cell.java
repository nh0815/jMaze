package maze;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Nick Hirakawa
 */
public class Cell {

    private int x;
    private int y;
    private boolean visited;
    private List<Cell> neighbors;


    public Cell(int x, int y){
        this.x = x;
        this.y = y;
        this.setVisited(false);
        this.neighbors = new LinkedList<Cell>();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void addNeighbor(Cell cell){
        if(!this.neighbors.contains(cell)){
            this.neighbors.add(cell);
        }
    }

    public List<Cell> getNeighbors(){
        return this.neighbors;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public int hashCode(){
        int result = 17;
        result = 37 * result + this.x;
        result = 37 * result + this.y;
        return result;
    }

    @Override
    public boolean equals(Object other){
        Cell cell = (Cell)other;
        return cell.x == this.x && cell.y == this.y;
    }

    @Override
    public String toString(){
        return "(" + x + "," + y + ")";
    }
}