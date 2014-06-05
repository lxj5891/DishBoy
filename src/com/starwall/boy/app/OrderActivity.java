package com.starwall.boy.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import com.starwall.boy.R;

/**
 * Created by mac on 14-6-1.
 */
public class OrderActivity extends Activity {

    private static final String[] COUNTRIES = new String[] {
            "belgium", "france", "italy", "germany", "spain"
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, COUNTRIES);

        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.editText2);
        textView.setAdapter(adapter);
        textView.setValidator(new AutoCompleteTextView.Validator() {
            @Override
            public boolean isValid(CharSequence text) {
                Log.i("OrderActivity","isValid : " + text);
                return false;
            }

            @Override
            public CharSequence fixText(CharSequence invalidText) {
                Log.i("OrderActivity", "OrderActivity : " + invalidText);
                return null;
            }
        });
    }
}