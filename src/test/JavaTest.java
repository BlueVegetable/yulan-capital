import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class JavaTest {
    public static void main(String args[]) {
        String x="7";
        int y = 2;
        int z=2;
        System.out.println(x+y+z);
    }
}
