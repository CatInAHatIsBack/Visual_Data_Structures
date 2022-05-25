package src.Components;

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

// stack & queue
public class StackController {
    List<Block> stackList;
    int size; 
    int width;
    int height;
    private void stackController(Graphics g){
        Stack<Block> stack = new Stack<>();

        for (Block block : stackList) {
            paintStackBlock(g, size, block.input, block.x, block.y);         
            stack.add(block);
        }

    }    
    
    private void createStack(){
        int x = width-(2*size); 
        int y = height / 2;
        Block b;    
        for (int i = 0; i < 10; i++) {
            if(stackList.size() == 0){
                b = new Block(String.valueOf(i), x, y,size);
            }
            else{
                Block prev = stackList.get(stackList.size() -1);
                b = new Block(String.valueOf(i), prev.x, prev.y - size/2,size );
            }
            stackList.add(b);
        }
    }
    private void stackRemove(){
        if(stackList.size() > 0){
            stackList.remove(stackList.size() -1);
        }
    }
    private void stackAdd(String input){
        Block prev = stackList.get(stackList.size()-1);
        Block temp = new Block(input, prev.x, prev.y- size/2, size);
        stackList.add(temp);
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
