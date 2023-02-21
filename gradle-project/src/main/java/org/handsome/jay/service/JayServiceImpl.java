package org.handsome.jay.service;

public class JayServiceImpl implements JayService {
    @Override
    public String hello(String name) {
        return "hello man. I am " + name;
    }
}
