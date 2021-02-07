package com.xuyao.test.serialization.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.xuyao.test.Person;

public class ObjectMapperDemo {
    public static void main(String[] args) throws JsonProcessingException {
        Person person = new Person();
        person.setName("xy");
        person.setHobby("play");
        person.setAge(88);

        ObjectMapper jsonMapper = new JsonMapper();
        conversion(jsonMapper, person);

        ObjectMapper xmlMapper = new XmlMapper();
        conversion(xmlMapper, person);
    }

    private static void conversion(ObjectMapper mapper, Person person) throws JsonProcessingException {
        String stringVal = mapper.writeValueAsString(person);
        System.out.println("string: " + stringVal);
        Person readValue = mapper.readValue(stringVal, Person.class);
        System.out.println("object: " + readValue);
    }
}