package com.example.artur.dispoimpoapp;

import android.content.Context;
import android.icu.util.Calendar;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(new Date()); // Устанавливаем текущее время
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); //Устанавливаем понедельник на календаре, будто сейчас понедельник

        for(int i = 0; i < 7; i++){
            System.out.print(calendar.get(Calendar.DATE));
            calendar.add(Calendar.DAY_OF_WEEK, 1); //Прибавляем сутки
        }

        assertEquals("com.example.artur.dispoimpoapp", appContext.getPackageName());
    }
}
