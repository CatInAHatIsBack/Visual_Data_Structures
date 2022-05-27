package src.Components;
import java.awt.*;
import java.awt.Color;
import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import java.util.Deque;
import java.util.List;


public class MatrixQueueController extends JComponent{
    
    int size;
    int width;
    List<Block> queueList;
    Deque<Cell> queue;
    public MatrixQueueController(int width, int size){
        queueList = new LinkedList<>();
        queue = new LinkedList<>();
        this.width = width;
        this.size = size; 
    }
    public void paintComponent(Graphics g){
        // super.paintComponent(g);
        paintBlocks(g);
    }
    public void paintBlocks(Graphics g) {
        for (Block block : queueList) {
            paintStackBlock(g, size, block.input, block.x, block.y);         
        }
    }
    public void queueAdd(Cell cell){
        int x = width-(2*size); 
        int y = 100;
        Block b;    
        if(queueList.size() == 0){
            b = new Block(cell.input, x, y,size);
        }
        else{
            Block prev = queueList.get(queueList.size() -1);
            b = new Block(cell.input, prev.x, prev.y + size/2,size);
        }
        queueList.add(b);
        queue.add(cell);
    }
    public Cell queueRemove(){
        if(!queueList.isEmpty()){
            queueList.remove(0);
            for(Block block : queueList){
                block.y -= size/2;
            }
            return queue.poll();
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