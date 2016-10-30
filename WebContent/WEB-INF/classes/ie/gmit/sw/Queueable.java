package ie.gmit.sw;

public interface Queueable<E> {
	public boolean add(E e);
	public E poll();
	public E peek();
	public int size();
}
