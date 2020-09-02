package com.example.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.jakewharton.rxbinding4.widget.RxTextView;

import java.util.regex.Pattern;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "OnSubscribe" ;
    TextView names;
    Disposable disposable;
    EditText editText;
    Button button;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        names = findViewById(R.id.names);
        editText = findViewById(R.id.editText);
       // button = findViewById(R.id.button);

        disposable = RxTextView.textChanges(editText)
                .subscribe(s->{
                    //names.setText(s);
                    if (!onEmail(s.toString())){
                        editText.setError("Enter email");
                    }
                });

//        Observable<String> namesObservable = getNameObservable();
//
//        disposable = (Disposable) namesObservable
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//
//                .map(Integer::parseInt)
//                .reduce(Integer::sum)
//                .filter(new Predicate<String>() {
//                    @Override
//                    public boolean test(String s) throws Exception {
//                        return s.startsWith("v");
//                    }
//                })
//                .filter(s->s.startsWith("s"))
//                .map(Object::toString)
//                .subscribe(s->{
//                    names.setText(s);
//                    //names.append(" ");
//                });

                //,
//                        Throwable::printStackTrace,
//                        ()-> Log.d(TAG, "onCreate: "),
//                        d->Log.d(TAG, "onSubscribe: "));
//        RxView.clicks(button)
//                .subscribe(u -> Toast.makeText(this, "Button clicked", Toast.LENGTH_SHORT).show());
//
//
    }

    Observable<String> getNameObservable(){
        return Observable.just("123", "111", "222", "333","444", "1234");

    }


    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }

    protected boolean onEmail(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return  pattern.matcher(email).matches();
    }
}