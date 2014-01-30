package org.k13n.swtstubber.util;

import java.io.ByteArrayOutputStream;
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

}
