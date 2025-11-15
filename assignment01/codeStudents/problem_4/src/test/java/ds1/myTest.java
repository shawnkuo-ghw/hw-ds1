package ds1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class myTest {

    @Test
    void testOne() {
        SBlockchain blockchain = new SBlockchain(2, 1000);
        blockchain.processTransaction("0", "A", 50);
        System.out.println(blockchain.toString());;   
        blockchain.processTransaction("0", "C", 50);
        System.out.println(blockchain.toString());;   

    }
}