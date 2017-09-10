package net.isanakamishiro.myfirstapp;

/**
 * Created by isana on 2017/09/09.
 */

public class Item {
    private String title;

    public Item(String s) {
        this.title = s;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
