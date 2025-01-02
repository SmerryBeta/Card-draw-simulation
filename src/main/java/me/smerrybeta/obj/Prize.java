package i.os.processHandler;

public class Prize {
    private final double begin;
    private final double end;
    private final String type;

    public Prize (double begin, double end, String type) {
        this.begin = begin;
        this.end = end;
        this.type = type;
    }

    /**
     * 遵守左闭右开区间
     * */
    public boolean isThat(double num) {
        return num >= begin && num < end;
    }

    public String getType () {
        return type;
    }
}
