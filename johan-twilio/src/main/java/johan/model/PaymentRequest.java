package johan.model;

import java.util.UUID;

public class PaymentRequest {

  private String id;
  private String requesterUsername;
  private String payeeUsername;
  private Long amount;
  private boolean paid;

  public PaymentRequest(String requesterUsername, String payeeUsername, Long amount) {
    this.id = UUID.randomUUID().toString();
    this.requesterUsername = requesterUsername;
    this.payeeUsername = payeeUsername;
    this.amount = amount;
    this.paid = false;
  }

  public String getId() {
    return id;
  }

  public String getRequesterUsername() {
    return requesterUsername;
  }

  public String getPayeeUsername() {
    return payeeUsername;
  }

  public Long getAmount() {
    return amount;
  }

  public boolean isPaid() {
    return paid;
  }

  @Override
  public String toString() {
    return "PaymentRequest{"
        + "id='"
        + id
        + '\''
        + ", requesterUsername='"
        + requesterUsername
        + '\''
        + ", payeeUsername='"
        + payeeUsername
        + '\''
        + ", amount="
        + amount
        + '}';
  }

    public void settle() {
      paid = true;
    }
}
