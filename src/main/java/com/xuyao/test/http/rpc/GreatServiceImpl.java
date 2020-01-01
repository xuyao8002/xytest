package com.xuyao.test.http.rpc;


public class GreatServiceImpl implements IGreetService{
    @Override
    public String greeting(Person person) {
        return "greating, " + person.getName();
    }
}
