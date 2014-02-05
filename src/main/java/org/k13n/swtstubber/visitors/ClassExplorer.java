package org.k13n.swtstubber.visitors;

import java.util.LinkedList;
import java.util.Queue;

import org.k13n.swtstubber.indexing.ClassIndex;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class ClassExplorer {
  private final ClassIndex index;
  private final Queue<String> classQueue;

  public ClassExplorer(ClassIndex index) {
    this.index = index;
    classQueue = new LinkedList<String>();
  }

  public void explore() {
    while (!classQueue.isEmpty()) {
      String className = classQueue.poll();
      visitClass(className);
    }
  }

  private void visitClass(String className) {
    try {
      byte[] bytecode = index.getBytecode(className);
      ClassReader reader = new ClassReader(bytecode);
      ClassWriter writer = new ClassWriter(0);
      SwtClassVisitor visitor = new SwtClassVisitor(writer);
      reader.accept(visitor, 0);
    } catch (RuntimeException e) {
      // FIXME
      System.out.println(e.getMessage());
    }
  }

}
