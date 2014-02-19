package org.k13n.asmstubber.codegen;

import org.objectweb.asm.Opcodes;

public class IntegerMethodGenerator extends DefaultValueMethodGenerator {

  public IntegerMethodGenerator() {
    super(Opcodes.ICONST_0, Opcodes.IRETURN);
  }

}
