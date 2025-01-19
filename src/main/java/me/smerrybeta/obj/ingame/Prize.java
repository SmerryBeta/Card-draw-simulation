package me.smerrybeta.obj.ingame;

import me.smerrybeta.obj.BaseItem;

public class Prize extends BaseItem {

    private final String type;

    public Prize (double begin, double end, String type) {
        super(begin, end);
        this.type = type;
    }

    public String getType () {
        return type;
    }
}
