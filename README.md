jbindgen
========

Jbindgen generates Rust functions from javac-generated JNI C headers while
making it easy to modify your Rust that implements those functions.

It does that by generating the global Rust functions needed to satisfy the C
JNI interface that, in turn, call matching methods on a given Rust struct. This allows us to modify the method bodies on that type and regenerate the JNI interfacing code without stomping over one or the other.

Jbindgen's use is similar to `bindgen` with arguments for clang given after a
  `--`. It also expects a `rustfmt` binary in your `PATH`.

Usage:

```
jbindgen
Generates Rust code from Java JNI headers

USAGE:
    jbindgen [FLAGS] [OPTIONS] <header> -- <clang-args>...

FLAGS:
    -h, --help       Prints help information
    -V, --version    Prints version information

OPTIONS:
        --jniimpl_use <jniimpl_use>    The full use path to find the JniImpl struct definition that the generated
                                       functions will expect a matching method on [default: jniimpl::JniImpl]

ARGS:
    <header>           Java JNI C or C++ header
    <clang-args>...
```
