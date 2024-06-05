package com.gildedrose;

class Itemizer {
    private final Item item;

    boolean isLegend() {
        return item.quality == 80;
    }

    boolean isBackstage() {
        return item.name.toLowerCase().startsWith("backstage passes");
    }

    boolean isAgedBrie() {
        return item.name.toLowerCase().startsWith("aged brie");
    }

    boolean isConjured() {
        return item.name.toLowerCase().startsWith("conjured");
    }

    Itemizer(Item item) {
        this.item = new Item(item.name, item.sellIn, item.quality);
    }

    Itemizer handleBackstage() {
        int sellIn = item.sellIn - 1;
        if (sellIn < 0) {
            return new Itemizer(new Item(item.name, sellIn, 0));
        } else if (sellIn <= 5) {
            return new Itemizer(new Item(item.name, sellIn, item.quality + 3));
        } else if (sellIn <= 10) {
            return new Itemizer(new Item(item.name, sellIn, item.quality + 2));
        } else {
            return new Itemizer(new Item(item.name, sellIn, item.quality + 1));
        }
    }

    Itemizer update() {
        if (isLegend()) {
            return this;
        } else if (isBackstage()) {
            return handleBackstage();
        } else if (isConjured()) {
            return new Itemizer(new Item(item.name, item.sellIn - 1, item.quality - 2));
        } else if (isAgedBrie()) {
            return new Itemizer(new Item(item.name, item.sellIn - 1, item.quality + 1));
        } else {
            return new Itemizer(new Item(item.name, item.sellIn - 1, item.quality - 1));
        }
    }

    Item toItem() {
        return isLegend() ?
                this.item :
                new Item(
                        item.name,
                        item.sellIn,
                        Math.min(
                                Math.max(item.quality, 0),
                                GildedRose.MAX_QUALITY));
    }
}