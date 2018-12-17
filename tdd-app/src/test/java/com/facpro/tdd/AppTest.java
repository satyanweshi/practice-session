package com.facpro.tdd;

import org.junit.Test;

public class AppTest {

    @Test(expected = RuntimeException.class)
    public final void whenMoreThan2NumbersAreUsedExceptionIsThrown(){
        App.add("1,2,3");
    }
}
