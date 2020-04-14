package com.cusc.cuscai.util;

import java.util.UUID;

public class UUIDUtil {
    public static String genId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String GenJobID() {
        return "Task_" + UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
