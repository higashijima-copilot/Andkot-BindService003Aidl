// IAaaService.aidl
package com.aaa.bindservice003aidl;
import com.aaa.bindservice003aidl.IAaaServiceCallback;

interface IAaaService {
    int getPid();
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString);
    void setAaaServiceCallback(IAaaServiceCallback callback);
}