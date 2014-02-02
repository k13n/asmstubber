package org.k13n.swtstubber.codegen;

import org.k13n.swtstubber.matcher.JavaClass;

public class InnerClassSourceGenerator extends ClassSourceGenerator {

  public InnerClassSourceGenerator(JavaClass javaClass,
      StringBuffer buffer, String indent) {
    super(javaClass, buffer, indent);
  }

  protected void generatePackageDefinition() {
    // inner class has no package definition
  }

  protected void openClassDefinition() {
    indentZero("public static class ");
    append(javaClass.getClassName());
    append(" {");
    append(System.lineSeparator());
  }

}
