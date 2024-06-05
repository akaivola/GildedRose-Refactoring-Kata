package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    public static final int MAX_QUALITY = 50;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        items = Arrays.stream(items)
                .map(Itemizer::new)
                .map(Itemizer::update)
                .map(Itemizer::toItem)
                .toArray(Item[]::new);
    }
}