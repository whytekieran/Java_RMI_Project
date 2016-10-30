package ie.gmit.sw;


import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

//Created my own Queue interface with bare bones functionality needed from a Queue
public class TaskQueue<E> implements Queueable<E> {

	Queue<E> queue = new ConcurrentLinkedQueue<E>();

	//Using Delegation below. Java's ConcurrentLinkedQueue to provide the functionality
	//for my custom created interface methods which are all implemented.
	
	//Inserts the specified element at the tail of this queue.
	public boolean add(E e) {
		return queue.add(e);
	}

	//Retrieves and removes the head of this queue, or returns null if this queue is empty.
	public E poll() {
		return queue.poll();
	}

	//Returns the number of elements in this queue.
	public int size() {
		return queue.size();
	}

	//Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
	public E peek() {
		return queue.peek();
	}
}
