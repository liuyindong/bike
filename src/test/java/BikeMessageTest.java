import com.bike.entity.BikeMessages;
import com.bike.entity.BikeTag;
import com.bike.entity.Page;
import com.bike.server.message.BikeMessageServer;
import com.bike.server.tags.TagServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ld on 2015/1/27.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BikeMessageTest
{
    @Autowired
    private BikeMessageServer bikeMessageServer;

    @Autowired
    private TagServer tagServer;

    @Test
    public void addMongoDB()
    {



        List<BikeMessages> messagesList = bikeMessageServer.bikeMessageList(new BikeMessages());

        for (BikeMessages bikeMessages : messagesList)
        {
            List<BikeTag> bikeTagList = tagServer.tagByNewUUID(bikeMessages.getUuid());

            System.out.println(bikeTagList.size());

            bikeMessages.setBikeTagList(bikeTagList);

            bikeMessageServer.add(bikeMessages);

            System.out.print(bikeMessages.getTitle());
        }


    }

 //   @Test
    public void newByTJSelect()
    {
       Page page = bikeMessageServer.newByTJSelect(1, "9bde2892-c444-4cdd-9f8f-6da9449c06a8", "6b587623-ddf9-4c67-9a93-036aafbe11da");

    //    System.out.print(page.getDatas());

        System.out.print("dddd");
    }

   // @Test
    public void bikeNewsXiangSi()
    {
        List<String> stringList = new ArrayList<>();

        stringList.add("1c014cee-02d4-4e5b-8c15-12174b18d55c");
        stringList.add("d4756dc6-5ace-429e-82a7-ef4a41da44db");
        stringList.add("b03b4f68-1d6e-48d5-a828-c16e9dc8545f");

        List<BikeMessages> bikeMessagesList = bikeMessageServer.bikeNewsXiangSi(stringList,"a0555313-dcc1-48d0-a173-57f07b297f0a");

        System.out.print(bikeMessagesList);
    }

   // @Test
    public void showTopTen()
    {
        List<BikeMessages> bikeMessagesList = bikeMessageServer.showTopTen();

        System.out.print(bikeMessagesList);

    }
}
