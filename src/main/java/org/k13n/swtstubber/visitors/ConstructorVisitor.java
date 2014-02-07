package org.k13n.swtstubber.visitors;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ConstructorVisitor extends MethodVisitor {
  private boolean hasCallToSuperAppeared;

  public ConstructorVisitor(MethodVisitor visitor) {
    super(Opcodes.ASM4, visitor);
    hasCallToSuperAppeared = false;
  }

  @Override
  public void visitMethodInsn(int opcode, String owner, String name,
      String desc) {
    if (!hasCallToSuperAppeared) {
      super.visitMethodInsn(opcode, owner, name, desc);
      if (opcode == Opcodes.INVOKESPECIAL)
        hasCallToSuperAppeared = true;
    }
  }

  @Override
  public void visitMaxs(int maxStack, int maxLocals) {
    super.visitMaxs(-1, -1);
  }

  @Override
  public void visitInsn(int opcode) {
    // let RETURN statement propagate
    if (opcode == Opcodes.RETURN || !hasCallToSuperAppeared)
      super.visitInsn(opcode);
  }

  @Override
  public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
    if (!hasCallToSuperAppeared)
      return super.visitAnnotation(desc, visible);
    return null;
  }

  @Override
  public AnnotationVisitor visitAnnotationDefault() {
    if (!hasCallToSuperAppeared)
      return super.visitAnnotationDefault();
    return null;
  }

  @Override
  public void visitAttribute(Attribute attr) {
    if (!hasCallToSuperAppeared)
      super.visitAttribute(attr);
  }

  @Override
  public void visitCode() {
    if (!hasCallToSuperAppeared)
      super.visitCode();
  }

  @Override
  public void visitEnd() {
    if (!hasCallToSuperAppeared)
      super.visitEnd();
  }

  @Override
  public void visitFieldInsn(int opcode, String owner, String name,
      String desc) {
    if (!hasCallToSuperAppeared)
      super.visitFieldInsn(opcode, owner, name, desc);
  }

  @Override
  public void visitFrame(int type, int nLocal, Object[] local,
      int nStack, Object[] stack) {
    if (!hasCallToSuperAppeared)
      super.visitFrame(type, nLocal, local, nStack, stack);
  }

  @Override
  public void visitIincInsn(int var, int increment) {
    if (!hasCallToSuperAppeared)
      super.visitIincInsn(var, increment);
  }

  @Override
  public void visitIntInsn(int opcode, int operand) {
    if (!hasCallToSuperAppeared)
      super.visitIntInsn(opcode, operand);
  }

  @Override
  public void visitInvokeDynamicInsn(String name, String desc,
      Handle bsm, Object... bsmArgs) {
    if (!hasCallToSuperAppeared)
      super.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs);
  }

  @Override
  public void visitJumpInsn(int opcode, Label label) {
    if (!hasCallToSuperAppeared)
      super.visitJumpInsn(opcode, label);
  }

  @Override
  public void visitLabel(Label label) {
    if (!hasCallToSuperAppeared)
      super.visitLabel(label);
  }

  @Override
  public void visitLdcInsn(Object cst) {
    if (!hasCallToSuperAppeared)
      super.visitLdcInsn(cst);
  }

  @Override
  public void visitLineNumber(int line, Label start) {
    if (!hasCallToSuperAppeared)
      super.visitLineNumber(line, start);
  }

  @Override
  public void visitLocalVariable(String name, String desc, String signature,
      Label start, Label end, int index) {
    if (!hasCallToSuperAppeared)
      super.visitLocalVariable(name, desc, signature, start, end, index);
  }

  @Override
  public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
    if (!hasCallToSuperAppeared)
      super.visitLookupSwitchInsn(dflt, keys, labels);
  }

  @Override
  public void visitMultiANewArrayInsn(String desc, int dims) {
    if (!hasCallToSuperAppeared)
      super.visitMultiANewArrayInsn(desc, dims);
  }

  @Override
  public AnnotationVisitor visitParameterAnnotation(int parameter, String desc,
      boolean visible) {
    if (!hasCallToSuperAppeared)
      return super.visitParameterAnnotation(parameter, desc, visible);
    return null;
  }

  @Override
  public void visitTableSwitchInsn(int min, int max, Label dflt,
      Label... labels) {
    if (!hasCallToSuperAppeared)
      super.visitTableSwitchInsn(min, max, dflt, labels);
  }

  @Override
  public void visitTryCatchBlock(Label start, Label end, Label handler,
      String type) {
    if (!hasCallToSuperAppeared)
      super.visitTryCatchBlock(start, end, handler, type);
  }

  @Override
  public void visitTypeInsn(int opcode, String type) {
    if (!hasCallToSuperAppeared)
      super.visitTypeInsn(opcode, type);
  }

  @Override
  public void visitVarInsn(int opcode, int var) {
    if (!hasCallToSuperAppeared)
      super.visitVarInsn(opcode, var);
  }

}
