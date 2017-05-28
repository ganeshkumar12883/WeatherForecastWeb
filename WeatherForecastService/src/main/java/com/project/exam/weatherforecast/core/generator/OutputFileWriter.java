package com.project.exam.weatherforecast.core.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class OutputFileWriter {

	private final static Logger logger = Logger.getLogger(OutputFileWriter.class.getName());

	public boolean generateOutputDataFile(List<String> outputData, String outputFile) throws IOException {

		File file = new File(outputFile);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new IOException("Failed to create output file. Error occured...");
			}
		}
		BufferedWriter bWriter = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));

		if (outputData != null && outputData.size() > 0) {
			for (String outputDataLine : outputData) {
				bWriter.write(outputDataLine);
				bWriter.newLine();
			}
			bWriter.close();
		} else {
			logger.severe("No data found to geenrate the output file");
			bWriter.close();
			return false;
		}
		return true;
	}

}
