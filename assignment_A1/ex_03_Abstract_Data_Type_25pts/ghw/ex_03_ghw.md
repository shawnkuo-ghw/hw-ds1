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



## 2. Implementation of `DoubleStackOfInt`

Let $Q$ denote the abstract data type of **Queue**.

-   **Abstract Data Type**: `DoubleStackOfInt`, $DS = (Q_1, Q_2)$

-   **Representation**: 
    $$
    R = <S: \text{array},\ head: \text{int},\ tail: \text{int}>
    $$

-   **Representation Invariant**: 
    $$
    RI(R) = (R\ \text{is acyclic})\ 
    \and\ (0 \leq head \leq |R.S|)\ 
    \and\ (1 \leq tail \leq |R.S| + 1)\ 
    \and\ (head < tail)
    $$

-   **Abstraction Function**: 
    $$
    AF(R) = DS \iff \\
    \big(head = 0\ \or\ R.S\big[0\ldots R.head \big] = Q_1 \big)\ 
    \and\
    \big(tail=|R.S| + 1\ \or\ R.S.\big[R.tail\ldots |R.S|\big] = Q_2 \big)
    $$

-   



## 3. Define `DoubleStackOfIntOnArray` in Java

Check file `DoubleStackOfIntOnArray.java`.



## 4. Provide meaningful test cases

Check file `DoubleStackOfIntTest.java`.



## 5.Sorting algorithm using `DoubleStackOfIntOnArray`

Check files `SortingOverDoubleStack.java` and `SortingOverDoubleStackTest.java`.



## 6. Time Complexity