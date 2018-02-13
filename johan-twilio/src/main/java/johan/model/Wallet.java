package johan.model;

public class Wallet {
  Long balance;

  public Wallet(Long initialBalance) {
    this.balance = initialBalance;
  }

  public Long getBalance() {
    return balance;
  }

  public void withdraw(Long amount) {
    balance -= amount;
  }

  public void putIn(Long amount) {
    balance += amount;
  }

  @Override
  public String toString() {
    return "Wallet{" + "balance=" + balance + '}';
  }
}
