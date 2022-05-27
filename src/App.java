package src;
import javax.swing.*;

import src.Components.Binary_Tree;

import java.awt.event.*;
import java.util.Random;
import java.util.Scanner;
import java.awt.*;

public class App extends JFrame implements KeyListener{
    Binary_Tree tree;
    // declare a new container to get the content pain
    Container window = this.getContentPane();

    /* Set Fonts and Colors ======================================= */

    /* Set Program Components ===================================== */
    


    /* Constructors =============================================== */
    public App(String title, int width, int height) {
        // create the window frame
        createWindow(title, width, height);
        int len = 30;
        int[] input = new int[len];
        // add components to the window
        // this.add(new myPanel(width, height));
        Random random = new Random(); 
        for (int i = 0; i < len; i++) {
            input[i] = random.nextInt(99);
        }
        // tree = new Binary_Tree(width, height);
        tree = new Binary_Tree(width, height, input);
        this.addKeyListener(this);
        this.add(tree);
        this.setLocation(0, 0);
        
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
