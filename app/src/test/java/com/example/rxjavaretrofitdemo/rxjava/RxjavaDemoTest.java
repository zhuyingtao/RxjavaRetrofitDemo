package com.example.rxjavaretrofitdemo.rxjava;

import org.junit.Before;
import org.junit.Test;

/**
 * @author zyt
 * @date 18-10-24
 */
public class RxjavaDemoTest {

    private RxjavaDemo instance;

    @Before
    public void setUp() throws Exception {
        instance = new RxjavaDemo();
    }

    @Test
    public void testCreate() throws Exception {
        instance.create();
    }
}