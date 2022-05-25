package src;


import javax.swing.Timer;

import src.Components.Node;

import java.awt.*;
import java.awt.event.*;
import java.util.Queue;

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
public class myPanel extends JPanel implements KeyListener, ActionListener{
    
    int width;
    int height;
    Node selected;
    Node root;
    Timer timer;
    
    int size;
     
    myPanel(int width, int height){

        this.width = width;
        this.height = height;
        this.setFocusable(true);
        this.addKeyListener(this);
        this.setBackground(Color.black);
        // Node root = nodeController(this.width, 100);
        // this.root = root;
        
        size = 100;
        // stack block
        // stackList = new ArrayList<>();
        // queueList = new LinkedList<>();
        // createStack();
        // createQueue();
        
              
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        // paintGrid(g, 15);
        
        // paintController(g, this.root); 
        // paintStackBlock(g, 150, "1");
        // stackController(g);
        // queueController(g);

        // int x = 150;
        // int y = 150;
        // int size = 100;
        // paintMatrixCell(g, size, "10",x,y );
        // paintMatrixCell(g, size, "9",x+ size,y+size);
        // matrixController(g);
        // printMatQueue(g);
    }
    

    


    private void paintGrid(Graphics g, int scale){
        int newHeight = this.height/scale;
        int newWidth = this.width/scale;

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(1.0f));
        g2.setColor(Color.white);
        for (int i = 0; i < scale; i++) {
            g2.drawLine( 0,newHeight *i, this.width, newHeight *i);
        }
        for (int i = 0; i < scale; i++) {
            g2.drawLine( newWidth *i,0, newWidth *i, this.height);
        }
        
    }
    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.blue);

        for (int i = 0; i <= 1000; i++) {

            var size = getSize();
            var insets = getInsets();

            int w = size.width - insets.left - insets.right;
            int h = size.height - insets.top - insets.bottom;

            var r = new Random();
            int x = Math.abs(r.nextInt()) % w;
            int y = Math.abs(r.nextInt()) % h;
            g2d.drawLine(x, y, x, y);
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {

        if(this.timer != null) timer.stop();
        // selected.isSelected = false;
        System.out.println(e.getKeyCode());
        int key = e.getKeyCode();
        Scanner scan; 
        switch (key){
            case KeyEvent.VK_LEFT:
                // selected.left.isSelected = true;
                // selected = selected.left;
                repaint();
                break;
            case KeyEvent.VK_RIGHT:
                // selected.right.isSelected = true;
                // selected = selected.right;
                repaint();
                break;
             case KeyEvent.VK_UP:
                // selected.parent.isSelected = true;
                // selected = selected.parent;
                repaint();
                break;
            case KeyEvent.VK_DOWN:
                Animate(); 
                break;
            case KeyEvent.VK_E:
                tempdfs = true; 
                // treeStack = new Stack<>();
                // treeStack.add(this.selected);
                Animate(); 
                break;
            case KeyEvent.VK_T:
                // stackRemove();
                break;
            case KeyEvent.VK_R:
                scan = new Scanner(System.in);
                System.out.println("insert num");
                String input = scan.nextLine();
                // stackAdd(input);
                break;
            case KeyEvent.VK_F:
                // queueRemove();
                break;
            case KeyEvent.VK_G:
                scan = new Scanner(System.in);
                System.out.println("insert num");
                String in = scan.nextLine();
                // queueAdd(in);
            case KeyEvent.VK_W:
                // matrixMoveUp();
                break;
            case KeyEvent.VK_A:
                // matrixMoveLeft();
                break;
            case KeyEvent.VK_S:
                // matrixMoveDown();
                break;
            case KeyEvent.VK_D:
                // matrixMoveRight();
                break;
            case KeyEvent.VK_Q:
                // matQueueAdd(current);
                Animate();
                break;
        }
    }
    
    boolean tempdfs = false;
    private void Animate(){
        //Set up timer to drive animation events.
        timer = new Timer(2000, this);
        timer.setInitialDelay(1000);
        timer.start(); 
        
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        // if(tempdfs){
        //     dfs();
        // }
        // else{
        //     if(selected.left != null)
        //         handler();
        //     if(selected.left == null)
        //         timer.stop();
        // }
        // if(!matQ.isEmpty()){
            // matrixBfs();
            // repaint();
        // }
        // else
            // timer.stop();
          
    } 
    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }
}

