package johan;

import johan.model.PaymentRequest;
import johan.model.User;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class Commands {

  private PaymentsService paymentsService;

  public Commands(PaymentsService paymentsService) {
    this.paymentsService =
        new PaymentsService(new UserRepository(), new PaymentsRequestsRepository());
  }

  @ShellMethod("Create a user")
  public String createUser(String username, Long initialBalance) {

    User user = paymentsService.createUser(username, initialBalance);

    return "Created user: " + user;
  }

  @ShellMethod("Get the balance a user")
  public String getBalance(String username) {

    Long balance = paymentsService.getUserBalance(username);

    return "Balance: " + String.valueOf(balance);
  }

  @ShellMethod("Request money")
  public String requestAmount(String requesterUsername, String payeeUsername, Long amount) {

    PaymentRequest request =
        paymentsService.requestAmount(requesterUsername, payeeUsername, amount);

    return "Request: " + String.valueOf(request);
  }

  @ShellMethod("Request money")
  public String settleRequest(String requestId) {

    PaymentRequest request = paymentsService.settleRequest(requestId);

    return "Settled!";
  }
}
