package johan;

import johan.model.PaymentRequest;
import johan.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class PaymentsRequestsRepository {

  private ConcurrentHashMap<String, PaymentRequest> paymentRequests;

  public PaymentsRequestsRepository() {
    this.paymentRequests = new ConcurrentHashMap<>();
  }

  public void put(PaymentRequest paymentRequest) {
    paymentRequests.put(paymentRequest.getId(), paymentRequest);
  }

  public PaymentRequest findById(String id) {
    return paymentRequests.get(id);
  }

  public List<PaymentRequest> findByPayee(String username) {
    return paymentRequests
        .entrySet()
        .stream()
        .filter(e -> e.getValue().getPayeeUsername().equals(username))
        .map(Map.Entry::getValue)
        .collect(Collectors.toList());
  }
}
