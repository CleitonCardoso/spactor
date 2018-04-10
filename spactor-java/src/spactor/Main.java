package spactor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		List<List<String>> testGroups = parseFileIntoTestGroups( "teste.txt" );
		List<String> resultLines = proccessTests( testGroups );

		int i = 0;
		for (String resultLine : resultLines) {
			System.out.println( "Resultado do grupo " + i + ": " + resultLine );
		}
		i++;
	}

	/* parseia o arquivo em grupos de teste */
	private static List<List<String>> parseFileIntoTestGroups(String pathToFile) {
		List<String> lines = readFilesLineByLine( pathToFile );
		List<String> groupLines = new ArrayList<>();
		List<List<String>> testGroups = new ArrayList<>();

		for (String line : lines) {
			if (!testGroups.contains( groupLines ))
				testGroups.add( groupLines );
			/* Ignora o cabeçalho */
			if (!line.startsWith( "#" )) {
				if (!line.isEmpty())
					groupLines.add( line );
				/* pula linha, muda o grupo */
				if (line.isEmpty() && !groupLines.isEmpty())
					groupLines = new ArrayList<>();
			}
		}
		return testGroups;
	}

	private static List<String> proccessTests(List<List<String>> testGroups) {
		List<String> resultLines = new ArrayList<>();
		for (List<String> testGroup : testGroups) {
			Integer numberOfVertices = getNumberOfVerticesFromGroup( testGroup );
			int[][] matrix = getMatrixFromGroup( testGroup, numberOfVertices );
			List<List<Integer>> testPaths = getPathsFromGroup( testGroup );
			StringBuilder sb = new StringBuilder();
			for (List<Integer> path : testPaths)
				sb.append( isValid( matrix, path.get( 0 ), path.get( 1 ) ) ? "*" : "!" ).append( " " );
			resultLines.add( sb.toString() );

		}
		return resultLines;
	}

	private static Integer getNumberOfVerticesFromGroup(List<String> group) {
		for (String line : group) {
			if (line.length() == 1)
				return Integer.valueOf( line );
		}
		throw new RuntimeException( "Um dos grupos não possui o número de vertices" );
	}

	public static boolean isValid(int[][] matrix, int x, int y) {
		return matrix[x][y] == 1;
	}

	private static int[][] getMatrixFromGroup(List<String> group, Integer numberOfVertices) {
		int[][] matrix = new int[numberOfVertices][numberOfVertices];
		List<String> rows = group.stream().filter( line -> line.length() > 3 ).collect( Collectors.toList() );
		// x => número da linha
		for (int x = 0; x < rows.size(); x++) {
			String row = rows.get( x );
			String[] columns = row.split( " " );
			// y => número da coluna
			for (int y = 0; y < columns.length; y++) {
				String character = columns[y];
				if (character.equals( "." )) {
					matrix[x][y] = 1;
				}
			}
		}

		return matrix;
	}

	/* Mapeia todas as linhas de teste para uma lista de posições numéricas */
	private static List<List<Integer>> getPathsFromGroup(List<String> group) {
		List<String> rows = group.stream().filter( line -> line.length() > 3 ).collect( Collectors.toList() );
		List<String> paths = group.stream().filter( line -> line.length() == 3 ).collect( Collectors.toList() );

		List<List<Integer>> numericPaths = new ArrayList<>();

		for (String path : paths) {
			List<Integer> numericPath = new ArrayList<>();
			String[] pathArray = path.split( " " );
			for (int i = 0; i < pathArray.length; i++)
				for (int j = 0; j < rows.size(); j++)
					if (rows.get( j ).contains( pathArray[i] ))
						numericPath.add( j );

			numericPaths.add( numericPath );
		}
		return numericPaths;
	}

	public static List<String> readFilesLineByLine(String fileName) {
		List<String> lines = new ArrayList<>();
		InputStream inputStream = Main.class.getResourceAsStream( fileName );
		try {
			BufferedReader bufferreader = new BufferedReader( new InputStreamReader( inputStream ) );
			String line = null;
			do {
				line = bufferreader.readLine();
				if (line != null)
					lines.add( line.trim() );
			} while (line != null);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return lines;
	}

}
