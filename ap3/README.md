### Para executar o projeto siga os passos abaixo:

```bash
cd ap3/
mvn spring-boot:run
```

Os exercícios 1 a 5 foram implementados no código-fonte do projeto. Ao executar a aplicação, os outputs desses exercícios são impressos no terminal separadamente.

Foi necessário modificar o tipo de dados da coluna **ap3.post.data_postagem** de **DATE** para **TIMESTAMP**, pois a classe **Post** utiliza o tipo **LocalDateTime**. Assim, no momento de recuperar as informações do banco de dados ocorria erro de conversão de tipos.

---
### 6. Relacionamentos 1-1 ou 1-N podem ser implementados como Referências a outras entidades ou Back Reference. 
### Suponha o exemplo: Uma pessoa possui uma Nacionalidade . A função pode ser um conjunto de poucos elementos bem definido (países ~ 200) que quase nunca muda.
### Para poder exibir a nacionalidade de uma lista de pessoas, é necessário obter as pessoas da base e consultar sua nacionalidade em seguida. Comente pelo menos duas de resolver este problema. Máximo 1 página.

O problema descrito é conhecido como problema das consultas N+1. Neste caso, ele ocorre se realizarmos uma consulta "SELECT" para buscar a nacionalidade de cada pessoa individualmente. Esse problema pode prejudicar consideravelmente o desempenho da aplicação.

Duas formas possíveis de contorná-lo são: 

1. **Fazendo cache de todas as nacionalidades:** Como o conjunto de nacionalidades não é muito grande (aproximadamente 200 itens), nos sistemas em que esse conjunto não é alterado com frequência, é possível carregar todas as nacionalidades para a memória RAM ao iniciar o spring ou a cada hora. Dessa forma, após realizarmos a consulta SQL que recupera as pessoas, podemos acessar diretamente suas nacionalidades via RAM, sem novas consultas ao banco de dados.

2. **Realizar a consulta às pessoas fazendo JOIN com a tabela de Nacionalidade**: Dessa forma uma única consulta ao banco de dados já retornaria todas as informações necessárias para a aplicação. Além disso, em cenários em que a **Nacionalidade** pode mudar essa solução garante que sempre será exibida para o usuário o valor mais atualizado.
