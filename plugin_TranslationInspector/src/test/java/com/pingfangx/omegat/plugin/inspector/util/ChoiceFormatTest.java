package com.pingfangx.omegat.plugin.inspector.util;

import org.junit.Test;

import java.text.MessageFormat;

public class ChoiceFormatTest {
    @Test
    public void test() {
        String pattern = "Would you like to stop {1, choice, 1#the running one|2#{1, number} running instances}?";
        String cnPattern = "你确定要停止正在运行的 {1} 个实例吗?";
        for (int i = 0; i < 10; i++) {
            Object[] args = new Object[]{"", i};
            System.out.println(i + "->" + MessageFormat.format(pattern, args));
            System.out.println(i + "->" + MessageFormat.format(cnPattern, args));
        }
    }
}
