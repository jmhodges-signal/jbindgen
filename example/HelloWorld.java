// Copyright 2020 Open Whisper Systems
//
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//
//  See the License for the specific language governing permissions and
//  limitations under the License.

class HelloWorld {
  private static native String hello(String input);
  private static native byte[] helloByte(byte[] input);
  private static native void factAndCallMeBack(int n, HelloWorld callback);

  private static native long counterNew(HelloWorld callback);
  private static native void counterIncrement(long counter_ptr);
  private static native void counterDestroy(long counter_ptr);

  private static native void asyncComputation(HelloWorld callback);

  static {
    System.loadLibrary("mylib");
  }

  public static void main(String[] args) {

    String output = HelloWorld.hello("World");
    System.out.println(output);
    byte[] outputByte = HelloWorld.helloByte("byte".getBytes());
    System.out.println(outputByte);


    HelloWorld.factAndCallMeBack(6, new HelloWorld());

    long counter_ptr = counterNew(new HelloWorld());

    for (int i = 0; i < 5; i++) {
      counterIncrement(counter_ptr);
    }

    counterDestroy(counter_ptr);

    System.out.println("Invoking asyncComputation (thread id = " + Thread.currentThread().getId() + ")");
    asyncComputation(new HelloWorld());
  }

  public void factCallback(int res) {
    System.out.println("factCallback: res = " + res);
  }

  public void counterCallback(int count) {
    System.out.println("counterCallback: count = " + count);
  }

  public void asyncCallback(int progress) {
    System.out.println("asyncCallback: thread id = " + Thread.currentThread().getId() + ", progress = " + progress + "%");
  }
}