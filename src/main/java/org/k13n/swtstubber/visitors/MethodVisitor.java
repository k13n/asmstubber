package org.k13n.swtstubber.visitors;

import org.k13n.swtstubber.matcher.Method;
import org.k13n.swtstubber.matcher.SwtMatcher;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;

public class MethodVisitor extends org.objectweb.asm.MethodVisitor {
  private final ClassExplorer explorer;
  private final SwtMatcher swtMatcher;

  public MethodVisitor(ClassExplorer explorer, SwtMatcher swtMatcher) {
    super(Opcodes.ASM4);
    this.explorer = explorer;
    this.swtMatcher = swtMatcher;
  }

  @Override
  public void visitFieldInsn(int opcode, String owner, String name,
      String typeDescriptor) {
    exploreTypeDescriptor(owner);
    exploreTypeDescriptor(typeDescriptor);
  }

  @Override
  public void visitTypeInsn(int opcode, String typeDescriptor) {
    exploreTypeDescriptor(typeDescriptor);
  }

  @Override
  public void visitLocalVariable(String name, String typeDescriptor,
      String signature, Label start, Label end, int index) {
    exploreTypeDescriptor(typeDescriptor);
  }

  @Override
  public void visitMethodInsn(int opcode, String owner, String name,
      String typeDescriptor) {
    String className = internalName(owner);
    if (isComplexType(className)) {
      if (swtMatcher.matches(className)) {
        Method method = new Method(name, typeDescriptor, opcode);
        swtMatcher.registerMethod(className, method);
      } else {
        explorer.markForExploration(className);
      }
    }
  }

  private void exploreTypeDescriptor(String typeDescriptor) {
    String type = internalName(typeDescriptor);
    if (isComplexType(type) && !swtMatcher.matches(type))
      explorer.markForExploration(type);
  }

  // see asm4-guide, section 2.1.3
  private String internalName(String type) {
    StringBuffer buffer = new StringBuffer(type);
    while (buffer.length() > 0 && buffer.charAt(0) == '[')
      buffer.deleteCharAt(0);
    if (buffer.charAt(0) == 'L')
      buffer.deleteCharAt(0);
    if (buffer.charAt(buffer.length() - 1) == ';')
      buffer.deleteCharAt(buffer.length() -1);
    return buffer.toString();
  }

  // see asm4-guide, section 2.1.3
  private boolean isComplexType(String type) {
    return type.length() > 1;
  }

}
