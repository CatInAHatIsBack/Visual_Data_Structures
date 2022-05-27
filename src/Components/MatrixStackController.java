package src.Components;

import java.awt.*;
import java.awt.Color;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JPanel;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// stack & queue
public class MatrixStackController extends JComponent{
    Deque<Block> stackList;
    Deque<Cell> stack;

    int size; 
    int width;
    int height;
    public MatrixStackController(int width, int heigth, int size){
        stackList = new LinkedList<>();
        stack = new LinkedList<>();
        this.size = size; 
        this.width = width;
        this.height = heigth;
    }
    public void paintComponent(Graphics g){
        // super.paintComponent(g);
        paintBlocks(g);
    }
  
    private void paintBlocks(Graphics g){

        for (Block block : stackList) {
            paintStackBlock(g, size, block.input, block.x, block.y);         
        }

    }    
    public void stackAdd(Cell cell){
        int x = width-(2*size); 
        int y = height / 2;
        Block b;    
        if(stackList.size() == 0){
            b = new Block(cell.input, x, y,size);
        }
        else{
            Block prev = stackList.getFirst();
            b = new Block(cell.input, prev.x, prev.y - size/2,size);
        }
        stackList.push(b);
        stack.push(cell);
    }
    public Cell stackRemove(){
        if(!stackList.isEmpty()){
            stackList.pop();
            return stack.poll();
        }
        return null;

    }
    
    
    private void paintStackBlock(Graphics g, int size, String input, int X, int Y){
        Graphics2D g2 = (Graphics2D) g;

        int sizeover3 = size/3;
        int space = sizeover3/4;
        g2.setColor(Color.ORANGE);
        g2.drawRect(X, Y, size, sizeover3);


        // text mod 
        Font mononoki = new Font("mononoki Nerd Font", Font.PLAIN, (int)(sizeover3*0.8));        
        g2.setFont(mononoki);
        g2.setColor(Color.DARK_GRAY);
        if(Integer.parseInt(input) < 10)
            g2.drawString(input,  X +(size/2) -(size/16), Y+sizeover3 - space );

        else
            g2.drawString(input, X +(size/2) - (size/4) + (size/8)  , Y+sizeover3 - space);


    }
    
}
