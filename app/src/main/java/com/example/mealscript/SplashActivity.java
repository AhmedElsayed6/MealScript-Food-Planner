package com.example.mealscript;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mealscript.Auth.Views.AuthActivity;
import java.util.concurrent.TimeUnit;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity {
public static String TAG ="OBONE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Observable.timer(2, TimeUnit.SECONDS).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> navigateToNextScreen());

//        Observable observable = Observable.create(new ObservableOnSubscribe<Object>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
//                for (int i = 0 ; i <5 ; i ++){
//                    emitter.onNext(i);
//                }
//                emitter.onComplete();
//            }
//        });
//        Observer observer = new Observer() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                Log.i(TAG, "onSubscribe: " + d.toString());
//            }
//
//            @Override
//            public void onNext(Object o) {
//                Log.i(TAG, "onNext: "+ o.toString());
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.i(TAG, "onError: " + e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                Log.i(TAG, "onComplete: ");
//            }
//        };
//        observable.observeOn(Schedulers.computation())
//        observable.subscribe(observer);
    }

    private void navigateToNextScreen() {
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }
}