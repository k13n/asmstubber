package org.k13n.swtstubber.visitors;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class SwtClassVisitor extends ClassVisitor {
  private final ClassVisitor visitor;

  public SwtClassVisitor(ClassVisitor visitor) {
    super(Opcodes.ASM4);
    this.visitor = visitor;
  }

  @Override
  public MethodVisitor visitMethod(int access, String name, String desc,
      String signature, String[] exceptions) {
    return null;
  }

}
