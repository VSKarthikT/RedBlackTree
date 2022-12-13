package util;

import java.util.HashMap;

public interface RBT_interface {
    //Interface for RedBlack tree showing all the operations done
    public void printerPreOrder(Node node);  
    public void printerInOrder(Node node);
    public void printerPostOrder(Node node);
    public int treeElementFinder(Node node, int val);
    public void deleteFix(Node val);
    public void resetRb(Node i, Node j);
    public void nodeRemoval(Node node, int val);
    public void insertFix(Node val);
    public void helperPrint(Node root, String space, boolean leaf);
    public void preOrder();
    public void inOrder();
    public void postOrder();
    public int findTree(int val);
    public Node min(Node node);
    public Node max(Node node);
    public void rotateLeft(Node child);
    public void rotateRight(Node child);
    public void insertion(int key);
    public void deletion(int data);
    public void treeTraversal();
    public void visualization(Node node, int count, Boolean pause, Node coloredvalue, Boolean isDel, HashMap<Integer, Integer> H) throws InterruptedException;
    public void addToMap(int key, String value);
    public void go(Node node, int level);
    public int height(Node node);

}
