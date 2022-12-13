package driver;
import util.RedBlackTree;
import java.util.*;

public class Driver {
    public static void main(String[] args) throws Exception {
    	try {
    	Scanner sc = new Scanner(System.in);
    	RedBlackTree rbt = new RedBlackTree();
    	int choice, value;
    	while(true)
    	{
    		System.out.println("1.Insertion");
    		System.out.println("2.Search");
    		System.out.println("3.Deletion");
    		System.out.println("4.Traversal");
    		System.out.println("5.InOrderTraversal");
    		System.out.println("6.PreOrderTraversal");
    		System.out.println("7.PostOrderTraversal");
    		System.out.println("8.Visualization of the Tree");
    		System.out.println("9.Exit");
    		System.out.println("Enter your choice:");
    		choice = sc.nextInt();
    		switch(choice) 
    		{
    			case 1:
    				System.out.println("Enter the element you want to insert:");
    				value = sc.nextInt();
    				rbt.insertion(value);
    				break;
    			case 2:
    				System.out.println("Enter the element you want to search:");
    				value = sc.nextInt();
    				if (rbt.findTree(value) == value)
    	    			System.out.println("Element Found");
    				else
    					System.out.println("Element Not Found");
    				break;
    			case 3:
    				System.out.println("Enter the element you want to delete:");
    				value = sc.nextInt();
    				rbt.deletion(value);
    				break;
    			case 4:
    				System.out.println("Printing the Tree:");
    				rbt.treeTraversal();
    				System.out.println();
    				break;
    			case 5:
    				System.out.println("Printing the Tree in InOrderTraversal:");
    				rbt.inOrder();
    				System.out.println();
    				break;
    			case 6:
    				System.out.println("Printing the Tree in PreOrderTraversal:");
    				rbt.preOrder();
    				System.out.println();
    				break;
    			case 7:
    				System.out.println("Printing the Tree in PostOrderTraversal:");
    				rbt.postOrder();
    				System.out.println();
    				break;
    			case 8:
    				rbt.visualization();
    				System.out.println();
    				break;
    			case 9:
    				System.out.println("Quiting the Program");
    				System.exit(0);
    			default:
    				System.out.println("Wrong Input");
    				sc.close();
    		}
    		}
    	}
    		catch(IllegalStateException e)
    		{
    			System.out.println("Quiting the Program");
    			System.exit(0);
    		}
    	}
}
