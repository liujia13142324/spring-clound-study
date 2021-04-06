import com.lj.springcloud.study.providerticket.ProviderTicketApplication8001;
import com.lj.springcloud.study.providerticket.properties.TestMyProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProviderTicketApplication8001.class)
public class ContextTest {

  @Autowired
  TestMyProperties testMyProperties;
  
  @Test
  public void test1(){
    System.out.println(testMyProperties);
  }

}
