package com.example.caios.rxexample.Dummy;

import com.example.caios.rxexample.Model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caios on 5/3/16.
 */
public class DummyContentItem {
    public static List<Item> ITEMS = new ArrayList<>();
    private static int size = 0;

    static {
        for (int i = 1; i <= size; i++) {
            ITEMS.add(createDummyItem(i));
        }
    }

    public static void addNewItem() {
        int indice = ITEMS.size();
        indice++;

        ITEMS.add(createDummyItem(indice));
    }

    public static void addNewItem(int i) {
        ITEMS.add(createDummyItem(i));
    }

    public static void addNewItem(double d) {
        ITEMS.add(createDummyItem(d));
    }

    public static void clearItems() {
        ITEMS = new ArrayList<>();
    }

    private static Item createDummyItem(int position) {
        return new Item("Item RxJava: " + position, detailsWithFor(position));
    }

    private static Item createDummyItem(double position) {
        return new Item("Item RxJava: " + position, detailsWithoutFor(position));
    }

    private static String detailsWithoutFor(double d){
        StringBuilder builder = new StringBuilder();
        builder.append("Detalhe item: ").append(d);
        return builder.toString();
    }

    private static String detailsWithFor(int position){
        StringBuilder builder = new StringBuilder();
        builder.append("Detalhe item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMais detalhes, click aqui.");
        }

        return builder.toString();
    }
}
