import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;
import org.ansj.dic.LearnTool;
import org.ansj.domain.Nature;
import org.ansj.domain.Term;
import org.ansj.recognition.NatureRecognition;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2015/1/24.
 */
public class Test001
{
    public static void main(String[] args) {

        /**
         * 第一个demo
         */
        List<Term> parse = NlpAnalysis.parse("让战士们过一个欢乐祥和的新春佳节。");
        System.out.println(parse);

        /**
         * 第二个demo
         */
        KeyWordComputer kwc = new KeyWordComputer(5);
        String title = "第16届环澳赛第五赛段全场录播";
        String content = "第16届环澳赛第五赛段全场录播。";
        Collection<Keyword> result = kwc.computeArticleTfidf(title, content);

        for (Keyword keyword : result) {
            System.out.println(keyword.getName());
        }

        /***
         * 第三个demo
         */
        List<Term> terms = ToAnalysis.parse("Ansj中文分词是一个真正的ict的实现.并且加入了自己的一些数据结构和算法的分词.实现了高效率和高准确率的完美结合!");
        new NatureRecognition(terms).recognition(); //词性标注
        System.out.println(terms);


        /****
         * 第四个
         */
        //构建一个新词学习的工具类。这个对象。保存了所有分词中出现的新词。出现次数越多。相对权重越大。
        LearnTool learnTool = new LearnTool() ;
        //进行词语分词。也就是nlp方式分词，这里可以分多篇文章
        NlpAnalysis.parse("第16届环澳赛第五赛段全场录播。", learnTool) ;
        NlpAnalysis.parse("第16届环澳赛第五赛段全场录播", learnTool) ;;
        //取得学习到的topn新词,返回前10个。这里如果设置为0则返回全部
        List<Map.Entry<String, Double>> entryList = learnTool.getTopTree(5);

        for (Map.Entry<String, Double> stringDoubleEntry : entryList)
        {
            System.out.println(stringDoubleEntry.getKey());
        }

        //只取得词性为Nature.NR的新词
        System.out.println(learnTool.getTopTree(10, Nature.NR));


        System.out.print(UUID.randomUUID().toString());



    }
}
