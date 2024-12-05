import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import constants.FrameworkConstants;

public class MavenRunner {

	public static void main(String[] args) throws IOException, InterruptedException {
		// Construct the Maven test command
		String[] command = { "mvn", "test", "DTest=TestRunner" };

		// Create a ProcessBuilder
		ProcessBuilder processBuilder = new ProcessBuilder(command);

		// Set the working directory (optional)
		processBuilder.directory(new File(FrameworkConstants.PROJECT_PATH));

		// Start the process
		Process process = processBuilder.start();

		// Read the output (optional)
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}

		// Read error output (optional)
		BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
		while ((line = errorReader.readLine()) != null) {
			System.err.println(line);
		}

		// Wait for the process to finish
		int exitCode = process.waitFor();
		System.out.println("Maven test command exited with code: " + exitCode);

		// Check if tests passed or failed based on the exit code
		if (exitCode == 0) {
			System.out.println("Tests passed!");
		} else {
			System.err.println("Tests failed!");
		}
	}
}
