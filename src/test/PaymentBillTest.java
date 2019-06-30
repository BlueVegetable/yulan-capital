import com.yulan.dao.PaymentBillDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PaymentBillTest {
    @Autowired
    private PaymentBillDao paymentBillDao;
    @Test
    public void test1() {
        System.out.println(paymentBillDao.getPaymentBillByID("PB17060500002"));
    }
}
