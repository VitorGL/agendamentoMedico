package rdf;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.VCARD;

import java.io.*;

public class CtrlRDF {

    public Model carregarRDF() {

        String uri = "https://base";
        String ns = uri + "#";

        Model raiz = ModelFactory.createDefaultModel();

//        Property especialidades = raiz.createProperty(ns + "especialidades");
//        Property medicos = raiz.createProperty(ns + "medicos");
//        Property pacientes = raiz.createProperty(ns + "pacientes");
//        Property consultas = raiz.createProperty(ns + "consultas");

        Resource especialidade;
        String nsEsp = uri + "/especialidades#";

        Property nomeP = raiz.createProperty(nsEsp + "nome");

        Property descricaoP = raiz.createProperty(nsEsp + "descricao");


        String nome_arq = "src/DS/especialidade.txt";
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

        nome_arq = "src/DS/medico.txt";
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Resource paciente;
        String nsPac = uri + "/pacientes#";

        nomeP = raiz.createProperty(nsPac + "nome");
        Property telefoneP = raiz.createProperty(nsPac + "telefone_contato");


        nome_arq = "src/DS/paciente.txt";
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        Resource consulta;
        String nsCon = uri + "/consultas#";

        Property crmP = raiz.createProperty(nsCon + "crm");
        Property cpfP = raiz.createProperty(nsCon + "cpf");


        nome_arq = "src/DS/consulta.txt";
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return raiz;
    }

    public static void main(String[] args) {
        CtrlRDF parser = new CtrlRDF();

        Model raiz = ModelFactory.createDefaultModel();

        raiz.add(parser.carregarRDF());

        raiz.write(System.out);
    }
}
