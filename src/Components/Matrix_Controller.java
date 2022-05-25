package src.Components;
import java.util.Queue;

import java.awt.*;
import java.awt.Color;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
public class Matrix_Controller {
    int width;
    int size;
    int height;
    ArrayList<List<Cell>> matrix;
    Queue<Cell> matQueue;
    Cell current;
    Deque<Block> matQ;
    private void init(){
        matQueue = new LinkedList<>();
        matQ = new LinkedList<>();
        makeMatrix();
        current = matrix.get(0).get(0);
        current.current = true;
        current.isVisited = true;
 
    }
    private void matrixController(Graphics g){

        int size = 50;
        
        for (List<Cell> list : matrix) {
            for (Cell cell: list) {
                paintMatrixCell(g, size, cell.input, cell.x, cell.y,cell.current, cell.isVisited);
            } 
        }
    }
    private void makeMatrix(){
        matrix = new ArrayList<>();
        int size = 50;
        int count = 0; 
        int xoff = 200;
        int yoff = 200;

        
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
    private void matQueueAdd(Cell cell){
        int x = width-(2*size); 
        int y = 100;

        if(!matQ.isEmpty()){
            Block prev = matQ.getLast();
            Block temp = new Block(cell.input, prev.x, prev.y + size/2,size);
            matQ.add(temp);
        }
        else{
            Block temp = new Block(cell.input, x, y + size/2,size);
            matQ.add(temp);
        }

        matQueue.add(cell);
        // repaint();       
    }
    private void matQueuePoll(){
        matQ.poll();
        current.current = false;
        current = matQueue.poll();
        current.current = true;
        
        for(Block block : matQ){
            block.y -= size/2;
        }
        // repaint();
    }
    private void matrixBfs(){
        int xlen = matrix.get(0).size();
        int ylen = matrix.size();
        
        matQueuePoll();
                           // down    up    right  left 
        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
        
        for (int i = 0; i < 4; i++) {
            int newI =  current.i + directions[i][1];    
            int newJ =  current.j + directions[i][0];    
            if(newI < ylen && newI >= 0 && newJ < xlen && newJ >= 0 ){
                Cell curr = matrix.get(newI).get(newJ); 
                if(!curr.isVisited){
                    matQueueAdd(curr);
                    curr.isVisited = true;
                }
            } 
        }

       // loop()
            // remove from top of queue
            // move to cell 
            // get directions
            // if not <0 || >size
            // add to queue
    }
    private void matrixMoveLeft() {

        if(current.j-1 >= 0){
            current.current = false;
            current = matrix.get(current.i).get(current.j-1);
            current.current = true;
        }
    }


    private void matrixMoveDown() {
        if(current.i+1 < matrix.size()){
            current.current = false;
            current = matrix.get(current.i+1).get(current.j);
            current.current = true;
        }
    }


    private void matrixMoveRight() {
        if(current.j+1 < matrix.get(0).size()){
            current.current = false;
            current = matrix.get(current.i).get(current.j+1);
            current.current = true;
        }
    }


    private void matrixMoveUp(){
        if(current.i -1 >= 0){
            current.current = false;
            current = matrix.get(current.i-1).get(current.j);
            current.current = true;
        }
    }
    private void printMatQueue(Graphics g) {
        for (Block cell: matQ) {
            // paintStackBlock(g, size, cell.input, cell.x, cell.y);
        }
    }
    private void paintMatrixCell(Graphics g, int size, String input, int X, int Y, boolean curr, boolean isVisited){
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.ORANGE);
        if(curr){
            g2.fillRect(X, Y, size, size);
        }
        else if(isVisited){
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
    
}
