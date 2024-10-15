# Games API

## 📌 Visão Geral

O projeto se trata de uma API Rest desenvolvida com Java e Spring Boot, contendo as funcionalidades de CRUD de desenvolvedores, consoles e jogos.

<p> <strong> BaseURL: </strong> https://games-api-3nbk.onrender.com </p>

## 🚀 Tecnologias utilizadas

- Java;
- Spring Boot;
- Lombok;
- Jpa Persistance;
- Starter Validation;
- PostgreSQL;
- Flyway;
- Swagger.

## 📚 Documentação

Todos os endpoints da API foram documentados usando Swagger. Para visualizar basta acessar a URL abaixo.

<p>Link: <a href="https://games-api-3nbk.onrender.com/swagger-ui/index.html" target="_blank">https://games-api-3nbk.onrender.com/swagger-ui/index.html</a></p>

## 💻 Instalação, Dependências e Executando o Projeto

Para poder executar a aplicação é necessário ter o java, o maven e o docker instalados.

1. Clone este repositório

```shell
git clone git@github.com:<seu-usuario-github>/games-api.git
```

2. Vá até o diretório raiz do projeto

```shell
cd games-api
```

3. Instalando as dependências da aplicação

```shell
mvn clean install
```

4. Crie os contâiners

```shell
docker-compose up -d
```

5. Execute a aplicação

```shell
mvn spring-boot:run
# O servidor será executado na porta 8080 (http://localhost:8080)
```

## 💻 Testando as requisições

Você pode testar as requisições a partir do Json do Insomnia disponibilizado no seguinte arquivo <a href="./src/main/java/diamond/games/assets/Insomnia.json" target='_blank'>clicando aqui.</a>
