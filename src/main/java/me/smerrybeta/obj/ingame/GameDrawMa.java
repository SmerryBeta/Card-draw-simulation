package me.smerrybeta.obj.ingame;

import me.smerrybeta.obj.BaseDrawMa;
import me.smerrybeta.obj.DrawMa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameDrawMa extends BaseDrawMa implements DrawMa {
    private static final Map<String, Double> TYPE_RATES = new HashMap<>();
    private final List<Prize> prizes = new ArrayList<>();

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

    public GameDrawMa () {
        /* 初始化奖励列表，生成 Prize 奖励对象表 */
        double begin = 0;
        for (String key : TYPE_RATES.keySet()) {
            double rate = TYPE_RATES.get(key);
            prizes.add(new Prize(begin, rate + begin, key));
            begin += rate;
        }
    }

    public Map<String, Integer> getPrizeNum () {
        return PRIZE_NUM;
    }

    public String draw () {
        // 抽奖函数，返回一个字符串，表示抽到的奖品类型
        double randomValue = Math.random();
        for (Prize prize : prizes)
            if (prize.isThat(randomValue))
                return insertPrize(prize.getType(), 1);
        return null;
    }

    public Map<String, Integer> drawToComplete () {
        // 创建一个用于存储抽取结果的 HashMap
        Map<String, Integer> resultMap = new HashMap<>();
        while (PRIZE_NUM.size() < TYPE_RATES.size()) { // 检查是否所有奖品齐全
            String prize = draw();
            resultMap.put(prize, resultMap.getOrDefault(prize, 0) + 1); // 累加物品数量
        }
        return this.sortByKey(resultMap);
    }

    @Override
    public Map<String, Integer> drawUntilGet (String target) {
        // 创建一个用于存储抽取结果的 HashMap
        Map<String, Integer> resultMap = new HashMap<>();

        // 寻找到目标
        boolean success = false;

        for (String val : TYPE_RATES.keySet()) {
            if (val.contains(target)) {
                target = val;
                success = true;
            }
        }
        // 防止陷入死循环
        if (! success)
            throw new IllegalStateException("不存在的值-> \"" + target + "\"");

        // 检查是否所有奖品齐全
        while (! PRIZE_NUM.containsKey(target)) {
            // 累加物品数量
            String prize = this.draw();
            resultMap.put(prize, resultMap.getOrDefault(prize, 0) + 1);
        }
        return resultMap;
    }

    @Override
    public Map<String, Integer> drawUntilContain (String target) {
        // 创建一个用于存储抽取结果的 HashMap
        Map<String, Integer> resultMap = new HashMap<>();

        // 寻找到目标
        String tmp = TYPE_RATE_key(target);

        // 防止陷入死循环
        if (tmp == null)
            throw new IllegalStateException("不存在的值-> \"" + target + "\"");

        // 检查是否所有奖品齐全
        while (PRIZE_NUM_key(target) == null) {
            // 累加物品数量
            String item = this.draw();
            resultMap.put(item, resultMap.getOrDefault(item, 0) + 1);
        }
        return resultMap;
    }

    private String TYPE_RATE_key (String val) {
        for (String v : TYPE_RATES.keySet())
            if (v.contains(val))
                return v;
        return null;
    }

    private String PRIZE_NUM_key (String val) {
        for (String k : PRIZE_NUM.keySet())
            if (k.contains(val))
                return k;
        return null;
    }

    public void print () {
        super.print();
        super.printDrawInfo(this.hasAll(), this.getDrawCount());
    }

    public boolean hasAll () {
        return PRIZE_NUM.size() == TYPE_RATES.size();
    }

    public Map<String, Integer> draw (int count) {
        // 创建一个用于存储抽取结果的临时 HashMap
        Map<String, Integer> resultMap = new HashMap<>();
        for (int i = 0; i < count; i++) {
            String prize = draw();
            resultMap.put(prize, resultMap.getOrDefault(prize, 0) + 1); // 累加物品数量
        }
        return resultMap;
    }

    @Override
    public String getType () {
        return "虚拟道具抽奖机";
    }

    public void clear () {
        this.PRIZE_NUM = new HashMap<>();
    }

    public int getDrawCount () {
        return PRIZE_NUM.values().stream().mapToInt(Integer :: intValue).sum();
    }
}
