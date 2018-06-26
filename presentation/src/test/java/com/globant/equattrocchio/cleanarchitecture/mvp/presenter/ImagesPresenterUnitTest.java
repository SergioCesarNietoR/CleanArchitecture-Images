package com.globant.equattrocchio.cleanarchitecture.mvp.presenter;

import com.globant.equattrocchio.cleanarchitecture.BuildConfig;
import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.ImagesPresenter;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView;
import com.globant.equattrocchio.domain.GetLatestImagesUseCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@Config(constants = BuildConfig.class)
@RunWith(RobolectricTestRunner.class)
public class ImagesPresenterUnitTest {

    private ImagesPresenter presenter;

    @Mock private GetLatestImagesUseCase getLatestImagesUseCase;
    @Mock private ImagesView view;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new ImagesPresenter(view, getLatestImagesUseCase);
    }

    @Test
    public void objectsNotNull() {
        assertNotNull(presenter);
        assertNotNull(view);
        assertNotNull(getLatestImagesUseCase);
    }

    @Test
    public void testViewInteractions() {
        assertNotNull(view);
        view.callServiceBtnPressed();
        verify(view).callServiceBtnPressed();
        view.showText("JSON");
        verify(view).showText("JSON");
        verifyNoMoreInteractions(view);
    }

    @Test
    public void testGetLastImagesUseCase() {
        assertNotNull(getLatestImagesUseCase);
        getLatestImagesUseCase.execute(null);
        verify(getLatestImagesUseCase).execute(null);
    }
}
