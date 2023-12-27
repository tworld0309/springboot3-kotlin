package com.boot3kotlin.kafka

import com.fasterxml.jackson.annotation.JsonProperty

data class Person (
    @JsonProperty("name")
    val name:String?=null,
    @JsonProperty("age")
    val age:Int?=0,
    @JsonProperty("zipCode")
    val zipCode:String?=null,
    @JsonProperty("address")
    val address:String?=null
)