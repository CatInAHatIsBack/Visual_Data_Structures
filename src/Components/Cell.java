package src.Components;

public class Cell {
    int x;
    int y;
    int i;
    int j;
    String input;
    boolean current = false;
    boolean isVisited = false;
    Cell(int x, int y, String input, int i, int j){
        this.x = x;
        this.y = y;
        this.input = input;
        this.i = i;
        this.j = j;
    }
    
}
