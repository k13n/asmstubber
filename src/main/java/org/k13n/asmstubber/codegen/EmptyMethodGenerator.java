package org.k13n.asmstubber.codegen;

import org.objectweb.asm.MethodVisitor;

public interface EmptyMethodGenerator {

  public void generate(MethodVisitor visitor);

}
