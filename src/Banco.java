import main.rdf.CtrlRDF;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

import java.util.ArrayList;

public class Banco {
	
	public ArrayList<ArrayList<String>> especialidades() //retorna especialidades
	{
		CtrlRDF leitor = new CtrlRDF();
		ArrayList<ArrayList<String>> especialidades = new ArrayList<ArrayList<String>>();
		ArrayList<String> linha = new ArrayList<String>();
		ArrayList<String> linha2 = new ArrayList<String>();

		Model raiz = leitor.carregarRDF();

		Query qry = QueryFactory.create("PREFIX bs:<http://base#>" +
				"SELECT ?data WHERE { " +
				"bs:especialidades bs:especialidade ?data .}");
		QueryExecution qe = QueryExecutionFactory.create(qry, raiz);
		ResultSet rs = qe.execSelect();
		while(rs.hasNext()) {
			QuerySolution qs = rs.next() ;
			Resource subject = qs.getResource("data");
			System.out.println("Subject: " + subject);
		}
		qe.close();

		linha.add("123");
		linha.add("ortopedista");
		linha.add("especialidade de ortopedista");

		linha2.add("456");
		linha2.add("dentista");
		linha2.add("especialidade de dentista");

		especialidades.add(linha);
		especialidades.add(linha2);


		return especialidades;
	
	}
	
	public ArrayList<ArrayList<String>> medicos(String cod_especialidade) //retorna medicos
	{
		//CRM, nome, cod_especialidade, ano_formacao, cod especialidade referencia especialidade, valor_consulta
		//Todos os medicos q tem a tal especialidade
		ArrayList<String> linha = new ArrayList<String>();
		linha.add("1");
		linha.add("claudio");
		linha.add("123");
		linha.add("1998");
		linha.add("50,00");
		
		ArrayList<String> linha2 = new ArrayList<String>();
		linha2.add("2");
		linha2.add("renato");
		linha2.add("123");
		linha2.add("1999");
		linha2.add("45,00");
		
		ArrayList<ArrayList<String>> medicos = new ArrayList<ArrayList<String>>();
		medicos.add(linha);
		medicos.add(linha2);
		
		return medicos;
	
	}
	
	public ArrayList<ArrayList<String>> consultas(String CRM) //retorna consultas
	{
		//CRM, nome, cod_especialidade, ano_formacao, cod especialidade referencia especialidade, valor_consulta
		//Todos as consultas do tal medico de tal CRM
		//paciente (CPF, nome, telefone_contato)
		//consulta (data_hora, CRM, CPF, CRM referencia medico, CPF referencia paciente)
		ArrayList<String> linha = new ArrayList<String>();
		String data = "2019-07-20 10:00";
		String[] dataSplit = data.split(" ");
		String dia = dataSplit[0];
		String hora = dataSplit[1];
		
		linha.add(dia);
		linha.add(hora);
		linha.add("1");
		linha.add("1111111111");
	
		
		ArrayList<String> linha2 = new ArrayList<String>();
		String data2 = "2019-07-20 11:00";
		String[] dataSplit2 = data2.split(" ");
		String dia2 = dataSplit2[0];
		String hora2 = dataSplit2[1];
		
		linha2.add(dia2);
		linha2.add(hora2);
		linha2.add("1");
		linha2.add("2222222222");
		
		
		ArrayList<String> linha3 = new ArrayList<String>();
		String data3 = "2019-07-21 09:00";
		String[] dataSplit3 = data3.split(" ");
		String dia3 = dataSplit3[0];
		String hora3 = dataSplit3[1];
		
		linha3.add(dia3);
		linha3.add(hora3);
		linha3.add("1");
		linha3.add("3333333333");
		
		ArrayList<ArrayList<String>> consultas = new ArrayList<ArrayList<String>>();
		consultas.add(linha);
		consultas.add(linha2);
		consultas.add(linha3);
		
		return consultas;
	
	}
}
