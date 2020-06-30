package challenge;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import challenge.model.Jogador;
import challenge.util.CsvFileUtil;

public class Main {
	
	
	private static CsvFileUtil fileCsv = new CsvFileUtil("data.csv",",");
	
	
	private static List<Jogador> leiaJogadores(){
		try {
			return fileCsv.buildJogadores();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Quantas nacionalidades (coluna `nationality`->14) diferentes existem no arquivo?
	public int q1() {
		List<String> nationalitys = new ArrayList<String>();
		try {
			for (String nationality : fileCsv.getValorColuna(14)) {
				if (!nationalitys.contains(nationality)) {
					nationalitys.add(nationality);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return nationalitys.size();
	}

	// Quantos clubes (coluna `club`->3) diferentes existem no arquivo?
	// Obs: Existem jogadores sem clube.
	public int q2() {
		List<String> clubs = new ArrayList<String>();
		try {
			for (String club : fileCsv.getValorColuna(3)) {
				if (club.isEmpty()) continue;
				if (!clubs.contains(club)) {
					clubs.add(club);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return clubs.size();
	}

	// Liste o nome completo (coluna `full_name`->2) dos 20 primeiros jogadores.
	public List<String> q3() {
		List<String> fullNames = new ArrayList<String>();
		
		try {
			fullNames = fileCsv.getValorColuna(2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fullNames.subList(0, 20);
	}

	// Quem são os top 10 jogadores que possuem as maiores cláusulas de rescisão?
	// (utilize as colunas `full_name`->2 e `eur_release_clause`->19)
	public List<String> q4() {
		List<Jogador> jogadores = leiaJogadores();
		List<String> names = new ArrayList<>();
		
		jogadores.sort(Comparator.comparing(Jogador::getEurRelClause).reversed());
		jogadores.subList(0, 10).forEach(jogador -> names.add(jogador.getFullName()));
		return names;
	}

	// Quem são os 10 jogadores mais velhos (use como critério de desempate o campo `eur_wage`->17)?
	// (utilize as colunas `full_name->2` e `birth_date`->9)
	public List<String> q5() {
		List<Jogador> jogadores = leiaJogadores();
		
		Stream<String> names = jogadores.stream().sorted(Comparator.comparing(Jogador::getBirthDate)
				.thenComparing(Comparator.comparing(Jogador::getEurWage)))
				.map(Jogador::getFullName);
		
		return names.limit(10).collect(Collectors.toList());
	}

	// Conte quantos jogadores existem por idade. Para isso, construa um mapa onde as chaves são as idades e os valores a contagem.
	// (utilize a coluna `age` -> 6)
	public Map<Integer, Integer> q6() {
		Map<Integer, Integer> idades = new HashMap<>();
		List<Jogador> jogadores = leiaJogadores();
		
		for (Jogador jogador : jogadores) {
			if (!idades.containsKey(jogador.getAge())){
				idades.put(jogador.getAge(), 1);
			} else {
				idades.put(jogador.getAge(), idades.get(jogador.getAge())+1);
			}
		}
		
		return idades;
	}

}
