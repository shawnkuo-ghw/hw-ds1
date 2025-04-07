package hw1;

public interface DoubleStackOfInt {
	void push_head(int elem); // push an integer onto the stack from the head of array
	void push_tail(int elem); // push an integer onto the stack from the tail of array
	void pop_head();  	      // pop an integer from the stack from the head of array
    void pop_tail();  	      // pop an integer from the stack from the tail of arry
	boolean empty_head();     // checks the head-side stack is empty
	boolean empty_tail();     // checks the tail-side stack is empty
    int top_head(); 		  // consult the top element of head-side stack without popping it
    int top_tail(); 		  // consult the top element of tail-side stack without popping it
}