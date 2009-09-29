/*******************************************************************************
 * This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     Peter Smith
 *******************************************************************************/
package org.boris.winrun4j.test;

import java.io.File;

import org.boris.winrun4j.winapi.Environment;
import org.boris.winrun4j.winapi.Kernel32;
import org.boris.winrun4j.winapi.Shell32;

public class EnvironmentTester implements Runnable
{
    public static void main(String[] args) throws Exception {
        System.out.println("Logical Drives");
        File[] logicalDrives = Environment.GetLogicalDrives();
        for (File f : logicalDrives) {
            System.out.println(f);
        }
        System.out.println();

        System.out.println("Special Folder Paths");
        for (int i = 0; i < 100; i++) {
            File f = Shell32.getFolderPath(i);
            if (f != null)
                System.out.printf("%x: %s\n", i, f);
        }
        System.out.println();

        System.out.println("Environment Variables");
        System.out.printf("TEMP: %s\n", Environment
                .GetEnvironmentVariable("TEMP"));
        System.out.printf("TEMP2: %s\n", Environment
                .GetEnvironmentVariable("TEMP2"));
        System.out.println(Environment.GetEnvironmentVariables());
        System.out.println(Environment.ExpandEnvironmentString("temp: %TEMP%"));
        System.out.println();

        System.out.println("Command Line Args");
        String[] ags = Environment.GetCommandLine();
        for (String a : ags) {
            System.out.println(a);
        }
        System.out.println();

        System.out.println("Version Info");
        System.out.println(Reflection.toString(Environment.GetVersionEx()));
        System.out.println();

        System.out.println("Tick Count");
        for (int i = 0; i < 10; i++)
            System.out.println(Kernel32.GetTickCount());

        System.out.printf("Current Process Id: %d\n", Kernel32
                .GetCurrentProcessId());

        System.out.println(System.getProperty("user.dir"));

        Runtime.getRuntime().addShutdownHook(
                new Thread(new EnvironmentTester()));
    }

    public void run() {
        System.out.println("Shutting down...");
    }
}
