package project1;

/* DListNode2.java */

/**
 *  A DListNode2 is a node in a DList2 (doubly-linked list).
 */

public class DListNode {

  /**
   *  item references the item stored in the current node.
   *  prev references the previous node in the DList.
   *  next references the next node in the DList.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  public int[] item;
  public DListNode prev;
  public DListNode next;

  /**
   *  DListNode2() constructor.
   */
  DListNode() {
    item = new int[4];//Ä¬ÈÏÖµÎª0
    prev = null;
    next = null;
  }

  DListNode(int[] i) {
    item = i;
    prev = null;
    next = null;
  }
  
  DListNode(int[] i , DListNode prevnode, DListNode nextnode){
	  item = i;
	  prev= prevnode;
	  next= nextnode;			  			 
  }
  
  public int getLength() {
	  return item[0];
  }
  
  public int getRed() {
	  return item[1];
  }
  
  public int getGreen() {
	  return item[2];
  }
  
  public int getBlue() {
	  return item[3];
  }
  


}