package com.fiap.SmartCities.controller;

import org.springframework.stereotype.Controller;

import com.fiap.SmartCities.mqtt.MqttConnection;

@Controller
public class MqttController {
	MqttConnection mqtt = MqttConnection.getInstance();
}