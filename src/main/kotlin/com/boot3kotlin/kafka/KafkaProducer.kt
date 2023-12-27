package com.boot3kotlin.kafka

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.kafka.support.SendResult
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture


@Service
class KafkaProducer constructor(
        val simpleKafkaTemplate: KafkaTemplate<String, String>,
        val dataKafkaTemplate: KafkaTemplate<String, Person>,
){

    val log = LoggerFactory.getLogger(KafkaProducer::class.java)

    @Value("\${spring.kafka.text-topic}")
    private lateinit var simpleTopic:String

    @Value("\${spring.kafka.data-topic}")
    private lateinit var dataTopic:String

    @Value("\${spring.kafka.bootstrap-servers}")
    private lateinit var BSERVER:String

    // String 타입 - text 메세지 발송
    fun sendMessage(input:String){
        val message: Message<String> = MessageBuilder.withPayload(input).setHeader(KafkaHeaders.TOPIC, simpleTopic).build()
        simpleKafkaTemplate.send(message)
        //log.info("produced message: name={}, age={}", message.getPayload().getName(), message.getPayload().getAge())

    }

    // 객체 타입 - Person 메세지 발송
    fun sendMessage(input:Person) {
        val message: Message<Person> = MessageBuilder.withPayload(input).setHeader(KafkaHeaders.TOPIC, dataTopic).build()

        // var future: CompletableFuture<SendResult<String, Person>> = dataKafkaTemplate.send(message)

        dataKafkaTemplate.send(message)
       //log.info("message : {}", message)
        //log.info("produced message: name={}, age={}", message.getPayload().name, message.getPayload().age)

//        dataKafkaTemplate.send(dataTopic, message)
//                .addCallback(
//                        SuccessCallback { result ->
//                            log.info("Sent Message: ${message} with offset:${result!!.recordMetadata.offset()}")
//                        }, FailureCallback { ex ->
//                    log.error("unable to send message:${message}-${ex}")
//                }
//                )
    }
}