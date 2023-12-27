package com.boot3kotlin.kafka

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaConsumer constructor(
){

    val log = LoggerFactory.getLogger(KafkaConsumer::class.java)

    @KafkaListener(topics = ["\${spring.kafka.consumer.text-group.topic}"]
            , groupId = "\${spring.kafka.consumer.text-group.gid}"
            , containerFactory = "textKafkaListenerContainerFactory")
    fun textTopicConsumer(message:String){
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        log.info("> receive message = {}" , message)
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")
    }

    @KafkaListener(topics = ["\${spring.kafka.consumer.data-group.topic}"]
            , groupId = "\${spring.kafka.consumer.data-group.gid}"
            , containerFactory = "dataKafkaListenerContainerFactory")
    fun dataTopicConsumer(message: Person){
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        log.info("> receive Person = {}" , message)
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")
    }

}