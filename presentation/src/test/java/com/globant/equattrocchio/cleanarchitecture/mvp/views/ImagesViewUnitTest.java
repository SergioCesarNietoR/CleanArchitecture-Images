package com.globant.equattrocchio.cleanarchitecture.mvp.views;

import android.widget.Button;
import android.widget.TextView;

import com.globant.equattrocchio.cleanarchitecture.BuildConfig;
import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.base.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;

@Config(constants = BuildConfig.class)
@RunWith(RobolectricTestRunner.class)
public class ImagesViewUnitTest {

    private MainActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();
    }


    @Test
    public void checkActivityNotNull() throws Exception {
        assertNotNull(activity);

    }

    @Test
    public void notNullViews() {
        TextView label = (TextView) activity.findViewById(R.id.tv_incoming_json);
        Button button = (Button) activity.findViewById(R.id.btn_call_service);

        assertNotNull(label);
        assertNotNull(button);
    }

    @Test
    public void buttonClickShouldStartNewActivity() throws Exception {
        Button button = (Button) activity.findViewById(R.id.btn_call_service);
        assertNotNull(button);
        button.performClick();
    }

}
