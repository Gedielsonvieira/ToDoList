# Gerenciador de tarefas
> Projeto realizado acompanhando a <a href="https://app.rocketseat.com.br/">RocketSeat</a>

## Aula 01 - Construção back-end de uma aplicação de To-Do List

### Relembrando

- Jar: gera um arquivo executável (conseguimos usar o próprio java para executaer esse executavel, sem preceisar de
  nenhum outro servidor)

- War: mais utilizado para app web, precisa de um servidor para executa-lo

- A pasta .mvn vem no projeto porque escolhemos o maven como o gerenciador de dependencias

- Antes do Spring Boot tinha que ser feito muitas configurações, hoje com o Spring Boot conseguimos ter as dependencias
  que gerenciam isso

### Aprendizado

- Spring boot tem um servidor embarcado chamado tomcat, por isso em uma aplicação que vai ser web conseguimos utilizar
  jar

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