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


public class Binary_Tree {
    int width;
    int height;
    int[] array;
    Node selected;
    Binary_Tree(){
        
    }
    Binary_Tree(int[] array){
        this.array = array;
    }
    private Node nodeController(int window, int size){
        int startx = window/2;
        int starty = window/2;
        int offset = -width/2;
        Node root = new Node(startx, 50, 100,0, window/2, 75, offset);
        root.isSelected = true;
        selected = root;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int rotation = 75;
        double curve = .5;
        while(!q.isEmpty()){
            Node curr = q.poll();
            if(curr.number * 2 +1 <= 40){
                makeNode(curr, true, true, rotation, (int)(curr.offset *curve),0, true);
                q.add(curr.left);
            }
            if(curr.number * 2 +2 <= 40){
                makeNode(curr, true,true, rotation, (int)(curr.offset *curve), 0, false);
                q.add(curr.right);
            }
        }
        return root;
    }
    public void paintController(Graphics g, Node root){
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        Node curr = root;
        while(!q.isEmpty()){
            curr = q.poll(); 

            if(curr!=null){
                // if(curr.hasParent){

                //     drawNewLine(g, curr, curr.parent);
                //     System.out.println(curr.number);
                // } 
                paintNode(g, curr.xPos, curr.yPos, curr.size,String.valueOf(curr.number), curr.isSelected, curr.isPath);
                q.add(curr.left);
                q.add(curr.right);
            }
        } 
    }
 
    private void makeNode(Node node, boolean s, boolean r, int rotation, int off, int space, boolean left){
        // s = false not shrinking
        // s = true shrinking 
        // boolean r = relative placement
        
        int newx;
        int newy;
        int size;
        int newWindow;
        if(s){
            size = (int) (node.size*0.8);
        }
        else{
            size = node.size;
        }
        if(r){
            // relative
            int theta;
            if(left){
                theta = node.rotation * -1;
            }
            else{
                theta = node.rotation ;
            }
            int ox = node.xPos + node.size/2;
            int oy = node.yPos + node.size;
            int x = 0;
            int y = off;
            int xoff = (int) ( x*Math.cos(Math.toRadians(theta)) - y*Math.sin(Math.toRadians(theta)));
            int yoff = (int) ( x*Math.sin(Math.toRadians(theta)) + y*Math.cos(Math.toRadians(theta)));
            if(left){
                newx = ox - size + xoff;
                newy = oy - yoff;
            }
            else{
                newx = ox + xoff;
                newy = oy - yoff;
            }
            newWindow = node.window;
        }
        else{
            newWindow = node.window/2;
            newx = node.xPos - newWindow;
            newy = node.yPos + 2*node.size + space;
        }
        if(left){
            node.left = new Node(newx, newy,size,node.number*2+1, newWindow,node.rotation -15, off);
            node.left.parent = node;
            node.left.hasParent = true;
        }
        else{
                
           node.right= new Node(newx, newy,size,node.number*2+2, newWindow, node.rotation -15, off);
            node.right.parent = node;
            node.right.hasParent = true;
        }
    }
    private void paintNode(Graphics g, int X, int Y, int size, String count, boolean isSelected, boolean isPath){
        Graphics2D g2 = (Graphics2D) g;
        int square = size;
        int dia = square/2;
        int font = dia;

        // size & color
        g2.setStroke(new BasicStroke(1.0f));

        if(isPath){
            g2.setColor(Color.GRAY);
            g2.fillOval(X, Y, size, size);
        }
        // circle
        if(isSelected){
            g2.setColor(Color.red);
            g2.fillOval(X, Y, size, size);
        }
        else{
            g2.setColor(Color.gray);
            g2.drawOval(X,Y , size, size);
        }

        
        // text mod 
        Font mononoki = new Font("mononoki Nerd Font", Font.PLAIN, font);        
        g2.setFont(mononoki);
        g2.setColor(Color.DARK_GRAY);
        
        //          str         x                      y 
        // g2.drawString("1", (3/2 * dia)-(font/4), (3/2 * dia)+(font/2));
        if(Integer.parseInt(count) < 10)
            g2.drawString(count, X +(dia/2) + (dia/4), Y +dia+(dia/4));

        else
            g2.drawString(count, X +(dia/2)  , Y +dia+(dia/4));

        
        // corner dot to show x,y
        g2.fillOval(X, Y, 10, 10);
    }
   
    
    private void handler(){
        selected.isSelected = false;
        selected.left.isSelected = true;
        this.selected = selected.left;
    }
    
    Stack<Node> treeStack;
    private void dfs(){

        if(moveUP == true){
            moveUp();
            moveUP = false;
        }
        else{
            Node curr = treeStack.pop();  
            changeSelected(curr);
            if(curr.right != null){
                treeStack.push(curr.right);
            }
            if(curr.left != null){
                treeStack.push(curr.left);
            }
            if(curr.left == null && curr.right == null){
                if(curr.parent == null){
                    // timer.stop();
                    return;
                }
                moveUP = true;
            }
        }

    }
    boolean moveUP = false;
    private void changeSelected(Node curr){
        selected.isPath = true;
        selected.isSelected = false;
        selected = curr;
        curr.isSelected = true;
    }

    private void moveUp(){
        this.selected.isSelected = false; 
        changeSelected(selected.parent); 
        selected.isPath = false;
    }
    private void handle(){
        if(!treeStack.isEmpty()){
            dfs();
        }
        else{
            // timer.stop();
        }
    }
}
