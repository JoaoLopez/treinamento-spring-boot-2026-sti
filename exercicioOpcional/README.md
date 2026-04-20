### Para executar o projeto siga os passos abaixo:

```bash
cd exercicioOpcional/
mvn compile
mvn spring-boot:run
```

### 1. Faça uma anotação de método que abra um arquivo CSV especificado como argumento da anotação. Um outro bean terá o método “gravarNota” que grava informações passadas no CSV aberto. Trate a situação de anotações alinhadas.

A anotação e o aspecto responsáveis por abrir um arquivo CSV foram implementados em **UsarArquivo.java** e **UsarArquivoAspect.java**.

A aplicação desenvolvida salva as notas dos alunos no arquivo **alunos.csv**.

Também são salvos logs do sistema no arquivo **log.csv**.

Gerei uma situação de anotações alinhadas ao fazer log do método **AlunoService.salvarAlunos**. Para salvar o log, abrimos o arquivo **log.csv** via **@UsarArquivo**. Durante sua execução o método **AlunoService.salvarAlunos** chama o método **AlunoService.gravarNota** que abre o arquivo **alunos.csv** também através de **@UsarArquivo** para salvar a nota de cada aluno.
