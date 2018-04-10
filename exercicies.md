# Exploração Lunar 

O veículo lunar Fritz foi lançado para a Lua pela Agência Espacial Joinvilense .
O Fritz decide de maneira autônoma o caminho que irá realizar para explorar a superfície lunar. 


 ![alt text](/assets/rover_lunar.png)

Os possíveis caminhos que Fritz pode percorrer são gerados nos laboratórios de computação científica da Agência através de reconhecimento de imagens via satélite. Essas imagens são analisadas com o objetivo de identificar terrenos livres e obstáculos gerando um grafo. A partir desse grafo, é possível identificar caminhos viáveis naquela região.


Seu trabalho é identificar os caminhos viáveis que devem ser enviados para o Fritz.


## Entrada

A entrada é composta por diversos casos testes e termina com o fim de arquivo. Cada teste descreve um grafo e começa com
um inteiro **V**(1 ≤  **V** ≤  1000) que representa a quantidade de vértices. Nas próximas **V** linhas descreve a matriz de adjacencia do grafo. Na diagonal principal é descrito o nome de cada vértice.
A seguir **N** linhas apresentam um possível caminho de **S** até **C**.

Entre casos de testes consecutivos, haverá uma linha em branco. 


```
4
1 . * *
* 2 . *
* * 3 .
* * * 4
1 2
2 1
3 2

4
6 * . *
* y . *
* . % .
* * * &
& 6
```

**Grafo 1** 
 ![alt text](/assets/grafo_03.png)


**Grafo 2** 
 ![alt text](/assets/grafo_04.png)

## Saída

A saída deve consistir de uma linha para cada caso de teste com a sequência de caracter ! ou *.

! significa que o caminho é inválido. * significa que o caminho é válido.

```
* ! !
!
```
