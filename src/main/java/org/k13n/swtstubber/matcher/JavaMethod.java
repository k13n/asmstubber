package org.k13n.swtstubber.matcher;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.Method;

public class JavaMethod extends Method {
  private final int opcode;

  public JavaMethod(String name, String typeDescriptor, int opcode) {
    super(name, typeDescriptor);
    this.opcode = opcode;
  }

  public int getOpcode() {
    return opcode;
  }

  public boolean isStatic() {
    return opcode == Opcodes.INVOKESTATIC;
  }

  public boolean isConstructor() {
    return getName().equals("<init>");
  }

  @Override
  public boolean equals(Object other) {
    if (other != null && other instanceof JavaMethod)
      return ((JavaMethod) other).opcode == opcode;
    return false;
  }

  @Override
  public int hashCode() {
    int result = 3489;
    result = 31 * result + super.hashCode();
    result = 31 * result + opcode;
    return result;
  }

}