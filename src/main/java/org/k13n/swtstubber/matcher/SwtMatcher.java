package org.k13n.swtstubber.matcher;

import java.util.ArrayList;
import java.util.List;

public class SwtMatcher {
  private final List<String> swtClasses;

  public SwtMatcher() {
    swtClasses = new ArrayList<String>();
  }

  public boolean matches(String className) {
    if (isSwtClass(className)) {
      if (!swtClasses.contains(className))
        swtClasses.add(className);
      return true;
    }
    return false;
  }

  public boolean isSwtClass(String className) {
    return className.startsWith("org/eclipse/swt");
  }

  public void dump() {
    for (String swtClass : swtClasses)
      System.out.println(swtClass);
  }

}
