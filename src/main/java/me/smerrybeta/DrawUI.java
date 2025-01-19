package me.smerrybeta;

import me.smerrybeta.obj.DrawMa;
import me.smerrybeta.obj.incard.CardDrawMa;
import me.smerrybeta.obj.ingame.GameDrawMa;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class DrawUI {
    private final CardDrawMa card = new CardDrawMa();
    private DrawMa drawMa = card;
    private final GameDrawMa game = new GameDrawMa();
    private final LinkedList<DrawMa> linkedDrawMa = new LinkedList<>(List.of(card, game));
    private final int defaultSize = 16;


    public static void main (String[] args) {
        SwingUtilities.invokeLater(DrawUI :: new);
    }

    public DrawUI () {
        JFrame frame = new JFrame("抽卡模拟器");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // 输入区域
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS)); // 垂直布局

        // 第一行：输入框
        JPanel firstRow = new JPanel(new FlowLayout(FlowLayout.CENTER)); // 水平布局，左对齐
        JLabel inputLabel = new JLabel("输入抽卡次数：");
        JTextField inputField = new JTextField("1", 20);

        firstRow.add(inputLabel);
        firstRow.add(inputField);

        // 第二行：按钮
        JPanel secondRow = new JPanel(new FlowLayout(FlowLayout.CENTER)); // 水平布局，居中
        JButton drawButton = new JButton("点击抽取");
        JButton drawAllButton = new JButton("抽全所有物品");
        JButton seeAllButton = new JButton("展示所有");
        JButton resetButton = new JButton("重置");
        JButton switchButton = new JButton("切换");

        secondRow.add(drawButton);
        secondRow.add(drawAllButton);
        secondRow.add(seeAllButton);
        secondRow.add(resetButton);
        secondRow.add(switchButton);

        // 添加到输入面板
        inputPanel.add(firstRow);
        inputPanel.add(secondRow);

        // 输出区域
        JTextPane outputPane = new JTextPane();
        outputPane.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputPane);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // 按钮逻辑
        drawButton.addActionListener(e -> {
            clearOutput(outputPane);
            String inputText = inputField.getText();
            try {
                int drawCount = Integer.parseInt(inputText);

                Map<String, Integer> dm = drawMa.draw(drawCount);
                appendColoredText(outputPane, "抽取了 " + drawCount + " 次，现奖励如下：\n", Color.RED, 20);

                for (String t : dm.keySet()) {
                    appendColoredText(outputPane, t + "：", Color.BLUE, defaultSize);
                    appendColoredText(outputPane, dm.get(t) + "\n", Color.ORANGE, defaultSize);
                }

                appendColoredText(outputPane, "\n目前奖励状态：" + (drawMa.hasAll() ? "齐" : "未齐") + "\n", Color.RED, defaultSize);
                appendColoredText(outputPane, "累计抽卡次数：" + drawMa.getDrawCount() + " 次\n", new Color(255, 0, 255), defaultSize);
                appendColoredText(outputPane, "预计开支：" + drawMa.getDrawCount() * 10 + " 元\n\n", new Color(0, 25, 30), defaultSize);
            } catch (NumberFormatException ex) {
                appendColoredText(outputPane, "请输入一个有效的数字！\n", Color.RED, defaultSize);
            }
        });

        drawAllButton.addActionListener(e -> {
            clearOutput(outputPane);
            Map<String, Integer> dm = drawMa.drawToComplete();
            appendColoredText(outputPane, "已抽全所有奖励，共抽卡 " +
                    dm.values().stream().mapToInt(Integer :: intValue).sum() + " 次，奖励如下：\n", Color.RED, 20);
            for (String t : dm.keySet()) {
                appendColoredText(outputPane, t + "：", Color.BLUE, defaultSize);
                appendColoredText(outputPane, dm.get(t) + "\n", Color.ORANGE, defaultSize);
            }

            appendColoredText(outputPane, "\n累计抽卡次数：" + drawMa.getDrawCount() +
                    (drawMa.getClass().toString().endsWith("CardDrawMa") ? "包" : "次"), new Color(255, 0, 255), defaultSize);
        });

        resetButton.addActionListener(e -> {
            drawMa.clear();
            clearOutput(outputPane);
            appendColoredText(outputPane, "已重置抽奖状态！\n\n", Color.RED, defaultSize);
        });

        seeAllButton.addActionListener(e -> {
            clearOutput(outputPane);
            appendColoredText(outputPane, "抽取了 " + drawMa.getDrawCount() + " 次，现已有奖励如下：\n", Color.RED, 20);
            for (String t : drawMa.getPrizeNum().keySet()) {
                appendColoredText(outputPane, t + "：", Color.BLUE, defaultSize);
                appendColoredText(outputPane, drawMa.getPrizeNum().get(t) + "\n", Color.ORANGE, defaultSize);
            }
        });

        switchButton.addActionListener(e -> {
            clearOutput(outputPane);
            int target = getIndex(drawMa);
            drawMa = linkedDrawMa.get(target);
            appendColoredText(outputPane, "切换至" + drawMa.getType() + "\n\n", Color.RED, 30);
        });

        frame.setVisible(true);
    }

    // 添加带颜色的文本
    private void appendColoredText(JTextPane pane, String text, Color color, int fontSize) {
        StyledDocument doc = pane.getStyledDocument();
        Style style = pane.addStyle("Style", null);

        // 设置字体颜色和大小
        StyleConstants.setForeground(style, color);
        StyleConstants.setFontSize(style, fontSize);

        try {
            // 插入文本并应用样式
            doc.insertString(doc.getLength(), text, style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private int getIndex (DrawMa drawMa) {
        return (linkedDrawMa.indexOf(drawMa) + 1) % linkedDrawMa.size();
    }

    // 清空输出框
    private void clearOutput (JTextPane pane) {
        pane.setText("");
    }
}
