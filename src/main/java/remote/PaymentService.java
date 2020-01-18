package remote;

public interface PaymentService {

    void pay(String pesel, double price) throws NotEnoughMoneyException;
}
