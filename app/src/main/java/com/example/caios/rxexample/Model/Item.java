package com.example.caios.rxexample.Model;

/**
 * Created by caios on 5/3/16.
 */
public class Item {
    public final String content;
    public final String details;

    public Item(String content, String details) {
        this.content = content;
        this.details = details;
    }

    @Override
    public String toString() {
        return content;
    }
}