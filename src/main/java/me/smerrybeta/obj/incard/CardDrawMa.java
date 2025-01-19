package me.smerrybeta.obj.incard;

import me.smerrybeta.obj.BaseDrawMa;
import me.smerrybeta.obj.DrawMa;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardDrawMa extends BaseDrawMa implements DrawMa {
    private static final Map<PrizesAndType, Double> TYPE_RATES = new HashMap<>();
    private static final List<Prizes> prizes = new ArrayList<>();

    static {
        /* 这里输入奖励列表 */
        List<String> C = Stream.of("尼莫",
                        "黄金尼莫",
                        "玛奇朵",
                        "黄金玛奇朵",
                        "芭比",
                        "雪花芭比",
                        "熔岩芭比",
                        "卡洛特",
                        "阿宝",
                        "利威尔",
                        "腹黑利威尔",
                        "布鲁斯",
                        "粉粉布鲁斯",
                        "白银布鲁斯",
                        "腹黑布鲁斯",
                        "鳄霸",
                        "黄金鳄霸",
                        "珞珞",
                        "蓝蓝珞珞",
                        "斯帕奇",
                        "黄金斯帕奇",
                        "粉粉斯帕奇",
                        "黄眼斯帕奇",
                        "黑眼斯帕奇",
                        "阿呆",
                        "粉粉阿呆",
                        "腹黑阿呆",
                        "豆豆")
                .map(str -> "普通C_" + str)
                .collect(Collectors.toList());

        List<String> N = Stream.of("荒野大膘客",
                        "武道会1",
                        "武道会2",
                        "炸弹投石机",
                        "猛兽点击球",
                        "暴力分拣",
                        "暴风雪",
                        "黑洞实验室",
                        "冬季小屋",
                        "猛兽冰球",
                        "断箭行动",
                        "猛兽潜艇")
                .map(str -> "普通N_" + str)
                .collect(Collectors.toList());

        List<String> U = List.of("CDK");

        List<String> R = Stream.of("瓜皮尼莫",
                        "雨衣玛奇朵",
                        "香橙玛奇朵",
                        "粉粉雨衣玛奇朵",
                        "格斗芭比",
                        "香蕉芭比",
                        "远古芭比",
                        "雪花远古芭比",
                        "宝宝卡洛特",
                        "玉兔卡洛特",
                        "黄金玉兔卡洛特",
                        "宝宝利威尔",
                        "篮球布鲁斯",
                        "宝宝布鲁斯",
                        "夏日布鲁斯",
                        "梦游鳄霸",
                        "菠萝鳄霸",
                        "橘橘菠萝鳄霸",
                        "粉粉梦游鳄霸",
                        "指挥官珞珞",
                        "在逃斯帕奇",
                        "救世主斯帕奇",
                        "秋游呆",
                        "咕咕",
                        "优罗莎",
                        "粉粉优罗莎",
                        "熔岩优罗莎",
                        "粉粉格斗芭比")
                .map(str -> "中级R_" + str)
                .collect(Collectors.toList());

        List<String> SR = Stream.of(
                        "夏日嘟嘟",
                        "夏日小新",
                        "夏日麦克斯",
                        "夏日可乐",
                        "夏日布鲁斯",
                        "夏日瓜",
                        "夏日瓦特",
                        "水兵芭比",
                        "夏日锤",
                        "夏日桑尼")
                .map(str -> "中级SR_" + str)
                .collect(Collectors.toList());

        List<String> SSR = Stream.of(
                        "牛仔尼莫",
                        "喷气背包尼莫",
                        "武装玛奇朵",
                        "黑衣芭比",
                        "小红帽卡洛特",
                        "武装卡洛特",
                        "木乐阿宝",
                        "翡翠阿宝",
                        "冰棍阿宝",
                        "恶魔利威尔",
                        "鲨僧",
                        "神棍鳄霸",
                        "浴袍鳄霸",
                        "战术珞珞",
                        "罗宾珞",
                        "天使珞珞",
                        "童子军斯帕奇",
                        "加拿大呆",
                        "RICH呆",
                        "隐士呆",
                        "恶魔呆",
                        "海盗豆豆")
                .map(str -> "高级SSR_" + str)
                .collect(Collectors.toList());

        List<String> HR = Stream.of("太空尼莫",
                        "皇家尼莫",
                        "黑魔法玛奇朵",
                        "雅典娜玛奇朵",
                        "四分卫芭比",
                        "兔爷卡洛特",
                        "公主卡洛特",
                        "星云阿宝",
                        "滋滋阿宝",
                        "火爆阿宝",
                        "粉粉阿宝",
                        "暗黑利威尔",
                        "青天利威尔",
                        "刺客利威尔",
                        "海盗布鲁斯",
                        "海盗鳄霸")
                .map(str -> "稀有HR_" + str)
                .collect(Collectors.toList());

        List<String> UR = Stream.of("猛兽钱钱阿瓜",
                        "猛兽钱钱暴莉",
                        "猛兽钱钱加肥",
                        "猛兽钱钱芭比",
                        "猛兽钱钱鳄霸",
                        "猛兽钱钱尼莫")
                .map(str -> "稀有UR_" + str)
                .collect(Collectors.toList());

        List<String> CP = Stream.of("宙斯尼莫",
                        "黄金骑士尼莫",
                        "魔法少女玛奇朵",
                        "水兵芭比",
                        "美神卡洛特",
                        "骑士卡洛特",
                        "神龙",
                        "德古拉利威尔",
                        "哈迪斯利威尔")
                .map(str -> "超稀有CP_" + str)
                .collect(Collectors.toList());

        List<String> SP = Stream.of("白金骑士尼莫", "RGB玉兔卡洛特")
                .map(str -> "超稀有SP_" + str)
                .collect(Collectors.toList());

        List<String> SSP = Stream.of("柯基像", "喵娜丽莎", "牛顿", "嘎喊", "琴手鳄", "火枪猫", "薛定鳄")
                .map(str -> "超稀有SSP_" + str)
                .collect(Collectors.toList());

        /* 这里输入概率 前三个必出一个 */
        TYPE_RATES.put(new PrizesAndType(C, ItemType.C), 1.0);
        TYPE_RATES.put(new PrizesAndType(N, ItemType.N), 1.0);
        TYPE_RATES.put(new PrizesAndType(U, ItemType.U), 1.0);

        TYPE_RATES.put(new PrizesAndType(R, ItemType.R), 0.1041 * 4);
        TYPE_RATES.put(new PrizesAndType(SR, ItemType.SR), 0.0417 * 4);
        TYPE_RATES.put(new PrizesAndType(SSR, ItemType.SSR), 0.0417 * 4);
        TYPE_RATES.put(new PrizesAndType(HR, ItemType.HR), 0.0278 * 4);
        TYPE_RATES.put(new PrizesAndType(UR, ItemType.UR), 0.0139 * 4);
        TYPE_RATES.put(new PrizesAndType(CP, ItemType.CP), 0.0139 * 4);
        TYPE_RATES.put(new PrizesAndType(SP, ItemType.SP), 0.0052 * 4);
        TYPE_RATES.put(new PrizesAndType(SSP, ItemType.SSP), 0.0017 * 4);
    }

    public CardDrawMa () {
        /* 初始化奖励列表，生成 Prize 奖励对象表 */
        double begin = 0;
        for (PrizesAndType key : TYPE_RATES.keySet()) {
            switch (key.type()) {
                case C, N, U:
                    prizes.add(new Prizes(key.prize_list(), key.type(), 0, 1));
                    continue;
                default:
                    double rate = TYPE_RATES.get(key);
                    prizes.add(new Prizes(key.prize_list(), key.type(), begin, rate + begin));
                    begin += rate;
            }
        }
    }

    public Map<String, Integer> draw () {
        // 抽奖函数，抽一包
        Map<String, Integer> resultMap = new HashMap<>();
        double randValue = Math.random();
        for (Prizes p : prizes) {
            switch (p.getType()) {

                /* 这里的 C N U 是必出的 */
                case C:
                    String pz = p.draw();
                    resultMap.put(pz, resultMap.getOrDefault(pz, 0) + 1); // 累加物品数量
                    continue;

                case N:
                    String pz1 = p.draw();
                    resultMap.put(pz1, resultMap.getOrDefault(pz1, 0) + 1); // 累加物品数量
                    continue;

                case U:
                    String pz2 = p.draw();
                    resultMap.put(pz2, resultMap.getOrDefault(pz2, 0) + 1); // 累加物品数量
                    continue;

                default:
                    if (p.isThat(randValue)) {                                          // 累加物品数量
                        String pz3 = p.draw();
                        resultMap.put(pz3, resultMap.getOrDefault(pz3, 0) + 1);
                    }
            }
        }
//        resultMap.forEach((key, value) -> System.out.println(key + "：" + value));
        resultMap.keySet().forEach(pz -> insertPrize(pz, resultMap.get(pz)));
        return resultMap;
    }

    @Override
    public Map<String, Integer> getPrizeNum () {
        return super.sortByKey(PRIZE_NUM);
    }

    @Override
    public Map<String, Integer> drawToComplete () {
        // 创建一个用于存储抽取结果的 HashMap
        Map<String, Integer> resultMap = new HashMap<>();

        int size = prizes.stream().mapToInt(Prizes :: getSize).sum();
        // 检查是否所有奖品齐全
        while (PRIZE_NUM.size() < size) {
            // 累加物品数量
            Map<String, Integer> prize = this.draw();
            prize.keySet().forEach(key -> resultMap.put(key, resultMap.getOrDefault(key, 0) + prize.get(key)));
        }
        return this.sortByKey(resultMap);
    }

    /**
     * 抽到含有 target 就退出
     *
     * @param target 需要提供一个精确的 target 目标
     * @throws IllegalStateException 因为没找到值而抛出
     */
    @Override
    public Map<String, Integer> drawUntilGet (String target) {

        // 创建一个用于存储抽取结果的 HashMap
        Map<String, Integer> resultMap = new HashMap<>();

        String tmp = target;

        // 寻找到目标
        target = TYPE_RATE_key(target);

        // 防止陷入死循环
        if (target == null)
            throw new IllegalStateException("不存在的值-> \"" + tmp + "\"");

        // 检查是否所有奖品齐全
        while (! PRIZE_NUM.containsKey(target)) {
            // 累加物品数量
            Map<String, Integer> prize = this.draw();
            prize.keySet().forEach(key -> resultMap.put(key, resultMap.getOrDefault(key, 0) + prize.get(key)));
        }
        return resultMap;
    }

    /**
     * 只要含有 target 就退出
     *
     * @param target 需要提供一个宽泛的 target 目标
     * @throws IllegalStateException 因为没找到值而抛出
     */
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
            Map<String, Integer> prize = this.draw();
            prize.keySet().forEach(key -> resultMap.put(key, resultMap.getOrDefault(key, 0) + prize.get(key)));
        }
        return resultMap;
    }

    private String TYPE_RATE_key (String val) {
        for (PrizesAndType k : TYPE_RATES.keySet())
            for (String v : k.prize_list())
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


    @Override
    public Map<String, Integer> draw (int count) {
        Map<String, Integer> resultMap = new HashMap<>();
        for (int i = 0; i < count; i++) {
            // 累加物品数量
            Map<String, Integer> prize = this.draw();
            prize.keySet().forEach(key -> resultMap.put(key, resultMap.getOrDefault(key, 0) + prize.get(key)));
        }
        return this.sortByKey(resultMap);
    }

    @Override
    public void clear () {
        PRIZE_NUM = new HashMap<>();
    }

    @Override
    public boolean hasAll () {
        return PRIZE_NUM.size() == prizes.stream().mapToInt(Prizes :: getSize).sum();
    }

    @Override
    public void print () {
        super.print();

        boolean full = this.hasAll();
        int sum = this.getDrawCount();

        System.out.println("[INFO]本次抽卡：\033[1;31m" + (full ? "齐" : "未齐") + "\033[0m");
        System.out.println("[INFO]现已抽卡：\033[1;31m " + sum + " \033[0m包，预计开支：\033[1;31m " + sum * 10 + " \033[0m元");
    }

    @Override
    public int getDrawCount () {
        return PRIZE_NUM.values().stream().mapToInt(Integer :: intValue).sum() / 4;
    }

    @Override
    public String getType () {
        return "实体卡抽奖机";
    }
}
