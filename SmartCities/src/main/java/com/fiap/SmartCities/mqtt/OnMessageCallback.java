package com.fiap.SmartCities.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;

import com.fiap.SmartCities.repository.LuminosidadeRepository;
import com.fiap.SmartCities.repository.TemperaturaRepository;
import com.fiap.SmartCities.repository.VelocidadeVentoRepository;

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
	}
}
