package org.k13n.asmstubber.codegen;

import org.objectweb.asm.Opcodes;

public class FloatMethodGenerator extends DefaultValueMethodGenerator {

  public FloatMethodGenerator() {
    super(Opcodes.FCONST_0, Opcodes.FRETURN);
  }

}

