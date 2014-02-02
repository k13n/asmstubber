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
      return equals((JavaMethod) other);
    return false;
  }

  public boolean equals(JavaMethod other) {
    return super.equals(other) &&
      isStatic() == other.isStatic() &&
      isConstructor() == other.isConstructor();
  }

  @Override
  public int hashCode() {
    int result = 3489;
    result = 31 * result + super.hashCode();
    result = 31 * result + (isStatic() ? 1 : 0);
    result = 31 * result + (isConstructor() ? 1 : 0);
    return result;
  }

}
