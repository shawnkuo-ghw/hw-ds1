# Problem 01: Big-O Simplification

>    Simplify each expression to **Big-O notation**. Justify your answer using the definition of Big-O and/or its properties.
>
>    1.   $𝑂(5𝑛^3 + 3𝑛^2 + 100𝑛 + 1000) + 𝑂(𝑛 \cdot \log{𝑛})$
>    2.   $O(2^n) \cdot O(n^2) + O(3^n)$
>    3.   $𝑂(\log_2{𝑛}) \cdot 𝑂(\sqrt{n}) + 𝑂(𝑛 \cdot \log{n})$
>    4.   $O(n!) + O(2^n) \cdot O(n^3)$

## 1. $𝑂(5𝑛^3 + 3𝑛^2 + 100𝑛 + 1000) + 𝑂(𝑛 \cdot \log{𝑛})$

Firstly,
$$
𝑂(5𝑛^3 + 3𝑛^2 + 100𝑛 + 1000) = O(n^3).
$$
Moreover,
$$
\lim\limits_{n\to\infty}\frac{n^3}{n\cdot\log{n}} = \infty \implies n^3 \ggg 𝑛 \cdot \log{𝑛}.
$$

Therefore, 

$$
𝑂(5𝑛^3 + 3𝑛^2 + 100𝑛 + 1000) + 𝑂(𝑛 \cdot \log{𝑛}) = O(n^3) + 𝑂(𝑛 \cdot \log{𝑛}) = O(n^3).
$$

## 2. $O(2^n) \cdot O(n^2) + O(3^n)$

Firstly,
$$
O(2^n) \cdot O(n^2) = O(2^n \cdot n^2).
$$
Moreover,
$$
\lim\limits_{n\to\infty}\frac{3^n}{2^n\cdot n^2} = \lim\limits\frac{\left( \frac{3}{2}\right)^n}{n^2} = \infty \implies 3^n \ggg 2^n \cdot n^2.
$$


Therefore,
$$
O(2^n) \cdot O(n^2) + O(3^n) = O(2^n \cdot n^2) + O(3^n) = O(3^n).
$$

## 3. $𝑂(\log_2{𝑛}) \cdot 𝑂(\sqrt{n}) + 𝑂(𝑛 \cdot \log{n})$

Firstly,
$$
𝑂(\log_2{𝑛}) \cdot 𝑂(\sqrt{n}) = 𝑂(\sqrt{n} \cdot \log_2{𝑛}).
$$
Moreover,
$$
\lim\limits_{n\to\infty}\frac{𝑛 \cdot \log{n}}{\sqrt{n} \cdot \log_2{𝑛}} = \lim\limits_{n\to\infty} \sqrt{n} = \infty \implies  𝑛 \cdot \log{n} \ggg \sqrt{n} \cdot \log_2{𝑛}.
$$
Therefore,
$$
𝑂(\log_2{𝑛}) \cdot 𝑂(\sqrt{n}) + 𝑂(𝑛 \cdot \log{n}) = 𝑂(\sqrt{n} \cdot \log_2{𝑛}) + 𝑂(𝑛 \cdot \log{n}) = 𝑂(𝑛 \cdot \log{n}).
$$

## 4. $O(n!) + O(2^n) \cdot O(n^3)$

Firstly,
$$
O(2^n) \cdot O(n^3) = O(2^n \cdot n^3).
$$
Moreover,
$$
\lim\limits_{n\to\infty}\frac{n!}{2^n \cdot n^3} = \infty \implies n! \ggg 2^n \cdot n^3.
$$
Therefore,
$$
O(n!) + O(2^n) \cdot O(n^3) = O(n!) + O(2^n \cdot n^3) = O(n!).
$$