# Gestão Obra

API desenvolvida em Java com o objetivo de gerenciar dados de obras e sua respectiva gestão administrativa e operacional.

## 📋 Descrição

Essa API manipula os dados relacionados a obras, incluindo:

- Cadastro de obras com seus respectivos endereços
- Gerenciamento de funcionários
- Controle manual de presença diária dos funcionários
- Registro de empreitadas por funcionário em dias específicos
- Gestão de receitas e despesas da obra
- Pagamentos baseados em presenças e empreitadas
- Geração de relatórios por data

## 🛠 Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Maven

## ▶️ Como executar o projeto

Certifique-se de ter o **MySQL** em execução e configurado corretamente.

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/gestao-obra.git
   ```

2. Acesse o diretório do projeto:
   ```bash
   cd gestao-obra
   ```

3. Configure o arquivo `application.properties` com os dados do seu banco MySQL:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_banco
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```

4. Execute o projeto com Maven:
   ```bash
   ./mvnw spring-boot:run
   ```
   ou
   ```bash
   mvn spring-boot:run
   ```

## 📁 Estrutura de Endpoints

Todas as rotas da API seguem o padrão:

```
/api/{nome-da-entidade}
```

Para mais informações e testes dos endpoints, acesse a documentação gerada automaticamente pelo Swagger:

📌 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---