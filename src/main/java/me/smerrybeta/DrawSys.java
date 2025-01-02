package i.os.processHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DrawSys {
    public static void main (String[] args) {
        // 文件路径
        String filePath = "output.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < 1000000; i++) {
                DrawMachine drawMachine = new DrawMachine();
                int sum = drawMachine.drawToComplete();

                // 将 sum 写入文件
                writer.write(String.valueOf(sum));
                writer.newLine(); // 换行
            }
            System.out.println("保存完成: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
