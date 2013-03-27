package model.ItemSystem;

import model.Attribute;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/17/13
 * Time: 9:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Item {
    private int ID;
    private String name;
    private Attribute stats;

    public Item() {
    }

    public Item(String name, Attribute stats) {
        this.name = name;
        this.stats = stats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Attribute getStats() {
        return stats;
    }

    public void setStats(Attribute stats) {
        this.stats = stats;
    }
}
