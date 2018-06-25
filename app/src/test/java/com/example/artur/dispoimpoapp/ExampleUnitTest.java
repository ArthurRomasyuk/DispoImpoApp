package com.example.artur.dispoimpoapp;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(new Date()); // Устанавливаем текущее время
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); //Устанавливаем понедельник на календаре, будто сейчас понедельник

        for(int i = 0; i < 7; i++){
            System.out.print(calendar.getTime());
            calendar.add(Calendar.DAY_OF_WEEK, 1); //Прибавляем сутки
        }
        assertEquals(4, 2 + 2);
    }
}