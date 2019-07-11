import com.yulan.dao.PaymentBillDao;
import com.yulan.dao.ReturnCompensationBillDao;
import com.yulan.service.ReturnCompensationBillService;
import com.yulan.utils.FileUpload;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PaymentBillTest {
    @Autowired
    private PaymentBillDao paymentBillDao;
    @Autowired
    private ReturnCompensationBillService returnCompensationBillService;
    @Autowired
    private ReturnCompensationBillDao returnCompensationBillDao;
    @Test
    public void test1() {
        System.out.println(paymentBillDao.getPaymentBillByID("PB17060500002"));
    }
    @Test
    public void test2() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
        String previous = simpleDateFormat.format(timestamp);
        String maxID = returnCompensationBillDao.getMaxID(previous);

        Integer number = Integer.parseInt(maxID.split(previous)[1]);
        number++;
        String newID = "RZ" + previous + String.format("%05d", number);
        System.out.println(newID);
    }

    @Test
    public void test3(){
        System.out.println(FileUpload.getFileName());
    }
}
