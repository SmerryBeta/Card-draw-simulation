package me.smerrybeta.obj;

import java.util.*;

public class BaseDrawMa {
    protected Map<String, Integer> PRIZE_NUM = new HashMap<>();

    protected String insertPrize (String type, int num) {
        PRIZE_NUM.put(type, PRIZE_NUM.containsKey(type) ? PRIZE_NUM.get(type) + num : num);
        return type;
    }

    public void print () {
        this.sortByKey(PRIZE_NUM).forEach((key, value) -> System.out.println(key + ": " + value));
    }

    protected Map<String, Integer> sortByKey(Map<String, Integer> map) {
        // 将 Map 的键值对转换为 List
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());

        // 按键（key）排序
        entryList.sort(Map.Entry.comparingByKey());

        // 将排序后的键值对存入 LinkedHashMap（保持顺序）
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entryList)
            sortedMap.put(entry.getKey(), entry.getValue());

        return sortedMap;
    }

    protected void printDrawInfo(boolean full, int sum) {
        System.out.println("[INFO]本次抽卡：\033[1;31m" + (full ? "齐" : "未齐") + "\033[0m");
        System.out.println("[INFO]现已抽卡：\033[1;31m " + sum + " \033[0m次，预计开支：\033[1;31m " + sum * 10 + " \033[0m元");
    }
}
