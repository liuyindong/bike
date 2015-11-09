import java.util.Calendar;

/**
 * Created by liuyindong on 2015/11/9.
 */
public class CalendarTest001
{
    public static void main(String[] args)
    {
        Calendar calendar = Calendar.getInstance();
        long start = calendar.getTimeInMillis();



        calendar = Calendar.getInstance();
        long end = calendar.getTimeInMillis();

        System.out.println(((end - start) / 1000 / 60 / 60 / 24 / 30) + "月");
        System.out.println(((end - start) / 1000 / 60 / 60 / 24) + "日");
        System.out.println(((end - start) / 1000 / 60 / 60) + "小时");
        System.out.println(((end - start) / 1000 / 60 ) + "分钟");
        System.out.println(((end - start) / 1000 ) + "秒");

    }
}
