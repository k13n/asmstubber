package org.k13n.asmstubber;

import java.io.IOException;

import org.k13n.asmstubber.indexing.ClassIndex;
import org.k13n.asmstubber.indexing.ClassIndexer;
import org.k13n.asmstubber.util.FileUtil;
import org.k13n.asmstubber.visitors.ClassTransformer;

public class App {
  private final String inputPath;
  private final String targetDirectory;

  private App(String inputPath, String targetDirectory) {
    this.inputPath = inputPath;
    this.targetDirectory = targetDirectory;
  }

  public void execute() {
    ClassIndex index = new ClassIndex();
    ClassIndexer indexer = new ClassIndexer(index);
    indexer.index(inputPath);

    ClassTransformer.Callback cb = new ClassTransformer.Callback() {
      @Override public void accept(String className, byte[] bytecode) {
        try {
          FileUtil.writeBytecode(targetDirectory, className, bytecode);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    };
    new ClassTransformer(index, cb).transform();
  }

  public static void main(String[] args) {
    if (args.length < 2) {
      printUsage();
      System.exit(1);
    }
    new App(args[0], args[1]).execute();
  }

  private static void printUsage() {
    System.out.println("Two arguments are required, the parameters are:");
    System.out.println("  <input path> <target directory>");
    System.out.println("The input path can be either a single class file, " +
        "a directory or a jar file");
  }

}
