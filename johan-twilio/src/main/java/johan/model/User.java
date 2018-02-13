package johan.model;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class User {
  private String username;
  private Wallet wallet;

  public User(String username, Long initialBalance) {
    this.username = username;
    this.wallet = new Wallet(initialBalance);
  }

  public Wallet getWallet() {
    return wallet;
  }

  public Long getBalance() {
    return wallet.getBalance();
  }

  public String getUsername() {
    return username;
  }

  @Override
  public String toString() {
    return "User{" + "username='" + username + '\'' + ", wallet=" + wallet + '}';
  }
}
