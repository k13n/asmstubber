package org.k13n.asmstubber.codegen;

import org.objectweb.asm.Opcodes;

public class NonPrimitiveMethodGenerator extends DefaultValueMethodGenerator {

  public NonPrimitiveMethodGenerator() {
    super(Opcodes.ACONST_NULL, Opcodes.ARETURN);
  }

}

