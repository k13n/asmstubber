package org.k13n.swtstubber.codegen;

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
        return new IntegerMethodGenerator();
      case Type.LONG:
        return new LongMethodGenerator();
      case Type.FLOAT:
        return new FloatMethodGenerator();
      case Type.DOUBLE:
        return new DoubleMethodGenerator();
      default:
        return new NonPrimitiveMethodGenerator();
    }
  }

}

