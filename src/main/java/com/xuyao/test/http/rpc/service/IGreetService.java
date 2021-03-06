package com.xuyao.test.http.rpc.service;


import com.xuyao.test.http.rpc.annotation.Consumer;
import com.xuyao.test.http.rpc.transmission.Person;

@Consumer(ref = "greatService")
public interface IGreetService {

    String greeting(Person person);

}
