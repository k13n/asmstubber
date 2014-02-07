package org.k13n.asmstubber.visitors;

import org.k13n.asmstubber.codegen.ConstructorGenerator;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ConstructorVisitor extends MethodVisitor {
  private boolean hasCallToSuperAppeared;
  private MethodVisitor methodVisitor;

  public ConstructorVisitor(MethodVisitor visitor) {
    super(Opcodes.ASM4);
    methodVisitor = visitor;
    hasCallToSuperAppeared = false;
  }

  @Override
  public void visitMethodInsn(int opcode, String owner, String name,
      String desc) {
    if (!hasCallToSuperAppeared && opcode == Opcodes.INVOKESPECIAL) {
      new ConstructorGenerator(owner, desc).generate(methodVisitor);
      hasCallToSuperAppeared = true;
    }
  }


}
