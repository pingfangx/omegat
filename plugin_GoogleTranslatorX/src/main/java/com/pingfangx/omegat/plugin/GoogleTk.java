package com.pingfangx.omegat.plugin;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * @author pingfangx
 * @date 2018/1/3
 */
public class GoogleTk {
    public static String getTk(String text) {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("javascript");
            engine.eval("\n" +
                    "function TL(a) {\n" +
                    "    var k = \"\";\n" +
                    "    var b = 406644;\n" +
                    "    var b1 = 3293161072;\n" +
                    "\n" +
                    "    var jd = \".\";\n" +
                    "    var $b = \"+-a^+6\";\n" +
                    "    var Zb = \"+-3^+b+-f\";\n" +
                    "    for (var e = [], f = 0, g = 0; g < a.length; g++) {\n" +
                    "        var m = a.charCodeAt(g);\n" +
                    "        128 > m ? e[f++] = m :\n" +
                    "            (2048 > m ? e[f++] = m >> 6 | 192 :\n" +
                    "                (55296 == (m & 64512) && g + 1 < a.length && 56320 == (a.charCodeAt(g + 1) & 64512) ?\n" +
                    "                    (m = 65536 + ((m & 1023) << 10) + (a.charCodeAt(++g) & 1023),\n" +
                    "                        e[f++] = m >> 18 | 240,\n" +
                    "                        e[f++] = m >> 12 & 63 | 128)\n" +
                    "                    :\n" +
                    "                    e[f++] = m >> 12 | 224,\n" +
                    "                    e[f++] = m >> 6 & 63 | 128),\n" +
                    "                e[f++] = m & 63 | 128)\n" +
                    "    }\n" +
                    "    a = b;\n" +
                    "    for (f = 0; f < e.length; f++) a += e[f],\n" +
                    "        a = RL(a, $b);\n" +
                    "    a = RL(a, Zb);\n" +
                    "    a ^= b1 || 0;\n" +
                    "    0 > a && (a = (a & 2147483647) + 2147483648);\n" +
                    "    a %= 1E6;\n" +
                    "    return a.toString() + jd + (a ^ b)\n" +
                    "};\n" +
                    "function RL(a, b) {\n" +
                    "    var t = \"a\";\n" +
                    "    var Yb = \"+\";\n" +
                    "    for (var c = 0; c < b.length - 2; c += 3) {\n" +
                    "        var d = b.charAt(c + 2),\n" +
                    "            d = d >= t ? d.charCodeAt(0) - 87 : Number(d),\n" +
                    "            d = b.charAt(c + 1) == Yb ? a >>> d : a << d;\n" +
                    "        a = b.charAt(c) == Yb ? a + d & 4294967295 : a ^ d\n" +
                    "    }\n" +
                    "    return a\n" +
                    "}");
            if (engine instanceof Invocable) {
                Invocable invoke = (Invocable) engine;
                return (String) invoke.invokeFunction("TL", text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
