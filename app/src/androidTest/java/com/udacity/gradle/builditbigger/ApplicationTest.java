package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.text.TextUtils;
import android.util.Log;

import java.util.concurrent.CountDownLatch;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    CountDownLatch signal = null;

    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        signal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        signal.countDown();
    }

    public void testJokeGetTask() throws InterruptedException {

        JokeAsyncTask task = new JokeAsyncTask();
        task.execute(new JokeAsyncTask.Listener() {
            @Override
            public void onComplete(String result) {
                Log.d( "TEST", result);
                assertFalse(TextUtils.isEmpty(result));
                signal.countDown();
            }
        });
        signal.await();



    }
}