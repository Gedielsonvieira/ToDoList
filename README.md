# Gerenciador de tarefas

> Projeto realizado acompanhando a <a href="https://app.rocketseat.com.br/">RocketSeat</a>

## Aula 01 - Construção back-end de uma aplicação de To-Do List

### Relembrado

- Jar: gera um arquivo executável (conseguimos usar o próprio java para executar esse executável, sem precisar de
  nenhum outro servidor)

- War: mais utilizado para app web, precisa de um servidor para executa-lo

- A pasta .mvn vem no projeto porque escolhemos o maven como o gerenciador de dependencias

- Antes do Spring Boot tinha que ser feito muitas configurações, hoje com o Spring Boot conseguimos ter as dependencias
  que gerenciam isso

### Aprendizado

- Spring boot tem um servidor embarcado chamado tomcat, por isso em uma aplicação que vai ser web conseguimos utilizar
  jar /*Postar dúvida dc sobre jar/war*/

- Ao utilizar a dependencia do Spring Web ele consegue subir a aplicação e fazer as configurações iniciais necessárias

- Dentro do folder resources vamos ter os nossos arquivos estaticos, que serve para criarmos template, páginas HTML com
  Thymeleaf que é uma lib para construir páginas estáticas

- Toda anotação é uma função (Ela tem uma função por tráz dela que o Spring Boot consegue identificar)

- Na controller utilizar @Controller ou @RestController:
    - @Controller: Utilizar quando a aplicação tiver templates HTML
    - @RestController: Utilizar quando está codificando uma API

- Verbo HTTP PATCH: Atualiza somente uma parte do dado

- <p style ="color:red">Spring Boot funciona de modo recursivo, ou seja, primeiro roda o main e a partir dele vai percorrer nos pacotes e
  classes que estão na mesma camada para começar a gerenciar as classes presentes no projeto</p>

## Aula 02 - Integração com Banco de Dados

### Relembrado

- @CreationTimestamp: ao colocar esta anotação em um atributo de uma entidade no momento da instanciação vai ser pego a
  data e hora da criação e vai ser pesistido no BD

- @Column(unique = true): torna o valor passado no atributo como único, ou seja, não tem como inserir o mesmo valor para
  o atributo

## Aula 03 - Implementando segurança nos dados do usuário

### Aprendizado

- Bcrypt: é uma função de hash de senha que podemos utilizar para ter uma maior segurança com a senha do usuário, ao
  salvar a senha no banco de dados vai ser criptografada

- O T que separa as horas da data existe por causa da convenção de uma ISO
    - Ex: yyyy-mm-ddTHH:mm:ss

- @Component: é a anotação de gerenciamento do spring mais genérica

- servlet: é a base para qualquer framework web que temos no java, tudo o que temos de web, spring boot e outros
  framewors tem como base o servlet

- <p style="color: red">O filter do jakarta.servlet ele nos dá somente o servletRequest, servletReposne mas para uma API 
  é melhor utilizar o OncePerRequestFilter, pois fazemos requisições HTTP e o OncePerRequestFilter nos entrega a 
  possibilidade de tratativa de requisições HTTP</p>

- <p style="color: red">Um header HTTP é uma parte da requisição ou resposta HTTP que contém informações sobre a 
  requisição ou resposta.</p>

- Basic auth: é uma das formas de autenticação, no basic auth temos o usuário e a senha para que seja autenticado.
  - Basic auth é codificada usando base64.

## Aula 04 - Atualizando tarefas e validação de rotas