package com.xuyao.test.http.rpc.service;


import com.xuyao.test.http.rpc.annotation.Consumer;
import com.xuyao.test.http.rpc.transmission.Person;

@Consumer(ref = "personService")
public interface IPersonService {

    Person get(Integer id);

}
