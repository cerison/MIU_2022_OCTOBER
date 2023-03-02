package assignment9_Lesson10;

//Represent a node of the singly linked list    
public class SinglyLinkedList {
	class Node {
		int data;
		Node next;

		public Node(int data) {
			this.data = data;
			this.next = null;
		}
	}

//Represent the head and tail of the singly linked list    
	public Node head = null;
	public Node tail = null;

//addNode() will add a new node to the list    
	public void addNode(int data) {
		// Create a new node
		Node newNode = new Node(data);

		// Checks if the list is empty
		if (head == null) {
			// If list is empty, both head and tail will point to new node
			head = newNode;
			tail = newNode;
		} else {
			// newNode will be added after tail such that tail's next will point to newNode
			tail.next = newNode;
			// newNode will become new tail of the list
			tail = newNode;
		}
	}

	public void removeLastOccurance(int n) {

		Node traverse = head; // start from head
		Node current = null; // finding the element

//=========================Get the required node==============================================================================
		while (traverse != null) {
			if (traverse.data == n)
				current = traverse;
			traverse = traverse.next;
		}
//================================If current points to null i.e it is last element============================================
		if (current != null && current.next == null) {
			traverse = head;
			while (traverse.next != current) {
				traverse = traverse.next;
			}
			traverse.next = null;
		}
//======================== If current is not last node =========================================================================

		// If it is not the last node
		if (current != null && current.next != null) {
			current.data = current.next.data;
			current.next = current.next.next;
			
		}

	}

	public boolean find(int n) {
		Node current = head;
		if (head == null) {
			return false;
		}

		while (current != null) {
			if (current.data == n) {
				return true;
			}
			current = current.next;
		}
		return false;
	}
	// remove last
//	public void removeLast() {
//		if(head==null) return;
//		Node secondLast = head;
//		while(secondLast.next.next !=null) {
//			secondLast=secondLast.next;
//		}
//		secondLast.next=null;
//		// return head;
//	}

//display() will display all the nodes present in the list    
	public void display() {
		// Node current will point to head
		Node current = head;

		if (head == null) {
			System.out.println("List is empty");
			return;
		}
		System.out.println("Nodes of singly linked list: ");
		while (current != null) {
			// Prints each node by incrementing pointer
			System.out.print(current.data + " ");
			current = current.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		SinglyLinkedList myList = new SinglyLinkedList();
		myList.addNode(5);
		myList.addNode(3);
		myList.addNode(5);
		myList.addNode(1);
		myList.addNode(5);
		myList.addNode(5);
		myList.display();
		myList.removeLastOccurance(5);
		myList.display();

	}
}
