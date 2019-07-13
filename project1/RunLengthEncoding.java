package project1;
/* RunLengthEncoding.java */

/**
 *  The RunLengthEncoding class defines an object that run-length encodes
 *  a PixImage object.  Descriptions of the methods you must implement appear
 *  below.  They include constructors of the form
 *
 *      public RunLengthEncoding(int width, int height);
 *      public RunLengthEncoding(int width, int height, int[] red, int[] green,
 *                               int[] blue, int[] runLengths) {
 *      public RunLengthEncoding(PixImage image) {
 *
 *  that create a run-length encoding of a PixImage having the specified width
 *  and height.
 *
 *  The first constructor creates a run-length encoding of a PixImage in which
 *  every pixel is black.  The second constructor creates a run-length encoding
 *  for which the runs are provided as parameters.  The third constructor
 *  converts a PixImage object into a run-length encoding of that image.
 *
 *  See the README file accompanying this project for additional details.
 */

import java.util.Iterator;

public class RunLengthEncoding implements Iterable {

  /**
   *  Define any variables associated with a RunLengthEncoding object here.
   *  These variables MUST be private.
   */
	private int width;
	private int height;
	private DList list;


  /**
   *  The following methods are required for Part II.
   */

  /**
   *  RunLengthEncoding() (with two parameters) constructs a run-length
   *  encoding of a black PixImage of the specified width and height, in which
   *  every pixel has red, green, and blue intensities of zero.
   *
   *  @param width the width of the image.
   *  @param height the height of the image.
   */

  public RunLengthEncoding(int width, int height) {
    // Your solution here.
	  this.width= width;
	  this.height=height;
	  list = new DList();
	  int[] black= new int[] {width*height,0,0,0};
	  list.insertFront( black);
	  //
  }

  /**
   *  RunLengthEncoding() (with six parameters) constructs a run-length
   *  encoding of a PixImage of the specified width and height.  The runs of
   *  the run-length encoding are taken from four input arrays of equal length.
   *  Run i has length runLengths[i] and RGB intensities red[i], green[i], and
   *  blue[i].
   *
   *  @param width the width of the image.
   *  @param height the height of the image.
   *  @param red is an array that specifies the red intensity of each run.
   *  @param green is an array that specifies the green intensity of each run.
   *  @param blue is an array that specifies the blue intensity of each run.
   *  @param runLengths is an array that specifies the length of each run.
   *
   *  NOTE:  All four input arrays should have the same length (not zero).
   *  All pixel intensities in the first three arrays should be in the range
   *  0...255.  The sum of all the elements of the runLengths array should be
   *  width * height.  (Feel free to quit with an error message if any of these
   *  conditions are not met--though we won't be testing that.)
   */

  public RunLengthEncoding(int width, int height, int[] red, int[] green,
                           int[] blue, int[] runLengths) {
    // Your solution here.
	  doTest(red.length == (green.length) && green.length ==blue.length && blue.length== runLengths.length,
	           "Length is not the same!");
	  int sum = 0;
	  this.width= width;
	  this.height=height;
	  list = new DList();
	  for (int i=0; i<runLengths.length; i++) {
		  doTest(red[i]>=0 && red[i]<=255&& green[i]>=0 && green[i]<=255 && blue[i]>=0 &&blue[i]<=255,
		           "Pixel intensities not in range!");
		  int[] run= new int[] {runLengths[i],red[i], green[i], blue[i]};
		  list.insertEnd(run);
		  sum += runLengths[i];
	  }
	  doTest(sum==width*height, "The sum of all the elements of the runLengths array does not equals to \n" + 
	  		"     width * height");
  }

  /**
   *  getWidth() returns the width of the image that this run-length encoding
   *  represents.
   *
   *  @return the width of the image that this run-length encoding represents.
   */

  public int getWidth() {
    // Replace the following line with your solution.
    return this.width;
  }

  /**
   *  getHeight() returns the height of the image that this run-length encoding
   *  represents.
   *
   *  @return the height of the image that this run-length encoding represents.
   */
  public int getHeight() {
    // Replace the following line with your solution.
    return this.height;
  }

  /**
   *  iterator() returns a newly created RunIterator that can iterate through
   *  the runs of this RunLengthEncoding.
   *
   *  @return a newly created RunIterator object set to the first run of this
   *  RunLengthEncoding.
   */
  public RunIterator iterator() {
    // Replace the following line with your solution.
	  RunIterator x = new RunIterator(this.list);
	  
	  return x;
    // You'll want to construct a new RunIterator, but first you'll need to
    // write a constructor in the RunIterator class.
  }

  /**
   *  toPixImage() converts a run-length encoding of an image into a PixImage
   *  object.
   *
   *  @return the PixImage that this RunLengthEncoding encodes.
   */
  public PixImage toPixImage() {
    // Replace the following line with your solution.
	  PixImage orgin = new PixImage(this.width,this.height );
	  RunIterator run = this.iterator();
	  while (run.hasNext()) {
		  int[] arr = run.next();
		  int min = 0;
		  int max=arr[0];	  
		  for (int j = 0; j< this.height; j++) {
			  for (int i =0; i< this.width; i++) {
			//	  System.out.println("inner:i"+i+",j"+j+", "+"no:"+orgin.getPixNumber(i, j)+",  min" +min+", max" +max+", length: "+arr[0]
			//			  +", red: "+arr[1]+", green:"+arr[2]); 
				  if (orgin.getPixNumber(i, j)>= min && orgin.getPixNumber(i, j)<max ) {
					//  System.out.print("Now the green is : "+(short)arr[2]+"\n");
					  orgin.setPixel(i, j, (short)arr[1],(short)arr[2], (short)arr[3]);
					//  System.out.print("Now the green is : "+(short)arr[2]+"\n");
				  }
				  else {
					  arr=run.next();
					  min = max;
					  max += arr[0];
					  orgin.setPixel(i, j, (short)arr[1],(short)arr[2], (short)arr[3]);
					  
				  }
			  }
		  }

	  }	  
    return orgin;
  }

  /**
   *  toString() returns a String representation of this RunLengthEncoding.
   *
   *  This method isn't required, but it should be very useful to you when
   *  you're debugging your code.  It's up to you how you represent
   *  a RunLengthEncoding as a String.
   *
   *  @return a String representation of this RunLengthEncoding.
   */
  public String toString() {
    // Replace the following line with your solution.

	  return this.list.toString();

  }


  /**
   *  The following methods are required for Part III.
   */

  /**
   *  RunLengthEncoding() (with one parameter) is a constructor that creates
   *  a run-length encoding of a specified PixImage.
   * 
   *  Note that you must encode the image in row-major format, i.e., the second
   *  pixel should be (1, 0) and not (0, 1).
   *
   *  @param image is the PixImage to run-length encode.
   */
  public RunLengthEncoding(PixImage image) {
    // Your solution here, but you should probably leave the following line
    // at the end.
	  width= image.getWidth();
	  height = image.getHeight();
	  list =new DList();
	  int[] array  =new int[] {0, image.getRed(0,0), image.getGreen(0, 0), image.getBlue(0, 0)};
	  for (int j = 0; j < height; j++) {
		  for (int i = 0; i< width; i++) {
			  if (image.getRed(i, j)==array[1]&& image.getGreen(i, j)==array[2]
					  && image.getBlue(i, j)==array[3]) {
				  array[0]+=1;
			  }
			  else {
				  list.insertEnd(array);
				  array = new int[] {1, image.getRed(i,j), image.getGreen(i, j), image.getBlue(i, j)};				  
			  }
		  }
	  }
	  list.insertEnd(array);	  

	  
	  check();

  }

  /**
   *  check() walks through the run-length encoding and prints an error message
   *  if two consecutive runs have the same RGB intensities, or if the sum of
   *  all run lengths does not equal the number of pixels in the image.
   */
  public void check() {
    // Your solution here.
	  int x = height*width;
	  int y = this.list.getTotalLength();
	  if (height*width != list.getTotalLength()) {
	      System.err.println("the sum of all run lengths does not equal the number of pixels in the image\n"
	    		  +" the width is "+width+ ", the height is "+height+", \n"+ "the total length is: "+y );
	  }
	  DListNode node = list.getHead().next;
	  while (node.next.item !=null) {
		  if (node.getRed()==node.next.getRed() && node.getGreen() == node.next.getGreen()
				  && node.getBlue()==node.next.getBlue()) {
			  System.err.println("two consecutive runs have the same RGB intensities");  
		  }
		  else {
		  node=node.next;
		  }
	  }
  }


  /**
   *  The following method is required for Part IV.
   */

  /**
   *  setPixel() modifies this run-length encoding so that the specified color
   *  is stored at the given (x, y) coordinates.  The old pixel value at that
   *  coordinate should be overwritten and all others should remain the same.
   *  The updated run-length encoding should be compressed as much as possible;
   *  there should not be two consecutive runs with exactly the same RGB color.
   *
   *  @param x the x-coordinate of the pixel to modify.
   *  @param y the y-coordinate of the pixel to modify.
   *  @param red the new red intensity to store at coordinate (x, y).
   *  @param green the new green intensity to store at coordinate (x, y).
   *  @param blue the new blue intensity to store at coordinate (x, y).
   *  IMPORTANT: For full points, your setPixel() method must run in time
		proportional to the number of runs in the encoding. You MAY NOT convert the
		run-length encoding to a PixImage object, change the pixel in the PixImage, and
		then convert back to a run-length encoding; not only is that much too slow, it
		will be considered CHEATING and punished accordingly.
   */

  public void setPixel(int x, int y, short red, short green, short blue) {
    // Your solution here, but you should probably leave the following line
    //   at the end.
	  //如果RGB没变，do nothing;
	  //判断xy处在哪个run的哪个位置
	  //如果本身length就为1，直接改RGB, 或者并到前面或者并到后面，或者让前后串成一个ohohohoh

	  //如果在一个run的中间，那要把原来的run断成2节再在中间加一个run，两边length-1
	  
	  //在run的第一个：1、变完后跟前面一样，那原所在的length-1，前面的run的length+1；
	  //2、变完跟前面不一样，原所在的length-1，再加一个run在前面
	  
	  //在run的最后一个：1、变完跟后面一样，原所在的length-1，后面length+1
	  //2、变完跟后面不一样，原所在的length-1, 再加一个run在后面
	  DListNode node=this.locateRun(x, y);
	  int oldred = this.locateRun(x, y).getRed();
	  int oldgreen = this.locateRun(x, y).getGreen();
	  int oldblue = node.getBlue();
	  int prered=1000;
	  int pregreen=1000;
	  int preblue = 1000;
	  int nextred=1000;
	  int nextgreen=1000;
	  int nextblue=1000;
	  

		  if (node.prev.item!= null){
			  prered=node.prev.getRed();
			  pregreen=node.prev.getGreen();
			  preblue=node.prev.getBlue();
		  }
		  
		  if (node.next.item!= null) {
			  nextred=node.next.getRed();
			  nextgreen = node.next.getGreen();
			  nextblue=node.next.getBlue();
		  }
  
	  int[] arr = new int[] {1,red,green, blue};
	  //

	  if (!(red==oldred && green == oldgreen && blue==oldblue)) {
		  if (node.getLength()==1) {
			  if (red==prered && green==pregreen&&blue==preblue
					&&(!(red==nextred &&green==nextgreen&&blue==nextblue))  ) {
				  node.prev.item[0]+=1;
				  node.prev.next=node.next;
				  node.next.prev=node.prev;
			  }else {
				  if (red==nextred &&green==nextgreen&&blue==nextblue
						  &&(!(red==prered && green==pregreen&&blue==preblue))) {
					  node.next.item[0] +=1;
					  node.prev.next=node.next;
					  node.next.prev=node.prev;
				  }
				  else {
					  if (red==prered && green==pregreen&&blue==preblue&&red==nextred 
							  &&green==nextgreen&&blue==nextblue) {
						  node.prev.item[0]+=node.next.getLength()+1;
						  node.prev.next=node.next.next;
						  node.next.next.prev=node.prev;					  
						  
					  }else {
						  node.item = arr;
					  }
				  }
			  }
		  }
		  else {
			  if (this.locateNth(x, y)==0) {
				  if (red==prered&& green==pregreen&& blue==preblue) {
					  node.prev.item[0]+=1;
					  node.item[0]-=1;					  
				  }else {
					  node.item[0]-=1;
					  list.insertPrev(arr, node);//
				  }
				  
			  }else {
				  if (this.locateNth(x, y)==node.getLength()-1) {
					  if (red==nextred &&green==nextgreen&&blue==nextblue) {
						  node.item[0]-=1;
						  node.next.item[0]+=1;
					  }
					  else {
						  node.item[0]-=1;
						  list.insertNext(arr, node);		
					  }
				  }else {
					  int[] arr2 = new int[] {node.getLength()-this.locateNth(x, y)-1,oldred,oldgreen, oldblue};	
					  node.item[0]=this.locateNth(x, y);
					  list.insertNext(arr, node);		  
					  list.insertNext(arr2, node.next);
				  }
			  }
		  }
	  
	  }
	  check();
	  
  }
  /**
   *  locateRun() matches the specific run of this run-length encoding 
   *  to the given (x, y) coordinates.    *
   *  @param x the x-coordinate of the pixel to modify.
   *  @param y the y-coordinate of the pixel to modify.
   */

  public DListNode locateRun(int x, int y) {
	  int num = x+(this.width)*y;
	  DListNode node = list.getHead().next;
	  int current = node.getLength();
	  while (node.item!=null) {
		  if (num < current) {
			  break;
		  }
		  else {
			  node= node.next;
			  current += node.getLength();
		  }
	  }	  
	  return node;  
  }
  
  /**
   *  locateNth() matches the specific run of this run-length encoding 
   *  to the given (x, y) coordinates,
   *  and return the location of the (x,y) inside the specific run.    *
   *  @param x the x-coordinate of the pixel to modify.
   *  @param y the y-coordinate of the pixel to modify.
   */

  public int locateNth(int x, int y) {
	  int num = x+(this.width)*y;
	  DListNode node = list.getHead().next;
	  int current = node.getLength();
	  int res  = 0;
	  while (node.item!=null) {
		  if (num < current) {
			  res = num-current+node.getLength();
			  break;
		  }
		  else {
			  node= node.next;
			  current += node.getLength();
		  }
	  }	  
	  return res;
  }
  
  /**
   * TEST CODE:  YOU DO NOT NEED TO FILL IN ANY METHODS BELOW THIS POINT.
   * You are welcome to add tests, though.  Methods below this point will not
   * be tested.  This is not the autograder, which will be provided separately.
   */


  /**
   * doTest() checks whether the condition is true and prints the given error
   * message if it is not.
   *
   * @param b the condition to check.
   * @param msg the error message to print if the condition is false.
   */
  private static void doTest(boolean b, String msg) {
    if (!b) {
      System.err.println(msg);
    }
  }

  /**
   * array2PixImage() converts a 2D array of grayscale intensities to
   * a grayscale PixImage.
   *
   * @param pixels a 2D array of grayscale intensities in the range 0...255.
   * @return a new PixImage whose red, green, and blue values are equal to
   * the input grayscale intensities.
   */
  private static PixImage array2PixImage(int[][] pixels) {
    int width = pixels.length;
    int height = pixels[0].length;
    PixImage image = new PixImage(width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y],
                       (short) pixels[x][y]);
      }
    }

    return image;
  }

  /**
   * setAndCheckRLE() sets the given coordinate in the given run-length
   * encoding to the given value and then checks whether the resulting
   * run-length encoding is correct.
   *
   * @param rle the run-length encoding to modify.
   * @param x the x-coordinate to set.
   * @param y the y-coordinate to set.
   * @param intensity the grayscale intensity to assign to pixel (x, y).
   */
  private static void setAndCheckRLE(RunLengthEncoding rle,
                                     int x, int y, int intensity) {
    rle.setPixel(x, y,
                 (short) intensity, (short) intensity, (short) intensity);
    rle.check();
  }

  /**
   * main() runs a series of tests of the run-length encoding code.
   */
  public static void main(String[] args) {
    // Be forwarned that when you write arrays directly in Java as below,
    // each "row" of text is a column of your image--the numbers get
    // transposed.
	  
	  int[] testred =new int[] { 0,1, 2,3 ,4};
	  int[] testgreen = new int[] {1,2,3,4,5};
	  int[] testblue = new int[] {2,3,4,5,6};
	  int[] testlength = new int[] {1,2,3,4,5};
	  RunLengthEncoding  rletest1 = new RunLengthEncoding(10,10);
	  System.out.print("nicole is testing rletest1: "+ rletest1+" \n" );
	  RunLengthEncoding  rletest2 = new RunLengthEncoding(5,3,testred, testgreen, testblue, testlength);	  
	  System.out.print("nicole is testing rletest2: \n"+ rletest2+",\n" );
	  
	  
	  PixImage image0 = rletest2.toPixImage();
	  System.out.print("nicole is testing toPixImage():\n"+image0.toString()+" \n");
	  RunLengthEncoding rletest3 = new RunLengthEncoding(image0);
	  System.out.print("nicole is testing RunLengthEncoding:\n"+rletest3+" \n");
	  System.out.print("nicole is testing locateRun:"+rletest3.locateRun(0, 0).getLength()
			  +" \n");
	  System.out.print("nicole is testing locateNth:"+rletest3.locateNth(0,0)
			  +" \n");
		


	  
	  
	  
    PixImage image1 = array2PixImage(new int[][] { { 0, 3, 6 },
                                                   { 1, 4, 7 },
                                                   { 2, 5, 8 } });
    
    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 3x3 image.  Input image:");
    System.out.print(image1);
    RunLengthEncoding rle1 = new RunLengthEncoding(image1);
    System.out.println(rle1.toString());
    rle1.check();
    System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
    doTest(rle1.getWidth() == 3 && rle1.getHeight() == 3,
           "RLE1 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x3 encoding.");
    doTest(image1.equals(rle1.toPixImage()),
           "image1 -> RLE1 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 0, 0, 42);
    image1.setPixel(0, 0, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           /*
                       array2PixImage(new int[][] { { 42, 3, 6 },
                                                    { 1, 4, 7 },
                                                    { 2, 5, 8 } })),
           */
           "Setting RLE1[0][0] = 42 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 1, 0, 42);
    image1.setPixel(1, 0, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[1][0] = 42 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 0, 1, 2);
    image1.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[0][1] = 2 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 0, 0, 0);
    image1.setPixel(0, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[0][0] = 0 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 2, 2, 7);
    image1.setPixel(2, 2, (short) 7, (short) 7, (short) 7);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[2][2] = 7 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 2, 2, 42);
    image1.setPixel(2, 2, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[2][2] = 42 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 1, 2, 42);
    image1.setPixel(1, 2, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[1][2] = 42 fails.");


    PixImage image2 = array2PixImage(new int[][] { { 2, 3, 5 },
                                                   { 2, 4, 5 },
                                                   { 3, 4, 6 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on another 3x3 image.  Input image:");
    System.out.print(image2);
    RunLengthEncoding rle2 = new RunLengthEncoding(image2);
    rle2.check();
    System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
    doTest(rle2.getWidth() == 3 && rle2.getHeight() == 3,
           "RLE2 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x3 encoding.");
    doTest(rle2.toPixImage().equals(image2),
           "image2 -> RLE2 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle2, 0, 1, 2);
    image2.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
    doTest(rle2.toPixImage().equals(image2),
           "Setting RLE2[0][1] = 2 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle2, 2, 0, 2);
    image2.setPixel(2, 0, (short) 2, (short) 2, (short) 2);
    doTest(rle2.toPixImage().equals(image2),
           "Setting RLE2[2][0] = 2 fails.");


    PixImage image3 = array2PixImage(new int[][] { { 0, 5 },
                                                   { 1, 6 },
                                                   { 2, 7 },
                                                   { 3, 8 },
                                                   { 4, 9 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 5x2 image.  Input image:");
    System.out.print(image3);
    RunLengthEncoding rle3 = new RunLengthEncoding(image3);
    //rle3.check();
    System.out.println("Testing getWidth/getHeight on a 5x2 encoding.");
    doTest(rle3.getWidth() == 5 && rle3.getHeight() == 2,
           "RLE3 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 5x2 encoding.");
    doTest(rle3.toPixImage().equals(image3),
           "image3 -> RLE3 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 5x2 encoding.");
    setAndCheckRLE(rle3, 4, 0, 6);
    image3.setPixel(4, 0, (short) 6, (short) 6, (short) 6);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[4][0] = 6 fails.");

    System.out.println("Testing setPixel() on a 5x2 encoding.");
    setAndCheckRLE(rle3, 0, 1, 6);
    image3.setPixel(0, 1, (short) 6, (short) 6, (short) 6);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[0][1] = 6 fails.");

    System.out.println("Testing setPixel() on a 5x2 encoding.");
    setAndCheckRLE(rle3, 0, 0, 1);
    image3.setPixel(0, 0, (short) 1, (short) 1, (short) 1);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[0][0] = 1 fails.");


    PixImage image4 = array2PixImage(new int[][] { { 0, 3 },
                                                   { 1, 4 },
                                                   { 2, 5 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 3x2 image.  Input image:");
    System.out.print(image4);
    RunLengthEncoding rle4 = new RunLengthEncoding(image4);
    rle4.check();
    System.out.println("Testing getWidth/getHeight on a 3x2 encoding.");
    doTest(rle4.getWidth() == 3 && rle4.getHeight() == 2,
           "RLE4 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x2 encoding.");
    doTest(rle4.toPixImage().equals(image4),
           "image4 -> RLE4 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 3x2 encoding.");
    setAndCheckRLE(rle4, 2, 0, 0);
    image4.setPixel(2, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[2][0] = 0 fails.");

    System.out.println("Testing setPixel() on a 3x2 encoding.");
    setAndCheckRLE(rle4, 1, 0, 0);
    image4.setPixel(1, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[1][0] = 0 fails.");
    System.out.println("Testing setPixel() on a 3x2 encoding.");
    setAndCheckRLE(rle4, 1, 0, 1);

    image4.setPixel(1, 0, (short) 1, (short) 1, (short) 1);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[1][0] = 1 fails.");
  }
}
