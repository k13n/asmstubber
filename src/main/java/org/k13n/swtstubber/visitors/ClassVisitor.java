package org.k13n.swtstubber.visitors;

import org.k13n.swtstubber.matcher.SwtMatcher;
import org.objectweb.asm.Opcodes;

public class ClassVisitor extends org.objectweb.asm.ClassVisitor {
  private final ClassExplorer explorer;
  private final SwtMatcher swtMatcher;
  private String className;

  public ClassVisitor(ClassExplorer explorer, SwtMatcher swtMatcher) {
    super(Opcodes.ASM4);
    this.explorer = explorer;
    this.swtMatcher = swtMatcher;
  }

  @Override
  public void visit(int version, int access, String name, String signature,
      String superName, String[] interfaces) {
    this.className = name;
    if (!swtMatcher.matches(superName))
      explorer.markForExploration(superName);
    if (interfaces != null)
      visitInterfaces(interfaces);
  }

  private void visitInterfaces(String[] interfaces) {
    for (String classInterface : interfaces) {
      if (!swtMatcher.matches(classInterface))
        explorer.markForExploration(classInterface);
    }
  }

  @Override
  public void visitOuterClass(String owner, String name, String desc) {
    if (!swtMatcher.matches(owner))
      explorer.markForExploration(owner);
  }

  @Override
  public void visitInnerClass(String name, String outerName,
      String innerName, int access) {
    if (!swtMatcher.matches(name))
      explorer.markForExploration(name);
  }

  @Override
  public MethodVisitor visitMethod(int access, String name, String desc,
      String signature, String[] exceptions) {
    return new MethodVisitor(explorer, swtMatcher);
  }

}
