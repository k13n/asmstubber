package org.k13n.swtstubber.visitors;

import org.k13n.swtstubber.codegen.EmptyMethodFactory;
import org.k13n.swtstubber.codegen.EmptyMethodGenerator;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class TransformingClassVisitor extends ClassVisitor {
  private final ClassVisitor visitor;

  public TransformingClassVisitor(ClassVisitor visitor) {
    super(Opcodes.ASM4, visitor);
    this.visitor = visitor;
  }

  @Override
  public MethodVisitor visitMethod(int access, String name, String desc,
      String signature, String[] exceptions) {
    MethodVisitor methodVisitor = visitor.visitMethod(access, name, desc,
        signature, exceptions);
    if (isAbstractMethod(access))
      return null;
    else if (isConstructor(name))
      return new ConstructorVisitor(methodVisitor);
    else
      return handleNormalMethod(desc, methodVisitor);
  }

  private boolean isAbstractMethod(int access) {
    return (access & Opcodes.ACC_ABSTRACT) != 0;
  }

  private boolean isConstructor(String name) {
    return name.equals("<init>");
  }

  private MethodVisitor handleNormalMethod(String desc,
      MethodVisitor methodVisitor) {
    EmptyMethodGenerator generator = EmptyMethodFactory.createMethod(desc);
    generator.generate(methodVisitor);
    return null;
  }


}
