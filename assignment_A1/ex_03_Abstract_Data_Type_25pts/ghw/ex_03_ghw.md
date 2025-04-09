# Exercise 3: Abstract Data Type [25 points]



## 1. Define the ADT `DoubleStackOfInt`

We define the ADT `DoubleStackOfInt` interface as follows,

```java
public interface DoubleStackOfInt
{
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
```

- **push** an integer onto the stack:
    - `void pushHead(int elem)`
    - `void pushTail(int elem)`

- **pop** an integer from the stack:
    - `int popHead()`
    - `int popTail()`

- checks the stack is **empty**:
    - `boolean isEmptyHead()`
    - `boolean isEmptyTail()`

- consult the **top element** without popping it:
    - `int topHead()`
    - `int topTail()`

- other operations necessary for other tasks:
    - `boolean isFull()` checkes whether the `DoubkeStackOfInt` is full.
    - `int headIdx()` and `int tailIdx()` return the index of top element of each stack, respectively.
    - `boolean isSortedDescendinglyHead()` checks whether the elements in head stack are sorted descendingly.



## 2. Implementation

-   Representation



## 3. Define `DoubleStackOfIntOnArray` in Java

Check 



## 4. Provide meaningful test cases



## 5.Sorting algorithm using `DoubleStackOfIntOnArray`



## 6. Time Complexity