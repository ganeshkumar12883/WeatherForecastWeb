package com.project.exam.weatherforecast.core.reader.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.project.exam.weatherforecast.core.parser.LineDataParser;

/**
 * This class will help in reading the input files and converting them to
 * respective output object required. The input file to be read will be passed
 * in the calling function while instantiating the reader.
 * 
 * @author ganeshkumarvellaichamy
 *
 * @param <T>
 */
public class FileDataLineReader<T> {

	private BufferedReader bReader;

	private LineDataParser<T> lineDataParser;

	public FileDataLineReader(File inputFile, LineDataParser<T> lineDataParser) throws IOException {
		/*
		 * Check all possible exception places and throw them 1) File can give
		 * NullPointerException/FileNotFoundException 2) File cannot be readable
		 * giving IOException
		 */
		if (inputFile == null)
			throw new NullPointerException("Input File is null and cannot continue");
		if (!inputFile.exists())
			throw new FileNotFoundException("Input File not found and cannot continue");
		if (!inputFile.canRead())
			throw new IOException("Input File cannot be read and cannot continue");

		/*
		 * The inputFile needs a BufferedReader to read the file line by line.
		 * Instantiating the same instance using the FileReader to file.
		 */
		this.bReader = new BufferedReader(new FileReader(inputFile));
		this.lineDataParser = lineDataParser;
	}

	/**
	 * Method to read the file passed in to this implementation class. The read
	 * contents are converted into respective object using the parser defined.
	 * 
	 * @throws IOException
	 */
	public T readFileData() throws IOException {
		if (bReader == null)
			return null;

		String lineRead = bReader.readLine();
		while (lineRead != null) {
			T readDataObj = lineDataParser.parseLineData(lineRead);
			if (readDataObj != null)
				return readDataObj;
		}
		
		return null;
	}

}
