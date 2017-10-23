package com.selenium.enums;

import java.util.ArrayList;
import java.util.List;

public enum Support implements GravitecService {

    EMAIL,
    CHAT,
    INFORMATION_MATERIALS,
    ACCOUNT_MANAGEMENT,
    DEDICATE_SUPPORT,
    CONSULTING;

    private static List<GravitecService> supportChannels = new ArrayList<>();

    private static List<GravitecService> support = new ArrayList<>();

    static {
        supportChannels.add(EMAIL);
        supportChannels.add(CHAT);
        support.add(ACCOUNT_MANAGEMENT);
        support.add(DEDICATE_SUPPORT);
        support.add(CONSULTING);
        support.add(INFORMATION_MATERIALS);
    }

    public static List<GravitecService> getSupportChannels() {
        return supportChannels;
    }

    public static List<GravitecService> getSupport() {
        return support;
    }
}
