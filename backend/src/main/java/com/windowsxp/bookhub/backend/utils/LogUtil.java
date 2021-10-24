package com.windowsxp.bookhub.backend.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogUtil {
    public static void debug(Object e){
        String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
        log.info("DEBUG\t" + fullClassName + "." + methodName + "():" + lineNumber + "\t" + e.toString());
    }

    public static void error(Object e) {
        String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
        log.info("ERROR\t" + fullClassName + "." + methodName + "():" + lineNumber + "\t" + e.toString());
    }
}
