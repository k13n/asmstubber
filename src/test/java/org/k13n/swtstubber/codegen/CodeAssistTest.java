package org.k13n.swtstubber.codegen;

import org.junit.Test;
import org.objectweb.asm.Type;

import static org.junit.Assert.*;

public class CodeAssistTest {

  @Test
  public void testBoolean() {
    assertTypeMatches("boolean", "Z");
  }

  @Test
  public void testChar() {
    assertTypeMatches("char", "C");
  }

  @Test
  public void testByte() {
    assertTypeMatches("byte", "B");
  }

  @Test
  public void testShort() {
    assertTypeMatches("short", "S");
  }

  @Test
  public void testInt() {
    assertTypeMatches("int", "I");
  }

  @Test
  public void testFloat() {
    assertTypeMatches("float", "F");
  }

  @Test
  public void testLong() {
    assertTypeMatches("long", "J");
  }

  @Test
  public void testDouble() {
    assertTypeMatches("double", "D");
  }

  @Test
  public void testSimpleArray() {
    assertTypeMatches("int[]", "[I");
  }

  @Test
  public void testNestedArray() {
    assertTypeMatches("long[][]", "[[J");
  }

  @Test
  public void testObject() {
    assertTypeMatches("java.lang.Object", "Ljava/lang/Object;");
  }

  @Test
  public void testArrayOfObjects() {
    assertTypeMatches("java.lang.String[][]", "[[Ljava/lang/String;");
  }

  @Test
  public void testNestedClass() {
    assertTypeMatches("java.lang.String.Foo", "Ljava/lang/String$Foo;");
  }

  private static void assertTypeMatches(String realType,
      String typeDescriptor) {
    Type type = Type.getType(typeDescriptor);
    assertEquals(realType, CodeAssist.getJavaType(type));
  }

}
