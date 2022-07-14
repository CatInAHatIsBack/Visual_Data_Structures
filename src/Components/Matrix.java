package src.Components;

import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.List;
public class Matrix extends JPanel{
    int width;
    int size;
    int height;
    Cell current;
    Cell corner;
    ArrayList<List<Cell>> matrix;
    MatrixQueueController queue;
    MatrixStackController stack;

    // choose bfs or dfs
    private char choose; 
    
    public Matrix(int width, int height, int size){
        this.width = width;
        this.height = height;
        this.size = size;            
         this.setFocusable(true);
        this.setBackground(Color.black);
        this.setVisible(true);
        init();

        queue = new MatrixQueueController(width - 100, 100); 
        this.add(queue);
        queue.queueAdd(corner);

        stack = new MatrixStackController(width - 100, height*2/3, 100);
        this.add(stack);
        stack.stackAdd(corner);
    }
    private void init(){
        makeMatrix();
        current = matrix.get(0).get(0);
        current.current = true;
        current.isVisited = true;
        corner = current;
 
    }
    
    private void makeMatrix(){
        matrix = new ArrayList<>();
        int count = 0; 
        int xoff = 100;
        int yoff = 100;

        
        for (int i = 0; i < 10; i++) {
            List<Cell> temp = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                Cell cell = new Cell(xoff+ j*size,yoff + i*size, String.valueOf(count), i, j);
                temp.add(cell);
                count++;
            }            
            matrix.add(temp);
        }
    }

    //*****************  Search  **************************/

     private void changeSelected(Cell curr){
        current.gray = true;
        current.current = false;
        current = curr;
        curr.current = true;
    }
    public void matrixDFS(){
        int xlen = matrix.get(0).size();
        int ylen = matrix.size();
        changeSelected(stack.stackRemove());
                           // down    up    right  left 
        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
        
        for (int i = 0; i < 4; i++) {
            int newI =  current.i + directions[i][1];    
            int newJ =  current.j + directions[i][0];    
            if(newI < ylen && newI >= 0 && newJ < xlen && newJ >= 0 ){
                Cell curr = matrix.get(newI).get(newJ); 
                if(!curr.isVisited){
                    stack.stackAdd(curr);
                    curr.isVisited = true;
                }
            } 
        }
    }
    public void matrixBFS(){
        int xlen = matrix.get(0).size();
        int ylen = matrix.size();
        changeSelected(queue.queueRemove());
                           // down    up    right  left 
        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
        
        for (int i = 0; i < 4; i++) {
            int newI =  current.i + directions[i][1];    
            int newJ =  current.j + directions[i][0];    
            if(newI < ylen && newI >= 0 && newJ < xlen && newJ >= 0 ){
                Cell curr = matrix.get(newI).get(newJ); 
                if(!curr.isVisited){
                    queue.queueAdd(curr);
                    curr.isVisited = true;
                }
            } 
        }
    }
    public void chooseBFS(){
       choose = 'b';        
    }
    public void chooseDFS(){
        choose = 'd';        
    } 
    public void next() {
        if( choose == 'b'){
            matrixBFS();    
        }
        else if( choose == 'd'){
            matrixDFS();    
        }
	}
    //*****************************************************/


    //*****************  Move with vimstyle **********************/
    
    private void changeArrowSelected(Cell curr){
        current.current = false;
        current = curr;
        curr.current = true;
    }
    public void matrixMoveLeft() {
        if(current.j-1 >= 0){
            changeArrowSelected(matrix.get(current.i).get(current.j-1));
        }
    }

    public void matrixMoveDown() {
        if(current.i+1 < matrix.size()){
            changeArrowSelected(matrix.get(current.i+1).get(current.j));
        }
    }

    public void matrixMoveRight() {
        if(current.j+1 < matrix.get(0).size()){
            changeArrowSelected(matrix.get(current.i).get(current.j+1));
        }
    }

    public void matrixMoveUp(){
        if(current.i -1 >= 0){
            changeArrowSelected(matrix.get(current.i-1).get(current.j));
        }
    }
    
    //*****************************************************/

    private void paintMatrixCell(Graphics g, int size, String input, int X, int Y, boolean curr, boolean gray){
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.ORANGE);
        if(curr){
            g2.fillRect(X, Y, size, size);
        }
        else if(gray){
            g2.setColor(Color.lightGray);
            g2.fillRect(X, Y, size, size);
        }
        else
            g2.drawRect(X, Y, size, size);
            
        int space = size/4;
        // text mod 
        Font mononoki = new Font("mononoki Nerd Font", Font.PLAIN, (int)(size * 0.6));        
        g2.setFont(mononoki);
        g2.setColor(Color.DARK_GRAY);
        if(Integer.parseInt(input) < 10)
            g2.drawString(input,  X +(size/2) -(size/8), Y+ size - space);

        else
            g2.drawString(input, X + (size/6)  , Y + size - space);
    }
    private void matrixController(Graphics g){

        for (List<Cell> list : matrix) {
            for (Cell cell: list) {
                paintMatrixCell(g, size, cell.input, cell.x, cell.y,cell.current, cell.gray);
            } 
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        matrixController(g);
        if (choose == 'b'){
            queue.paintComponent(g);
        }
        else if (choose == 'd'){
            stack.paintComponent(g);
        }
    }
	
}
