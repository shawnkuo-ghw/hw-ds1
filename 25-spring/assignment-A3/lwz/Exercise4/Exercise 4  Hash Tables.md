### Exercise 4 : Hash Tables

#### Task 1: Open addressing with linear probing

​	[code in the wet part : `HashFuncComp`]

Here's the output we get :

```sql
HashFunction      SuccessProbes      UnscsProbes     LargestCluster 
hash1                1.941             4.648              19.826
hash2                1.933             4.651              19.798
hash3                1.938             4.646              19.852
hashBest             1.904             4.460              19.374
hashMul              1.906             4.492              19.310
hashDiv              1.894             4.415              19.053
```

We find that the discrepancies between the function values are not statistically significant, which may be attributed to the low **load factor**(67%) that is unlikely to induce catastrophic clustering.

#### Task 2: Hashing with Overflow Area

​	[code in the wet part : `OverflowHash`]