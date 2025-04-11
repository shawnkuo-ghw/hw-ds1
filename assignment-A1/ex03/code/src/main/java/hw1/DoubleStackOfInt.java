package hw1;

public interface DoubleStackOfInt {
	void pushHead(int elem); // push an integer into the head-side stack
	void pushTail(int elem); // push an integer into the head-side stack
	int popHead();  	     // pop an integer from the head-side stack
    int popTail();  	     // pop an integer from the head-side stack
	boolean isEmptyHead();   // checks the head-side stack is empty
	boolean isEmptyTail();   // checks the tail-side stack is empty
    int topHead(); 		     // consult the top element of head-side stack without popping it
    int topTail(); 		     // consult the top element of tail-side stack without popping it
	boolean isFull();        // check whether the stack is full
	int headIdx();			 // consult the index of stack pointer of head-stack
	int tailIdx();			 // consult the index of stack pointer of tail-stack 
	boolean isSortedDescendinglyHead(); // check whether the head-side stack is sorted ascendingly
}