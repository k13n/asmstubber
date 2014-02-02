package org.k13n.swtstubber.codegen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collection;

import org.k13n.swtstubber.matcher.JavaClass;

public class SourceCodeGenerator {
  private final Collection<JavaClass> javaClasses;
  private final String targetDirectory;

  public SourceCodeGenerator(Collection<JavaClass> javaClasses,
      String targetDirectory) {
    this.javaClasses = javaClasses;
    this.targetDirectory = targetDirectory;
    createDirectoryIfNotExists(targetDirectory);
  }

  public void generate() throws FileNotFoundException,
      UnsupportedEncodingException {
    ClassSourceGenerator generator;
    for (JavaClass javaClass : javaClasses) {
      generator = new ClassSourceGenerator(javaClass);
      String source = generator.generate();
      writeSource(javaClass, source);
    }
  }

  private void writeSource(JavaClass javaClass, String source)
      throws FileNotFoundException, UnsupportedEncodingException {
    String path = createPackageHierarchy(javaClass.getPackageName());
    String file = path + File.separator + javaClass.getClassName() + ".java";
    PrintWriter writer = new PrintWriter(file, "UTF-8");
    writer.print(source);
    writer.close();
  }

  private String createPackageHierarchy(String packageName) {
    String path = targetDirectory;
    for (String folder : packageName.split("\\.")) {
      path = path + File.separator + folder;
      createDirectoryIfNotExists(path);
    }
    return path;
  }

  private void createDirectoryIfNotExists(String directory) {
    File file = new File(directory);
    if (file.exists()) {
      if (!file.isDirectory()) {
        String msg = "Path " + directory + " exists, but is not a directory";
        throw new RuntimeException(msg);
      }
    } else {
      file.mkdir();
    }
  }

}
