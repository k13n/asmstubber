package org.k13n.swtstubber.codegen;

import org.k13n.swtstubber.matcher.JavaClass;
import org.k13n.swtstubber.matcher.JavaMethod;
import org.objectweb.asm.Type;

public class ClassSourceGenerator {
  protected static final String SPACE = " ";
  protected static final String TAB = "  ";

  protected final JavaClass javaClass;
  protected final StringBuffer buffer;
  protected final String indent;

  public ClassSourceGenerator(JavaClass javaClass) {
    this.javaClass = javaClass;
    buffer = new StringBuffer();
    indent = "";
  }

  public ClassSourceGenerator(JavaClass javaClass, StringBuffer buffer,
      String indent) {
    this.javaClass = javaClass;
    this.buffer = buffer;
    this.indent = indent;
  }

  public String generate() {
    generatePackageDefinition();
    openClassDefinition();
    for (JavaMethod constructor : javaClass.getConstructors())
      generateConstructor(constructor);
    for (JavaMethod method : javaClass.getMethods())
      generateMethod(method);
    for (JavaClass innerClass : javaClass.getInnerClasses())
      generateInnerClass(innerClass);
    closeClassDefinition();
    return buffer.toString();
  }

  protected void generatePackageDefinition() {
    indentZero("package ");
    append(javaClass.getPackageName());
    append(";");
    append(System.lineSeparator());
    append(System.lineSeparator());
  }

  protected void openClassDefinition() {
    indentZero("public class ");
    append(javaClass.getClassName());
    append(" {");
    append(System.lineSeparator());
  }

  protected void closeClassDefinition() {
    indentZero("}");
    append(System.lineSeparator());
  }

  protected void generateConstructor(JavaMethod constructor) {
    indentOne("public ");
    append(javaClass.getClassName());
    generateMethodArguments(constructor.getArgumentTypes());
    append(" {");
    append(System.lineSeparator());
    append(System.lineSeparator());
    indentOne("}");
    append(System.lineSeparator());
    append(System.lineSeparator());
  }

  protected void generateMethod(JavaMethod method) {
    indentOne("public ");
    if (method.isStatic())
      append("static ");
    append(CodeAssist.getJavaType(method.getReturnType()));
    append(SPACE);
    append(method.getName());
    generateMethodArguments(method.getArgumentTypes());
    append(" {");
    append(System.lineSeparator());
    generateMethodBody(method.getReturnType());
    append(System.lineSeparator());
    indentOne("}");
    append(System.lineSeparator());
    append(System.lineSeparator());
  }

  protected void generateMethodArguments(Type[] arguments) {
    append("(");
    for (int i = 0; i < arguments.length; i++) {
      Type argument = arguments[i];
      append(CodeAssist.getJavaType(argument));
      append(SPACE);
      append("value" + i);
      if (i < arguments.length - 1)
        append(", ");
    }
    append(")");
  }

  protected void generateInnerClass(JavaClass innerClass) {
    new InnerClassSourceGenerator(innerClass, buffer, indent + TAB).generate();
  }

  protected void generateMethodBody(Type returnType) {
    if (returnType.getSort() != Type.VOID) {
      indentTwo("return ");
      append(CodeAssist.neutralValue(returnType));
      append(";");
    }
  }

  protected void append(String value) {
    buffer.append(value);
  }

  protected void indentZero(String value) {
    indent(value, 0);
  }

  protected void indentOne(String value) {
    indent(value, 1);
  }

  protected void indentTwo(String value) {
    indent(value, 2);
  }

  protected void indent(String value, int level) {
    buffer.append(indent);
    for (int i = 0; i < level; i++)
      buffer.append(TAB);
    buffer.append(value);
  }

}
