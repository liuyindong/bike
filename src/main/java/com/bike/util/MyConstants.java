package com.bike.util;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.text.DecimalFormat;

/**
 * Created by zz on 2015/9/9.
 */
public class MyConstants extends BodyTagSupport
{

    public static float msTokmh(float msMsg)
    {
        return Float.parseFloat(new DecimalFormat("#.00").format(msMsg*3.6));
    }

    public static float mTokm(float kmMsg)
    {
        return Float.parseFloat(new DecimalFormat("#.00").format(kmMsg/1000));
    }



    //秒转换成**：**：**
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    private static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }
}
