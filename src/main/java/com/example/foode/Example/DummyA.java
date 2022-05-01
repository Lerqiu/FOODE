package com.example.foode.Example;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DummyA {

    private final DummyB dummyB;

    public int addFromDummy(int x){
        return x + dummyB.getDummyInt();
    }

    public int example(){
        return dummyB.dummyAdd(0,1);
    }
}
