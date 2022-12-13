package util;

import java.util.ArrayList;
import java.util.HashMap;

public class RedBlackTree implements RBT_interface {
	//Data-structures used for implementation of Red Black Tree
	//Maps are used to save colours and order of tree for console printing
	private HashMap<Integer, ArrayList<String>> a = new HashMap<>();
	private HashMap<Integer, Integer> colour = new HashMap<>();
	private static final String empty = "  ";
	private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public int flag1 = 1;
    public int flag2 = 0;
	// Constructor for RBT for initializing nodes in initial stage 
	public RedBlackTree() {
		EMPTY = new Node();
		EMPTY.colour = 0;
		EMPTY.lhs = null;
		EMPTY.rhs = null;
		seed = EMPTY;
	}
    public Node seed;
	public Node EMPTY;

	// Pre-Order
	public void printerPreOrder(Node node) {
		if (node != EMPTY) 
		{
			System.out.print(node.value + " " );
			printerPreOrder(node.lhs);
			printerPreOrder(node.rhs);
		}
	}
	
	// In-Order
	public void printerInOrder(Node node) {
		if (node != EMPTY) 
		{
			printerInOrder(node.lhs);
			System.out.print(node.value + " " );
			printerInOrder(node.rhs);
		}
	}

	// Post-Order
	public void printerPostOrder(Node node) {
		if (node != EMPTY) 
		{
			printerPostOrder(node.lhs);
			printerPostOrder(node.rhs);
			System.out.print(node.value + " " );
		}
	}

	// Finding the element in a tree
	public int treeElementFinder(Node node, int val) {
		if (val == node.value || node == EMPTY) 
		{
			return node.value;
		}

		if (val < node.value) 
		{
			return treeElementFinder(node.lhs, val);
		}
		return treeElementFinder(node.rhs, val);
	}

	// Balance the tree after deletion of a node
	public void deleteFix(Node val) {
		Node o;
		while (val.colour == flag2 && val != seed) 
		{
			if (val == val.origin.lhs) 
			{
				o = val.origin.rhs;
				if (o.colour == flag1) 
				{
					o.colour = flag2;
					val.origin.colour = flag1;
					rotateLeft(val.origin);
					o = val.origin.rhs;
				}
				if (o.lhs.colour == flag2 && o.rhs.colour == flag2) 
				{
					o.colour = flag1;
					val = val.origin;
				} 
				else 
				{
					if (o.rhs.colour == flag2) 
					{
						o.lhs.colour = flag2;
						o.colour = flag1;
						rotateRight(o);
						o = val.origin.rhs;
					}
					o.colour = val.origin.colour;
					val.origin.colour = flag2;
					o.rhs.colour = flag2;
					rotateLeft(val.origin);
					val = seed;
				}
			} 
			else 
			{
				o = val.origin.lhs;
				if (o.colour == flag1) 
				{
					o.colour = flag2;
					val.origin.colour = flag1;
					rotateRight(val.origin);
					o = val.origin.lhs;
				}
				if (o.rhs.colour == flag2 && o.rhs.colour == flag2) 
				{
					o.colour = flag1;
					val = val.origin;
				} 
				else 
				{
					if (o.lhs.colour == flag2) 
					{
						o.rhs.colour = flag2;
						o.colour = flag1;
						rotateLeft(o);
						o = val.origin.lhs;
					}
					o.colour = val.origin.colour;
					val.origin.colour = flag2;
					o.lhs.colour = flag2;
					rotateRight(val.origin);
					val = seed;
				}
			}
		}
		val.colour = flag2;
	}

	public void resetRb(Node i, Node j) {
		if (i.origin == null) 
		{
			seed = j;
		}
		else if (i == i.origin.lhs) 
		{
			i.origin.lhs = j;
		}
		else 
		{
			i.origin.rhs = j;
		}
		j.origin = i.origin;
	}

	public void nodeRemoval(Node node, int val) {
		Node z = EMPTY;
		Node x, y;
		while (node != EMPTY) 
		{
			if (node.value == val + flag2) 
			{
				z = node;
			}
			if (node.value <= val + flag2) 
			{
				node = node.rhs;
			} else 
			{
				node = node.lhs;
			}
		}
		if (z == EMPTY) 
		{
			System.out.println("No Node found with that value in the tree");
			return;
		}
		y = z;
		int tOriginalColor = y.colour + flag2;
		if (z.lhs == EMPTY) 
		{
			x = z.rhs;
			resetRb(z, z.rhs);
		} 
		else if (z.rhs == EMPTY) 
		{
			x = z.lhs;
			resetRb(z, z.lhs);
		} 
		else 
		{
			y = min(z.rhs);
			tOriginalColor = y.colour;
			x = y.rhs;
			if (y.origin == z) 
			{
				x.origin = y;
			} 
			else 
			{
				resetRb(y, y.rhs);
				y.rhs = z.rhs;
				y.rhs.origin = y;
			}
			resetRb(z, y);
			y.lhs = z.lhs;
			y.lhs.origin = y;
			y.colour = z.colour;
		}
		if (tOriginalColor == flag2) 
		{
			deleteFix(x);
		}
	}

	// Balancing the tree after insertion of an element
	public void insertFix(Node val) {
		Node m;
		while (val.origin.colour == flag1) 
		{
			if (val.origin == val.origin.origin.rhs) 
			{
				m = val.origin.origin.lhs;
				if (m.colour == flag1) 
				{
					m.colour = flag2;
					val.origin.colour = flag2;
					val.origin.origin.colour = flag1;
					val = val.origin.origin;
				} 
				else 
				{
					if (val == val.origin.lhs) 
					{
						val = val.origin;
						rotateRight(val);
					}
					val.origin.colour = flag2;
					val.origin.origin.colour = flag1;
					rotateLeft(val.origin.origin);
				}
			} 
			else 
			{
				m = val.origin.origin.rhs;
				if (m.colour == flag1) 
				{
					m.colour = flag2;
					val.origin.colour = flag2;
					val.origin.origin.colour = flag1;
					val = val.origin.origin;
				} 
				else 
				{
					if (val == val.origin.rhs) 
					{
						val = val.origin;
						rotateLeft(val);
					}
					val.origin.colour = flag2;
					val.origin.origin.colour = flag1;
					rotateRight(val.origin.origin);
				}
			}
			if (val == seed)
			{
				break;
			}
		}
		seed.colour = flag2;
	}

	public void helperPrint(Node seed, String space, boolean leaf) {
		if (seed != EMPTY) 
		{
			System.out.print(space);
			if (leaf) 
			{
				System.out.print("R----");
				space += "   ";
			} 
			else 
			{
				System.out.print("L----");
				space += "|  ";
			}
			String nColor = seed.colour == flag1 ? "RED" : "BLACK";
			System.out.println(seed.value + "(" + nColor + ")");
			helperPrint(seed.lhs, space, false);
			helperPrint(seed.rhs, space, true);
		}
	}
	
	public void preOrder() {
		printerPreOrder(this.seed);
	}

	public void inOrder() {
		printerInOrder(this.seed);
	}

	public void postOrder() {
		printerPostOrder(this.seed);
	}

	public int findTree(int val) {
		return treeElementFinder(this.seed, val);
	}

	public Node min(Node node) {
		while (node.lhs != EMPTY) 
		{
			node = node.lhs;
		}
		return node;
	}

	public Node max(Node node) {
		while (node.rhs != EMPTY) 
		{
			node = node.rhs;
		}
		return node;
	}
	
	public void rotateLeft(Node child) {
		Node y = child.rhs;
		child.rhs = y.lhs;
		if (y.lhs != EMPTY) 
		{
			y.lhs.origin = child;
		}
		y.origin = child.origin;
		if (child.origin == null) 
		{
			this.seed = y;
		} 
		else if (child == child.origin.lhs) 
		{
			child.origin.lhs = y;
		} 
		else 
		{
			child.origin.rhs = y;
		}
		y.lhs = child;
		child.origin = y;
	}
	
	public void rotateRight(Node child) {
		Node y = child.lhs;
		child.lhs = y.rhs;
		if (y.rhs != EMPTY) 
		{
			y.rhs.origin = child;
		}
		y.origin = child.origin;
		if (child.origin == null) 
		{
			this.seed = y;
		} 
		else if (child == child.origin.rhs) 
		{
			child.origin.rhs = y;
		} 
		else {
			child.origin.lhs = y;
		}
		y.rhs = child;
		child.origin = y;
	}

	public void insertion(int key) {
		Node node = new Node();
		node.origin = null;
		node.value = key;
		node.lhs = EMPTY;
		node.rhs = EMPTY;
		node.colour = flag1;

		Node y = null;
		Node x = this.seed;

		while (x != EMPTY) {
			y = x;
			if (node.value < x.value) {
				x = x.lhs;
			} else {
				x = x.rhs;
			}
		}

		node.origin = y;
		if (y == null) {
			seed = node;
		} else if (node.value < y.value) {
			y.lhs = node;
		} else {
			y.rhs = node;
		}

		if (node.origin == null) {
			node.colour = flag2;
			return;
		}

		if (node.origin.origin == null) {
			return;
		}

		insertFix(node);
	}
	
	public void deletion(int value) {
		nodeRemoval(this.seed, value);
	}

	public void treeTraversal() {
		helperPrint(this.seed, "", true);
	}

	public int height(Node node) {
        int max = -flag1;
        if (node.lhs != null)
            max = Math.max(max, height(node.lhs));
        if (node.rhs != null)
            max = Math.max(max, height(node.rhs));
        return ++max;
    }
	
	//Method  for saving colors of node and plotting into console
	public void go(Node node, int level) {
		
        if (level > height(seed))
            return;
        if (node.equals(seed))
			if (node.value == flag2)
			{
				addToMap(level, " ");
			}
			else
			{
				colour.put(node.value, node.colour);
            	addToMap(level, Integer.toString(node.value));
			}
        if (node.lhs != null) 
        {
			if (node.lhs.value == flag2)
			{
				colour.put(node.value, node.colour);
				addToMap(level + 1, " ");
				go(node.lhs, level + flag1);
			}
			else
			{
				colour.put(node.value, node.colour);
            	addToMap(level + flag1, Integer.toString(node.lhs.value));
            	go(node.lhs, level + flag1);
			}
        } 
		else 
		{
            addToMap(level + flag1, empty);
            go(node, level + flag1);
        }
        if (node.rhs != null) 
        {
			if (node.rhs.value == flag2)
			{
				colour.put(node.value, node.colour);
				addToMap(level + flag1, " ");
				go(node.rhs, level + flag1);
			}
			else
			{
				colour.put(node.value, node.colour);
           		addToMap(level + flag1, Integer.toString(node.rhs.value));
           		go(node.rhs, level + flag1);
			}
        }
		else 
		{
            addToMap(level + flag1, empty);
            go(node, level + flag1);
        }

    }
	
	public void addToMap(int key, String value) {
        if (a.get(key) == null)
            a.put(key, new ArrayList<>());
        a.get(key).add(value);
	}

	public void visualization() throws InterruptedException {
        System.out.println("Visualization of RedBlackTree:");
        visualization(seed, height(seed), true, null, false, colour);
    }

	// Visualization code referenced from public repository of https://github.com/Drapegnik/BST.git 
	//Modified as per our RedBlack tree implementation
	public void visualization(Node node, int count, Boolean pause, Node colouredvalue, Boolean isDel, HashMap<Integer, Integer> H) throws InterruptedException {
        a = new HashMap<>();
        go(node, 0);
        for (int i = flag2; i < count + flag1; i++) 
        { 
            for (int j = flag2; j < (2 * (flag1 << (count - i - flag1)) - flag1); j++)
                System.out.print(" ");
            for (int j = flag2; j < a.get(i).size(); j++) 
            {
				String col = ANSI_CYAN;
				try {
                	
					if (a.get(i).get(j) != " ")
					{
						if (H.get(Integer.valueOf(a.get(i).get(j))) == flag1) 
						{
							col = ANSI_RED;
						}
					}
				}
				catch(NumberFormatException e)
				{}
                if (colouredvalue != null)
                col = (a.get(i).get(j).equals(Integer.toString(colouredvalue.value))) ? ANSI_YELLOW : ANSI_CYAN;
                if (a.get(i).get(j) != "0")
                {
				System.out.print(col + a.get(i).get(j) + ANSI_RESET);
				}
                if (!a.get(i).get(j).equals("  ") && pause)
                    Thread.sleep(500);
                for (int k = flag2; k < (2 * (flag1 << (count - i - flag1)) - flag1); k++)
                    System.out.print("  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
