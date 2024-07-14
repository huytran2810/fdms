package org.SWP391_FUHL_SE1825.FDMS.utils;

import java.util.UUID;

public class CommonUtils {
        public static long generateUniqueId() {
            return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }
}
