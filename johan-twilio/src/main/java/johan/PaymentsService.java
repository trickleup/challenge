package johan;

import johan.model.PaymentRequest;
import johan.model.User;
import johan.model.Wallet;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentsService {

  private UserRepository userRepository;
  private PaymentsRequestsRepository paymentsRequestsRepository;

  public PaymentsService(
      UserRepository userRepository, PaymentsRequestsRepository paymentsRequestsRepository) {
    this.userRepository = userRepository;
    this.paymentsRequestsRepository = paymentsRequestsRepository;
  }

  public User createUser(String username, Long initialBalance) {
    User user = new User(username, initialBalance);
    userRepository.put(user);
    return user;
  }

  public Long getUserBalance(String username) {
    User user = userRepository.findByUsername(username);

    return user.getBalance();
  }

  public Long getAmountOwed(String username) {
    return paymentsRequestsRepository
        .findByPayee(username)
        .stream()
        .filter(pr -> !pr.isPaid())
        .mapToLong(PaymentRequest::getAmount)
        .sum();
  }

  public PaymentRequest requestAmount(String requesterUsername, String payeeUsername, Long amount) {
    PaymentRequest paymentRequest = new PaymentRequest(requesterUsername, payeeUsername, amount);
    paymentsRequestsRepository.put(paymentRequest);
    return paymentRequest;
  }

  public synchronized PaymentRequest settleRequest(String paymentRequestId) {
    PaymentRequest paymentRequest = paymentsRequestsRepository.findById(paymentRequestId);
    User payee = userRepository.findByUsername(paymentRequest.getPayeeUsername());
    User requester = userRepository.findByUsername(paymentRequest.getRequesterUsername());

    payee.getWallet().withdraw(paymentRequest.getAmount());
    requester.getWallet().putIn(paymentRequest.getAmount());
    paymentRequest.settle();

    return paymentRequest;
  }

  public List<PaymentRequest> getPaymentHistory(String username) {
    return paymentsRequestsRepository
        .findByPayee(username)
        .stream()
        .filter(PaymentRequest::isPaid)
        .collect(Collectors.toList());
  }
}
