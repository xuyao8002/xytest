package com.xuyao.test.http.rpc.service.impl;


import com.xuyao.test.http.rpc.service.IGreetService;
import com.xuyao.test.http.rpc.transmission.Person;

public class GreatServiceImpl implements IGreetService {
    @Override
    public String greeting(Person person) {
        return "greating, " + person.getName();
    }

}
