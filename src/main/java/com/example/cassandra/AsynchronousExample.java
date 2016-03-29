package com.example.cassandra;


import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.sun.org.apache.xpath.internal.operations.String;


public class AsynchronousExample extends SimpleClient {
    public AsynchronousExample(){}

    private Session session;

   //ResultSetFuture implements the java.util.concurrent.Future<V> interface. Objects which implement this interface allow for non-blocking computation
   //the calling code may wait for the completion or check if it is done

    public ResultSetFuture getRows() {
        Statement statement = QueryBuilder.select().all().from("simplex","songs");
        return session.executeAsync(statement);
    }

    public static void main (String[] args) {
        AsynchronousExample client = new AsynchronousExample();
        client.connect("127.0.0.1");
        client.createSchema();
        client.loadData();
        ResultSetFuture resultSetFuture = client.getRows();
        for (Row row: resultSetFuture.getUninterruptibly()) {
            System.out.printf( "%s: %s / %s\n",
                    row.getString("artist"),
                    row.getString("title"),
                    row.getString("album") );
        }

    }


}
