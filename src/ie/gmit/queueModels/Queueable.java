package ie.gmit.queueModels;

public interface Queueable<E> {
	public boolean offer(E e);
	public E poll();
	public E peek();
	public int size();
	public E take();
}
