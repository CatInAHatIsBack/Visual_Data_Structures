package src.Components;

import javax.swing.JComponent;


public class Node extends JComponent{
    Node parent = null;
    Node left;
    Node right;

    int xPos;
    int yPos;
    int size;
    int number;
    int index;
    boolean hasParent = false;
    boolean isSelected = false; 
    int rotation;
    boolean isPath = false;
    int offset;
    Node(int x, int y,
         int size, int number, 
         int index, int rotation, int offset){

        this.xPos = x;
        this.yPos = y;
        this.size = size;
        this.number = number;
        this.index = index;
        this.rotation = rotation;
        this.offset = offset;
    }
}
