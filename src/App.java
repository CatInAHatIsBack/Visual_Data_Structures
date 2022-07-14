package src;
import javax.swing.*;

import src.Components.Binary_Tree;
import src.Components.Matrix;

import java.awt.event.*;
import java.util.Random;
import java.util.Scanner;
import java.awt.*;

public class App extends JFrame implements KeyListener{
    Binary_Tree tree;
    Matrix matrix;
    // declare a new container to get the content pain
    Container window = this.getContentPane();

    /* Set Fonts and Colors ======================================= */

    /* Set Program Components ===================================== */
    
    // choose 
    // 0 = matrix
    // 1 = binary tree
    int a = 1;

    // keybindings 
     /*
        tree 

            b = set bfs
            d = set dfs
            n = next                 

            a = search from root

        vim nav
            k = up    ^ 
            h = left  <
            l = right >                   
    */
    
    /*
        Matrix 

            b = set bfs
            d = set dfs
            n = next                 

        vim nav
            h = left  <
            j = down  v
            k = up    ^ 
            l = right >                   
        */
    /* Constructors =============================================== */
    public App(String title, int width, int height) {
        // create the window frame
        createWindow(title, width, height);

        this.setLocation(0, 0);
        this.addKeyListener(this);
        
        if (a == 0){
            matrix = new Matrix(width, height, 75);
            this.add(matrix);
        }
        else if(a == 1){
            int len = 30;
            int[] input = new int[len];
            Random random = new Random(); 
            for (int i = 0; i < len; i++) {
                input[i] = random.nextInt(99);
            }
            // tree = new Binary_Tree(width, height);
            tree = new Binary_Tree(width, height, input);
            this.add(tree);
        }

    }

    /* Methods ==================================================== */
    public void createWindow(String title, int width, int height) {
        // set title, visibility, size and default close operation
        setTitle(title);
        this.setFocusable(true);
        setVisible(true);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void keyPressed(KeyEvent e) {
   
        System.out.println(e.getKeyCode());
        int key = e.getKeyCode();
        Scanner scan; 
        // matrix
        if(a == 0){
            switch (key){

                /*
                    Matrix 

                        b = set bfs
                        d = set dfs
                        n = next                 

                    vim nav
                        h = left  <
                        j = down  v
                        k = up    ^ 
                        l = right >                   
                 */
                case KeyEvent.VK_B:
                    matrix.chooseBFS(); ;
                    break;
                case KeyEvent.VK_D:
                    matrix.chooseDFS();
                    break;
                case KeyEvent.VK_N:
                    matrix.next(); 
                    repaint();
                    break;

 
                case KeyEvent.VK_H:
                    matrix.matrixMoveLeft();
                    repaint();
                    break;
                case KeyEvent.VK_J:
                    matrix.matrixMoveDown();
                    repaint();
                    break;
                case KeyEvent.VK_K:
                    matrix.matrixMoveUp();
                    repaint();
                    break;
                case KeyEvent.VK_L:
                    matrix.matrixMoveRight();
                    repaint();
                    break;
            }
        }
        // tree
        else if(a == 1){
            switch (key){
                /*
                    tree 

                        b = set bfs
                        d = set dfs
                        n = next                 

                        a = search from root

                    vim nav
                        k = up    ^ 
                        h = left  <
                        l = right >                   
                 */
                case KeyEvent.VK_B:
                    tree.chooseBFS(); ;
                    break;
                case KeyEvent.VK_D:
                    tree.chooseDFS();
                    break;
                case KeyEvent.VK_N:
                    tree.next(); 
                    repaint();
                    break;
                case KeyEvent.VK_A:
                    tree.clicked();
                    break;
                case KeyEvent.VK_K:
                    tree.moveUp();
                    repaint();
                    break;
                case KeyEvent.VK_H:
                    tree.moveLeft();
                    repaint();
                    break;
                case KeyEvent.VK_L:
                    tree.moveRight();
                    repaint();
                    break;
                
            }

        }

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
