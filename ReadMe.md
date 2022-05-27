# Visual datastructures 

I am have not tested on any other OS than Archo linux Kernel: 5.15 but should work on most OS.
openjdk 17.0.3

Repository for visual datastructures and algorithms
Entry is Main.java
Overview - (some functions might be commented out or not called for testing)
bfs and dfs have assosiated queue and stack with visual representation.
- Binary tree 
    move
    insert
    bfs 
    dfs
- Matrix
    move
    bfs
    dfs

In src/Notes&VC you can find previous code & testing

# Displaying binary tree

![box](https://raw.githubusercontent.com/CatInAHatIsBack/Visual_Data_Structures/master/src/Notes%26VC/Square.png)

- Box is painted from D 
 
### issue 
1) binary trees take up to much space 
// from parent point e
   // draw a line down 
   // rotate it to the by -70 & 70 deg.
   // for left nodes subtract nodesize from x

## pic offset & selection
![angle](https://raw.githubusercontent.com/CatInAHatIsBack/Visual_Data_Structures/master/src/Notes%26VC/Selection_002.png)


![offset](https://raw.githubusercontent.com/CatInAHatIsBack/Visual_Data_Structures/master/src/Notes%26VC/offset.png)

### Space
To create extra space the root node has a degree of x
all children have x-y, those childen are x-2y etc.
the length of line from one node to another follows a similar shrinking pattern.
as does the node size.