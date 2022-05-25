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


public class QueueController {
    
    int size;
    int width;
    List<Block> queueList;
    Deque<Block> queue;
    private void queueController(Graphics g) {
        queue = new LinkedList<>();
        for (Block block : queueList) {
            paintStackBlock(g, size, block.input, block.x, block.y);         
            queue.add(block);
        }
    }
private void createQueue() {
        int x = width-(2*size); 
        int y = 100;
        Block b;    
        for (int i = 0; i < 10; i++) {
            if(queueList.size() == 0){
                b = new Block(String.valueOf(i), x, y,size);
            }
            else{
                Block prev = queueList.get(queueList.size() -1);
                b = new Block(String.valueOf(i), prev.x, prev.y + size/2,size);
            }
            queueList.add(b);
        }
        
    }
    private void queueAdd(String input){
        Block prev = queueList.get(queueList.size()-1);
        Block temp = new Block(input, prev.x, prev.y + size/2,size);
        queueList.add(temp);
    }
    private void queueRemove(){
        queue.poll();
        queueList.remove(0);
        for(Block block : queueList){
            block.y -= size/2;
        }
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