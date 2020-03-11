package challenge.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import challenge.model.Jogador;

public class CsvFileUtil {
	
	private String fileName = "data.csv";
	private String csvSplit = ",";
	
	public CsvFileUtil(String fileName, String split) {
		this.fileName = fileName;
		this.csvSplit = split;
	}
	
	private File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }

    }
	
	public List<String> getLinhas() throws FileNotFoundException {
		List<String> linhas = new ArrayList<>();
		Scanner scanner = new Scanner(getFileFromResources(fileName));
		
		while (scanner.hasNext()) {
			linhas.add(scanner.nextLine());
		}
		scanner.close();
		
		return linhas;
		
	}
	
	public List<String> getValorColuna(int column) throws FileNotFoundException{
		
		List<String> values = new ArrayList<>();
		Scanner scan = new Scanner(getFileFromResources(fileName));
		
		while (scan.hasNext()) {
			String[] linha = scan.nextLine().split(csvSplit);
			if (linha[0].equalsIgnoreCase("ID")) continue;
			values.add(linha[column]);
		}
		
		return values;
		
	}
	
	public List<Jogador> buildJogadores() throws FileNotFoundException{
		List<Jogador> jogadores = new ArrayList<>();
		
		Scanner scan = new Scanner(getFileFromResources(fileName));
		
		while (scan.hasNext()) {
			String[] line = scan.nextLine().split(csvSplit);
			if (line[0].equalsIgnoreCase("ID")) continue;
			BigDecimal eurRelClause = line[18].equals("") ? BigDecimal.ZERO : new BigDecimal(line[18]);
			Jogador jogador = new Jogador.Builder( Long.parseLong(line[0]))
					.fullName(line[2])
					.inClub(line[3])
					.withAge(Integer.parseInt(line[6]))
					.andBirthDate(LocalDate.parse(line[8]))
					.fromNationality(line[14])
					.withEurWage(new BigDecimal(line[17]))
					.withEurReleaseClause(eurRelClause)
					.build();
			jogadores.add(jogador);
		}
		
		return jogadores;
	}
	
}
