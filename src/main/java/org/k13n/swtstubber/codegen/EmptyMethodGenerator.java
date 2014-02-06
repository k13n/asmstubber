package org.k13n.swtstubber.codegen;

import org.objectweb.asm.MethodVisitor;

public interface EmptyMethodGenerator {

  public void generate(MethodVisitor visitor);

}
