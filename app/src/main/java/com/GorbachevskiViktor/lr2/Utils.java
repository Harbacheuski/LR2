package com.GorbachevskiViktor.lr2;

import android.text.TextUtils;

public class Utils {

    public static boolean hasEmptyFields(String... strings) {
        for (String string : strings) {
            if (TextUtils.isEmpty(string)) {
                return true;
            }
        }

        return false;
    }

}