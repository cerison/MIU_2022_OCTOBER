package assignment10_Lesson11;

public class BST {
	BinaryNode root;
	public BST() {
		root = null;
	}

	private class BinaryNode {
		private Integer item;
		private BinaryNode left;
		private BinaryNode right;
		
		public BinaryNode(Integer item) {
			this(item, null, null);
		}
		
		public BinaryNode(Integer item, BinaryNode left, BinaryNode right) {
			this.item = item;
			this.left = left;
			this.right = right;
		}
	}
	
	public int size() {
		if(isEmpty()) return 0;
		return countNodes(root);
	}
	
	private int countNodes(BinaryNode node) {
		int count = 1;
		if(node.left != null) count += countNodes(node.left);
		if(node.right != null) count += countNodes(node.right);
		return count;
	}
	
	public Integer leafNodes(){
		return leafNodes(root);
	}
	
	private int leafNodes(BinaryNode node) {
		if(node.left == null && node.right == null) return 1;
		int count = 0;
		if(node.left != null) count += leafNodes(node.left);
		if(node.right != null) count += leafNodes(node.right);
		return count;
	}
	
	public Integer findMax(){
		 return findMax(root);
	}
	
	private Integer findMax(BinaryNode node) {
		if(node.right== null) return node.item;
		return findMax(node.right);
	}
	
	public Integer findMin(){
		 return findMin(root);
	}
	
	private Integer findMin(BinaryNode node) {
		if(node.left == null) return node.item;
		return findMin(node.left);
	}
	
	public Integer getRoot() {
		if(isEmpty()) return null;
		else return root.item;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public boolean contains(Integer key) {
		boolean found = false;
		if(isEmpty()) {
			System.out.println("Cannot search in an empty tree.");
			return false;
		}
		BinaryNode current = root;
		while(!found) {
			if(current == null) continue;
			else if(current.item > key) {
				if(current.left != null) current = current.left;
				else break;
			}
			else if(current.item < key) {
				if(current.right != null) current = current.right;
				else break;
			}
			else {
				found = true;
				break;
			}
		}
		return found;
	}
	
	public void add(Integer item) {
		BinaryNode newNode = new BinaryNode(item);
		if(isEmpty()) root = newNode;
		else {
			BinaryNode current = root;
			boolean inserted = false;
			while(!inserted) {
				if(current.item < item) {
					if(current.right == null) {
						current.right = new BinaryNode(item, null, null);
						inserted = true;
					} else current = current.right;
				} else if(current.item > item) {
					if(current.left == null) {
						current.left = new BinaryNode(item, null, null);
						inserted = true;
					} else current = current.left;
				} else inserted = true;
			}
		}
	}
	
	public void printTree() {
		if(isEmpty()) System.out.println("Empty tree");
		else {
			System.out.print("Current tree: ");
			printTree(root);
			System.out.println();
		}
	}
	
	private void printTree(BinaryNode node) {
		if(node == null) return;
		printTree(node.left);
		System.out.print(" -> " + node.item);
		printTree(node.right);
	}
	
	public void preOrder(){
		if(isEmpty()) System.out.println("Cannot traverse empty tree in pre order.");
		else {
			System.out.print("Traversing BST in pre order: ");
			preOrder(root);
			System.out.println();
		}
	}
	
	private void preOrder(BinaryNode node) {
		if(node == null) return;
		System.out.print(" --> " + node.item);
		printTree(node.left);
		printTree(node.right);
	}
	
	public void postOrder(){
		if(isEmpty()) System.out.println("Cannot traverse empty tree in post order.");
		else {
			System.out.print("Traversing BST in post order: ");
			postOrder(root);
			System.out.println();
		}
	}
	
	private void postOrder(BinaryNode node) {
		if(node == null) return;
		printTree(node.left);
		printTree(node.right);
		System.out.print(" --> " + node.item);
	}
	
	public static void main(String[] args) {
		BST bst = new BST();
		Integer[] arr = {15, 12, 9, 56, 1, 16, 19, 22, 3, 100, 2, 25};
		
		for(int i = 0; i < arr.length; i++) bst.add(arr[i]);
		
		bst.preOrder();
		System.out.println("----------------------");
		bst.postOrder();
		System.out.println("----------------------");
		bst.printTree();
		System.out.println("----------------------");

		//Check if the tree contains an element
		System.out.println("52 is present is the tree: " + bst.contains(25) + "  ");
		System.out.println("100 is present is the tree: " + bst.contains(20)  + "  ");
		System.out.println("----------------------");

		//Get root of tree
		System.out.println("The root of the tree is: " + bst.getRoot());
		System.out.println("----------------------");

		//Getting the leaf nodes count
		System.out.println("The number of leaf nodes in the tree is: " + bst.leafNodes());

		//Getting the size of the tree
		System.out.println("The size of nodes in the tree is: " + bst.size());
		System.out.println("----------------------");

		//Checking if the tree is empty
		System.out.println("Is empty tree: " + bst.isEmpty());
		System.out.println("----------------------");
		
		//Getting minimum value in the tree
		System.out.println("The minimum value in the tree is: " + bst.findMin());
		System.out.println("----------------------");
		
//		Getting maximum value in the tree
		System.out.println("The maximum value in the tree is: " + bst.findMax());
		System.out.println("----------------------");
	}
}