# SmartCities

**Nome dos alunos** 

**Murilo Pereira - 84319** | **Bruno Brito - 86523** | **Gustavo Dalla - 86066** | **Bruno Ermacora - 86066**  

**Turma:** **2TDSF**

**Ano:** **2021**

## Objetivo / Descrição do Projeto

Projeto desenvolvido com intuito de conectar grandes cidades, para ler uma variadade de processos e sensores integrando para facilitar a vida e automatizar processos. A integração pode ser feita de diversas formas e a automatização é integrada com outras plataformas, seguindo um fluxo de dados baseados em sensores e integração com APIs.
Existem diversas ferramentas para integração e diversas formas de implementar, vai do jeito do desenvolvedor de acordo com sua espectativa e processo que seguem de acordo com uma estrutura e regras.

## Diagrama do projeto

**Essa é a imagem do NODE-RED plataforma usada para integração entre MQTT e TWITTER**

<img src="/diagrama.png" width="550">


## Como usar 

* Primeiramente baixe o projeto, entre na pasta raiz. Use sua IDE preferida para rodar o projeto, Inicie a aplicação Spring.
* Rode o seu servidor MQTT e passe suas informações no corpo do projeto MQTT


* Toda aplicação gira em torno do NODE-RED e MQTT usando Java para fazer a transição de informações entre o banco de dados e o consumo de APIs


```
mvn package -P SmartCities 
mvn clean install -U -P SmartCities 
mvn clean package
mvn clean install
java -jar SmartCities-1.0-SNAPSHOT.jar
cls
```


## Funcionalidades

:heavy_check_mark: Verificação de temperatura  

:heavy_check_mark: Integração de API do Twitter 

:heavy_check_mark: MQTT funcicional

:heavy_check_mark: NODE-RED rodando em background


## Pré-requisitos

:warning: [JDK 11](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html)

:warning: [Node](https://nodejs.org/en/download/)

##

Pode adicionar algum trecho de código, por exemplo para clonar esse repositório:

    cd /home/iot
    git clone https://github.com/guhdalla/SmartCities
    cd SmartCities
    ls
  

## Link de vídeo demonstração

[Link para o video youtube](https://www.youtube.com/watch?v=_U5KF7totG0)


### Referências 

* [Documentação node-red Twitter](https://flows.nodered.org/node/node-red-node-twitter)
* [Exemplo BOT](https://github.com/rinaldodev/exemplo-bot-twitter)
* [Repositorio integração Twitter e Java](https://github.com/redouane59/twittered)
* [Documentação como usar APIs](https://medium.com/programadores-ajudando-programadores/api-do-twitter-criando-o-app-e-obtendo-os-tokens-28ef3e2a281c)
* [Documentação MQTT](https://developer.ibm.com/br/articles/iot-mqtt-why-good-for-iot/)
* [Video exemplo anemometro](https://www.youtube.com/watch?v=tGQ_5120qAs)
* [mastering-markdown](https://guides.github.com/features/mastering-markdown/)
* [Basic writing and formatting syntax](https://docs.github.com/en/github/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax)
