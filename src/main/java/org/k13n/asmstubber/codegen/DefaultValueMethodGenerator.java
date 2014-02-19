package org.k13n.asmstubber.codegen;

import org.objectweb.asm.MethodVisitor;

public class DefaultValueMethodGenerator implements EmptyMethodGenerator {
  private int valueOpCode;
  private int returnOpCode;

  public DefaultValueMethodGenerator(int valueOpCode, int returnOpCode) {
    this.valueOpCode = valueOpCode;
    this.returnOpCode = returnOpCode;
  }

  @Override
  public void generate(MethodVisitor visitor) {
    visitor.visitCode();
    visitor.visitInsn(valueOpCode);
    visitor.visitInsn(returnOpCode);
    visitor.visitMaxs(-1, -1);
    visitor.visitEnd();
  }

}
