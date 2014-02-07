package org.k13n.asmstubber.indexing;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ClassIndex {
  private Map<String, byte[]> index;

  public ClassIndex() {
    index = new HashMap<String, byte[]>();
  }

  public void add(String className, byte[] bytecode) {
    if (!index.containsKey(className))
      index.put(className, bytecode);
  }

  public byte[] getBytecode(String className) {
    if (!index.containsKey(className))
      throw new RuntimeException("Class definition not found for " + className);
    return index.get(className);
  }

  public Set<String> keys() {
    return index.keySet();
  }

}

