package org.k13n.swtstubber.matcher;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SwtMatcher {
  private final Set<String> swtClasses;
  private final Map<String, Set<Method>> methods;

  public SwtMatcher() {
    swtClasses = new HashSet<String>();
    methods = new HashMap<String, Set<Method>>();
  }

  public boolean matches(String className) {
    if (isSwtClass(className)) {
      if (!swtClasses.contains(className))
        swtClasses.add(className);
      return true;
    }
    return false;
  }

  public void registerMethod(Method method) {
    String className = method.getClassName();
    if (isSwtClass(className))
      getMethodsOfClass(className).add(method);
  }

  private Set<Method> getMethodsOfClass(String className) {
    Set<Method> classMethods;
    if (!methods.containsKey(className)) {
      classMethods = new HashSet<Method>();
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
    for (Set<Method> classMethods : methods.values()) {
      for (Method method : classMethods)
        System.out.println(method);
      System.out.println();
    }
  }

}
