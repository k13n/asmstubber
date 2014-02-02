package org.k13n.swtstubber.matcher;

import java.util.HashMap;
import java.util.Map;

public class SwtMatcher {
  private final Map<String, JavaClass> swtClasses;

  public SwtMatcher() {
    swtClasses = new HashMap<String, JavaClass>();
  }

  public boolean matches(String className) {
    if (isSwtClass(className)) {
      if (!swtClasses.containsKey(className))
        swtClasses.put(className, new JavaClass(className));
      return true;
    }
    return false;
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
    JavaClass javaClass;
    if (!swtClasses.containsKey(className)) {
      javaClass = new JavaClass(className);
      swtClasses.put(className, javaClass);
    } else {
      javaClass = swtClasses.get(className);
    }
    return javaClass;
  }

  public boolean isSwtClass(String className) {
    return className.startsWith("org/eclipse/swt") &&
      !className.startsWith("org/eclipse/swt/internal");
  }

  public void dump() {
    for (JavaClass javaClass : swtClasses.values()) {
      System.out.println(javaClass);
      for (JavaMethod constructor : javaClass.getConstructors())
        System.out.println("  " + constructor);
      for (JavaMethod method : javaClass.getMethods())
        System.out.println("  " + method);
      System.out.println();
    }
  }

}
