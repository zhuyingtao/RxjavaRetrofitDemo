package com.example.rxjavaretrofitdemo;

public class MovieCountException extends Throwable {
    private int count;

    public MovieCountException(int count) {
        this.count = count;
    }
}
