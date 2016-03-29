import com.datastax.driver.core.BoundStatement;
import com.example.cassandra.SimpleClient;


public class SimpleApp {

    public static void main (String[] args) {
        SimpleClient client = new SimpleClient();
        client.connect("127.0.0.1");
        client.createSchema();
        client.loadData();
        client.querySchema();
        client.close();
    }
}
