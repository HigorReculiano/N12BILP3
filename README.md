<h2 align="center">
  <a href="http://ftt.com.br/">
    <img alt="FTT Logo" src="https://upload.wikimedia.org/wikipedia/commons/1/12/Logo_ftt.png" width="150px"/>
  </a>
</h2>

<p align="center"> Projeto realizado para atender aos requisitos da N1 do segundo bimestre da disciplina de Linguagem de Programção 3</p>

# Interface

## Rodando o frontend :scroll:

- Execute **`npm ci`** dentro da pasta **<a href="./interface">interface</a>** para isntalar as dependências
- E para rodar o frontend **`npm run start`**
# API 
## Rodando o Banco de Dados :wrench:

- Subimos uma instância MYSQL 8.0 no docker: 

```
    docker run --name mysql -e MYSQL_DATABASE=ftt -e MYSQL_ROOT_PASSWORD=docker -p 3306:3306 -d mysql:8.0
```

- Para criar o banco de dados, as tabelas e popular o banco, rode o script **<a href="./api/database/create.sql">create.sql</a>** no diretório database.

