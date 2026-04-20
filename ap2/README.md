### Para executar o projeto siga os passos abaixo:

```bash
cd ap2/
mvn spring-boot:run
```

O arquivo JSON de entrada lido pelo programa se encontra em **ap2/input.json**

O arquivo CSV de saída produzido pela aplicação é escrito em **ap2/output.csv**

O arquivo CSV com todos os logs da aplicação se encontra em **ap2/log.csv**

---
### 4. Desenhe os objetos criados e as dependências entre eles. (formato JPG ou PNG). Pode usar o google draw  e usar qualquer padrão de documentação. Pode ser UML, mas pode ser informal.

O diagrama UML com as dependências da aplicação se encontra em **"ap2/E2 Dependencias.png"**

---
### 5. Explique como seria fazer um Spring? Como um HashMap pode ajudar? Máximo 1 página fonte 12pt.

Para desenvolver uma versão simplificada do Spring, seria necessário implementar um mecanismo de Inversão de Controle (IoC). Inicialmente realizaremos uma profunda análise do código-fonte Java a fim de identificar todos as classes **@Component** existentes e suas dependências. Essa análise pode ser feita de diferentes formas, utilizando o código-fonte, ASTs, bytecote ou classpath. Utilizar um HashMap seria muito proveitoso neste momento, pois permitiria referenciar rapidamente cada um dos componentes de forma unívoca.

O resultado dessa análise de código-fonte seria um grafo direcionado cujos vértices seriam os componentes do sistema e as arestas as dependências entre eles. Esta estrutura de dados seria utilizada durante a execução da aplicação para saber como construir os objetos, como destruí-los e quando injetar dependências corretamente em tempo de execução. 
