import com.datastax.driver.dse.DseCluster;
import com.datastax.driver.dse.DseSession;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.ResultSet;
import java.util.Iterator;


public class SampleMain {

    public static void main(String[] args){
        DseCluster cluster = null;
        try {
            cluster = DseCluster.builder()
                    .addContactPoint("127.0.0.1")
                    .build();
            DseSession session = cluster.connect();

            Row row = session.execute("select release_version from system.local").one();
            System.out.println("Release version:" + row.getString("release_version"));

            //List all Keyspaces
            ResultSet rs = session.execute("select keyspace_name from system_schema.keyspaces;");

            Iterator<Row> iter = rs.iterator();
            while (iter.hasNext()) {
                if (rs.getAvailableWithoutFetching() == 100 && !rs.isFullyFetched())
                    rs.fetchMoreResults();
                Row row2 = iter.next();
                System.out.println("Keyspace name: " + row2.getString("keyspace_name"));
            }

            //Describe all tables in a specified keyspace

            rs = session.execute("select * from system_schema.columns where keyspace_name = \'system_auth\';");

            iter = rs.iterator();
            while (iter.hasNext()) {
                if (rs.getAvailableWithoutFetching() == 100 && !rs.isFullyFetched())
                    rs.fetchMoreResults();
                Row row2 = iter.next();
                System.out.println("Keyspace name: " + row2.getString("keyspace_name") + "   " +
                        "Table name:" + row2.getString("table_name") + "   " +
                        "Column name:" + row2.getString("column_name"));
            }

        } finally {
            if (cluster != null) cluster.close();
        }
    }
}



