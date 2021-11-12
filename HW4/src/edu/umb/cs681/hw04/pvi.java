package edu.umb.cs681.hw04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.Stream;
public class pvi {
	
	public static void main(String[] args) throws Exception  
	{  

		
Path path = Paths.get("D:\\Model_11.2.1_20200228_results.csv");
		
		try( Stream<String> lines = Files.lines(path)) {
			List<List<String>> matrix =
	
					lines.filter((String line) -> line.startsWith("\"0.4"))
						 .filter((String line) -> line.endsWith("\"0.0\""))
						 .filter((String line)  -> line.contains("South"))
						 .map( line -> {
							return Stream.of(line.split(","))
									 //.map(value->value.substring(1, value.length()-2))
									 .collect(Collectors.toList());	})
						 .collect( Collectors.toList());
			matrix.forEach(value -> System.out.println(value));
		} catch (IOException ex) {} 
    }
	

}
