import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class JavaTest extends Thread{

        public static void main(String[] args) {
            JavaTest t=new JavaTest();
            JavaTest s=new JavaTest();
            t.start();
            System.out.println("one.");
            s.start();
            System.out.println("two.");
        }
        public void run() {
            System.out.println("Thread");
        }

}

