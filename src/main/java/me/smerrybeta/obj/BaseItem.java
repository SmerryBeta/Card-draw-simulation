package me.smerrybeta.obj;

public class BaseItem {
    protected final double begin;
    protected final double end;

    public BaseItem (double begin, double end) {
        this.begin = begin;
        this.end = end;
    }

    /**
     * 遵守左闭右开区间
     */
    public boolean isThat (double num) {
        return num >= begin && num < end;
    }
}
