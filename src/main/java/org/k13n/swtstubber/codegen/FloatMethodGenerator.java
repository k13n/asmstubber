package org.k13n.swtstubber.codegen;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class FloatMethodGenerator implements EmptyMethodGenerator {

  @Override
  public void generate(MethodVisitor visitor) {
    visitor.visitCode();
    visitor.visitInsn(Opcodes.FCONST_0);
    visitor.visitInsn(Opcodes.FRETURN);
    visitor.visitMaxs(-1, -1);
    visitor.visitEnd();
  }

}

