# CASE-ITAU / DECODIFICAR JWT

Está aplicação foi desenvolvida para validar tokens JWT que é passado nos parâmetros da requisição. O token passa por uma séria de verificações para garantir sua validade. Por fim, no response é indicado se o JWT é válido ou não.

### Tecnologias Utilizadas no Projeto

* [Java](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) + [Spring Boot](https://spring.io/projects/spring-boot)
* [Docker](https://www.docker.com/)
* [Maven](https://maven.apache.org/)

## Dependências e Versões Utilizadas

* JDK 17.0.2
* Apache Maven 3.8.5
* Docker - Versão: 27.4.0
  * Se você utiliza o sistema operacional Windows, clique [aqui](https://www.youtube.com/watch?v=Lgh8JgcYFwM) para ver o turotial de como instalar o docker na sua máquia.

## Como Rodar o Projeto ✅

Para rodar o projeto, primeiro passo é clonar a aplicação em sua máquina, para isso rode o comando:

```
git clone https://github.com/aguiar-thiago/itau-case.git
```

Após clonar o projeto em sua máquina, faça um import na IDE de sua preferência. 

* Se sua IDE for o Spring Tool Suite, certifique-se que o lombok está instalado. Clique [aqui](https://ia-tec-development.medium.com/lombok-como-instalar-na-spring-tool-suite-4-ide-48defb1d0eb9) para ver como instalar.

Em seguida, rode o comando abaixo no terminal (pasta raiz do projeto). Esse comando é do Maven, o mesmo executa todas as etapas do ciclo de vida do projeto, desde a construção até a instalação das suas dependências.

```
mvn clean install
```

Antes de dar um start na aplicação, precisamos subir 2 containers que já estão configurados no arquivo docker-compose.yml, e para isso rode o seguinte comando na raiz do seu projeto.

```
docker-compose up
```
* Nesse momento estamos rodando o [Prometheus](https://prometheus.io/) e o [Grafana](https://grafana.com/) no Docker. Essas ferramentas trabalham em conjunto com a finalidade de monitorar a aplicação e visualizar metricas em dashboards.

Por fim, para iniciarmos a aplicação rode o comando no terminal (pasta raiz do projeto):

```
mvn spring-boot:run
```

Para dar um stop na aplicação, pressione a tecla CRTL + C

## Como rodar os testes
Para rodar os testes unitarios, rodamos o seguinte comando do Maven:

```
mvn clean test
```

## 📌 Endpoint da Aplicação 📌

* Descrição: Valida se o JWT informado atende ou não as regras estabelecidas
* Método HTTP: POST 
* Endpoint: v1/api/validate
* Parâmetros: 
  * Nome: token
  * Tipo: String
  * Descrição: Deve informar o token JWT para saber se é válido ou não


* Resposta: Um valor boolean se é valido ou não

Para realizar os testes na aplicação, acesse o Swagger UI:
```
http://localhost:8080/security/swagger-ui.html
```
⚠️ Lembre-se, a aplicação deve estar rodando para acessar o swagger

## Acessar Dashboard de Monitoração

Após subir os containers no Docker, o dashboard no Grafana já está disponivel. Link:

```
http://localhost:3000/d/OS7-NUiGz/spring-boot-statistics-and-endpoint-metrics
```

Para acessar o grafana:
  * Login: admin
  * Senha: admin

## ⏭️ Próximos passos

A próxima feature a ser desenvolvida é implementar o Tracing de Observalibidade!!
