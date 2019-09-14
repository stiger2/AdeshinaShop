package android.bignerdbranch.adeshinashop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mPlusButton;
    private Button mMinusButton;
    private Button mAddToCart;
    private int mCount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mPlusButton = findViewById(R.id.button_plus);
        mMinusButton = findViewById(R.id.button_minus);
        mAddToCart = findViewById(R.id.addToCart_Button);

        mPlusButton.setOnClickListener(this);
        mAddToCart.setOnClickListener(this);
        mMinusButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        ViewGroup motherView = (ViewGroup)v.getParent();
        if (v.getId() == R.id.button_plus){
            TextView quantityView = (TextView)motherView.getChildAt(1);
            int mCount = Integer.parseInt(quantityView.getText().toString());
            mCount++;
            quantityView.setText(Integer.toString(mCount));
        }

        if (v.getId() == R.id.button_minus){
            TextView quantityView = (TextView)motherView.getChildAt(1);
            int mCount = Integer.parseInt(quantityView.getText().toString());
            mCount--;
            quantityView.setText(Integer.toString(mCount));
        }

        Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
        toast.show();


    }


}
