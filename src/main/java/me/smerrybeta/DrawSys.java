package me.smerrybeta;

import me.smerrybeta.obj.ingame.GameDrawMa;

public class DrawSys {
    public static void main (String[] args) {
        GameDrawMa gameDrawMa = new GameDrawMa();

        int sum = gameDrawMa.drawToComplete().values().stream().mapToInt(Integer::intValue).sum();
        gameDrawMa.print();
        boolean full = gameDrawMa.hasAll();
        System.out.println("[INFO]本次抽卡：\033[1;31m" + (full ? "齐" : "未齐") + "\033[0m");
        System.out.println("[INFO]现已抽卡：\033[1;31m " + sum + " \033[0m次，预计开支：\033[1;31m " + sum * 10 + " \033[0m元");

        // 刷新已抽奖励列表
        gameDrawMa.clear();

        sum = gameDrawMa.draw(100).size();
        gameDrawMa.print();
        full = gameDrawMa.hasAll();
        System.out.println("[INFO]本次抽卡：\033[1;31m" + (full ? "齐" : "未齐") + "\033[0m");
        System.out.println("[INFO]现已抽卡：\033[1;31m " + sum + " \033[0m次，预计开支：\033[1;31m " + sum * 10 + " \033[0m元");

        System.out.println(gameDrawMa.getPrizeNum());
    }
}
