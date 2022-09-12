package xml;

/**
 * 돈을 관리하는 클래스
 * 
 * @author 이정윤
 */

public class Money {
  private int amount; // 총 금액

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public Money(int amount) {
    this.amount = amount;
  }

}
