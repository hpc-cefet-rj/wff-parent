package uml;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import net.sourceforge.plantuml.SourceStringReader;

public class UmlCreatePng {

	public static void main(String[] args) throws IOException {
		doIt(null);
	}

	public static void doIt(String[] args) throws IOException {
		OutputStream png = new FileOutputStream("uml-01.png");
		String source = "@startuml\n";
		source += "Measurement <|-- HistoricalMeasurement \n"
				+ "HistoricalMeasurement --> HistoricalSeries \n"
				+ "HistoricalSeries -> HistoricalMeta \n"
				+ "HistoricalMeta --> DataSource \n";
		source += " \n";
		source += " \n";
		source += " \n";
		source += " \n";
		source += " \n";
		source += " \n";
		source += " \n";
		source += " \n";
		source += " \n";
		source += " \n";
		source += " \n";
		source += " \n";
		source += " \n";
		source += " \n";
		source += " \n";
		source += " \n";
		source += " \n";
		source += "@enduml\n";
		SourceStringReader reader = new SourceStringReader(source);
		// Write the first image to "png"
		String desc = reader.generateImage(png);
		// Return a null string if no generation
	}

}
