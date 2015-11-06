import com.bike.entity.Book;
import com.bike.entity.garmin.UserBikeFitSession;
import com.bike.repositories.garmin.GarminBikeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by liuyindong on 2015/11/4.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/elasticsearch.xml")
public class elasticsearch
{
    @Resource
    private GarminBikeRepository repository;

    @Before
    public void emptyData(){
        repository.deleteAll();
    }

    @Test
    public void shouldIndexSingleBookEntity(){

        Book book = new Book();
        book.setId("123455");
        book.setName("Spring Data Elasticsearch");
        book.setVersion(System.currentTimeMillis());


        UserBikeFitSession userBikeFitSession = new UserBikeFitSession();

        userBikeFitSession.setVersion(System.currentTimeMillis());
        userBikeFitSession.setId(UUID.randomUUID().toString());



        repository.save(userBikeFitSession);
        //lets try to search same record in elasticsearch
        UserBikeFitSession indexedBook = repository.findOne(book.getId());
        assertThat(indexedBook,is(notNullValue()));
        assertThat(indexedBook.getId(),is(book.getId()));
    }
}
