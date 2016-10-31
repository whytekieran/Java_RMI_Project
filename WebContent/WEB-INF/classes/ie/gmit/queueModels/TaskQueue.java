package ie.gmit.queueModels;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

//Created my own Queue interface with bare bones functionality needed from a Queue. Implement it here
public class TaskQueue<E> implements Queueable<E> {

	//TaskQueue is composed of an instance of LinkedBlockingQueue
	BlockingQueue<E> queue = new LinkedBlockingQueue<E>();

	//Using Delegation below. Java's LinkedBlockingQueue can provide the functionality i need
	//for my custom created interface methods which are all implemented as they must be when implementing
	//an interface.
	
	@Override
	//Inserts the specified element at the tail of this queue.
	public boolean offer(E e) {
		return queue.add(e);
	}

	//Retrieves and removes the head of this queue, or returns null if this queue is empty.
	@Override
	public E poll() {
		return queue.poll();
	}

	@Override
	//Returns the number of elements in this queue.
	public int size() {
		return queue.size();
	}

	@Override
	//Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
	public E peek() {
		return queue.peek();
	}

	@Override
	//Retrieves and removes the head of this queue, waiting if necessary until an element becomes available.
	public E take() {
		
		try {
			return queue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
