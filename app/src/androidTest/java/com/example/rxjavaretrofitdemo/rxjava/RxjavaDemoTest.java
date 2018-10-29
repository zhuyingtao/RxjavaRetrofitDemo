package com.example.rxjavaretrofitdemo.rxjava;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.runner.AndroidJUnit4;

/**
 * @author zyt
 * @date 18-10-24
 */
@RunWith(AndroidJUnit4.class)
public class RxjavaDemoTest {

    private RxjavaDemo mDemo;

    @Before
    public void setUp() throws Exception {
        mDemo = new RxjavaDemo();
    }

    @Test
    public void create() throws Exception {
        mDemo.create();
    }

    @Test
    public void map() throws Exception {
        mDemo.map();
    }

    @Test
    public void flatMap() throws Exception {
        mDemo.flatMap();
    }

    @Test
    public void zip() throws Exception {
        mDemo.zip();
    }
}