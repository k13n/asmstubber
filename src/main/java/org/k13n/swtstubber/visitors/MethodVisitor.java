package org.k13n.swtstubber.visitors;

import org.k13n.swtstubber.matcher.SwtMatcher;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;

public class MethodVisitor extends org.objectweb.asm.MethodVisitor {
  private final String className;
  private final SwtMatcher swtMatcher;

  public MethodVisitor(String className, SwtMatcher swtMatcher) {
    super(Opcodes.ASM4);
    this.className = className;
    this.swtMatcher = swtMatcher;
  }

  public void visitTypeInsn(int opcode, String type) {
    swtMatcher.matches(type);
  }

  public void visitLocalVariable(String name, String type,
    String signature, Label start, Label end, int index) {
    swtMatcher.matches(type);
  }

}
