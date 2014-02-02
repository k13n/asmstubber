package org.k13n.swtstubber.matcher;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SwtMatcher {
  private final Map<String, JavaClass> swtClasses;

  public SwtMatcher() {
    swtClasses = new HashMap<String, JavaClass>();
  }

  public boolean matches(String className) {
    if (!isSwtClass(className))
      return false;
    else if (isInnerClass(className))
      return matchInnerClass(className);
    else
      return matchNormalClass(className);
  }

  private boolean matchInnerClass(String className) {
    if (!isValidInnerClass(className))
      return false;
    JavaClass outer = getJavaClass(outerClass(className));
    JavaClass inner = new JavaClass(className, innerClass(className));
    outer.addInnerClass(inner);
    return true;
  }

  private boolean matchNormalClass(String className) {
    if (!swtClasses.containsKey(className))
      swtClasses.put(className, new JavaClass(className));
    return true;
  }

  public void registerMethod(String className, JavaMethod method) {
    if (isSwtClass(className)) {
      JavaClass javaClass = getJavaClass(className);
      if (method.isConstructor())
        javaClass.addConstructor(method);
      else
        javaClass.addMethod(method);
    }
  }

  private JavaClass getJavaClass(String className) {
    if (isInnerClass(className))
      return getInnerJavaClass(className);
    else
      return getNormalClass(className);
  }

  private JavaClass getInnerJavaClass(String className) {
    if (!isValidInnerClass(className)) {
      String msg = "class " + className + " is not a valid inner class";
      throw new RuntimeException(msg);
    }
    String innerClass = innerClass(className);
    JavaClass outer = getJavaClass(outerClass(className));
    for (JavaClass inner : outer.getInnerClasses()) {
      if (inner.getClassName().equals(innerClass))
        return inner;
    }
    String msg = "class " + className + " was not yet indexed";
    throw new RuntimeException(msg);
  }

  private JavaClass getNormalClass(String className) {
    if (!swtClasses.containsKey(className)) {
      String msg = "class " + className + " was not yet indexed";
      throw new RuntimeException(msg);
    }
    return swtClasses.get(className);
  }

  public boolean isSwtClass(String className) {
    return className.startsWith("org/eclipse/swt") &&
      !className.startsWith("org/eclipse/swt/internal");
  }

  private boolean isInnerClass(String className) {
    return className.indexOf('$') >= 0;
  }

  private boolean isValidInnerClass(String className) {
    // avoid classes like org/eclipse/swt/custom/TreeEditor$3
    int pos = className.indexOf('$');
    char digit = className.charAt(pos + 1);
    return !(digit >= '0' && digit <= '9');
  }

  private String outerClass(String className) {
    int pos = className.indexOf('$');
    if (pos == -1)
      return className;
    else
      return className.substring(0, pos);
  }

  private String innerClass(String className) {
    return className.substring(className.indexOf('$') + 1, className.length());
  }

  public void dump() {
    for (JavaClass javaClass : swtClasses.values()) {
      dumpClass(javaClass, "");
      System.out.println();
    }
  }

  private void dumpClass(JavaClass javaClass, String indent) {
    System.out.println(indent + javaClass);
    for (JavaMethod constructor : javaClass.getConstructors())
      System.out.println(indent + "  " + constructor);
    for (JavaMethod method : javaClass.getMethods())
      System.out.println(indent + "  " + method);
    for (JavaClass innerClass : javaClass.getInnerClasses())
      dumpClass(innerClass, indent + "  ");
  }

  public Collection<JavaClass> getClasses() {
    return swtClasses.values();
  }

}
