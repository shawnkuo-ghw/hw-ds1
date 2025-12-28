>   2.   [10 pts] Hash Tables and Collisions (Dry)
>
>        **a.** [5 pts] We want to insert keys into a Hash Table of size $m = 11$ using the hash function $h(k) = k \mod 11$.
>
>        • Insert the keys: 12, 25, 45, 15, 1, 23, 95.
>
>        • Show the table content using **Open Addressing with Linear Probing**.
>
>        • Show the table content using **Open Addressing with Double Hashing**, where $h_2(k) = 1 + (k \mod 10)$.
>
>        **b.** [5 pts] Prove that in a hash table with chaining (close addressing), under the assumption of simple uniform hashing, the expected time for an unsuccessful search is $O(1 + \alpha)$, where $\alpha$ is the load factor.

## item a.

-   insert keys: 12, 25, 45, 15, 1, 23, 95

###  a.1. Open Addressing with Linear Probing

|   Hash Function    |          Probing Function          |
| :----------------: | :--------------------------------: |
| $h(k) = k \mod 11$ | $H(k, i) = (\ h(k) + i\ ) \mod 11$ |


#### (1) Insert: 12

-   $H(12, 0) = (\ h(12) + 0\ ) \mod 11 = 1 \mod 11 = 1$

| Index | Keys |
| :---: | :--: |
|   0   |      |
|   1   |  12  |
|   2   |      |
|   3   |      |
|   4   |      |
|   5   |      |
|   6   |      |
|   7   |      |
|   8   |      |
|   9   |      |
|  10   |      |

#### (2) Insert: 25

-   $H(25, 0) = (\ h(25) + 0\ ) \mod 11 = 3 \mod 11 = 3$

| Index | Keys |
| :---: | :--: |
|   0   |      |
|   1   |  12  |
|   2   |      |
|   3   |  25  |
|   4   |      |
|   5   |      |
|   6   |      |
|   7   |      |
|   8   |      |
|   9   |      |
|  10   |      |

#### (3) Insert: 45

-   $H(45, 0) = (\ h(45) + 0\ ) \mod 11 = 1 \mod 11 = 1$
-   $H(45, 1) = (\ h(45) + 1\ ) \mod 11 = 2 \mod 11 = 2$

| Index | Keys |
| :---: | :--: |
|   0   |      |
|   1   |  12  |
|   2   |  45  |
|   3   |  25  |
|   4   |      |
|   5   |      |
|   6   |      |
|   7   |      |
|   8   |      |
|   9   |      |
|  10   |      |

#### (4) Insert: 15

-   $H(15, 0) = (\ h(15) + 0\ ) \mod 11 = 4 \mod 11 = 4$

| Index | Keys |
| :---: | :--: |
|   0   |      |
|   1   |  12  |
|   2   |  45  |
|   3   |  25  |
|   4   |  15  |
|   5   |      |
|   6   |      |
|   7   |      |
|   8   |      |
|   9   |      |
|  10   |      |

#### (5) Insert: 1

-   $H(1, 0) = (\ h(1) + 0\ ) \mod 11 = 1 \mod 11 = 1$
-   $H(1, 1) = (\ h(1) + 1\ ) \mod 11 = 2 \mod 11 = 2$
-   $H(1, 2) = (\ h(1) + 2\ ) \mod 11 = 3 \mod 11 = 3$
-   $H(1, 3) = (\ h(1) + 3\ ) \mod 11 = 4 \mod 11 = 4$
-   $H(1, 4) = (\ h(1) + 4\ ) \mod 11 = 5 \mod 11 = 5$

| Index | Keys |
| :---: | :--: |
|   0   |      |
|   1   |  12  |
|   2   |  45  |
|   3   |  25  |
|   4   |  15  |
|   5   |  1   |
|   6   |      |
|   7   |      |
|   8   |      |
|   9   |      |
|  10   |      |

#### (6) Insert: 23

-   $H(23, 0) = (\ h(23) + 0\ ) \mod 11 = 1 \mod 11 = 1$
-   $H(23, 1) = (\ h(23) + 1\ ) \mod 11 = 2 \mod 11 = 2$
-   $H(23, 2) = (\ h(23) + 2\ ) \mod 11 = 3 \mod 11 = 3$
-   $H(23, 3) = (\ h(23) + 3\ ) \mod 11 = 4 \mod 11 = 4$
-   $H(23, 4) = (\ h(23) + 4\ ) \mod 11 = 5 \mod 11 = 5$
-   $H(23, 5) = (\ h(23) + 5\ ) \mod 11 = 6 \mod 11 = 6$

| Index | Keys |
| :---: | :--: |
|   0   |      |
|   1   |  12  |
|   2   |  45  |
|   3   |  25  |
|   4   |  15  |
|   5   |  1   |
|   6   |  23  |
|   7   |      |
|   8   |      |
|   9   |      |
|  10   |      |

#### (7) Insert: 95

-   $H(95, 0) = (\ h(95) + 0\ ) \mod 11 = 7 \mod 11 = 7$

| Index | Keys |
| :---: | :--: |
|   0   |      |
|   1   |  12  |
|   2   |  45  |
|   3   |  25  |
|   4   |  15  |
|   5   |  1   |
|   6   |  23  |
|   7   |  95  |
|   8   |      |
|   9   |      |
|  10   |      |



### a.2. Open Addressing with Double Hashing

|   Hash Function    |       Aux Hash Function        |                Probing Function                 |
| :----------------: | :----------------------------: | :---------------------------------------------: |
| $h(k) = k \mod 11$ | $h_2(k) = 1 + (\ k \mod 10\ )$ | $H(k, i) = (\ h(k) + i \cdot h_2(k)\ ) \mod 11$ |

#### (1) Insert: 12

-   $H(12, 0) = \left(\ h(12) + 0 \cdot h_2(12)\ \right) \mod 11 = h(12) = 1$

| Index | Keys |
| :---: | :--: |
|   0   |      |
|   1   |  12  |
|   2   |      |
|   3   |      |
|   4   |      |
|   5   |      |
|   6   |      |
|   7   |      |
|   8   |      |
|   9   |      |
|  10   |      |

#### (2) Insert: 25

-   $H(25, 0) = \left(\ h(25) + 0 \cdot h_2(25)\ \right) \mod 11 = h(25) = 3$

| Index | Keys |
| :---: | :--: |
|   0   |      |
|   1   |  12  |
|   2   |      |
|   3   |  25  |
|   4   |      |
|   5   |      |
|   6   |      |
|   7   |      |
|   8   |      |
|   9   |      |
|  10   |      |

#### (3) Insert: 45

-   $H(45, 0) = \left(\ h(45) + 0 \cdot h_2(45)\ \right) \mod 11 = h(45) = 1$
-   $H(45, 1) 
    = (\ h(45) + 1 \cdot h_2(45)\ ) \mod 11 
    = (\ 1 + 6\ ) \mod 11 
    = 7$

| Index | Keys |
| :---: | :--: |
|   0   |      |
|   1   |  12  |
|   2   |      |
|   3   |  25  |
|   4   |      |
|   5   |      |
|   6   |      |
|   7   |  45  |
|   8   |      |
|   9   |      |
|  10   |      |

#### (4) Insert: 15

-   $H(15, 0) = (\ h(15) + 0 \cdot h_2(15)\ ) \mod 11 = h(15) = 4$

| Index | Keys |
| :---: | :--: |
|   0   |      |
|   1   |  12  |
|   2   |      |
|   3   |  25  |
|   4   |  15  |
|   5   |      |
|   6   |      |
|   7   |  45  |
|   8   |      |
|   9   |      |
|  10   |      |

#### (5) Insert: 1

-   $H(1, 0) = (\ h(1) + 0 \cdot h_2(1)\ ) \mod 11 = h(1) = 1$
-   $H(1, 1) = (\ h(1) + 1 \cdot h_2(1)\ ) \mod 11 = (\ 1 + 2\ ) \mod 11 = 3$
-   $H(1, 2) = (\ h(1) + 2 \cdot h_2(1)\ ) \mod 11 = (\ 1 + 2 \cdot 2\ ) \mod 11 = 5$

| Index | Keys |
| :---: | :--: |
|   0   |      |
|   1   |  12  |
|   2   |      |
|   3   |  25  |
|   4   |  15  |
|   5   |  1   |
|   6   |      |
|   7   |  45  |
|   8   |      |
|   9   |      |
|  10   |      |

#### (6) Insert: 23

-   $H(23, 0) = (\ h(23) + 0 \cdot h_2(23)\ ) \mod 11 = h(23) = 1$
-   $H(23, 1) = (\ h(23) + 1 \cdot h_2(23)\ ) \mod 11 = (\ 1+4\ ) \mod 11 = 5$
-   $H(23, 2) = (\ h(23) + 2 \cdot h_2(23)\ ) \mod 11 = (\ 1+ 2 \cdot 4\ ) \mod 11 = 9$

| Index | Keys |
| :---: | :--: |
|   0   |      |
|   1   |  12  |
|   2   |      |
|   3   |  25  |
|   4   |  15  |
|   5   |  1   |
|   6   |      |
|   7   |  45  |
|   8   |      |
|   9   |  23  |
|  10   |      |

#### (7) Insert: 95

-   $H(95, 0) = (\ h(95) + 0 \cdot h_2(95)\ ) \mod 11 = h(95) = 7$
-   $H(95, 1) = (\ h(95) + 1 \cdot h_2(95)\ ) \mod 11 = (\ 7 + 6\ ) \mod 11 = 2$

| Index | Keys |
| :---: | :--: |
|   0   |      |
|   1   |  12  |
|   2   |  95  |
|   3   |  25  |
|   4   |  15  |
|   5   |  1   |
|   6   |      |
|   7   |  45  |
|   8   |      |
|   9   |  23  |
|  10   |      |

## item b.

>   Prove that in a hash table with chaining (close addressing), under the assumption of simple uniform hashing, the expected time for an unsuccessful search is $O(1 + \alpha)$, where $\alpha$ is the load factor.

$ Proof.$

In an unsuccessful search:

-   we first compute the hash value, which takes constant time, i.e. $O(1)$,
-   then we iterate all elements stored in the list associated with that hash value.

Therefore, the expected time of an unsuccessful search is the sum of the expected time of computing hash value and the expected time of iterating elements of list of an randomly chosen slot, i.e.
$$
\begin{align}
E[\ T(\text{unsuccessful search})\ ] 
& = E[\ T(\text{computing hash})\ ] + E[\ T(\text{iterating elements})\ ] \\
& = O(1) + E[\ T(\text{iterating elements})\ ].
\end{align}
$$
Let $n$ be the number of all elements stored in the hash table and $m$ be the number of slots. Under the assumption of **simple unifom hashing**, the expected value of elements stored in an random slot is
$$
E[\ \text{number of elements}\ ] = \frac{n}{m} := \alpha,
$$
which also equals to the load factor $\alpha := \frac{n}{m}$. Suppose the expected time of scaning each element is $E[\ T(\text{scaning each element})\ ] = c$. Therefore,

\[
\begin{align}
E[\ T(\text{iterating elements})\ ]
& = E[\ T(\text{scaning each element})\ ] \cdot E[\ \text{number of elements}\ ] \\
& = c\ \cdot \alpha \\
& = O(\alpha).
\end{align}
\]
Overall, the expected time for an unsuccessful search is
\[
\begin{align}
E[\ T(\text{unsuccessful search})\ ]
& = O(1) + E[\ T(\text{iterating elements})\ ] \\
& = O(1) + O(\alpha) \\
& = O(1 + \alpha).
\end{align}
\]

<p style="text-align: right;">⬛︎ </p>

