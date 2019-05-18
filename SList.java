package sList;

/* SList.java */

/**
 *  The SList class is a singly-linked implementation of the linked list
 *  abstraction.  SLists are mutable data structures, which can grow at either
 *  end.
 *
 *  @author Kathy Yelick and Jonathan Shewchuk
 **/

public class SList {

  private SListNode head;
  private int size;

  /**
   *  SList() constructs an empty list.
   *  with a sentinel
   **/
  /**
   * @head Head is a sentinel, is a SListNode, with item=Null.
   * When the linked list is empty, next and prev points to head itself.
   * When the linked list is not empty, head.next -> the first node,
   * head.prev->the last node
   */

  public SList() {
    size = 0;
    head=new SListNode(null,null, null);
    head.prev=head;
    head.next=head;
  }
  
  /**
   *  isEmpty() indicates whether the list is empty.
   *  @return true if the list is empty, false otherwise.
   **/

  public boolean isEmpty() {
    return size == 0;
  }

  /**
   *  length() returns the length of this list.
   *  @return the length of this list.
   **/

  public int length() {
    return size;
  }

  /**
   *  insertFront() inserts item "obj" at the beginning of this list.
   *  @param obj the item to be inserted.
   **/

  public void insertFront(Object obj) {
	  
	  	SListNode node = new SListNode(head, obj, head.next);
	  	head.next.prev=node;
	  	head.next=node;
	  	size++;
	    
//	    head = new SListNode(head, obj, head.next);
//	    size++;
  }

  /**
   *  insertEnd() inserts item "obj" at the end of this list.
   *  @param obj the item to be inserted.
   **/

  public void insertEnd(Object obj) {
//    if (head == null) {
//      head = new SListNode(obj);
//    } else {
//      SListNode node = head;
//      while (node.next != null) {
//        node = node.next;
//      }
//      node.next = new SListNode(obj);
//    }
//    size++;

	  SListNode node =new SListNode(head.prev, obj, head);  
	  head.prev.next=node;
	  head.prev=node;   
	  size++;
  }

  /**
   *  nth() returns the item at the specified position.  If position < 1 or
   *  position > this.length(), null is returned.  Otherwise, the item at
   *  position "position" is returned.  The list does not change.
   *  @param position the desired position, from 1 to length(), in the list.
   *  @return the item at the given position in the list.
   **/

  public Object nth(int position) {
    SListNode currentNode;

    if ((position < 1) || (head == null)) {
      return null;
    } else {
      currentNode = head;
      while (position > 1) {
        currentNode = currentNode.next;
        if (currentNode == null) {
          return null;
        }
        position--;
      }
      return currentNode.item;
    }
  } 

  /**
   *  toString() converts the list to a String.
   *  @return a String representation of the list.
   **/

  public String toString() {
    int i;
    Object obj;
    String result = "[  ";

    SListNode cur = head.next;//改了这里，从第一个node开始打！！！！

    while (cur.item != null) {		//改了这里
      obj = cur.item;
      result = result + obj.toString() + "  ";
      cur = cur.next;
    }
    result = result + "]";
    return result;
  }


  /**
   *  main() runs test cases on the SList class.  Prints summary
   *  information on basic operations and halts with an error (and a stack
   *  trace) if any of the tests fail.
   **/

  public static void main (String[] args) {
    // Fill in your solution for Part I here.
	SList ny = new SList();
	ny.insertFront(12);
	ny.insertEnd(9);
	ny.insertEnd(6);
	System.out.println("P1 ANSWER:"
		       + ny.toString());
	
    testEmpty();
    testAfterInsertFront();
    testAfterInsertEnd();
  }

    
  /**
   *  testEmpty() tests toString(), isEmpty(), length(), insertFront(), and
   *  insertEnd() on an empty list.  Prints summary information of the tests
   *  and halts the program if errors are detected.
   **/

  private static void testEmpty() {
    SList lst1 = new SList();
    SList lst2 = new SList();
    System.out.println();
    System.out.println("Here is a list after construction: "
		       + lst1.toString());
    TestHelper.verify(lst1.toString().equals("[  ]"),
		      "toString on newly constructed list failed");

    System.out.println("isEmpty() should be true. It is: " +
		       lst1.isEmpty());
    TestHelper.verify(lst1.isEmpty() == true,
		      "isEmpty() on newly constructed list failed");    

    System.out.println("length() should be 0. It is: " +
		       lst1.length());
    TestHelper.verify(lst1.length() == 0, 
		      "length on newly constructed list failed");    
    lst1.insertFront(new Integer(3));
    System.out.println("Here is a list after insertFront(3) to an empty list: "
		       + lst1.toString());
    TestHelper.verify(lst1.toString().equals("[  3  ]"),
		      "InsertFront on empty list failed");
    lst2.insertEnd(new Integer(5));
    System.out.println("Here is a list after insertEnd(5) on an empty list: "
		       + lst2.toString());
    TestHelper.verify(lst2.toString().equals("[  5  ]"),
		      "insertEnd on empty list failed");
  }

  /**
   *  testAfterInsertFront() tests toString(), isEmpty(), length(),
   *  insertFront(), and insertEnd() after insertFront().  Prints summary
   *  information of the tests and halts the program if errors are detected.
   **/

  private static void testAfterInsertFront() {
    SList lst1 = new SList();
    lst1.insertFront(3);
    lst1.insertFront(2);
    lst1.insertFront(1);
    System.out.println();
    System.out.println("Here is a list after insertFront 3, 2, 1: "
		       + lst1.toString());
    TestHelper.verify(lst1.toString().equals("[  1  2  3  ]"),
		      "InsertFronts on non-empty list failed");
    System.out.println("isEmpty() should be false. It is: " +
		       lst1.isEmpty());
    TestHelper.verify(lst1.isEmpty() == false,
		      "isEmpty() after insertFront failed");    
    System.out.println("length() should be 3. It is: " +
		       lst1.length());
    TestHelper.verify(lst1.length() == 3, 
		      "length() after insertFront failed");
  //  lst1.insertEnd(4);
    lst1.insertEnd(4);

    System.out.println("Here is the same list after insertEnd(4): "
		       + lst1.toString());
    TestHelper.verify(lst1.toString().equals("[  1  2  3  4  ]"),
		      "insertEnd on non-empty list failed");
  }
    
  /**
   *  testAfterInsertEnd() tests toString(), isEmpty(), length(),
   *  insertFront(), and insertEnd() after insertEnd().  Prints summary
   *  information of the tests and halts the program if errors are detected.
   **/

  private static void testAfterInsertEnd() {
    SList lst1 = new SList();
    lst1.insertEnd(new Integer(6));
    lst1.insertEnd(new Integer(7));
    System.out.println();
    System.out.println("Here is a list after insertEnd 6, 7: "
		       + lst1.toString());
    System.out.println("isEmpty() should be false. It is: " +
		       lst1.isEmpty());
    TestHelper.verify(lst1.isEmpty() == false,
		      "isEmpty() after insertEnd failed");    
    System.out.println("length() should be 2. It is: " +
		       lst1.length());
    TestHelper.verify(lst1.length() == 2, 
		      "length() after insertEndfailed");
    lst1.insertFront(new Integer(5));
    System.out.println("Here is the same list after insertFront(5): "
		       + lst1.toString());
    TestHelper.verify(lst1.toString().equals("[  5  6  7  ]"),
		      "insertFront after insertEnd failed");
  }
}