package conexao;

import org.apache.jena.atlas.lib.StrUtils;
import org.apache.jena.fuseki.main.FusekiServer;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.tdb.TDBLoader;
import org.apache.jena.tdb.store.DatasetGraphTDB;
import org.apache.jena.tdb.store.GraphTDB;
import org.apache.jena.update.*;
import org.apache.jena.vocabulary.RDF;
import rdf.CtrlRDF;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static void main(String[] args) throws SQLException {
        String diretorioBD = "C:\\Users\\vitor\\Documents\\GitHub\\agendamentoMedico\\BDS\\TDB";

        Dataset dataset = TDBFactory.createDataset(diretorioBD);
        CtrlRDF ctrlRDF = new CtrlRDF();

        Model model = ctrlRDF.carregarRDF();


//        dataset.begin(ReadWrite.WRITE);
//        try {
//            dataset.getDefaultModel().add(model);
//            // API calls to a model in the dataset
//
//            // A SPARQL query will see the new statement added.
//            try (QueryExecution qExec = QueryExecutionFactory.create(
//                    "SELECT (count(*) AS ?count) { ?s ?p ?o} LIMIT 10",
//                    dataset)) {
//                ResultSet rs = qExec.execSelect();
//                ResultSetFormatter.out(rs);
//            }
//
//            // ... perform a SPARQL Update
//            GraphStore graphStore = GraphStoreFactory.create(dataset) ;
//            String sparqlUpdateString = StrUtils.strjoinNL(
//                    "PREFIX . <http://example/>",
//                    "INSERT { :s :p ?now } WHERE { BIND(now() AS ?now) }"
//            ) ;
//
//            UpdateRequest request = UpdateFactory.create(sparqlUpdateString) ;
//            UpdateProcessor proc = UpdateExecutionFactory.create(request, graphStore) ;
//            proc.execute() ;
//
//            // Finally, commit the transaction.
//            dataset.commit() ;
//            // Or call .abort()
//        } finally {
//            dataset.end() ;
//        }

        dataset.begin(ReadWrite.WRITE);
        dataset.getDefaultModel().add(model);
        dataset.commit() ;
        dataset.end() ;



        dataset.begin(ReadWrite.READ);
        Model modelo = dataset.getDefaultModel();
        modelo.write(System.out);
        dataset.end();


//        FusekiServer servidor = FusekiServer.create().add("TDBBD", dataset).build();
//
//        servidor.start();


//        TDBLoader.load((GraphTDB) dataset,"dsdsd");
    }
}
