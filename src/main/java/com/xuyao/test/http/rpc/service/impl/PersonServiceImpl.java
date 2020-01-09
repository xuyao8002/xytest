package com.xuyao.test.http.rpc.service.impl;

import com.xuyao.test.http.rpc.annotation.Producer;
import com.xuyao.test.http.rpc.service.IPersonService;
import com.xuyao.test.http.rpc.transmission.Person;

import java.util.*;

@Producer(name = "personService")
public class PersonServiceImpl implements IPersonService {

    private Map<Integer, Person> personMap;

    @Override
    public Person get(Integer id) {
        initPersonMap();
        return personMap.get(id);
    }

    private void initPersonMap() {
        if(personMap == null){
            personMap = new HashMap<>();
            Random random = new Random();
            Integer id;
            for(int i = 0; i < 10; i++){
                id = i + 1;
                Person person = new Person();
                person.setId(id);
                person.setAge(random.nextInt(30));
                person.setName("hello" + i);
                personMap.put(id, person);
            }
        }
    }
}
