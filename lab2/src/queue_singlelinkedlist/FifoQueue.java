package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		QueueNode<E> n = new QueueNode<E>(e);
		// Om det är tomt i den länkade listan
		if(last == null) {
			last = n;
			last.next = last;
		}
		// Om det finns element i den länkade listan
		else {
			n.next = last.next;
			last.next = n;
			last = n;
		}
		size++;
		return true; // "Metoden ska därför i klassen FifoQueue alltid sätta in elementet och returnera true."
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if(size == 0) {
			return null;
		}
		else {
			return last.next.element;
		}
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		if(size == 0) {
			return null;
		}
		if(size == 1) {
			size--;
			QueueNode<E> temp = last;	// Skapar ett objekt som refererar till huvudet
			last = null;	
			return temp.element;
		}
		else {
			QueueNode<E> temp = last.next;	// Skapar ett objekt som refererar till huvudet
			last.next = last.next.next;		// Ändrar huvudet till objektet som ligger efter huvudet i den länkade listan.
			size--;
			return temp.element;			// Returnerar det förra huvudet.
		}
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator () {
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;
		private int c;

			/* Konstruktor */
		private QueueIterator() {
			pos = last;
			c = size;
		}
	
		public boolean hasNext() {
			if(c > 0) {
				return true;
			}
			else
				return false;
		}
	
		public E next() {
			if(hasNext()) {
				pos = pos.next;
				c--;
				return pos.element;
			}
			else {
				throw new NoSuchElementException();
			}
		}
	}
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}
	
	/**
	* Appends the specified queue to this queue
	* post: all elements from the specified queue are appended
	* to this queue. The specified queue (q) is empty after the call.
	* @param q the queue to append
	* @throws IllegalArgumentException if this queue and q are identical
	*/
	public void append(FifoQueue<E> q) {
		if(this == q) {
			throw new IllegalArgumentException();
		}
		// Empty to empty || empty to non-empty
		if(q.size == 0) {
			return;
		}
		// Non-empty to empty
		if(this.size == 0) {
			this.last = q.last;
			this.size = q.size;
			q.size = 0;
			return;
		}
		// Non-empty to non-empty
		else {
			this.size += q.size;
			QueueNode<E> temp = this.last.next;
			this.last.next = q.last.next;
			q.last.next = temp;
			this.last = q.last;
			q.last = null;
			q.size = 0;
		}
	}

}
