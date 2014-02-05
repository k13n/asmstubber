package org.k13n.swtstubber;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import org.k13n.swtstubber.indexing.ClassIndex;
import org.k13n.swtstubber.indexing.ClassIndexer;
import org.k13n.swtstubber.visitors.ClassExplorer;

public class App {

  public static void main(String[] args) throws FileNotFoundException,
      UnsupportedEncodingException {
    ClassIndex index = new ClassIndex();
    ClassIndexer indexer = new ClassIndexer(index);

    indexer.index("/path/to/swt/jar/file");
    System.out.printf("Indexed classes: %d%n", index.keys().size());

    ClassExplorer explorer = new ClassExplorer(index);

    explorer.explore();
  }

}
