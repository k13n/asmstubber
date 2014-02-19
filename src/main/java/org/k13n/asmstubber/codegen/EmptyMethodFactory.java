package org.k13n.asmstubber.codegen;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class EmptyMethodFactory {

  public static EmptyMethodGenerator createMethod(String desc) {
    Type type = Type.getType(desc).getReturnType();
    switch (type.getSort()) {
      case Type.VOID:
        return new VoidMethodGenerator();
      case Type.BOOLEAN:
      case Type.BYTE:
      case Type.CHAR:
      case Type.SHORT:
      case Type.INT:
        return createIntegerMethod();
      case Type.LONG:
        return createLongMethod();
      case Type.FLOAT:
        return createFloatMethod();
      case Type.DOUBLE:
        return createDoubleMethod();
      default:
        return createNonPrimitiveMethod();
    }
  }

  private static EmptyMethodGenerator createIntegerMethod() {
    return new DefaultValueMethodGenerator(Opcodes.ICONST_0, Opcodes.IRETURN);
  }

  private static EmptyMethodGenerator createLongMethod() {
    return new DefaultValueMethodGenerator(Opcodes.LCONST_0, Opcodes.LRETURN);
  }

  private static EmptyMethodGenerator createFloatMethod() {
    return new DefaultValueMethodGenerator(Opcodes.FCONST_0, Opcodes.FRETURN);
  }

  private static EmptyMethodGenerator createDoubleMethod() {
    return new DefaultValueMethodGenerator(Opcodes.DCONST_0, Opcodes.DRETURN);
  }

  private static EmptyMethodGenerator createNonPrimitiveMethod() {
    return new DefaultValueMethodGenerator(Opcodes.ACONST_NULL, Opcodes.ARETURN);
  }

}

