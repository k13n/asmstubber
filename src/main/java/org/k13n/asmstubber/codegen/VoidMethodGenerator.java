package org.k13n.asmstubber.codegen;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class VoidMethodGenerator implements EmptyMethodGenerator {

  @Override
  public void generate(MethodVisitor visitor) {
    visitor.visitCode();
    visitor.visitInsn(Opcodes.RETURN);
    visitor.visitMaxs(-1, -1);
    visitor.visitEnd();
  }

}
