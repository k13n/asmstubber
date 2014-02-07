package org.k13n.swtstubber.visitors;

import org.k13n.swtstubber.codegen.EmptyMethodFactory;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class SwtClassVisitor extends ClassVisitor {
  private final ClassVisitor visitor;

  public SwtClassVisitor(ClassVisitor visitor) {
    super(Opcodes.ASM4, visitor);
    this.visitor = visitor;
  }

  @Override
  public MethodVisitor visitMethod(int access, String name, String desc,
      String signature, String[] exceptions) {
    if (isAbstractMethod(access)) {
      super.visitMethod(access, name, desc, signature, exceptions);
      return null;
    }
    MethodVisitor methodVisitor = visitor.visitMethod(access, name, desc,
        signature, exceptions);
    if (isConstructor(name)) {
      return new ConstructorVisitor(methodVisitor);
    } else {
      EmptyMethodFactory.createMethod(desc).generate(methodVisitor);
      return null;
    }
  }

  private boolean isAbstractMethod(int access) {
    return (access & Opcodes.ACC_ABSTRACT) != 0;
  }

  private boolean isConstructor(String name) {
    return name.equals("<init>");
  }

}
