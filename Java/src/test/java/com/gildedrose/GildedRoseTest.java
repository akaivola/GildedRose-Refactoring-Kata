package com.gildedrose;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    public Item item(String name, int sellIn, int quality) {
        return new Item(name, sellIn, quality);
    }

    public Item first(GildedRose app) {
        return app.items[0];
    }

    public void advance(GildedRose app, int days) {
        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            app.updateQuality();
        }
    }

    public GildedRose app(Item... items) {
        return new GildedRose(items);
    }

    @Test
    void testFoo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void testBrie() {
        Item[] items = new Item[] { item("Aged Brie", 2, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, first(app).quality);
        assertEquals(1, first(app).sellIn);
        advance(app, 100);
        assertEquals(-99, first(app).sellIn);
        assertEquals(50, first(app).quality);
    }

    @Test
    void testLegendary() {
        GildedRose rose = app(new Item("Sulfuras, Hand of Ragnaros", 99, 80));
        app().updateQuality();
        assertEquals(80, first(rose).quality);
        assertEquals(99, first(rose).sellIn);
        advance(rose, 100);
        assertEquals(80, first(rose).quality);
        assertEquals(99, first(rose).sellIn);
    }

    @Test
    void testConjured() {
        var app = app(new Item("Conjured Mana Cake", 3, 6));
        app.updateQuality();
        assertEquals(4, first(app).quality);
        assertEquals(2, first(app).sellIn);
        advance(app, 10);
        assertEquals(-8, first(app).sellIn);
        assertEquals(0, first(app).quality);
    }

    @Test
    void testBackstage() {
        var app = app(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        app.updateQuality();
        assertEquals(21, first(app).quality);
        assertEquals(14, first(app).sellIn);


        advance(app, 4);
        assertEquals(10, first(app).sellIn);
        assertEquals(26, first(app).quality);

        advance(app, 1);
        assertEquals(9, first(app).sellIn);
        assertEquals(28, first(app).quality);

        advance(app, 4);
        assertEquals(5, first(app).sellIn);
        assertEquals(37, first(app).quality);

        advance(app, 1);
        assertEquals(4, first(app).sellIn);
        assertEquals(40, first(app).quality);

        advance(app, 4);
        assertEquals(0, first(app).sellIn);
        assertEquals(50, first(app).quality);

        advance(app, 1);
        assertEquals(-1, first(app).sellIn);
        assertEquals(0, first(app).quality);
    }


}