#include "DHT.h"
#include <WiFi.h>
#include <PubSubClient.h>

#define pinoDHT   12 // pino onde o sensor DHT está conectado
#define pinoLDR   34 // pino onde o LDR está conectado
#define anenometro 15

//WiFi
const char* SSID = "BIN 2.4";                // SSID / nome da rede WiFi que deseja se conectar
const char* PASSWORD = "********";   // Senha da rede WiFi que deseja se conectar
WiFiClient wifiClient;

//MQTT Server
const char* BROKER_MQTT = "broker.hivemq.com"; //URL do broker MQTT que se deseja utilizar
int BROKER_PORT = 1883;                      // Porta do Broker MQTT

#define ID_MQTT  "clientId-kIXFqW4TAM"            //Informe um ID unico e seu. Caso sejam usados IDs repetidos a ultima conexão irá sobrepor a anterior. 
#define TOPIC_PUB_TEMP "bgmbnewgen8462/smartcities/temperatura" //Informe um Tópico único. Caso sejam usados tópicos em duplicidade, o último irá eliminar o anterior.
#define TOPIC_PUB_LUZ "bgmbnewgen8462/smartcities/luminosidade"
#define TOPIC_PUB_VENTO "bgmbnewgen8462/smartcities/velocidadevento"
PubSubClient MQTT(wifiClient);        // Instancia o Cliente MQTT passando o objeto espClient

//Declaração das Funções
void mantemConexoes();  //Garante que as conexoes com WiFi e MQTT Broker se mantenham ativas
void conectaWiFi();     //Faz conexão com WiFi
void conectaMQTT();     //Faz conexão com Broker MQTT
void enviaPacote();     //

unsigned long tempo2, tempoLdr, tempo, tempoMQ = millis();

// --- Constantes ---
const float pi = 3.14159265;     //Número de pi
int period = 5000;               //Tempo de medida(miliseconds)
int delaytime = 2000;            //Invervalo entre as amostras (miliseconds)
int radius = 180;                //Raio do anemometro(mm)

// --- Variáveis Globais ---
unsigned int Sample  = 0;        //Armazena o número de amostras
unsigned int counter = 0;        //Contador para o sensor
unsigned int RPM = 0;            //Rotações por minuto
float speedwind = 0;             //Velocidade do vento (m/s)
float windspeed = 0;             //Velocidade do vento (km/h)


int leitura = 0; // variável para armazenar o valor lido pelo ADC
float Vout = 0.0; // variável para armazenar a tensão
float Vcc = 5.0; // variavel 5V
float RLDR = 0.0; // variavel de resistência (Ω) do LDR
float R1 = 10000; // variavel de resistência de 10 kΩ do resistor em série com o LDR
float L = 0.0; // variavel de lux
float Rdark = 127410.0; // variavel de resistência (Ω) do LDR no escuro
float a = 0.77; // variavel constante (Ω /lux)
float temperatura = 0; // variavel da temperatura

DHT dht(pinoDHT, DHT11); // define o pino e o tipo de DHT

void setup() {
  Serial.begin(115200);
  pinMode(anenometro, INPUT);
  pinMode(pinoLDR, INPUT); // configura o pino com LDR como entrada
  dht.begin(); // inicializa o sensor DHT
  conectaWiFi();
  MQTT.setServer(BROKER_MQTT, BROKER_PORT);
}

void loop() {
  calculaLuz();
  leTemperatura();
  if ((millis() - tempoMQ) > 15000) {
    tempoMQ = millis();
    leVento();
    enviaValores(L, temperatura, windspeed);
  }
  mantemConexoes();
  MQTT.loop();
}

void calculaLuz() {
  leitura = analogRead(pinoLDR);
  // converte e imprime o valor em tensão elétrica
  Vout = leitura * 5.0 / 4095.0;
  RLDR = R1 * (Vcc / Vout - 1);
  L = pow(Rdark / RLDR, 1 / a);

  // le o valor de tensão no pino do LDR
  Serial.print(String("L: "));
  Serial.print(L);
  Serial.print("\t");
  Serial.print(String("Leitura: "));
  Serial.print(leitura);
  Serial.print("\t");
  Serial.println(); // nova linha
}

void leTemperatura() {
  temperatura = dht.readTemperature(); // lê a temperatura em Celsius
  Serial.print(String("Temperatura: "));
  Serial.print(temperatura);
  Serial.print("\t");
  Serial.println();
}

//Função para medir velocidade do vento
void leVento() {
  Sample++;
  Serial.print(Sample);
  Serial.print(": Inicia Leitura...");
  windvelocity();
  Serial.println("   Finalizado.");
  Serial.print("Contador: ");
  Serial.print(counter);
  Serial.print(";  RPM: ");
  RPMcalc();
  Serial.print(RPM);
  Serial.print(";  Vel. Vento: ");

  //*****************************************************************
  //print m/s
  WindSpeed();
  Serial.print(windspeed);
  Serial.print(" [m/s] ");

  //*****************************************************************
  //print km/h
  SpeedWind();
  Serial.print(speedwind);
  Serial.print(" [km/h] ");
  Serial.println();
}

void windvelocity() {
  speedwind = 0;
  windspeed = 0;

  counter = 0;
  attachInterrupt(anenometro, interrupcao, RISING);
  unsigned long millis();
  long startTime = millis();
  while (millis() < startTime + period) {}
}

//Função para calcular o RPM
void RPMcalc() {
  RPM = ((counter) * 60) / (period / 1000); // Calculate revolutions per minute (RPM)
}

//Velocidade do vento em m/s
void WindSpeed() {
  windspeed = ((2 * pi * radius * RPM) / 60) / 1000; //Calcula a velocidade do vento em m/s
} //end WindSpeed

//Velocidade do vento em km/h
void SpeedWind() {
  speedwind = (((2 * pi * radius * RPM) / 60) / 1000) * 3.6; //Calcula velocidade do vento em km/h
} //end SpeedWind

void interrupcao()
{
  counter++;
}

void mantemConexoes() {
  if (!MQTT.connected()) {
    conectaMQTT();
  }

  conectaWiFi(); //se não há conexão com o WiFI, a conexão é refeita
}

void conectaWiFi() {

  if (WiFi.status() == WL_CONNECTED) {
    return;
  }

  Serial.print("Conectando-se na rede: ");
  Serial.print(SSID);
  Serial.println("  Aguarde!");

  WiFi.begin(SSID, PASSWORD); // Conecta na rede WI-FI
  while (WiFi.status() != WL_CONNECTED) {
    delay(100);
    Serial.print(".");
  }

  Serial.println();
  Serial.print("Conectado com sucesso, na rede: ");
  Serial.print(SSID);
  Serial.print("  IP obtido: ");
  Serial.println(WiFi.localIP());
}

void conectaMQTT() {
  while (!MQTT.connected()) {
    Serial.print("Conectando ao Broker MQTT: ");
    Serial.println(BROKER_MQTT);
    if (MQTT.connect(ID_MQTT)) {
      Serial.println("Conectado ao Broker com sucesso!");
    }
    else {
      Serial.println("Nao foi possivel se conectar ao broker.");
      Serial.println("Nova tentatica de conexao em 10s");
      delay(10000);
    }
  }
}

void enviaValores(float luz, float temperatura, float km) {

  String pubTemp = String(temperatura);
  char tempBuf[100];
  pubTemp.toCharArray(tempBuf, 100);
  MQTT.publish(TOPIC_PUB_TEMP, tempBuf);

  String pubLuz = String(luz);
  char luzBuf[100];
  pubLuz.toCharArray(luzBuf, 100);
  MQTT.publish(TOPIC_PUB_LUZ, luzBuf);

  String pubVento = String(km);
  char kmBuf[100];
  pubVento.toCharArray(kmBuf, 100);
  MQTT.publish(TOPIC_PUB_VENTO, kmBuf);
}
