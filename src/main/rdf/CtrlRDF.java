package main.rdf;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.rdf.model.Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CtrlRDF {

    public Model carregarRDF() {

        String uri = "https://base";
        String ns = uri + "#";

        Model raiz = ModelFactory.createDefaultModel();

        Property especialidadeP = raiz.createProperty(ns + "especialidade");
        Property medicoP = raiz.createProperty(ns + "medico");
        Property pacienteP = raiz.createProperty(ns + "paciente");
        Property consultaP = raiz.createProperty(ns + "consulta");

        Resource especialidadesR = raiz.createResource(ns + "especialidades");
        Resource medicosR = raiz.createResource(ns + "medicos");
        Resource pacientesR = raiz.createResource(ns + "pacientes");
        Resource consultasR = raiz.createResource(ns + "consultas");



        Resource especialidade;
        String nsEsp = uri + "/especialidades#";

        Property nomeP = raiz.createProperty(nsEsp + "nome");

        Property descricaoP = raiz.createProperty(nsEsp + "descricao");


        String nome_arq = "data/especialidade.txt";
        File arq = new File(nome_arq);
        BufferedReader leitor;
        String linha = null;

        try {
            leitor = new BufferedReader(new FileReader(arq));

            while ((linha = leitor.readLine()) != null) {
                String codigo = linha.substring(0, linha.indexOf('|'));
                linha = linha.substring(linha.indexOf('|')+1);

                String nome = linha.substring(0, linha.indexOf('|'));

                String descricao = linha.substring(linha.indexOf('|')+1);


                especialidade = raiz.createResource(nsEsp + codigo);

                especialidade.addProperty(nomeP, nome);

                especialidade.addProperty(descricaoP, descricao);

                especialidadesR.addProperty(especialidadeP, especialidade);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        Resource medico;
        String nsMed = uri + "/medicos#";

        nomeP = raiz.createProperty(nsMed + "nome");
        Property codEspecialidadeP = raiz.createProperty(nsMed + "cod_especialidade");
        Property anoFormacaoP = raiz.createProperty(nsMed + "ano_formacao");
        Property valorConsultaP = raiz.createProperty(nsMed + "valor_consulta");

        nome_arq = "data/medico.txt";
        arq = new File(nome_arq);
        leitor = null;
        linha = null;

        try {
            leitor = new BufferedReader(new FileReader(arq));

            while ((linha = leitor.readLine()) != null) {
                String crm = linha.substring(0, linha.indexOf('|'));
                linha = linha.substring(linha.indexOf('|')+1);

                String nome = linha.substring(0, linha.indexOf('|'));
                linha = linha.substring(linha.indexOf('|')+1);

                String codEspecialidade = linha.substring(0, linha.indexOf('|'));
                linha = linha.substring(linha.indexOf('|')+1);

                String anoFormacao = linha.substring(0, linha.indexOf('|'));
                linha = linha.substring(linha.indexOf('|')+1);

                String valorConsulta = linha.substring(linha.indexOf('|')+1);


                medico = raiz.createResource(nsMed + crm);

                medico.addProperty(nomeP, nome);

                medico.addProperty(codEspecialidadeP, raiz.getResource(nsEsp + codEspecialidade));

                medico.addProperty(anoFormacaoP, anoFormacao);

                medico.addProperty(valorConsultaP, valorConsulta);

                medicosR.addProperty(medicoP, medico);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Resource paciente;
        String nsPac = uri + "/pacientes#";

        nomeP = raiz.createProperty(nsPac + "nome");
        Property telefoneP = raiz.createProperty(nsPac + "telefone_contato");


        nome_arq = "data/paciente.txt";
        arq = new File(nome_arq);
        leitor = null;
        linha = null;

        try {
            leitor = new BufferedReader(new FileReader(arq));

            while ((linha = leitor.readLine()) != null) {
                String cpf = linha.substring(0, linha.indexOf('|'));
                linha = linha.substring(linha.indexOf('|')+1);

                String nome = linha.substring(0, linha.indexOf('|'));
                linha = linha.substring(linha.indexOf('|')+1);

                String telefone = linha.substring(linha.indexOf('|')+1);


                paciente = raiz.createResource(nsPac + cpf);

                paciente.addProperty(nomeP, nome);

                paciente.addProperty(telefoneP, telefone);

                pacientesR.addProperty(pacienteP, paciente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        Resource consulta;
        String nsCon = uri + "/consultas#";

        Property crmP = raiz.createProperty(nsCon + "crm");
        Property cpfP = raiz.createProperty(nsCon + "cpf");


        nome_arq = "data/consulta.txt";
        arq = new File(nome_arq);
        leitor = null;
        linha = null;

        try {
            leitor = new BufferedReader(new FileReader(arq));

            while ((linha = leitor.readLine()) != null) {
                String data_hora = linha.substring(0, linha.indexOf('|'));
                linha = linha.substring(linha.indexOf('|')+1);

                String crm = linha.substring(0, linha.indexOf('|'));
                linha = linha.substring(linha.indexOf('|')+1);

                String cpf = linha.substring(linha.indexOf('|')+1);


                consulta = raiz.createResource(nsCon + data_hora);

                consulta.addProperty(crmP, raiz.getResource(nsMed + crm));

                consulta.addProperty(cpfP, raiz.getResource(nsPac + cpf));

                consultasR.addProperty(consultaP, consulta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return raiz;
    }

    public static void main(String[] args) {
        CtrlRDF leitor = new CtrlRDF();

        Model raiz = leitor.carregarRDF();

        raiz.write(System.out);;

        Query qry = QueryFactory.create("PREFIX bs:<http://base/especialidades#>" +
                "PREFIX uri:<http://base#>" +
                "SELECT DISTINCT ?data WHERE { " +
                "uri:especialidades uri:especialidade ?data .}");
        QueryExecution qe = QueryExecutionFactory.create(qry, raiz);
        ResultSet rs = qe.execSelect();

        System.out.println(rs.getResourceModel());
        while(rs.hasNext()) {
            QuerySolution qs = rs.next();
            Resource subject = qs.getResource("data");
            System.out.println("Subject: " + subject);
        }
        qe.close();
    }
}
