
package org.k13n.swtstubber.codegen;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class DoubleMethodGenerator implements EmptyMethodGenerator {

  @Override
  public void generate(MethodVisitor visitor) {
    visitor.visitCode();
    visitor.visitInsn(Opcodes.DCONST_0);
    visitor.visitInsn(Opcodes.DRETURN);
    visitor.visitMaxs(-1, -1);
    visitor.visitEnd();
  }

}
