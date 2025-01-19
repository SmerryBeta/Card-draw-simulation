package me.smerrybeta.obj;

import java.util.Map;

/**
 * @author SmerryBeta
 */

public interface DrawMa {

    /**
     * 获取抽奖池中所有奖励的数量
     *
     * @return 奖励键值对表
     */
    Map<String, Integer> getPrizeNum ();

    /**
     * 抽到所有奖励都集齐
     *
     * @return 抽奖结果
     */
    Map<String, Integer> drawToComplete ();

    /**
     * 抽奖直到抽到特定奖励
     *
     * @param target 特定奖励
     * @return 抽奖结果
     */
    Map<String, Integer> drawUntilGet (String target);

    /**
     * 抽奖直到抽到含有特定字符串的奖励
     *
     * @param target 特定字符串
     * @return 抽奖结果
     */
    Map<String, Integer> drawUntilContain (String target);

    /**
     * 抽奖
     *
     * @param count 抽奖次数
     * @return 抽奖结果
     */
    Map<String, Integer> draw (int count);

    /**
     * 清空抽奖池
     */
    void clear ();

    /**
     * 是否含有所有奖励
     */
    boolean hasAll ();

    /**
     * 打印抽奖池
     */
    void print ();

    /**
     * 获取总抽奖次数
     */
    int getDrawCount ();

    /**
     * 获取抽奖池类型
     */
    String getType ();
}
