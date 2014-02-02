package org.k13n.swtstubber.codegen;

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
    return descriptor.replace('/', '.');
  }

}
