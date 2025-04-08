package hw1;

public interface DoubleStackOfInt {
	void push_head(int elem); // push an integer into the head-side stack
	void push_tail(int elem); // push an integer into the head-side stack
	int pop_head();  	      // pop an integer from the head-side stack
    int pop_tail();  	      // pop an integer from the head-side stack
	boolean empty_head();     // checks the head-side stack is empty
	boolean empty_tail();     // checks the tail-side stack is empty
    int top_head(); 		  // consult the top element of head-side stack without popping it
    int top_tail(); 		  // consult the top element of tail-side stack without popping it
	boolean isFull();         // check whether the stack is full
}