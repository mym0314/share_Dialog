// IMyAidlInterface.aidl
package com.example.myaidl;
import com.example.myaidl.Person;
// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void getPerson(String account,String pwd,in Person person);
}
