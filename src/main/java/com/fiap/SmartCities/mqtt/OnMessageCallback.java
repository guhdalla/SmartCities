package com.fiap.SmartCities.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;

import com.fiap.SmartCities.controller.TemperaturaController;
import com.fiap.SmartCities.model.Luminosidade;
import com.fiap.SmartCities.model.Temperatura;
import com.fiap.SmartCities.model.VelocidadeVento;
import com.fiap.SmartCities.repository.LuminosidadeRepository;
import com.fiap.SmartCities.repository.TemperaturaRepository;
import com.fiap.SmartCities.repository.VelocidadeVentoRepository;
import com.fiap.SmartCities.rest.TemperaturaEndpoint;

public class OnMessageCallback implements MqttCallback {

	@Autowired
	LuminosidadeRepository repositoryLux;

	@Autowired
	VelocidadeVentoRepository repositoryVento;

	@Autowired
	TemperaturaRepository repositoryTemp;

	public void connectionLost(Throwable cause) {
		// After the connection is lost, it usually reconnects here
		System.out.println("disconnect，you can reconnect");
		MqttConnection mqtt = MqttConnection.getInstance();
		mqtt.connectMqtt();
	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {
		// The messages obtained after subscribe will be executed here
		System.out.println("Received message topic:" + topic);
		System.out.println("Received message Qos:" + message.getQos());
		System.out.println("Received message content:" + new String(message.getPayload()));
		
	}

	public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("deliveryComplete---------" + token.isComplete());
		System.out.println("Topic--------------------" + token.getTopics());
		System.out.println("TopicToString------------" + token.getTopics().toString());
		try {
			System.out.println("Message--------------" + new String(token.getMessage().getPayload()));
		} catch (MqttException e) {
			e.printStackTrace();
		}
		
//		try {
//		Thread.sleep(1000);
//		VelocidadeVento v;
//		if (topic.equals("bgmbnewgen8462/smartcities/velocidadevento")) {
//			v = new VelocidadeVento();
//			v.setKilometrosHora(Double.parseDouble(new String(message.getPayload())));
//			v.setDATASINAL("05:05:05");
//			repositoryVento.save(v);
//		}
//	} catch (Exception e) {
//		System.out.println(e);
//	}
//	
//
//
////	Temperatura temperatura;
////	if (topic.equals("bgmbnewgen8462/smartcities/temperatura")) {
////		temperatura = new Temperatura();
////		temperatura.setTemperatura(Double.parseDouble(new String(message.getPayload())));
////		temperatura.setDATASINAL("05:05:05");
////		repositoryTemp.save(temperatura);
////	}
//
////	switch (topic) {
////	case "bgmbnewgen8462/smartcities/temperatura":
////		
////	case "bgmbnewgen8462/smartcities/luminosidade":
////		Luminosidade luminosidade = new Luminosidade();
////		luminosidade.setLuminosidade(Double.parseDouble(new String(message.getPayload())));
////		repositoryLux.save(luminosidade);
////		break;
////	case "bgmbnewgen8462/smartcities/velocidadevento":
////		VelocidadeVento v = new VelocidadeVento();
////		v.setKilometrosHora(Double.parseDouble(new String(message.getPayload())));
////		repositoryVento.save(v);
////		break;
////	default:
////		break;
////	}
	}
}
