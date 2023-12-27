package com.boot3kotlin.kafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.*
import org.springframework.kafka.support.serializer.JsonSerializer
import org.springframework.stereotype.Service

@Service
class KafkaConsumerConfig {
    @Value("\${spring.kafka.bootstrap-servers}")
    private lateinit var BSERVER:String

    @Value("\${spring.kafka.consumer.text-group.gid}")
    private lateinit var T_GID:String

    @Value("\${spring.kafka.consumer.data-group.gid}")
    private lateinit var D_GID:String

    // ----------------------------------------------------------------------
    // tgroup 의 consumer group id를 가진 메시지 ( String )를 deserializer하는 설정
    // ----------------------------------------------------------------------
    @Bean
    fun textConsumerFactory(): ConsumerFactory<String, String> =
            DefaultKafkaConsumerFactory(
                    mapOf(
                            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to BSERVER
                            ,ConsumerConfig.GROUP_ID_CONFIG to T_GID
                            ,ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java
                            ,ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java
                            ,ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "earliest"
//            JsonDeserializer.TRUSTED_PACKAGES to "*"
                    )
            )


    @Bean
    fun textKafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, String> =
            ConcurrentKafkaListenerContainerFactory<String , String>().apply {
                this.consumerFactory = textConsumerFactory()
            }


    // ----------------------------------------------------------------------
    // dgroup 의 consumer group id를 가진 메시지 ( Person )를 deserializer하는 설정
    //    - Deserializer를 상속한 PersonDeserializer를 생성후 설정에 주입.
    // ----------------------------------------------------------------------
    @Bean
    fun dataConsumerFactroy():ConsumerFactory<String , Person>{
        return DefaultKafkaConsumerFactory(mapOf(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to BSERVER
                ,ConsumerConfig.GROUP_ID_CONFIG to D_GID
                ,ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java
                ,ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to PersonDeserializer::class.java
                ,ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "earliest"
        ))
    }


    @Bean
    fun dataKafkaListenerContainerFactory():ConcurrentKafkaListenerContainerFactory<String , Person> =
            ConcurrentKafkaListenerContainerFactory<String , Person>().apply {
                this.consumerFactory = dataConsumerFactroy()
            }

}
