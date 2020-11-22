# rjent
Simplifies dynamical jar appending to classpath

## Background:

Java doesn't make it easy to add a folder of jars to classpath dynamically, for example to dynamically load jdbc drivers without needing to bundle them together into application.
This project attempts to make things easier. It allows adding a folder of jars to the classpath on startup

## Usage:

rjent works as javaagent. To use it together with your application, you need to get the rjent jar from maven or similar

Then, when you start your application main jar, you need to change command line to:

```
  java -javaagent:path-to-rgent-jar/rgent-0.0.1-SNAPSHOT.jar=path-to-dropin-folder -jar your-real-jar.jar
```

 * path-to-dropin-folder: is the folder of jars path that you want to attach to classpath, for example: dropin
 * lib/rgent-0.0.1-SNAPSHOT.jar: path to the rgent-jar
 * your-real-jar.jar: is the actual jar of your application
 

## License:
You can use it in whatever way you want :)
