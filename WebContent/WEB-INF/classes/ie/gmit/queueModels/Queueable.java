package ie.gmit.queueModels;

//My own custom Queue interface contains the few and only methods i will be needing
public interface Queueable<E> {
	public boolean offer(E e);
	public E poll();
	public E peek();
	public int size();
	public E take();
}
