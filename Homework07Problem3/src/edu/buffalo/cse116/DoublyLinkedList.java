package edu.buffalo.cse116;

/**
 * This defines a few basic methods in a doubly linked-based List. Since there are lots of special cases for adding add
 * and removing data, this has you tackle just a few of them.
 *
 * @author Matthew Hertz
 * @param <E> Type of data held in this collection
 */
public class DoublyLinkedList<E> {

  /** Reference to the first node in our linked list. This will be null if the list is empty. */
  private Entry<E> head;

  /** Reference to the last node in our linked list. This will be null if the list is empty. */
  private Entry<E> tail;

  /**
   * Number of elements in our linked list
   */
  private int size;

  /**
   * Creates an empty list.
   */
  public DoublyLinkedList() {
    head = null;
    tail = null;
    size = 0;
  }

  /**
   * Adds a new node to the middle of the linked list. This node will need to be created and then setup so that it
   * appears between the specified nodes already in the list. Finally, update any instance variable so that they reflect
   * the newly added node.
   *
   * @param prior Node that will come before the one being created in this method.
   * @param elem Element we are adding to the linked list
   * @param follower Node that comes after the one being created in this method.
   */
  private void addBetween(Entry<E> prior, E elem, Entry<E> follower) {
	  Entry<E> temp = new Entry<E>(elem);
	  prior.setNext(temp);
	  temp.setPrev(prior);
	  temp.setNext(follower);
	  follower.setPrev(temp);
	  size++;
    // TODO Finish this method

  }

  /**
   * Adds a new node at the start of the linked list. This node will need to be created and then setup so that it comes
   * before the current head node. Finally, update any instance variable so that they properly reflect the addition of
   * this new first node.
   *
   * @param elem Element we are adding to the front of the linked list
   */
  private void addToFront(E elem) {
	  if(size == 0) {
		  size++;
		  head = new Entry<E>(elem);
		  tail = head; 
	  }
	  else {
		  size++;
		  Entry<E> temp = new Entry<E>(elem);
		  temp.setNext(head);
		  head.setPrev(temp);
		  head = temp;
	  }
  }

  /**
   * Returns the number of elements currently in this list.
   *
   * @return the number of elements in the list
   */
  public int size() {
    return size;
  }
}