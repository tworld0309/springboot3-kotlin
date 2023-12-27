package com.boot3kotlin.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Deserializer
import org.slf4j.LoggerFactory
import kotlin.text.Charsets.UTF_8

//사용자 정의 객체 ( Person)를 Deserializer
class PersonDeserializer : Deserializer<Person> {
    private val om: ObjectMapper = ObjectMapper()
    private val log = LoggerFactory.getLogger(PersonDeserializer::class.java)
    override fun deserialize(topic: String?, data: ByteArray?): Person {
        log.info(">> PersonDeserializer deserialize....")
        return om.readValue(
                String(data?:throw SerializationException("Error when deserializing byte[] to Product") , UTF_8)
                ,Person::class.java)
    }
}
