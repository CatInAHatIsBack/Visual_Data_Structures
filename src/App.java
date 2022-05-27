package src;
import javax.swing.*;

import src.Components.Binary_Tree;
import src.Components.Matrix;

import java.awt.event.*;
import java.util.Scanner;
import java.awt.*;

public class App extends JFrame implements KeyListener{
    Binary_Tree tree;
    Matrix matrix;
    // declare a new container to get the content pain
    Container window = this.getContentPane();

    /* Set Fonts and Colors ======================================= */

    /* Set Program Components ===================================== */
    


    /* Constructors =============================================== */
    public App(String title, int width, int height) {
        // create the window frame
        createWindow(title, width, height);

        this.setLocation(0, 0);
        this.addKeyListener(this);

        // add components to the window

        // this.add(new myPanel(width, height));

        // int len = 30;
        // int[] input = new int[len];

        // Random random = new Random(); 
        // for (int i = 0; i < len; i++) {
        //     input[i] = random.nextInt(99);
        // }
        // // tree = new Binary_Tree(width, height);
        // tree = new Binary_Tree(width, height, input);
        // this.add(tree);
        
        matrix = new Matrix(width, height, 75);
        this.add(matrix);
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
        switch (key){
            case KeyEvent.VK_E:
                tree.clicked();
                break;
            case KeyEvent.VK_N:
                // tree.bfs();
                matrix.matrixBFS();
                repaint();
                break;
            case KeyEvent.VK_R:
                // tree.dfs();
                matrix.matrixDFS();
                repaint();
                break;
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
