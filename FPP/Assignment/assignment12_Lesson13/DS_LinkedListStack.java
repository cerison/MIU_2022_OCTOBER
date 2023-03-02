package assignment12_Lesson13;

public class DS_LinkedListStack {
	
    private Node top;
    private int LIST_SIZE;
    private int size;

    public DS_LinkedListStack(int size) {
        this.top = null;
        this.size = 0;
        LIST_SIZE = size;
    }
    
    private class Node {
        private Object value;
        private Node prev;

        public Node(Object value) {
            this.value = value;
            this.prev = null;
        }
    }

    public void push(Object value) {
    	if(LIST_SIZE == size) {
			System.out.println("Stack is full");
			return;
		}
        Node newNode = new Node(value);
    	newNode.prev = top;
        top = newNode;
        size++;
    }

    public Object pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        Object value = top.value;
        top = top.prev;
        size--;
        return value;
    }

    public Object peek() {
        if (isEmpty()) {
            return -1;
        }
        return top.value;
    }

    public boolean isEmpty() {
        return (top == null);
    }

    public int size() {
        return size;
    }
    public static void main(String[] args) {
    	DS_LinkedListStack stk = new DS_LinkedListStack(4); // create stack of size 4
		Object item;
		stk.push('A'); // push 3 items onto stack
		stk.push('B');
		stk.push('C');
		stk.push('D');
		
		System.out.println("size(): " + stk.size());
		item = stk.pop(); // delete item
		System.out.println(item + " is deleted");
		stk.push('D'); // add three more items to the stack
		stk.push('E');
		System.out.println(stk.pop() + " is deleted");
		stk.push('G'); // push one item
		item = stk.peek(); // get top item from the stack
		System.out.println(item + " is on top of stack");
		System.out.println("Size of the Stack : " + stk.size());
		DS_LinkedListStack stk1 = new DS_LinkedListStack(4);
		stk1.push("Java");
		stk1.push(20);
		stk1.push(30);
		item = stk1.peek(); // get top item from the stack
		System.out.println(item + " is on top of stack");
    }
}