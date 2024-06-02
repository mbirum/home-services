package org.birum.home.services.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {

	public static String getStackTraceAsString(Throwable t) {
		try (StringWriter stringWriter = new StringWriter()) {
			try (PrintWriter printWriter = new PrintWriter(stringWriter)) {
				t.printStackTrace(printWriter);
				return stringWriter.toString();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
			return t.getMessage();
		}
	}
}
