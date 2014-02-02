package org.k13n.swtstubber.matcher;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class JavaClass {
  private final String internalName;
  private final String className;
  private final String packageName;
  private final Set<JavaMethod> constructors;
  private final Set<JavaMethod> methods;
  private final Set<JavaClass> innerClasses;

  public JavaClass(String internalName) {
    this.internalName = internalName;
    className = className(internalName);
    packageName = packageName(internalName);
    constructors = new HashSet<JavaMethod>();
    methods = new HashSet<JavaMethod>();
    innerClasses = new HashSet<JavaClass>();
  }

  private String className(String internalName) {
    int separatorPos = internalName.lastIndexOf("/");
    return internalName.substring(separatorPos + 1, internalName.length());
  }

  private String packageName(String internalName) {
    int separatorPos = internalName.lastIndexOf("/");
    String packageName = internalName.substring(0, separatorPos);
    return packageName.replace('/', '.');
  }

  public void addConstructor(JavaMethod constructor) {
    constructors.add(constructor);
  }

  public void addMethod(JavaMethod method) {
    methods.add(method);
  }

  public void addInnerClass(JavaClass innerClass) {
    innerClasses.add(innerClass);
  }

  public String getClassName() {
    return className;
  }

  public String getPackageName() {
    return packageName;
  }

  public Set<JavaMethod> getConstructors() {
    return Collections.unmodifiableSet(constructors);
  }

  public Set<JavaMethod> getMethods() {
    return Collections.unmodifiableSet(methods);
  }

  public Set<JavaClass> getInnerClasses() {
    return Collections.unmodifiableSet(innerClasses);
  }

  @Override
  public String toString() {
    return internalName;
  }

  @Override
  public int hashCode() {
    return internalName.hashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (other != null && other instanceof JavaClass)
      return equals((JavaClass) other);
    return false;
  }

  private boolean equals(JavaClass other) {
    return internalName.equals(other.internalName);
  }

}
