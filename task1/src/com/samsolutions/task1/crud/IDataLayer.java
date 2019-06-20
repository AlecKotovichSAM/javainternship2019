/**
 * 
 */
package com.samsolutions.task1.crud;

import java.io.IOException;
import java.util.Collection;

/**
 * @author olegk
 *
 */
public interface IDataLayer {

	Long appendNewLine(String str) throws IOException;

	String readLine(Long id) throws IOException;

	String editLine(Long id, String newData) throws IOException;

	void deleteLine(Long id) throws IOException;

	Collection<String> readAllLines();

}
