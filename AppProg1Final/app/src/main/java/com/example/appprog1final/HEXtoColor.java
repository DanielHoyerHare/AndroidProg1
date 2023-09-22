package com.example.appprog1final;

import android.graphics.Color;

public class HEXtoColor {
    public static int RGB(String r, String g, String b) {
        String colorStr = "#" + r + g + b;
        return Color.parseColor(colorStr);
    }
}
