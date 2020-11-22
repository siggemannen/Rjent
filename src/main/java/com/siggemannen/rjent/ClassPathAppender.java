package com.siggemannen.rjent;

import java.lang.instrument.Instrumentation;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.jar.JarFile;
import java.util.stream.Stream;

/**
 * A java agent class that appends provided folder's jar files into class path on the fly
 */
public class ClassPathAppender
{
    /**
     * @param args path to the folder that one wants to append. Whole folder will be scanned for jar files
     */
    public static void premain(String args, Instrumentation instrumentation)
    {
        //List of jars in the dropin folder...
        Path path = Paths.get(args);
        try (Stream<Path> paths = Files.walk(path))
        {
            paths.filter(Files::isRegularFile).filter(f -> f.toString().endsWith(".jar")).forEach(e ->
            {
                appendPathToInstrumentation(instrumentation, e);
            });
        }
        catch (Exception iex)
        {
            System.err.println("Failed to append path of " + args + " to classpath. Exception was:" + iex);
        }
    }

    private static void appendPathToInstrumentation(Instrumentation instrumentation, Path e)
    {
        try
        {
            instrumentation.appendToSystemClassLoaderSearch(new JarFile(e.toFile().getAbsolutePath()));
        }
        catch (Exception ex)
        {
        }
    }
}
