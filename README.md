AsmStubber
====================

This project was initiated to stub the [Standard Widget Toolkit (SWT)](http://www.eclipse.org/swt/)
library. I wanted to use a SWT-Java application merely as a library and was
not interested in the GUI at all. Moreover the target system didn't have a
running X-server, so that application couldn't even be started on the system.
To cope with this problem I tried to stub SWT, hoping that the application's
back-end code wouldn't be too tightly coupled with SWT. Unfortunately this was
not the case and I ultimately failed to get the application up and running.

If you find an actual use case for this little project, please let me know! :-)

How it works
--------------------

This project uses the [ASM bytecode framework](http://asm.ow2.org/) to
stub compiled Java classfiles. Input classfiles are analyzed using ASM and
after ripping out each constructor or method body, the modified classfile is
written back to the filesystem.

More precisely, the method body of void-methods is completely discarded,
whereas non-void methods simply return the appropriate [default
value](http://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html)
of their return types, e.g. 0 for integers, null for objects. Similarly,
constructor bodys are replaced with a single call to the super-constructor
again with the appropriate default values.


How to build and execute
--------------------

Assuming you are in the root directory of the repository, simply use Apache
Maven to build and package the code with the following commands:

```shell
mvn package
```

Then, executing the application is simple:

```shell
java -jar target/asmstubber-1.0-SNAPSHOT.jar <INPUT_PATH> <OUTPUT_PATH>
```

The INPUT\_PATH parameter can be either a single java class file, a directory
that is recursively searched for class files or a single jar file. After the
stubber does its magic the modified class files are written to the existing
OUTPUT\_PATH directory.
