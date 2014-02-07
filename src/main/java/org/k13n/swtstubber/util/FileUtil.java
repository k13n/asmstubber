package org.k13n.swtstubber.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {

  public static byte[] writeStreamToArray(InputStream stream)
      throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    byte[] buffer = new byte[1024];
    int length = 0;
    while ((length = stream.read(buffer)) != -1)
      baos.write(buffer, 0, length);
    return baos.toByteArray();
  }

  public static void writeBytecode(String targetDirectory, String className,
      byte[] bytecode) throws IOException {
    String packageName = packageName(className);
    createPackageHierarchy(targetDirectory, packageName);
    String file = targetDirectory + File.separator + className + ".class";
    FileOutputStream stream = new FileOutputStream(new File(file));
    stream.write(bytecode, 0, bytecode.length);
    stream.flush();
    stream.close();
  }

  private static String createPackageHierarchy(String targetDirectory,
      String packageName) {
    String path = targetDirectory;
    for (String folder : packageName.split("\\.")) {
      path = path + File.separator + folder;
      createDirectoryIfNotExists(path);
    }
    return path;
  }

  private static void createDirectoryIfNotExists(String directory) {
    File file = new File(directory);
    if (file.exists()) {
      if (!file.isDirectory()) {
        String msg = "Path " + directory + " exists, but is not a directory";
        throw new RuntimeException(msg);
      }
    } else {
      file.mkdir();
    }
  }

  private static String packageName(String internalName) {
    int separatorPos = internalName.lastIndexOf("/");
    String packageName = internalName.substring(0, separatorPos);
    return packageName.replace('/', '.');
  }

}
