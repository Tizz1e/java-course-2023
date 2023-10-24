package edu.hw2.callingInfo;

public class Task4 {
    private static final int CALL_DEEP = 3;

    private Task4() {
    }

    public static CallingInfo callingInfo() {
        StackTraceElement[] traceElements = Thread.currentThread().getStackTrace();
        if (traceElements.length < CALL_DEEP) {
            throw new IllegalThreadStateException("No caller for function");
        }
        return new CallingInfo(traceElements[CALL_DEEP].getClassName(), traceElements[CALL_DEEP].getMethodName());
    }
}
