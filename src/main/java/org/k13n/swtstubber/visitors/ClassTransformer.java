package org.k13n.swtstubber.visitors;

import org.k13n.swtstubber.indexing.ClassIndex;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

public class ClassTransformer {

  public interface Callback {
    public void accept(String className, byte[] bytecode);
  }

  private final ClassIndex index;
  private final Callback callback;

  public ClassTransformer(ClassIndex index, Callback callback) {
    this.index = index;
    this.callback = callback;
  }

  public void transform() {
    for (String className : index.keys())
      transformClass(className);
  }

  private void transformClass(String className) {
    byte[] bytecode = index.getBytecode(className);
    byte[] transformedBytecode = transformBytecode(bytecode);
    callback.accept(className, transformedBytecode);
  }

  private byte[] transformBytecode(byte[] bytecode) {
    ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
    ClassVisitor visitor = new TransformingClassVisitor(writer);
    ClassReader reader = new ClassReader(bytecode);
    reader.accept(visitor, 0);
    return writer.toByteArray();
  }

}
