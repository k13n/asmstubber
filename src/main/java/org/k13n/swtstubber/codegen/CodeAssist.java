package org.k13n.swtstubber.codegen;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class CodeAssist {

  public static String getJavaType(String type) {
    return getJavaType(Type.getType(type));
  }

  public static String getJavaType(Type type) {
    switch (type.getSort()) {
      case Type.BOOLEAN: return "boolean";
      case Type.BYTE:    return "byte";
      case Type.CHAR:    return "char";
      case Type.DOUBLE:  return "double";
      case Type.FLOAT:   return "float";
      case Type.INT:     return "int";
      case Type.LONG:    return "long";
      case Type.SHORT:   return "short";
      case Type.VOID:    return "void";
      case Type.ARRAY:   return getArrayJavaType(type);
      case Type.OBJECT:  return getObjectJavaType(type);
    }
    throw new AssertionError("impossible");
  }

  private static String getArrayJavaType(Type type) {
    String descriptor = type.getDescriptor();
    int level = 0;
    while (descriptor.charAt(level) == '[')
      level++;
    String realType = getJavaType(descriptor.substring(level));
    for (int i = 0; i < level; i++)
      realType = realType + "[]";
    return realType;
  }

  private static String getObjectJavaType(Type type) {
    String descriptor = type.getDescriptor();
    descriptor = descriptor.substring(1, descriptor.length() - 1);
    return descriptor.replace('/', '.').replace('$', '.');
  }

  public static String neutralValue(String type) {
    return neutralValue(Type.getType(type));
  }

  public static String neutralValue(Type type) {
    switch (type.getSort()) {
      case Type.BOOLEAN: return "false";
      case Type.BYTE:    return "0";
      case Type.CHAR:    return "'\u0000'";
      case Type.DOUBLE:  return "0.0d";
      case Type.FLOAT:   return "0.0f";
      case Type.INT:     return "0";
      case Type.LONG:    return "0L";
      case Type.SHORT:   return "0";
      case Type.VOID:    return "";
      case Type.ARRAY:   return "null";
      case Type.OBJECT:  return "null";
    }
    throw new AssertionError("impossible");
  }

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
