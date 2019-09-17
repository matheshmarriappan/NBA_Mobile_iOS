package com.nba.ios.api.helper;

import java.io.IOException;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;

import com.cucumber.listener.Reporter;

public class AppiumServer {

	public static void main(String[] args) throws InterruptedException {
		
		appium_Start();
		System.out.println("Server started. calling stop service");
		appium_stop();
	}

	public static void appium_Start() throws InterruptedException {
		
		/*AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
		service.start();
		System.out.println("Service started");
		Thread.sleep(7000);*/
		
		CommandLine cmd = new CommandLine("/usr/local/bin/node");
		//cmd.addArgument("appium")
		cmd.addArgument("/usr/local/bin/appium",false);
		//cmd.addArgument("/usr/local/lib/node_modules/appium/build/lib/appium.js", false);
		cmd.addArgument("--address");
		cmd.addArgument("127.0.0.1");
		cmd.addArgument("--port");
		cmd.addArgument("4723");
		cmd.addArgument("--udid");
		cmd.addArgument("1e93dd47cb72c4eb539fa61bf6f22761de16a371");
		cmd.addArgument("--webdriveragent-port");
		cmd.addArgument("5723");
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(1);
		
		try {
			executor.execute(cmd, resultHandler);
			Thread.sleep(70000);
			System.out.println("Appium started");
		} catch (IOException e) {
			System.out.println("Exception "+e.getMessage());
		}
		
	}
	
	public static void appium_stop() {
		System.out.println("closed the drivers");
        String sb = "KillAll -c node";
        CommandLine command = new CommandLine(sb);
        System.out.println("Print before executing  the command "+command.toString());
        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);
        try {
        executor.execute(command, resultHandler);
        Thread.sleep(5000);
        System.out.println("Appium server killed in iOS.");
        } catch (IOException e) {
        e.printStackTrace();
        } catch (InterruptedException e) {
        e.printStackTrace();
        }

		
	}

}
