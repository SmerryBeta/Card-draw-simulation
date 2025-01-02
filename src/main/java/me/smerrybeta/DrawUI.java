package me.smerrybeta;

import me.smerrybeta.obj.DrawMachine;

import javax.swing.*;
import java.awt.*;


public class GachaUI {
    private final DrawMachine drawMachine = new DrawMachine();

    public static void main (String[] args) {
        SwingUtilities.invokeLater(GachaUI :: new);
    }

    public GachaUI () {
        JFrame frame = new JFrame("抽卡模拟器");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // 输入区域
        JPanel inputPanel = new JPanel();
        JLabel inputLabel = new JLabel("输入抽卡次数：");
        JTextField inputField = new JTextField(10);
        JButton drawButton = new JButton("点击抽取");
        JButton drawAllButton = new JButton("抽全所有物品");
        JButton resetButton = new JButton("重置");

        inputPanel.add(inputLabel);
        inputPanel.add(inputField);
        inputPanel.add(drawButton);
        inputPanel.add(drawAllButton);
        inputPanel.add(resetButton);

        // 输出区域
        JTextArea outputArea = new JTextArea(15, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // 按钮逻辑
        drawButton.addActionListener(e -> {
            String inputText = inputField.getText();
            try {
                int drawCount = Integer.parseInt(inputText);

                outputArea.append("抽取了 " + drawCount + " 次，现奖励如下：\n");
                for (String t : drawMachine.PRIZE_NUM.keySet()) {
                    outputArea.append(
                            t + "：" + drawMachine.PRIZE_NUM.get(t) + "\n"
                    );
                }
                outputArea.append("目前奖励状态：" + (drawMachine.print() ? "齐" : "未齐") + "\n");
                outputArea.append("累计抽卡次数：" + drawMachine.getDrawCount() + " 次\n");
                outputArea.append("预计开支：" + drawMachine.getDrawCount() * 10 + " 元\n\n");
            } catch (NumberFormatException ex) {
                outputArea.append("请输入一个有效的数字！\n");
            }
        });

        drawAllButton.addActionListener(e -> {
            int drawCount = drawMachine.drawToComplete();
            outputArea.append("已抽全所有奖励，共计抽卡 " + drawCount + " 次\n");
            outputArea.append("预计开支：" + drawCount * 10 + " 元\n");
            outputArea.append("目前奖励状态：齐\n\n");
        });

        resetButton.addActionListener(e -> {
            drawMachine.clear();
            outputArea.setText("");
            outputArea.append("已重置抽奖状态！\n\n");
        });

        frame.setVisible(true);
    }
}
