package org.k13n.asmstubber.indexing;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.k13n.asmstubber.util.FileUtil;
import org.objectweb.asm.ClassReader;

public class ClassIndexer {
  private final ClassIndex index;

  public ClassIndexer(ClassIndex index) {
    this.index = index;
  }

  public void index(String path) {
    File file = new File(path);
    if (file.exists()) {
      if (file.isFile() && path.endsWith(".jar"))
        indexJar(file);
      else if (file.isFile() && path.endsWith(".class"))
        indexClassFile(file);
      else if (file.isDirectory())
        indexDirectory(file);
    }
  }

  private void indexJar(File file) {
    try {
      ZipFile zipFile = new ZipFile(file);
      Enumeration<? extends ZipEntry> enumerator = zipFile.entries();
      while (enumerator.hasMoreElements()) {
        ZipEntry entry = enumerator.nextElement();
        String name = entry.getName();
        if (name.endsWith(".class")) {
          indexClassFile(zipFile.getInputStream(entry));
        }
      }
      zipFile.close();
    } catch (IOException ignore) { }
  }

  private void indexDirectory(File directory) {
    for (File file : directory.listFiles())
      index(file.toString());
  }

  private void indexClassFile(File file) {
    FileInputStream stream = null;
    try {
      stream = new FileInputStream(file);
      indexClassFile(stream);
    } catch (FileNotFoundException ignore) {
      // ignore
    } finally {
      try {
        stream.close();
      } catch (IOException ignore) { }
    }
  }

  private void indexClassFile(InputStream stream) {
    try {
      byte[] bytecode = FileUtil.writeStreamToArray(stream);
      ByteArrayInputStream bais = new ByteArrayInputStream(bytecode);
      ClassReader reader = new ClassReader(bais);
      index.add(reader.getClassName(), bytecode);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
