// Node_Google_TESTE_2
 
#include <ESP8266WiFi.h>
#include <WiFiClientSecure.h>

WiFiClientSecure client;

char ssid[] = "Saneamento 2";
char password[] = "saneamento2019";

const char* host = "script.google.com";
const int httpsPort = 443;
//const char* fingerprint = "46 B2 C3 44 9C 59 09 8B 01 B6 F8 BD 4C FB 00 74 91 2F EF F6";
const char* fingerprint = "F0 5C 74 77 3F 6B 25 D7 3B 66 4D 43 2F 7E BC 5B E9 28 86 AD";
String googleSheetsID = "1yuSuvdj1ox72aQt0gG1Y9Hn8ynpWsSxHk3hgvVmnMv0";


void connectToWiFi();


void setup() 
{
  Serial.begin(115200);
  connectToWiFi();
}
 
void loop() 
{
  sendData(5);
  delay(30000); 
}
 
void connectToWiFi() 
{
  Serial.println("Conectando a rede: ");
  Serial.println(ssid);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED)
  {
    Serial.print(".");
    delay(1000);
  }
  Serial.println("");
  Serial.println("Conectado!");
}

void sendData(int x)
{
 Serial.print("connecting to ");
 Serial.println(host);
 if (!client.connect(host, httpsPort)) {
   Serial.println("connection failed");
   return;
 }
 Serial.println("Conectado no host");
 /*while(!client.verify(fingerprint, host))
 {
    Serial.print(".");
    delay(500);
 }*/
 if (client.verify(fingerprint, host)) {
 Serial.println("certificate matches");
 } else {
 Serial.println("certificate doesn't match");
 }
 String string_x     =  String(x, DEC);
 String url = "/macros/s/" + googleSheetsID + "/exec?PRESSAO=" + string_x;
 Serial.print("requesting URL: ");
 Serial.println(url);
 client.print(String("GET ") + url + " HTTP/1.1\r\n" +
        "Host: " + host + "\r\n" +
        "User-Agent: BuildFailureDetectorESP8266\r\n" +
        "Connection: close\r\n\r\n");
 Serial.println("request sent");
 while (client.connected()) {
 String line = client.readStringUntil('\n');
 if (line == "\r") {
   Serial.println("headers received");
   break;
 }
 }
 String line = client.readStringUntil('\n');
 if (line.startsWith("{\"state\":\"success\"")) {
 Serial.println("esp8266/Arduino CI successfull!");
 } else {
 Serial.println("esp8266/Arduino CI has failed");
 }
 Serial.println("reply was:");
 Serial.println("==========");
 Serial.println(line);
 Serial.println("==========");
 Serial.println("closing connection");
}

/*void sendDataToGoogleSheets(int PRESSAO) 
{
  Serial.print("Conectando à: ");
  Serial.println(host);
  if (!client.connect(host, httpsPort)) {
    Serial.println("Falha na conexão ao Google Sheets");
    return;
  }
  if (client.verify(fingerprint, host)) Serial.println("Certificado OK");
  else Serial.println("Certificado NOK, verificar");
 
  String stringTemp = String(PRESSAO, DEC);
  String url = "/macros/s/" + googleSheetsID + "/exec?PRESSAO=" + stringTemp;
  Serial.print("Requisitando URL ");
  Serial.println(url);
 
  client.print(String("GET ") + url + " HTTP/1.1\r\n" +
               "Host: " + host + "\r\n" +
               "User-Agent: BuildFailureDetectorESP8266\r\n" +
               "Connection: close\r\n\r\n");
 
  Serial.println("Request enviada");
  while (client.connected()) {
    String line = client.readStringUntil('\n');
    if (line == "\r") {
      Serial.println("Headers Received");
      break;
    }
  }
  String line = client.readStringUntil('\n');
  if (line.startsWith("{\"state\":\"success\"")) {
    Serial.println("Sucesso");
  } else {
    Serial.println("Falha no envio!");
  }
  Serial.println("Resposta:");
  Serial.println("==========");
  Serial.println(line);
  Serial.println("==========");
  Serial.println("Fechando conexão!");
}*/
