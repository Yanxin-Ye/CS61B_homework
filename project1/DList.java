package project1;

/* DList.java */

/**
 *  A DList is a mutable doubly-linked list.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 */

public class DList {

  /**
   *  head references the sentinel node.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  protected DListNode head;
  protected long size;

  /* DList invariants:
   *  1)  head != null.
   *  2)  For any DListNode2 x in a DList, x.next != null.
   *  3)  For any DListNode2 x in a DList, x.prev != null.
   *  4)  For any DListNode2 x in a DList, if x.next == y, then y.prev == x.
   *  5)  For any DListNode2 x in a DList, if x.prev == y, then y.next == x.
   *  6)  size is the number of DListNode2s, NOT COUNTING the sentinel
   *      (denoted by "head"), that can be accessed from the sentinel by
   *      a sequence of "next" references.
   */

  /**
   *  DList() constructor for an empty DList.
   */
  public DList() {
    head = new DListNode();
    head.item = null;
    head.next = head;
    head.prev = head;
    size = 0;
  }

  /**
   *  DList() constructor for a one-node DList.
   */
  public DList(int[] a) {
    head = new DListNode();
    head.item = null;
    head.next = new DListNode();
    head.next.item = a;
    head.prev = head.next;
    head.next.prev = head;
    head.prev.next = head;
    size = 1;
  }

  /**
   *  DList() constructor for a two-node DList.
   */
  public DList(int[] a, int[] b) {
    head = new DListNode();
    head.item = null;
    head.next = new DListNode();
    head.next.item = a;
    head.prev = new DListNode();
    head.prev.item = b;
    head.next.prev = head;
    head.next.next = head.prev;
    head.prev.next = head;
    head.prev.prev = head.next;
    size = 2;
  }

  /**
   *  insertFront() inserts an item at the front of a DList.
   */
  public void insertFront(int[] i) {
    // Your solution here.
	  DListNode node= new DListNode(i, head, head.next);
	  head.next.prev=node;
	  head.next=node;
	  size++;
  }
  
  public void insertEnd(int[] i) {
	  DListNode node = new DListNode(i,head.prev,head);
	  head.prev.next=node;
	  head.prev=node;
	  size++;
  }
  
  public void insertPrev(int[] item, DListNode node) {
	  DListNode x = new DListNode(item, node.prev, node);
	  node.prev.next=x;
	  node.prev=x;
	  size++;
  }
  
  public void insertNext(int[] item, DListNode node) {
	  DListNode x = new DListNode(item, node, node.next);
	  node.next.prev=x;
	  node.next=x;
	  size++;
  }

  /**
   *  removeFront() removes the first item (and first non-sentinel node) from
   *  a DList.  If the list is empty, do nothing.
   */
  public void removeFront() {
    // Your solution here.
	  if (size!=0) {
		  head.next.next.prev=head;
		  head.next=head.next.next;
		  size--;
	  }
  }
  
  public void removeEnd() {
	  if (size!=0) {
		  head.prev.prev.next=head;
		  head.prev=head.prev.prev;
		  size--;
	  }
  }
  
  public DListNode getHead() {
	  return this.head;
	  //because my DList class has a sentinal, the pixel(0,0) is head.next.
  }
  
  /**
   *  getTotalLength()  returns the total number of pixels 
   *  		in the list of a RunLengthEncoding
   */  
  
  public int getTotalLength() {
	  if (size == 0) {
		  return 0;
	  }
	  int sum = 0;
	  DListNode node = head.next;
	  while (node.item != null) {
		  sum += node.getLength();
		  node= node.next;
	  }
	  return sum;
  }

  /**
   *  toString() returns a String representation of this DList.
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a String representation of this DList.
   */
  public String toString() {
    String result = "{  ";
    DListNode current = head.next;
    while (current != head) {
      result = result + "["+current.item[0] + ", "+current.item[1]+ ", "
    		  			+current.item[2]+ ", "+current.item[3]+"]   ";
      current = current.next;
    }
    return result + "}";
  }


  public static void main(String[] args) {
//    // DO NOT CHANGE THE FOLLOWING CODE.
//
	    int[] CX = new int[] {3,5,7,9};
	    int[] CY= new int[] {1,12,3,3};
    DList l = new DList();
    System.out.println("### TESTING insertFront ###\nEmpty list is " +  l.toString());
//
    l.insertFront(CX);
    l.insertFront(CY);
//    l.insertEnd(new int[] {1,12});
    l.insertEnd(CX);
    l.insertFront(CX);
    System.out.println("### TESTING insertFront CX\nEmpty list is " + l.toString());
    System.out.println(l.getTotalLength());

  }
//
}