package com.dom.a1000coin.coin;
import android.support.annotation.DrawableRes;
import com.dom.a1000coin.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoinContent {

    public static final List<Coin> ITEMS = new ArrayList<Coin>();
    public static final Map<String, Coin> ITEM_MAP = new HashMap<String, Coin>();
    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createCoin(i));
        }
    }

    private static Coin createCoin(int position) {
        return new Coin(String.valueOf(position), "coin № " + position, makeDetails(position), R.drawable.test);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Coin: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    private static void addItem(Coin item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static class Coin {
        public final String id;
        public final int img;
        public final String content;
        public final String details;

        public Coin(String id, String content, String details, @DrawableRes int imgRes) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.img=  imgRes;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
