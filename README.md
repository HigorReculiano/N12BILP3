# N12BILP3

<p> </p>

# Interface

# API 

## Rodando o Banco de Dados :wrench:

- Subimos uma instância MYSQL 8.0 no docker: 

```
    docker run --name mysql -e MYSQL_DATABASE=ftt -e MYSQL_ROOT_PASSWORD=docker -p 3306:3306 -d mysql:8.0
```

- Para criar o banco de dados, as tabelas e popular o banco, rode o script **<a href="./api/database/create.sql">create.sql</a>** no diretório database.

