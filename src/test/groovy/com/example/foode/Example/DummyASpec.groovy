package com.example.foode.Example

import spock.lang.Specification

class DummyASpec extends Specification{

    def "this is example test"(){
        given: "example mock"
        def dummyB = Mock(DummyB){
            getDummyInt() >> 5
        }
        def dummyA = new DummyA(dummyB)

        when:
        def result = dummyA.addFromDummy(3)

        then:
        result == 5 + 3
    }

    def "wildCart example"(){
        given: "example mock"
        def dummyB = Mock(DummyB)
        //this mean - for any arguments that have good type return 7 ->
        // its very usefull shortcut -> you dont need to create a lot of object
        dummyB.dummyAdd(_, _) >> 7

        def dummyA = new DummyA(dummyB)

        when:
        def result = dummyA.example()

        then:
        result == 7
    }

    def "where example"(){
        given:
        def dummyB = Mock(DummyB)
        dummyB.getDummyInt() >> 7

        def dummyA = new DummyA(dummyB)

        when:
        def result = dummyA.addFromDummy(dummyExample)

        then:
        result == 7 + dummyExample

        //this empty column is only need if you had only one column
        where:
        _ | dummyExample
        _ | 8
        _ | 3
        _ | 2
        _ | 1
    }

    def "where example - one column shortcut"(){
        given:
        def dummyB = Mock(DummyB)
        dummyB.getDummyInt() >> 7

        def dummyA = new DummyA(dummyB)

        when:
        def result = dummyA.addFromDummy(dummyExample)

        then:
        result == 7 + dummyExample

        //if you have only one column you can use this syntax
        where:
        dummyExample << [8,
                         3,
                         2,
                         1]
    }
}
