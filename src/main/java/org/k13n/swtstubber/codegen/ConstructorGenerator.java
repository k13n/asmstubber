package org.k13n.swtstubber.codegen;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class ConstructorGenerator implements EmptyMethodGenerator {
  private final String superClass;
  private final String descriptor;
  private final Type[] argumentTypes;

  public ConstructorGenerator(String superClass, String descriptor) {
    this.superClass = superClass;
    this.descriptor = descriptor;
    argumentTypes = Type.getArgumentTypes(descriptor);
  }

  @Override
  public void generate(MethodVisitor visitor) {
    visitor.visitCode();
    pushThisToStack(visitor);
    for (Type argumentType : argumentTypes)
      pushArgumentNeutralValue(argumentType, visitor);
    createConstructorCall(visitor);
    visitor.visitInsn(Opcodes.RETURN);
    setStackSize(visitor);
    visitor.visitEnd();
  }

  private void pushThisToStack(MethodVisitor visitor) {
    visitor.visitIntInsn(Opcodes.ALOAD, 0);
  }

  private void pushArgumentNeutralValue(Type type, MethodVisitor visitor) {
    visitor.visitInsn(CodeAssist.neutralValueOpcode(type));
  }

  private void createConstructorCall(MethodVisitor visitor) {
    visitor.visitMethodInsn(Opcodes.INVOKESPECIAL, superClass,
        "<init>", descriptor);
  }

  private void setStackSize(MethodVisitor visitor) {
    visitor.visitMaxs(-1, -1);
  }

}
