package com.kilkags.touchecho.lotus;

import com.google.common.base.CaseFormat;
import com.kilkags.touchecho.TouchEcho;

/**
 * @author DustW
 */
public class LotusSymphony {
    public static String getLangKeyFromRegKey(String regKey) {
        return new StringBuffer().append(TouchEcho.MOD_ID).append(".").append(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, regKey)).toString();
    }
}
