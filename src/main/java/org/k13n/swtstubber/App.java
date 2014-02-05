package org.k13n.swtstubber;

import org.k13n.swtstubber.indexing.ClassIndex;
import org.k13n.swtstubber.indexing.ClassIndexer;
import org.k13n.swtstubber.visitors.ClassTransformer;

public class App {

  public static void main(String[] args) {
    ClassIndex index = new ClassIndex();
    ClassIndexer indexer = new ClassIndexer(index);

    indexer.index("/path/to/swt/jar/file");
    System.out.printf("Indexed classes: %d%n", index.keys().size());

    ClassTransformer.Callback cb = new ClassTransformer.Callback() {
      @Override public void accept(String className, byte[] bytecode) {
        System.out.println("transformed " + className);
      }
    };
    new ClassTransformer(index, cb).transform();
  }

}
