### Problem 4
[10p] Given a parameter i,
1. Provide the pseudocode of an algorithm that generates the ith Fibonacci tree.
    ```
    FibonacciTree(int i)
        if(i == 0) return null
        else if(i == 1)
            rootNode = new node()
            rootNode.left = null
            rootNode.right = null
            return rootNode
        else 
            rootNode = new node()
            rootNode.left = FibonacciTree(i - 1)
            rootNode.right = FibonacciTree(i - 2)
            return rootNode
    ```