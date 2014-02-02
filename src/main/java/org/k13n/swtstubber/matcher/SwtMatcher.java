package org.k13n.swtstubber.matcher;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SwtMatcher {
  private final Set<String> swtClasses;
  private final Map<String, Set<JavaMethod>> methods;

  public SwtMatcher() {
    swtClasses = new HashSet<String>();
    methods = new HashMap<String, Set<JavaMethod>>();
  }

  public boolean matches(String className) {
    if (isSwtClass(className)) {
      if (!swtClasses.contains(className))
        swtClasses.add(className);
      return true;
    }
    return false;
  }

  public void registerMethod(String className, JavaMethod method) {
    if (isSwtClass(className))
      getMethodsOfClass(className).add(method);
  }

  private Set<JavaMethod> getMethodsOfClass(String className) {
    Set<JavaMethod> classMethods;
    if (!methods.containsKey(className)) {
      classMethods = new HashSet<JavaMethod>();
      methods.put(className, classMethods);
    } else
      classMethods = methods.get(className);
    return classMethods;
  }

  public boolean isSwtClass(String className) {
    return className.startsWith("org/eclipse/swt") &&
      !className.startsWith("org/eclipse/swt/internal");
  }

  public void dump() {
    for (Map.Entry<String, Set<JavaMethod>> entry : methods.entrySet()) {
      System.out.println(entry.getKey());
      for (JavaMethod method : entry.getValue())
        System.out.println("  " + method);
      System.out.println();
    }
  }

}
