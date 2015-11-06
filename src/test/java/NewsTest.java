import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;

/**
 * Created by Administrator on 2015/1/31.
 */
public class NewsTest
{
    public static void aa()
    {
        String aa = "<p>\n" +
                "    <embed type=\"application/x-shockwave-flash\" class=\"edui-faked-video\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" src=\"http://player.youku.com/player.php/sid/XODgyNjE4MTc2/v.swf\" width=\"420\" height=\"280\" wmode=\"transparent\" play=\"true\" loop=\"false\" menu=\"false\" allowscriptaccess=\"never\" allowfullscreen=\"true\"/>\n" +
                "</p>\n" +
                "<p>\n" +
                "    <embed type=\"application/x-shockwave-flash\" class=\"edui-faked-music\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" src=\"http://box.baidu.com/widget/flash/bdspacesong.swf?from=tiebasongwidget&url=&name=%E8%BD%A6%E7%AB%99&artist=%E8%8C%83%E5%AE%97%E6%B2%9B&extra=%E8%8C%83%E5%AE%97%E6%B2%9B%E7%BB%8F%E5%85%B8%E9%85%8D%E4%B9%90%E9%80%89%E8%BE%91&autoPlay=false&loop=true\" width=\"400\" height=\"95\" align=\"none\" wmode=\"transparent\" play=\"true\" loop=\"false\" menu=\"false\" allowscriptaccess=\"never\" allowfullscreen=\"true\"/>\n" +
                "</p>\n" +
                "<p>\n" +
                "    <img src=\"/server/ueditor/upload/image/3 2.jpg\" style=\"\"/>\n" +
                "</p>\n" +
                "<p>\n" +
                "    <img src=\"/server/ueditor/upload/image/26.jpg\" style=\"\"/>\n" +
                "</p>\n" +
                "<p>\n" +
                "    <br/>\n" +
                "</p>";

        Document newContent = Jsoup.parse(aa);



        Whitelist whitelist = new Whitelist();


        System.out.println(Jsoup.clean(aa,Whitelist.relaxed()));

        //Query query = TextQuery.searching(new TextCriteria().matchingAny("coffee", "cake")).sortByScore();
        //List<Document> page = template.find(query, Document.class);


    }

    public static void main(String[] args) {
        aa();
    }
}
