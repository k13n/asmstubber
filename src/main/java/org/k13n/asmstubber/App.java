package org.k13n.asmstubber;

import java.io.IOException;

import org.k13n.asmstubber.indexing.ClassIndex;
import org.k13n.asmstubber.indexing.ClassIndexer;
import org.k13n.asmstubber.util.FileUtil;
import org.k13n.asmstubber.visitors.ClassTransformer;

public class App {

  public static void main(String[] args) {
    ClassIndex index = new ClassIndex();
    ClassIndexer indexer = new ClassIndexer(index);

    indexer.index("/path/to/swt/jar/file");
    System.out.printf("Indexed classes: %d%n", index.keys().size());

    final String targetDirectory = "/path/to/store/the/transformed/class/files";

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

}
