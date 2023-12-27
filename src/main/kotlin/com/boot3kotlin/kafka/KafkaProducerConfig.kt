package com.boot3kotlin.kafka

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer
import org.springframework.stereotype.Service

@Service
class KafkaProducerConfig {
    @Value("\${spring.kafka.bootstrap-servers}")
    lateinit var BSERVER: String

    // -------------------------------------------------
    // 간단한 text ( String type )의 메세지를 발송하는 설정
    // -------------------------------------------------
    @Bean
    fun simpleProducerFactory(): ProducerFactory<String, String> =
            DefaultKafkaProducerFactory<String , String>(mapOf(
                    ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to BSERVER,
                    ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
                    ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java
            ))


    @Bean
    fun simpleKafkaTemplate():KafkaTemplate<String , String> = KafkaTemplate<String , String>(simpleProducerFactory())

    // -------------------------------------------------
    // Custom 객체 ( Person Class type )의 메세지를 발송하는 설정
    // -------------------------------------------------
    @Bean
    fun dataProducerFactory():ProducerFactory<String , Person> =
            DefaultKafkaProducerFactory<String , Person>(mapOf(
                    ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to BSERVER,
                    ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
                    ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java
            ))


    @Bean
    fun dataKafkaTemplate():KafkaTemplate<String , Person> = KafkaTemplate<String , Person>(dataProducerFactory())
}
