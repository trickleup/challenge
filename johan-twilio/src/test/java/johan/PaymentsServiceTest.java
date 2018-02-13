package johan;

import johan.model.PaymentRequest;
import johan.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PaymentsServiceTest {

  private PaymentsService paymentsService;

  @Before
  public void setUp() throws Exception {
    paymentsService = new PaymentsService(new UserRepository(), new PaymentsRequestsRepository());
  }

  @Test
  public void createUser() {
    User user = paymentsService.createUser("johndoe", 100L);

    assertNotNull(user);
    assertEquals("johndoe", user.getUsername());
    assertEquals(new Long(100L), user.getBalance());
  }

  @Test
  public void getBalanceOwed() {
    paymentsService.createUser("johndoe", 100L);
    paymentsService.createUser("janedoe", 100L);

    PaymentRequest paymentRequest = paymentsService.requestAmount("johndoe", "janedoe", 55L);

    assertEquals(new Long(55L), paymentsService.getAmountOwed("janedoe"));
  }

  @Test
  public void payRequest() {
    paymentsService.createUser("johndoe", 100L);
    paymentsService.createUser("janedoe", 100L);

    PaymentRequest paymentRequest = paymentsService.requestAmount("johndoe", "janedoe", 55L);

    paymentsService.settleRequest(paymentRequest.getId());

    assertEquals(new Long(0L), paymentsService.getAmountOwed("janedoe"));
    assertEquals(new Long(45L), paymentsService.getUserBalance("janedoe"));
    assertEquals(new Long(155L), paymentsService.getUserBalance("johndoe"));

    assertEquals(paymentRequest, paymentsService.getPaymentHistory("janedoe").get(0));
  }
}
