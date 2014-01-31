package org.k13n.swtstubber.visitors;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class ClassExplorer {
  private final Queue<String> classQueue;
  private final Set<String> exlporedClasses;

  public ClassExplorer() {
    classQueue = new LinkedList<String>();
    exlporedClasses = new HashSet<String>();
  }

  public ClassExplorer(Set<String> seed) {
    this();
    for (String className : seed)
      classQueue.add(className);
  }

  public void explore() {
    while (!classQueue.isEmpty()) {
      String className = classQueue.poll();
      markAsExplored(className);
    }
  }

  public void markForExploration(String className) {
    if (!exlporedClasses.contains(className))
      classQueue.add(className);
  }

  private void markAsExplored(String className) {
    exlporedClasses.add(className);
  }

}
