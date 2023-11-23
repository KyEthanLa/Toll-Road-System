/*
    used in the case where there is an insufficient account balance in the try catch block
 */
public class InsufficientAccountBalanceException extends Exception {
    public InsufficientAccountBalanceException() {
        super();
    }
}