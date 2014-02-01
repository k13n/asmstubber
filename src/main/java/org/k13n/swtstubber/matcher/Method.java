package org.k13n.swtstubber.matcher;

public class Method {
  private final String name;
  private final String typeDescriptor;
  private final String className;
  private final int opcode;

  public Method(String name, String typeDescriptor,
      String className, int opcode) {
    this.name = name;
    this.typeDescriptor = typeDescriptor;
    this.className = className;
    this.opcode = opcode;
  }

  public int getOpcode() {
    return opcode;
  }

  public String getClassName() {
    return className;
  }

  public String getName() {
    return name;
  }

  public String getTypeDescriptor() {
    return typeDescriptor;
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof Method)
      return equals((Method) object);
    else
      return false;
  }

  public boolean equals(Method other) {
    return opcode == other.opcode &&
      className.equals(other.className) &&
      name.equals(other.name) &&
      typeDescriptor.equals(other.typeDescriptor);
  }

  @Override
  public int hashCode() {
    int result = 3489;
    result = 31 * result + name.hashCode();
    result = 31 * result + typeDescriptor.hashCode();
    result = 31 * result + className.hashCode();
    result = 31 * result + opcode;
    return result;
  }

  @Override
  public String toString() {
    StringBuffer buffer = new StringBuffer();
    buffer.append(className);
    buffer.append('#');
    buffer.append(name);
    buffer.append(" ");
    buffer.append(typeDescriptor);
    return buffer.toString();
  }

}
