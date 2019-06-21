/**
 * 
 */
package com.samsolutions.task1.crud;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * @author olegk
 *
 */
public class FileDataLayer implements IDataLayer {

	private final Path tmpFile;
	private static final String DELIMITER = "\t";

	public FileDataLayer() throws IOException {
		this.tmpFile = Files.createTempFile("CrudFile", ".txt");
	}

	@Override
	public Long appendNewLine(String str) throws IOException {
		Long newId = null;
		List<String> lines = Files.readAllLines(tmpFile);
		if (lines.size() == 0) {
			newId = 1L;
			Files.write(this.tmpFile,
					new StringBuilder() //
							.append(newId) //
							.append(System.lineSeparator()) //
							.append(newId) //
							.append(DELIMITER) //
							.append(str) //
							.append(System.lineSeparator()) //
							.toString().getBytes(),
					StandardOpenOption.APPEND);
		} else {
			newId = Long.valueOf(lines.get(0)) + 1;

			List<String> newLines = new ArrayList<>(Arrays.asList(newId.toString()));

			newLines.addAll(lines.subList(1, lines.size()));

			String newLine = new StringBuilder() //
					.append(newId) //
					.append(DELIMITER) //
					.append(str) //
					.toString();

			newLines.add(newLine);

			overwriteFile(newLines);
		}

		return newId;
	}

	private void overwriteFile(List<String> newLines) throws IOException {
		Files.write(this.tmpFile, newLines, //
				StandardOpenOption.CREATE, //
				StandardOpenOption.TRUNCATE_EXISTING);
	}

	@Override
	public String readLine(Long id) throws IOException {
		return Files.lines(tmpFile).filter(line -> isFoundById(id, line)).findFirst().orElse(null);
	}

	private boolean isFoundById(Long id, String line) {
		return line.matches("^(" + id + DELIMITER + ").*$");
	}

	@Override
	public String editLine(Long id, String newData) throws IOException {
		if (readLine(id) != null) {
			List<String> lines = Files.readAllLines(tmpFile);
			ListIterator<String> it = lines.listIterator();
			while (it.hasNext()) {
				String line = it.next();
				if (isFoundById(id, line)) {
					String updatedLine = new StringBuilder() //
							.append(id) //
							.append(DELIMITER) //
							.append(newData) //
							.toString();
					it.set(updatedLine);
					break;
				}
			}
			overwriteFile(lines);
			return readLine(id);
		}
		return null;
	}

	@Override
	public void deleteLine(Long id) throws IOException {
		List<String> lines = Files.readAllLines(tmpFile);
		lines.removeIf(line -> isFoundById(id, line));
		overwriteFile(lines);
	}

	@Override
	public Collection<String> readAllLines() {
		try {
			List<String> readAllLines = Files.readAllLines(tmpFile);
			if (readAllLines == null || readAllLines.size() == 0) {
				return Collections.emptyList();
			} else {
				return readAllLines.subList(1, (int) Files.lines(tmpFile).count());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
