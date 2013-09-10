package com.stepheneisenhauer.ninjamandroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by stephen on 9/7/13.
 */
public class NinjamServerSet {
    /**
     * An array of sample (dummy) items.
     */
    public static List<NinjamServer> ITEMS = new ArrayList<NinjamServer>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, NinjamServer> ITEM_MAP = new HashMap<String, NinjamServer>();

    static {
        // Add 3 sample items.
        addItem(new NinjamServer("1", "Ninbot:2049", "ninbot.com:2049"));
        addItem(new NinjamServer("2", "Ninbot:2050", "ninbot.com:2050"));
        addItem(new NinjamServer("3", "Ninbot:2051", "ninbot.com:2051"));
        addItem(new NinjamServer("4", "Ninjamer:2049", "ninjamer.com:2049"));
        addItem(new NinjamServer("5", "Ninjamer:2050", "ninjamer.com:2050"));
        addItem(new NinjamServer("6", "Ninjamer:2051", "ninjamer.com:2051"));
    }

    private static void addItem(NinjamServer item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class NinjamServer {
        public String id;
        public String name;
        public String host;

        public NinjamServer(String id, String name, String host) {
            this.id = id;
            this.name = name;
            this.host = host;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
