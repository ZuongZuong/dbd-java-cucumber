import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import constants.FrameworkConstants;

public class DockerRunner {
	
	public static void run() throws IOException, InterruptedException {
		// Construct the docker-compose command
        String[] command = { "docker-compose", "up", "-d" }; 

        // Create a ProcessBuilder
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        
        // Set the working directory (optional, if your docker-compose.yml is not in the same directory)
        processBuilder.directory(new File(FrameworkConstants.PROJECT_PATH));

        // Start the process
        Process process = processBuilder.start();

        // Read the output (optional)
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        // Wait for the process to finish
        int exitCode = process.waitFor();
        System.out.println("Docker Compose exited with code: " + exitCode);
	}
}
