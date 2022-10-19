package Unit12JDBC;

import javax.sql.RowSet;
import javax.sql.rowset.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class ConnectionExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            File file = new File("./sampledb");
            System.out.println("db path " + file.getAbsolutePath());
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection(
                    "jdbc:derby:" + file.getAbsolutePath() + ";create=true"
            );
            statement = connection.createStatement();
            System.out.println("db path " + file.getAbsolutePath());
            System.out.println("Create database successful!");
            String sql = "create table student(\n" +
                    "id bigint PRIMARY KEY \n" +
                    " generated always as identity(start with 1, increment by 1),\n" +
                    "name varchar(1000),\n" +
                    "age integer default 20\n" +
                    ")";
            System.out.println(statement.execute(sql));
        } finally {
            statement.close();
            connection.close();
        }

    }
}

class InsertDataExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            File file = new File("./sampledb");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection(
                    "jdbc:derby:" + file.getAbsolutePath() + ";create=true"
            );
            statement = connection.createStatement();
            statement.executeUpdate("insert into student(name,age) values ('Tran Van B',20)");
            statement.executeUpdate("insert into student(name,age) values ('Tran Van C',12)");
            statement.executeUpdate("insert into student(name,age) values ('Tran Van D',56)");

            System.out.println("Successful");
        } finally {
            statement.close();
            connection.close();
        }
    }
}

class SelectDataExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            File file = new File("./sampledb");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection(
                    "jdbc:derby:" + file.getAbsolutePath() + ";create=true"
            );
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from student");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.println("id: " + id + " name: " + name + " age: " + age);
            }
            rs.close();
        } finally {
            statement.close();
            connection.close();
        }
    }
}

class UpdateDataExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        File file = new File("./sampledb");
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        connection = DriverManager.getConnection(
                "jdbc:derby:" + file.getAbsolutePath() + ";create=true");
        statement = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE student " +
                "SET name = ? WHERE id =?");
        preparedStatement.setString(1, "Nguyen Ngoc Khai");
        preparedStatement.setInt(2, 2);
        preparedStatement.executeUpdate();
        connection.close();

    }
}

class TransactionExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        File file = new File("./sampledb");
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        connection = DriverManager.getConnection(
                "jdbc:derby:" + file.getAbsolutePath() + ";create=true");
        statement = connection.createStatement();
        connection.setAutoCommit(false);
        for (int i = 0; i < 10; i++) {
            String name = "Tran Van " + i;
            int age = 10 + i;
            statement.executeUpdate("insert into student (name,age) " +
                    "values " + "('" + name + "'," + age + ")");
        }
        connection.rollback();
        connection.setAutoCommit(true);
        ResultSet rs = statement.executeQuery("select count(*) from student");
        if (rs.next()) {
            System.out.println("Total records= " + rs.getInt(1));
            connection.close();
        }
    }
}

class BatchProcessingExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        File file = new File("./sampledb");
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        connection = DriverManager.getConnection(
                "jdbc:derby:" + file.getAbsolutePath() + ";create=true");
        statement = connection.createStatement();
        for (int i = 0; i < 20; i++) {
            String name = "Nguyen Ngoc " + i;
            int age = 20 + i;
            String sql = "insert into student (name,age) " +
                    "values " + "('" + name + "'," + age + ")";
            statement.addBatch(sql);
        }
        statement.executeBatch();
        ResultSet rs = statement.executeQuery("select count(*) from student");
        if (rs.next()) {
            System.out.println("Total records= " + rs.getInt(1));
            connection.close();
        }
    }
}

class JdbcMetadataExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        try {
            File file = new File("./sampledb");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection(
                    "jdbc:derby:" + file.getAbsolutePath() + ";create=true");
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println("DB version: " + metaData.getDriverVersion());
            System.out.println("Driver name: " + metaData.getDriverName());

        } finally {
            connection.close();
        }

    }
}

class JdbcRowSetExample {
    public static void main(String[] args) throws SQLException {
        JdbcRowSet jdbcRowSetExample = RowSetProvider.newFactory().createJdbcRowSet();
        File file = new File("./sampledb");
        jdbcRowSetExample.setUrl("jdbc:derby:" + file);
        jdbcRowSetExample.setCommand("select *from student ");
        jdbcRowSetExample.execute();
        while (jdbcRowSetExample.next()) {
            System.out.println(jdbcRowSetExample.getInt(1) +
                    "\t" + jdbcRowSetExample.getString("name") +
                    "\t" + jdbcRowSetExample.getInt(3));
        }

        jdbcRowSetExample.first();
        jdbcRowSetExample.updateString("name", "Hoang Van Y");
        jdbcRowSetExample.commit();
        jdbcRowSetExample.first();
        System.out.println(jdbcRowSetExample.getInt("id") + "\t" + jdbcRowSetExample.getString(2));
        jdbcRowSetExample.absolute(8);
        System.out.println(jdbcRowSetExample.getString("name") + ":" + jdbcRowSetExample.getInt("age"));

        jdbcRowSetExample.close();
    }
}

class DataAccessLogic {
    private final static Logger logger = Logger.getLogger(DataAccessLogic.class.getName());
    Connection connection = null;
    CachedRowSet rowSet;

    public DataAccessLogic() throws SQLException {
        File file = new File("./sampledb");
        this.connection = DriverManager.getConnection("jdbc:derby:" + file.getAbsolutePath() + ";create=true");
        RowSetFactory factory = RowSetProvider.newFactory();
        rowSet = factory.createCachedRowSet();
        rowSet.setCommand("select * from student");
        rowSet.execute(connection);
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.log(Level.WARNING, e.toString());
            }
        }

    }

    int pageSize = 5;

    List<String> loadNames(int page) throws SQLException {
        List<String> names = new ArrayList<>();
        rowSet.setPageSize(pageSize);
        int start = (page - 1) * pageSize + 1;
        if (!rowSet.absolute(start)) {
            return names;
        }
        rowSet.previous();
        while (rowSet.next()) {
            names.add(rowSet.getString("name"));
            if (names.size() >= pageSize) break;
        }

        return names;
    }

    int numberOfPage() throws SQLException {
        Statement statement = null;
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select count(*) from student");
        if (!rs.next()) return 0;
        int total = rs.getInt(1);
        int totalPage = total / pageSize;
        if (total % pageSize != 0) totalPage++;
        return totalPage;
    }

    public static void main(String[] args) throws SQLException {
        DataAccessLogic data = new DataAccessLogic();
        List<String> names = data.loadNames(5);
        //names.forEach(name -> System.out.println(name));
        // System.out.println("Total page = "+data.numberOfPage());
        IntStream.range(1, data.numberOfPage() + 1).forEach(page -> {
            System.out.println("============" + page);
            try {
                List<String> name = data.loadNames(page);
                name.forEach(a -> System.out.println(a));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });
    }
}

class WebRowSetExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Connection connection = null;
        Statement statement = null;
        try {
            File file = new File("./sampledb");
            System.out.println("db path " + file.getAbsolutePath());
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection(
                    "jdbc:derby:" + file.getAbsolutePath() + ";create=true"
            );
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from student");
            file = new File("output.xml");
            FileWriter Writer = new FileWriter(file);
            RowSetFactory factory = RowSetProvider.newFactory();
            WebRowSet webRowSet = factory.createWebRowSet();
            webRowSet.writeXml(rs, Writer);
            Desktop.getDesktop().open(file);
        }finally {
            connection.close();
            statement.close();

        }

    }
}
class DataFilter implements Predicate {

    @Override
    public boolean evaluate(RowSet rs) {
        CachedRowSet rowSet = (CachedRowSet) rs;
        try {
            return rowSet.getString("name").indexOf("D") >-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean evaluate(Object value, int column) throws SQLException {
        return false;
    }

    @Override
    public boolean evaluate(Object value, String columnName) throws SQLException {
        return false;
    }
}
class DataFilterExample {
    public static void main(String[] args) throws SQLException {
        File file = new File("./sampledb");
        FilteredRowSet frs = RowSetProvider.newFactory().createFilteredRowSet();
        frs.setUrl("jdbc:derby:"+file);
        frs.setCommand("select * from student");
        frs.setFilter(new DataFilter());
        frs.execute();
        System.out.println("ID\tName\t\t Age");
        while (frs.next()) {
            System.out.println(frs.getInt(1)+"\t"
                    +frs.getString("name")+"\t"+frs.getInt(3));
        }

    }
}
class DbFunction {
    public static void getAge(String name,int[] ages) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        File file = new File("./sampledb");
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        connection = DriverManager.getConnection(
                "jdbc:derby:" + file.getAbsolutePath() + ";create=true"
        );
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select max(age) from student where" +
                " name like '%"+name+"%'");
        ages[0] = rs.next()? rs.getInt(1):-1;
        connection.close();

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        int[] ages = new int[1];
        getAge("Khai",ages);
        System.out.println(ages[0]);
    }
}
class CreateStoredProcedureExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        File file = new File("./sampledb");
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        connection = DriverManager.getConnection(
                "jdbc:derby:" + file.getAbsolutePath() + ";create=true"
        );
        statement = connection.createStatement();
        statement.executeUpdate("create procedure GETAGE(STREAM_NAME VARCHAR(255), " +
                "OUT AGE INT) PARAMETER STYLE JAVA READS " +
                "SQL DATA LANGUAGE JAVA EXTERNAL NAME " +
                "'jdbc.DBFunction.getAge'");
        System.out.println("done");
        connection.close();


    }
}









