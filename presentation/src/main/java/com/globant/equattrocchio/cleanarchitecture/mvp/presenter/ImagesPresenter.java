package com.globant.equattrocchio.cleanarchitecture.mvp.presenter;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;

import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesAdapter;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;
import com.globant.equattrocchio.data.response.Image;
import com.globant.equattrocchio.data.response.Result;
import com.globant.equattrocchio.domain.GetLatestImagesUseCase;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

/**
 * This is a presenter to get a list of images from a service
 */
public class ImagesPresenter {

    private ImagesView view;
    /**
     * Business logic layer
     */
    private GetLatestImagesUseCase getLatestImagesUseCase;


    public ImagesPresenter(ImagesView view, GetLatestImagesUseCase getLatestImagesUseCase) {
        this.view = view;
        this.getLatestImagesUseCase = getLatestImagesUseCase;
    }

    /**
     * Show the json response
     *
     * @param jsonResponse JSON response, list images
     */
    public void onShowDataResponse(String jsonResponse) {
        //view.showText(jsonResponse);
        Result result = new Gson().fromJson(jsonResponse, Result.class);
        List<Image> images = result.getImages();
        ImagesAdapter imagesAdapter = new ImagesAdapter(view.getActivity(), images);
        view.rvImages.setAdapter(imagesAdapter);
        view.rvImages.setHasFixedSize(true);
        view.rvImages.setLayoutManager(new LinearLayoutManager(view.getActivity()));
    }

    /**
     * This method execute the use case for download the list of images.
     */
    private void onCallServiceButtonPressed() {

        getLatestImagesUseCase.execute(new DisposableObserver<String>() {
            @Override
            public void onNext(String imagesServices) {
                onShowDataResponse(imagesServices);
            }

            @Override
            public void onError(Throwable e) {
                view.showError();
            }

            @Override
            public void onComplete() {

            }
        }, null);

    }

    private void loadFromPreferences() {
        // view.showText("EL TEXTO QUE ME TRAGIA DE LAS PREFERENCES");// todo: traerme el texto de las preferences
    }

    public void register() {
        Activity activity = view.getActivity();

        if (activity == null) {
            return;
        }

        RxBus.subscribe(activity, new CallServiceButtonObserver() {
            @Override
            public void onEvent(CallServiceButtonPressed event) {
                onCallServiceButtonPressed();
            }
        });

    }

    public void unregister() {
        Activity activity = view.getActivity();

        if (activity == null) {
            return;
        }
        RxBus.clear(activity);
    }
}
