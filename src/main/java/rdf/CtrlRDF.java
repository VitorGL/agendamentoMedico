package rdf;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.VCARD;

public class CtrlRDF {
    public static void main(String[] args) {
        Model model = ModelFactory.createDefaultModel();

        int crm = 2;

        String baseURI    = "jdbc:h2:mem:default#";

        Resource raiz = model.createResource(baseURI + "medicos");

        Resource medico = model.createResource(baseURI + crm);

        Resource medico2 = model.createResource(baseURI + 3);

        Property med = model.createProperty(baseURI + "medico");

        Property nome = model.createProperty(baseURI + "nome");

//        Property codEspecialidade = model.createProperty(baseURI + "medicos");

        Property anoFormacao = model.createProperty(baseURI + "ano_formacao");

        Property valorConsulta = model.createProperty(baseURI + "valor_consulta");

//        medico.addProperty(CRM, "Jao das Neves");

        medico.addProperty(nome, "Jao das Neves");

//        medico.addProperty(codEspecialidade, raiz);

        medico.addProperty(anoFormacao, "1994");

        medico.addProperty(valorConsulta, "193");

        model.add(raiz, med, medico);
        model.add(raiz, med, medico2);


        medico2.addProperty(nome, "Jeff");
        medico2.addProperty(anoFormacao, "2001");
        medico2.addProperty(valorConsulta, "2000");

        model.write(System.out);
//        model.add(ResourceFactory.createResource("medicu"), RDF.value, model.createTypedLiteral("pirimpimpim"));
    }
}
