package org.usfirst.frc.team8.robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Supplies wrapper methods for using adb to control the Android
 *
 * <h1><b>Fields</b></h1>
 * 	<ul>
 * 		<li>Instance and State variables:
 * 			<ul>
 * 				<li>{@link AdbComputerBridge#s_instance}: Private static instance of this class (Singleton)</li>
 * 			</ul>
 * 		</li>
 * 	</ul>
 *
 * <h1><b>Accessors and Mutators</b></h1>
 * 	<ul>
 * 		<li>{@link AdbComputerBridge#getInstance()}</li>
 * 	</ul>
 *
 * <h1><b>External Access Functions</b>
 * 	<br><BLOCKQUOTE>For using as a wrapper for RIOdroid</BLOCKQUOTE></h1>
 * 	<ul>
 * 		<li>{@link AdbComputerBridge#exec(String)}</li>
 * 		<li>{@link AdbComputerBridge#init()}</li>
 * 	</ul>
 *
 * @author Alvin
 *
 */
public class AdbComputerBridge {

	// Instance and state variables
	private static AdbComputerBridge s_instance;

	/**
	 * Creates an AdbComputerBridge instance
	 * Cannot be called outside as a Singleton
	 */
	private AdbComputerBridge(){}

	/**
	 * @return The instance of the ACB
	 */
	public static AdbComputerBridge getInstance(){
		if(s_instance == null){
			s_instance = new AdbComputerBridge();
		}
		return s_instance;
	}

	/**
	 * Executes a command in the command line during
	 * runtime
	 * @param command Command to execute
	 * @return Console output of executing the command
	 */
	public String exec(String command){
		// Builds the output of the console
		StringBuilder out = new StringBuilder();

		try {
			String line;

			// Execute the command as a process
			System.out.println("Execing");
			Process p = Runtime.getRuntime().exec(command);
			System.out.println("You probably won't see this");

			// Read in console output from the process object
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = input.readLine()) != null) {
				System.out.println("Getting line");
				out.append(line+"\n");
			}
			System.out.println("Finished getting lines");
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return out.toString();
	}

	/**
	 * Initializes this computer as an adb server
	 */
	public void init(){
		System.out.println(this.exec("adb start-server"));;
	}
}
