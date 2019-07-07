package conexao;

import org.apache.jena.query.Dataset;
import org.apache.jena.tdb.TDBFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static void main(String[] args) throws SQLException {
        String diretorioBD = "C:\\Users\\vitor\\.IntelliJIdea2019.1\\config\\consoles\\db\\23de16b8-b08a-4343-8021-936a08d41850\\console.sql";

        Dataset dataset = TDBFactory.createDataset(diretorioBD);

        Connection conn = DriverManager.getConnection("jdbc:jena:mem:empty=true");
    }
}
