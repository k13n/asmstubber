package org.k13n.asmstubber.codegen;

import org.objectweb.asm.Opcodes;

public class DoubleMethodGenerator extends DefaultValueMethodGenerator {

  public DoubleMethodGenerator() {
    super(Opcodes.DCONST_0, Opcodes.DRETURN);
  }

}
