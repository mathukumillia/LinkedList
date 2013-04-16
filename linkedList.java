/**
 * LinkedList
 *
 * 		creates and manages a linked list
 *
 */
public class LinkedList {
	
	private Node head;

	/**
	 * Constructor
	 */
	public LinkedList(){
		this.head = new Node();
	}

	/**
	 * Constructor
	 * 
	 * @param  value - the value you want the head to have
	 * 
	 */
	public LinkedList(int value){
		this.head = new Node(value);
	}

	/**
	 * getter
	 */
	public Node getHead(){
		return head;
	}

	/**
	 * push
	 * 		add a new node to the linked list
	 *
	 * @param  value - the value you want the new Node to hold
	 */
	public void push(int value){
		Node next = new Node(value, head.getNext());
		head.setNext(next);
	}

	/**
	 * length
	 * 		returns the number of nodes in the list
	 *
	 * @return - int
	 */
	public int length(){
		Node now = head;
		int length = 0;
		while(now.getNext() != null){
			now = now.getNext();
			length++;
		}
		return length;
	}

	/**
	 * buildOneTwoThree
	 * 		set values of this list {1,2,3} and overwrite any previous values
	 * 		
	 */
	public void buildOneTwoThree(){
		Node now = head;
		for(int i = 3; i >= 1; i--){
			push(i);
			now = now.getNext();
		}
		now = head.getNext().getNext().getNext();
		now.setNext(null);
	}

	/**
	 * print
	 * 		prints the linked list to the screen
	 */
	public void print(){
		int length = length();
		Node now = head;
		for(int i = 0; i<=length; i++){
			System.out.print("Node " + i + ": " + now);
			now = now.getNext();
		}	
		System.out.println("The list has finished printing.");
	}

	/**
	 * count
	 * 		counts the number of times a certain number occurs in the linked list
	 *
	 * @param num - the number you want to look for in the linkedList
	 *
	 * @return int
	 * 
	 */
	public int count(int num){
		int length = length();
		Node now = head;
		int count = 0;
		for(int i = 0; i<length; i++){
			now = now.getNext();
			if(now.getValue() == num){
				count++;
			}
		}
		return count;
	}

	/**
	 * getNth
	 * 		returns the value of the node at the nth index of the list
	 *
	 * @param n - the index that you want to find the value of
	 *
	 * @return int
	 * 
	 */
	public int getNth(int n){
		int value;
		Node now = head.getNext();
		for(int i = 0; i<n; i++){
			now = now.getNext();
		}
		value = now.getValue();
		return value;
	}

	/**
	 * empty 
	 * 		empties the list
	 */
	public void empty(){
		head.setNext(null);
	}

	/**
	 * pop
	 * 		removes the first element from the list and returns its value
	 *
	 * precondition - the list may not be empty
	 * 
	 * @return int;
	 */
	public int pop(){
		Node first = head.getNext();
		Node now = first.getNext();
		head.setNext(now);
		return first.getValue();
	}

	/**
	 * linearSearch
	 * 		Given an int n, returns the index where it is found
	 *
	 * @param n - the number you want to find the index of
	 *
	 * @return int
	 */
	public int linearSearch(int n){
		Node now = head;
		int length = length();
		int index = -1;
		for(int i = 0; i<length; i++){
			now = now.getNext();
			if(now.getValue() == n){
				index = i;
			}
		}
		return index;
	}

	/**
	 * insertNth
	 * 		insert the given value into the linked list at the given n index
	 *
	 * @param value - value that the user wants inserted into the linkedList
	 * @param  n - index that you want the value to be inserted into
	 */
	public void insertNth(int value, int index){
		Node n = new Node(value);
		Node now = head;
		if(index == 0){
			push(value);
		}
		if(index > length()){
			return;
		}
		for(int i = 0; i<=index; i++){
			now = now.getNext();
			if(i == index-1){
				n.setNext(now.getNext());
				now.setNext(n);
			}
		}
	}

	/**
	 * sortedInsert
	 * 		creates and inserts a node into a sorted linkedList so that the list stays sorted
	 *
	 * precondition: the list must be sorted in ascending order
	 * @param  value - value of the node you want to insert
	 * 
	 */
	public void sortedInsert(int value){
		Node now = head.getNext();
		int index = 0;
		boolean run = true;
		if(now != null){
			while(run && now.getValue()<value){
				index++;
				if(now.getNext() != null){
					now = now.getNext();
				}else{
					run = false;;
				}	
			}
		}	
		insertNth(value, index);
	}

	/**
	* insertSort
	*		rearranges nodes in list so they are sorted in ascending order
	*
	**/
	public void insertSort(){
		int[] values = new int[length()];
		Node now = head;
		for(int i = 0; i<length(); i++){
			now = now.getNext();
			values[i] = now.getValue();
		}
		empty();
		for(int i = 0; i<values.length; i++){
			sortedInsert(values[i]);
		}
	}

	/**
	* append
	*	given another LinkedList, appends the values from that list to the end of this list
	*
	*@param list - the LinkedList you wish to append to this list
	*
	**/
	public void append(LinkedList l){
   		Node now = head;
   		for(int i = 0; i<length(); i++){
   			now = now.getNext();
   		}
   		now.setNext(l.getHead().getNext());
	}	

	/**
	* frontBackSplit
	*		return a length 2 array of LinkedLists that contains the front and back halves of the list
	*
	*@return LinkedList[2]
	*
	**/
	public LinkedList[] frontBackSplit(){
		int halfLength = length()/2;
		LinkedList first = new LinkedList();
		LinkedList second = new LinkedList();
		LinkedList[] halves = {first, second};
		Node now = head;
		int[] values = new int[length()];
		for(int i = 0; i<length(); i++){
			now = now.getNext();
			values[i] = now.getValue();
		}
		for(int i = halfLength-1; i>=0; i--){
			first.push(values[i]);
		}
		for(int i = length() - 1; i>=halfLength; i--){
			second.push(values[i]);
		}
		return halves;
	}
	
	/**
	* removeDuplicates
	* 		deletes any duplicate nodes from the list
	*
	*precondition: the list must be sorted in increasing order
	*
	**/
	public void removeDuplicates(){
		Node now = head.getNext();
		Node next = now.getNext();
		int counter = 0; 
		for(int i = 1; i < length(); i++){
			while(i<length() && now.getValue() == next.getValue()){
				now.setNext(next.getNext());
				next = now.getNext();
				i++;
			}
			now = now.getNext();
			next = now.getNext();
		}	
	}

	/**
	* alternatingSplit
	*		returns a length 2 array of LinkedLists; the first list consists of the values of the odd nodes, and the second consists of the values of the even nodes
	*
	*@return LinkedList[2]
	*
	**/
	public LinkedList[] alternatingSplit(){
		LinkedList first = new LinkedList();
		LinkedList second = new LinkedList();
		LinkedList[] halves = {first, second};
		Node now = head;
		int[] values = new int[length()];
		for(int i = 0; i<length(); i++){
			now = now.getNext();
			values[i] = now.getValue();	
		}
		for(int i = length() - 1; i>=0; i--){
			if(i%2 == 1){
				first.push(values[i]);
			}else{
				second.push(values[i]);
			}
		}
		return halves;
	}	

	/**
	* sortedMerge
	*		takes in 2 LinkedLists and returns the result of merging the 2 lists and organizing the result in ascending order
	*
	*@param a - the first list you want to merge
	*@param b - the second list you want to merge
	*
	*@return LinkedList
	*
	**/
	public static LinkedList sortedMerge(LinkedList a, LinkedList b){
		LinkedList answer = new LinkedList();
		answer.append(a);
		answer.append(b);
		answer.insertSort();
		return answer;
	}
}