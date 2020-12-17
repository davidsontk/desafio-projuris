# Desafio ProJuris - Manutenção de Equipamentos
Sistema para controle e manutenção de equipamentos.

# Tecnologias utilizadas
- Springboot
- H2
- Mockito
- JUnit5

#Exigências
 - [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
 - [Maven](https://maven.apache.org)


# Como executar o Projeto
Um projeto maven e necessário que execute o comando na raiz do projeto. 
Com isso ele irá baixar as dependências do projeto.
 
```sh
 $ mvn clean install
```
Depois de instaladas as dependências. É necessário acessar a pasta target 
e executar o comando.

```sh
 $ java -jar projuris-0.0.1-SNAPSHOT.jar
```
Com isso o projeto irá inicializar na porta 8080.

# Endpoints mapeados no Postman
Está disponível no [link](https://www.postman.com/collections/082faec2ee41c942cca3).
Abra o Postman e va no aba Import > Import From Link, para importar os endpoints mapeados.
