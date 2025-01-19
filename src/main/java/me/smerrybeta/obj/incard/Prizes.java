package me.smerrybeta.obj.incard;

import me.smerrybeta.obj.BaseItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Prizes extends BaseItem {
    private final List<String> types;
    private final ItemType type;
    private final Random random = new Random();

    public Prizes (List<String> types, ItemType type, double begin, double end) {
        super(begin, end);
        this.type = type;
        this.types = types == null ? new ArrayList<>() : types;
    }

    public String draw () {
        return types.get(random.nextInt(types.size()));
    }

    public ItemType getType () {
        return type;
    }

    public int getSize() {
        return types.size();
    }
}
