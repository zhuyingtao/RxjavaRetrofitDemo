package com.example.rxjavaretrofitdemo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpMethods {
    private static final String BASE_URL = "https://api.douban.com/v2/movie/";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private MovieService movieService;

    private HttpMethods() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        movieService = retrofit.create(MovieService.class);
    }

    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void getMovie(Observer<List<Movie>> observer, int start, int count) {
        movieService.getTopMovie(start, count)
                .map(new HttpResultFunc<List<Movie>>())
//                .flatMap(new Function<List<Movie>, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(List<Movie> movies) throws Exception {
//                        return Observable.just(movies);
//                    }
//                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .doOnNext(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) throws Exception {

                    }
                })
                .subscribe(observer);
    }

    private class HttpResultFunc<T> implements Function<HttpResult<T>, T> {
        @Override
        public T apply(HttpResult<T> tHttpResult) throws Exception {
            if (tHttpResult.getCount() == 0) {
                try {
                    throw new MovieCountException(tHttpResult.getCount());
                } catch (MovieCountException e) {
                    e.printStackTrace();
                }
            }
            return tHttpResult.getSubjects();
        }
    }

}
