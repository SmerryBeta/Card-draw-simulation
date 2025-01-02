package i.os.processHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrawMachine {
    private static final Map<String, Double> TYPE_RATES = new HashMap<>();
    private static final List<Prize> prizes = new ArrayList<>();

    static {
        TYPE_RATES.put("扭蛋币", 0.405);
        TYPE_RATES.put("闪魂头像", 0.075);
        TYPE_RATES.put("闪魂头像框", 0.075);
        TYPE_RATES.put("闪魂头名牌", 0.075);
        TYPE_RATES.put("来打牌（表情）", 0.075);
        TYPE_RATES.put("闪魂远古地包天", 0.025);
        TYPE_RATES.put("闪魂雨伞玛奇朵", 0.025);
        TYPE_RATES.put("闪魂玉兔", 0.025);
        TYPE_RATES.put("闪魂雨衣毛毛", 0.025);
        TYPE_RATES.put("闪魂拳王泰哥", 0.025);
        TYPE_RATES.put("闪魂格斗芭比", 0.025);
        TYPE_RATES.put("闪魂救世主", 0.025);
        TYPE_RATES.put("闪魂篮球布鲁斯", 0.025);
        TYPE_RATES.put("闪魂和服八公", 0.025);
        TYPE_RATES.put("闪魂海盗豆豆", 0.025);
        TYPE_RATES.put("闪魂荷官卡罗特", 0.02);
        TYPE_RATES.put("闪魂荷官泰哥", 0.02);
        TYPE_RATES.put("闪魂牌手宝珀", 0.005);
    }

    public DrawMachine () {
        /* 初始化奖励列表，生成 Prize 奖励对象表 */
        double begin = 0;
        for (String key : TYPE_RATES.keySet()) {
            double rate = TYPE_RATES.get(key);
            prizes.add(new Prize(begin, rate + begin, key));
            begin += rate;
        }
    }

    public Map<String, Integer> PRIZE_NUM = new HashMap<>();

    public String draw () {
        // 抽奖函数，返回一个字符串，表示抽到的奖品类型
        double randomValue = Math.random();
        for (Prize prize : prizes)
            if (prize.isThat(randomValue))
                return insertPrize(prize.getType());
        return null;
    }

    public int drawToComplete () {
        // 抽奖函数，直到抽齐全为止
        int sum = 10;
        this.draw(10);
        while (! (PRIZE_NUM.size() == TYPE_RATES.size())) {
            this.draw();
            sum += 1;
        }
        return sum;
    }

    private String insertPrize (String type) {
        PRIZE_NUM.put(type, PRIZE_NUM.containsKey(type) ? PRIZE_NUM.get(type) + 1 : 1);
        return type;
    }

    public boolean print () {
        for (String key : PRIZE_NUM.keySet())
            System.out.println(key + ": " + PRIZE_NUM.get(key));
        return PRIZE_NUM.size() == TYPE_RATES.size();
    }

    public List<String> draw (int count) {
        // 根据需求，抽取指定数量的奖品
        List<String> result = new ArrayList<>();
        for (int i = 0; i < count; i++)
            result.add(draw());
        return result;
    }
}
