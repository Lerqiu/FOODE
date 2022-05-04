package com.example.foode.Example;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DummyB {

    private final int dummyInt = 5;


    public int getDummyInt(){
        return dummyInt;
    }

    public int dummyAdd(int x, int y){
        return x + y;
    }
}
