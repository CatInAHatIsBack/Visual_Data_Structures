package src.Components;
import java.util.Queue;

import javax.swing.Timer;
import java.awt.event.*;
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
import java.util.Arrays;
import java.util.Deque;
import java.util.List;


public class Binary_Tree extends JPanel implements ActionListener{
    int width;
    int height;
    int[] array;
    Node root;
    Node selected;
    List<Node> nodes;
    double curve = .5;
    double shrinkage = 0.7;
    Timer timer;
    int neg = 10;
    public Binary_Tree(int width, int height){
        this.width = width;
        this.height = height;
        nodes = new ArrayList<>(); 
        this.setFocusable(true);
        this.setBackground(Color.green);
        this.setVisible(true);
        makeRoot(3);
    }
    Binary_Tree(int width, int height, int[] array){
        this.width = width;
        this.width = height;
        this.array = array;
        nodes = new ArrayList<>(); 
        insertNodes(array); 
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        paintController(g, this.root);
    }
    public void clicked(){
        this.setBackground(Color.BLACK);
        Animate(); 
    }
    private void Animate(){
        //Set up timer to drive animation events.
        timer = new Timer(200, this);
        timer.setInitialDelay(1000);
        timer.start(); 
        
    }
    private void insertNode(int input){
        if(nodes.size() == 0){
            makeRoot(input);
            return;
        }
        Node curr = root;
        while(curr != null){
            Node temp = null;
            if( input > curr.number){
                if(curr.right == null){
                    insertRight(curr, input); 
                    break;
                }
                temp = curr.right;
            }
            if( input < curr.number){
                if(curr.left == null){
                    insertLeft(curr, input); 
                    break;
                }
                temp = curr.left;
            }
            curr = temp;
        }
    }
    
    private void moveRight() {

    }
    private void moveLeft() {
    }
    private void insertLeft(Node curr, int input){

        Point point;

        int theta = curr.rotation;
        int currLeft = curr.number*2+1;
        int off = (int)(curr.offset * curve);
        int newRotation =  theta-neg;
        point = makeNode(curr, off, newRotation * -1);
        point.x -= curr.size;
        int size = (int) (curr.size*shrinkage);
        Node lNode= new Node(point.x, point.y,size,input, currLeft,newRotation, off);
        curr.left = lNode;
        curr.left.parent = lNode;
        curr.left.hasParent = true;
   
    }
    private void insertRight(Node curr, int input){
        Point point;
        int theta = curr.rotation;
        int currRight = curr.number*2+2;
        int off = (int)(curr.offset * curve);
        int newRotation =  theta-neg;
        point = makeNode(curr, off, newRotation);
        int size = (int) (curr.size*shrinkage);
        Node rNode= new Node(point.x, point.y,size,input, currRight,newRotation, off);
        curr.right = rNode;
        curr.right.parent = rNode;
        curr.right.hasParent = true;

    }
    private void makeRoot(int i){
        int starty = 100;
        int offset = -width/2;
        int rotation = 80;
        int size = 100;
        int startx = width/2;
        Node temp = new Node(startx-size, starty, size, i, 0, rotation, offset);
        nodes.add(temp);
        root = temp;
        root.isSelected = true;
        selected = root;
    }
    private void insertNodes(int[] array){
        if(array.length > 0){
            for (int i = 0; i < array.length; i++) {
                if(nodes.size() == 0){
                    makeRoot(array[i]);
                }
                else{
                    int par = (i-1)/2;
                    Node parent = nodes.get(par); 
                    
                    int currLeft = parent.number*2+1;
                    int currRight = parent.number*2+2;

                    if(currLeft < array.length){
                        insertLeft(parent, array[i]); 
                    }
                    if(currRight < array.length){
                        insertRight(parent, array[i]); 
                    }
                }
            }
        }
    }

    
    
    private void removeNode(){}
    
    private void nodeMoveUp(){}

    private void printTree(){}
    private void printSearchTree(){}


    private Point makeNode(Node node, int off, int theta){
        
        int newx;
        int newy;
        
        // middle of bot line
        int ox = node.xPos + node.size/2;
        int oy = node.yPos + node.size;
        // erect negative spacer
        int x = 0;
        int y = off;
        // calculate pos after rotation
        int xoff = (int) ( x*Math.cos(Math.toRadians(theta)) - y*Math.sin(Math.toRadians(theta)));
        int yoff = (int) ( x*Math.sin(Math.toRadians(theta)) + y*Math.cos(Math.toRadians(theta)));
        // apply to x&y
        newx = ox + xoff;
        newy = oy - yoff;

        return new Point(newx,newy);
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
        g2.fillOval(X, Y, size/10, size/10);
    }
    int index = 0;
    @Override
    public void actionPerformed(ActionEvent arg0) {
        Random random = new Random(); 
        if(index >= 30)
            pause();
        else{
            insertNode(random.nextInt(99));
        }
        repaint();
        
    }
    private void pause(){
        this.timer = null;
    }
   
    
    
}
