/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.social.goalapp.utils;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;


import com.social.goalapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class CommonUtils {

    private static final String TAG = "CommonUtils";

    private CommonUtils() {

    }



    public static String getFormattedDate(String datetime){
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        String res=null;
        String d[]=datetime.split("T");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date=formatter.parse(d[0]);
            formatter = new SimpleDateFormat("MMM d, yyyy");
            formatter.setTimeZone(tz);
            res= formatter.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
      return res;
    }
}
