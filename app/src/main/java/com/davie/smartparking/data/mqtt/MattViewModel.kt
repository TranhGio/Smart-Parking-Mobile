package com.davie.smartparking.data.mqtt

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions
import org.eclipse.paho.client.mqttv3.IMqttActionListener
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.IMqttToken
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage

class MattViewModel: ViewModel() {

    lateinit var mqttClient: MqttAndroidClient
    private val serverURL ="tcp://test.mosquitto.org:1883"
    private val tag = "MqttClient"


    private var temperature = MutableLiveData<String>()
    private var moisture = MutableLiveData<String>()
    private var humidity = MutableLiveData<String>()
    private var concentration = MutableLiveData<String>()
    private var availableSlots = MutableLiveData<String>()

    fun temp(): LiveData<String>{
        return temperature
    }

    fun slots(): LiveData<String>{
        return availableSlots
    }

    fun humi(): LiveData<String>{
        return humidity
    }

    fun moist(): LiveData<String>{
        return moisture
    }

    fun conc(): LiveData<String>{
        return concentration
    }


    fun connect(context: Context){
        mqttClient = MqttAndroidClient(context, serverURL,"Adafruit")
        mqttClient.setCallback(object: MqttCallbackExtended {
            override fun connectionLost(cause: Throwable?) {
                displayLog("Connection Lost!")
            }

            override fun messageArrived(topic: String?, message: MqttMessage?) {
                val messagePayload = String(message!!.payload)
                when(topic){
                    "mytemp" -> temperature.value = messagePayload
                    "humidity" -> humidity.value = messagePayload
                    "concentration" -> concentration.value = messagePayload
                    "moisture"-> moisture.value = messagePayload
                    "parking"-> availableSlots.value = messagePayload

                    else -> println("Unknown Channel")
                }

            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {
                displayLog("Delivered")
            }

            override fun connectComplete(reconnect: Boolean, serverURI: String?) {
                displayLog("Connected!")
            }

        })

        try {
            mqttClient.connect(serverOptions(),null,object: IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    mqttClient.setBufferOpts(disconnectedBufferOptions())
                    subscribe("mytemp")
                    subscribe("humidity")
                    subscribe("concentration")
                    subscribe("moisture")
                    subscribe("parking")
                    //  displayLog("Connected to Broker!")
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    displayLog("Failed to connect!")
                }

            })

        } catch (e: MqttException){
            e.printStackTrace()
        }
    }

    fun subscribe(topic: String,qos: Int =1){
        mqttClient.subscribe(topic,qos,null,object: IMqttActionListener {
            override fun onSuccess(asyncActionToken: IMqttToken?) {
                //displayLog("Subscribed!")
            }

            override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                displayLog("Failed to subscribe")
            }

        })
    }

    fun publish(topic: String, msg: String, qos: Int, retained: Boolean = false){
        val message = MqttMessage()
        message.payload = msg.toByteArray()
        message.qos = qos
        message.isRetained = retained

        try {
            mqttClient.publish(topic,message,null, object: IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    displayLog("Published to $topic")
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    displayLog("Failed to publish")
                }

            })
        } catch (e: MqttException){
            e.printStackTrace()
        }
    }

    private fun disconnect(){
        try {
            mqttClient.disconnect(null,object: IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    displayLog("Disconnected!")
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    displayLog("Failed to Disconnect!")
                }

            })

        } catch (e: MqttException){
            e.printStackTrace()
        }
    }

    private fun serverOptions(): MqttConnectOptions {
        return MqttConnectOptions().apply {
            isAutomaticReconnect = true
            isCleanSession = false
        }
    }

    private fun disconnectedBufferOptions(): DisconnectedBufferOptions {
        return DisconnectedBufferOptions().apply {
            isBufferEnabled = true
            bufferSize =100
            isPersistBuffer = false
            isDeleteOldestMessages = false
        }
    }

    private fun displayLog(message: String){
        Log.d(tag, message)
    }


}