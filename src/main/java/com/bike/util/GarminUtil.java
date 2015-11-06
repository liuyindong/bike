package com.bike.util;

/**
 * Created by zz on 2015/9/15.
 */
public class GarminUtil
{
    public static int judge(Short par) {
        if (par >= 122 && par <= 152) {
            return 1;
        } else if (par >= 153 && par <= 164) {
            return 2;
        } else if (par >= 165 && par <= 172) {
            return 3;
        } else if (par >= 173 && par <= 183) {
            return 4;
        } else if (par >= 184 && par <= 187) {
            return 5;
        } else if (par >= 188 && par <= 194) {
            return 6;
        } else if (par >= 195 && par <= 204) {
            return 7;
        } else {
            return 8;
        }
    }

    public static double toDegrees(final Integer s) {
        return s * (180D/Math.pow(2,31)) ;
    }
}
