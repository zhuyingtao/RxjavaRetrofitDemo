package com.example.rxjavaretrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.btn_click)
    Button mButton;

    @BindView(R.id.tv_show)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_click)
    public void onClick() {
        getMovie();
    }

    private void getMovie() {
        Observer<List<Movie>> observer = new Observer<List<Movie>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(List<Movie> movies) {
                StringBuilder stringBuilder = new StringBuilder();
                for (Movie movie : movies) {
                    stringBuilder.append(movie.toString());
                }
                mTextView.setText(stringBuilder);
            }

            @Override
            public void onError(Throwable e) {
                mTextView.setText(e.getMessage());
            }

            @Override
            public void onComplete() {
                Toast.makeText(MainActivity.this, "Completed!", Toast.LENGTH_SHORT).show();
            }
        };
        HttpMethods.getInstance().getMovie(observer, 0, 10);
    }
}
