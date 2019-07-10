package main.conexao;

import org.apache.jena.atlas.lib.StrUtils;
import org.apache.jena.fuseki.main.FusekiServer;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.tdb.TDBFactory;

import main.rdf.CtrlRDF;
import org.apache.jena.update.*;

public class Conexao {

    public static void main(String[] args) {
        String diretorioBD = "C:\\Users\\vitor\\Documents\\GitHub\\agendamentoMedico\\TDB";

        Dataset dataset = TDBFactory.createDataset(diretorioBD);
        CtrlRDF ctrlRDF = new CtrlRDF();

        Model model = ctrlRDF.carregarRDF();

        dataset.begin(ReadWrite.WRITE);
        try {
            Model modelBD = dataset.getDefaultModel();

            model.add(model);

            try (QueryExecution qExec = QueryExecutionFactory.create(
                    "SELECT (count(*) AS ?count) { ?s ?p ?o} LIMIT 10",
                    dataset)) {
                ResultSet rs = qExec.execSelect();
                ResultSetFormatter.out(rs);
            }

            // ... perform a SPARQL Update
            GraphStore graphStore = GraphStoreFactory.create(dataset);
            String sparqlUpdateString = StrUtils.strjoinNL(
                    "PREFIX . <http://base/>",
                    "INSERT { :s :p ?now } WHERE { BIND(now() AS ?now) }"
            ) ;

            UpdateRequest request = UpdateFactory.create(sparqlUpdateString);
            UpdateProcessor proc = UpdateExecutionFactory.create(request, graphStore);
            proc.execute();

            dataset.commit();
        } finally {
            dataset.end();
        }



        dataset.begin(ReadWrite.READ);
        Model modelo = dataset.getDefaultModel();
        modelo.write(System.out);
        dataset.end();


        FusekiServer servidor = FusekiServer.create().add("TDBBD", dataset).build();

        servidor.start();


//        TDBLoader.load((GraphTDB) dataset,"dsdsd");
    }
}
