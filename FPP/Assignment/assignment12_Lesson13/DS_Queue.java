package assignment12_Lesson13;

class ArrayQueueImpl {
    private int[] arr = new int[10];
    private int front = -1;
    private int rear = 0;

    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is Empty");
            return -1;
        }
        return arr[front];
    }

    public void enqueue(int obj) {
        if(rear == arr.length) {
            resize();
        }
        if(front == -1 && rear == 0) front++;
        arr[rear] = obj;
        rear++;
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is Empty");
            return -1;
        }
        int dequeued = arr[front];
        arr[front] = 0;
        front++;
        return dequeued;
    }

    public boolean isEmpty() {
        return (rear == 0);
    }

    public int size() {
    	if(isEmpty()) return 0;
    	else return (rear - front);
    }

    private void resize() {
        int[] newArr = new int[arr.length * 2];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }
	public void display() {
		if(isEmpty()) {
			System.out.println("Queue is Empty");
			return;
		}
		for(int i = front; i < rear; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
}

public class DS_Queue {
	public static void main(String[] args) {
		ArrayQueueImpl q = new ArrayQueueImpl();
		q.display();
		System.out.println("size(): " + q.size());
		q.enqueue(10);
		q.enqueue(3);
		q.enqueue(14);
		q.enqueue(4);
		q.enqueue(1);
		q.display();
		System.out.println("size(): " + q.size());
		System.out.println("Remove(): " + q.dequeue());
		q.display();
		System.out.println("peek(): " + q.peek());
		System.out.println("Remove(): " + q.dequeue());
		q.display();
		System.out.println("size(): " + q.size());
		System.out.println("isEmpty(): " + q.isEmpty());
		ArrayQueueImpl q1 = new ArrayQueueImpl();
		System.out.println("isEmpty(): " + q1.isEmpty());
	}

}