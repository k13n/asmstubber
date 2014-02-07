package org.k13n.swtstubber.codegen;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class NonPrimitiveMethodGenerator implements EmptyMethodGenerator {

  @Override
  public void generate(MethodVisitor visitor) {
    visitor.visitCode();
    visitor.visitInsn(Opcodes.ACONST_NULL);
    visitor.visitInsn(Opcodes.ARETURN);
    visitor.visitMaxs(-1, -1);
    visitor.visitEnd();
  }

}

