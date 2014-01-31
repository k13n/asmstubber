package org.k13n.swtstubber.visitors;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.k13n.swtstubber.indexing.ClassIndex;
import org.k13n.swtstubber.matcher.SwtMatcher;
import org.objectweb.asm.ClassReader;

public class ClassExplorer {
  private final ClassIndex index;
  private final SwtMatcher swtMatcher;
  private final Queue<String> classQueue;
  private final Set<String> exlporedClasses;

  public ClassExplorer(ClassIndex index, SwtMatcher swtMatcher) {
    this.index = index;
    this.swtMatcher = swtMatcher;
    classQueue = new LinkedList<String>();
    exlporedClasses = new HashSet<String>();
  }

  public ClassExplorer(ClassIndex index, SwtMatcher swtMatcher,
      Set<String> seed) {
    this(index, swtMatcher);
    for (String className : seed)
      classQueue.add(className);
  }

  public void explore() {
    while (!classQueue.isEmpty()) {
      String className = classQueue.poll();
      visitClass(className);
      markAsExplored(className);
    }
  }

  private void visitClass(String className) {
    try {
      byte[] bytecode = index.getBytecode(className);
      ClassReader reader = new ClassReader(bytecode);
      ClassVisitor visitor = new ClassVisitor(this, swtMatcher);
      reader.accept(visitor, 0);
    } catch (RuntimeException e) {
      // FIXME
      System.out.println(e.getMessage());
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
