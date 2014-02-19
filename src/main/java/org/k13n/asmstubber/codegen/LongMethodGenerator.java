package org.k13n.asmstubber.codegen;

import org.objectweb.asm.Opcodes;

public class LongMethodGenerator extends DefaultValueMethodGenerator {

  public LongMethodGenerator() {
    super(Opcodes.LCONST_0, Opcodes.LRETURN);
  }

}
