import com.yulan.dao.PaymentBillDao;
import com.yulan.service.ReturnCompensationBillService;
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
    @Autowired
    private ReturnCompensationBillService returnCompensationBillService;
    @Test
    public void test1() {
        System.out.println(paymentBillDao.getPaymentBillByID("PB17060500002"));
    }
    @Test
    public void test2() {
//        System.out.println(returnCompensationBillService.getSimpleReturnCompensationBills("C01613",1,10));
    }
}
