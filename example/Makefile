default: java_run

javac_run:
	javac HelloWorld.java && \
	javac -h . HelloWorld.java

java_run: javac_run lib
	java -Djava.library.path=mylib/target/debug/ HelloWorld

.PHONY: lib

lib:
	jbindgen --jniimpl_use 'crate::jniimpl' HelloWorld.h -- -I/usr/lib/jvm/java-11-openjdk-amd64/include/ -I/usr/lib/jvm/java-11-openjdk-amd64/include/linux/ > mylib/src/jniffi.rs && \
	cd mylib && cargo build