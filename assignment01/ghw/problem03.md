# Problem 03: Palindrome Collector

>   A **Palindrome Collector** is a sequence that can grow or shrink **only at its two ends** (a double-ended queue) and can always tell—**in constant time**—whether the current sequence is a palindrome (reads identically left-to-right and right-to-left).
>
>   After every successful insertion at either end the collector **does NOT rotate**; it simply updates its internal symmetry information.
>
>   Deletions likewise do **NOT** trigger any rearrangement.



## Item a. (5 pts, wet) 

>   (Define a Java interface `Palindrome Collector` of Char with operations `addFirst`, `addLast`, `removeFirst`, `removeLast`, `isPalindrome`, `isEmpty`, `size()`. Document **pre-/post-conditions** in clear English **without revealing any internal representation**.
