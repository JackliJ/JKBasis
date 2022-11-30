package com.jk.comm.viewmodel;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;


import com.jk.comm.model.BaseModel;
import com.jk.comm.model.SingleLiveEvent;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class BaseViewModel<M extends BaseModel> extends ViewModel implements IBaseViewModel,Consumer<Disposable> {
    protected M mModel;
    UIChangeLiveData mUIChangeLiveData;

    public UIChangeLiveData getUC() {
        if (mUIChangeLiveData == null) {
            mUIChangeLiveData = new UIChangeLiveData();
        }
        return mUIChangeLiveData;
    }

    public BaseViewModel<M> setUIChangeLiveData(UIChangeLiveData mUIChangeLiveData) {
        this.mUIChangeLiveData = mUIChangeLiveData;
        return this;
    }

    public BaseViewModel() {
        super();
    }

    public BaseViewModel(M model) {
        super();
        this.mModel = model;
    }

    @Override
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {

    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {
    }

    protected SingleLiveEvent createLiveData(SingleLiveEvent liveData) {
        if (liveData == null) {
            liveData = new SingleLiveEvent();
        }
        return liveData;
    }




    public final class UIChangeLiveData extends SingleLiveEvent {
        private SingleLiveEvent<Boolean> showLoadDialogEvent;
        private SingleLiveEvent<String> showErrorEvent;


        public SingleLiveEvent<Boolean> getShowLoadDialogEvent() {
            return showLoadDialogEvent = createLiveData(showLoadDialogEvent);
        }

        public SingleLiveEvent<String> getShowErrorEvent() {
            return showErrorEvent = createLiveData(showErrorEvent);
        }

    }

    public void showDialog(boolean show){
        getUC().getShowLoadDialogEvent().postValue(show);
    }

    public void showError(String error){
        getUC().getShowErrorEvent().postValue(error);
    }


    @Override
    public void accept(Disposable disposable) throws Exception {
        if(mModel != null){
            mModel.addSubscribe(disposable);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mModel != null) {
            mModel.onCleared();
        }
    }

    public interface ObserverListener<T>{
        void onSuccess(T response);
        void onFail(String message);
        void onComplete();
    }
}
