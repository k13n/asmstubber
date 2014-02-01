package org.k13n.swtstubber;

import java.util.Set;

import org.k13n.swtstubber.indexing.ClassIndex;
import org.k13n.swtstubber.indexing.ClassIndexer;
import org.k13n.swtstubber.matcher.SwtMatcher;
import org.k13n.swtstubber.visitors.ClassExplorer;

public class App {

  public static void main(String[] args) {
    ClassIndex index = new ClassIndex();
    ClassIndexer indexer = new ClassIndexer(index);

    indexer.index("/path/to/eclim/build");
    Set<String> eclimClasses = index.keys();

    indexer.index("/path/to/eclipse/plugins");
    System.out.printf("Indexed classes: %d%n", index.keys().size());

    SwtMatcher swtMatcher = new SwtMatcher();
    ClassExplorer explorer = new ClassExplorer(index, swtMatcher, eclimClasses);

    explorer.explore();
    swtMatcher.dump();
  }

}
