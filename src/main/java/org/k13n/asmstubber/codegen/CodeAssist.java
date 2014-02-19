package org.k13n.asmstubber.codegen;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class CodeAssist {

  public static int neutralValueOpcode(Type type) {
    switch (type.getSort()) {
      case Type.BOOLEAN: return Opcodes.ICONST_0;
      case Type.BYTE:    return Opcodes.ICONST_0;
      case Type.CHAR:    return Opcodes.ICONST_0;
      case Type.SHORT:   return Opcodes.ICONST_0;
      case Type.INT:     return Opcodes.ICONST_0;
      case Type.LONG:    return Opcodes.LCONST_0;
      case Type.FLOAT:   return Opcodes.FCONST_0;
      case Type.DOUBLE:  return Opcodes.DCONST_0;
      case Type.ARRAY:
      case Type.OBJECT:
        return Opcodes.ACONST_NULL;
    }
    throw new AssertionError("impossible");
  }

}
