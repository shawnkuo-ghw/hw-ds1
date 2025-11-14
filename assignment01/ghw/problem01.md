# Problem 01: Big-O Simplification

>    Simplify each expression to **Big-O notation**. Justify your answer using the definition of Big-O and/or its properties.
>
>   1.   $𝑂(5𝑛^3 + 3𝑛^2 + 100𝑛 + 1000) + 𝑂(𝑛 \cdot \log{𝑛})$
>   2.   $O(2^n) \cdot O(n^2) + O(3^n)$
>   3.   $𝑂(\log_2{𝑛}) \cdot 𝑂(\sqrt{n}) + 𝑂(𝑛 \cdot \log{n})$
>   4.   $O(n!) + O(2^n) \cdot O(n^3)$

## 1. $𝑂(5𝑛^3 + 3𝑛^2 + 100𝑛 + 1000) + 𝑂(𝑛 \cdot \log{𝑛})$

**Solution.**

-   $𝑂(5𝑛^3 + 3𝑛^2 + 100𝑛 + 1000) = O(n^3)$
-   $\lim\limits_{n\to\infty}\cfrac{n^3}{n\cdot\log{n}} = \infty \implies n^3 >>> 𝑛 \cdot \log{𝑛} $

Therefore, 
$$
𝑂(5𝑛^3 + 3𝑛^2 + 100𝑛 + 1000) + 𝑂(𝑛 \cdot \log{𝑛}) = O(n^3) + 𝑂(𝑛 \cdot \log{𝑛}) = O(n^3).
$$

## 2. $O(2^n) \cdot O(n^2) + O(3^n)$

-   $O(2^n) \cdot O(n^2) = O(2^n \cdot n^2)$

-   $\lim\limits_{n\to\infty}$

## 3. $𝑂(\log_2{𝑛}) \cdot 𝑂(\sqrt{n}) + 𝑂(𝑛 \cdot \log{n})$

## 4. $O(n!) + O(2^n) \cdot O(n^3)$