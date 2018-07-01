package com.diabin.latte.net.callback;

import android.os.Handler;

import com.diabin.latte.app.Latte;
import com.diabin.latte.ui.LatteLoader;
import com.diabin.latte.ui.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/6/26.
 */

public class RequestCallbacks implements Callback {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;

    private static final Handler handler = new Handler();

    public RequestCallbacks(IRequest request, ISuccess success, IFailure failure, IError error, LoaderStyle style) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE = style;
    }

    @Override
    public void onResponse(Call call, Response response) {
        if(response.isSuccessful()){
            if(call.isExecuted()){
                if(SUCCESS !=null){
                    SUCCESS.onSuccess((String) response.body());
                }
            }
        }else{
            if(ERROR != null){
                ERROR.onError(response.code(), response.message());
            }
        }
        stopLoading();

    }

    @Override
    public void onFailure(Call call, Throwable t) {
        if(FAILURE != null){
            FAILURE.onFailure();
        }
        if(REQUEST != null){
            REQUEST.onRequestEnd();
        }
        stopLoading();
    }

    private void stopLoading(){
        //关闭Loader
        if (LOADER_STYLE != null){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            }, 1000);
        }
    }
}
