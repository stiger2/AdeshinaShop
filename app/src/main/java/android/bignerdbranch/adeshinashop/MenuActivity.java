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

import java.util.ArrayList;
import java.util.HashMap;


public class MenuActivity extends AppCompatActivity {

    private TextView mItemsInCart;
    private TextView mCurrentTotal;
    private Float mTotal = 0F;
    private int mNoOfItems = 0;
    private int quantityInCart = 0;
    private int mCount;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mItemsInCart = findViewById(R.id.current_inCart);
        mCurrentTotal = findViewById(R.id.current_amount);
    }

    public void quantityIncrease(View view) {
        ViewGroup motherView = (ViewGroup) view.getParent();
        TextView quantityView = (TextView) motherView.getChildAt(1);
        int mCount = Integer.parseInt(quantityView.getText().toString());
        mCount++;
        mNoOfItems++;
        //mItemsInCart.setText(Integer.toString(mNoOfItems));
        quantityView.setText(Integer.toString(mCount));

        ViewGroup parentView = (ViewGroup) motherView.getParent();
        ViewGroup priceParent = (ViewGroup) parentView.getChildAt(2);
        TextView priceView = (TextView) priceParent.getChildAt(0);
        ViewGroup subToTalParent = (ViewGroup) parentView.getChildAt(5);
        TextView subTotal = (TextView) subToTalParent.getChildAt(1);
        Float mPrice = Float.parseFloat(priceView.getText().toString());
        mTotal += mPrice;
        Float subTot = Float.parseFloat(subTotal.getText().toString()) + mPrice;
        String subTott = String.format("%.2f", subTot);
        subTotal.setText(subTott);
    }

    public void quantityDecrease(View view) {
        ViewGroup motherView = (ViewGroup) view.getParent();
        TextView quantityView = (TextView) motherView.getChildAt(1);
        int mCount = Integer.parseInt(quantityView.getText().toString());
        if (mCount != 0) {
            mCount--;
            mNoOfItems--;
            ViewGroup parentView = (ViewGroup) motherView.getParent();
            ViewGroup priceParent = (ViewGroup) parentView.getChildAt(2);
            TextView priceView = (TextView) priceParent.getChildAt(0);
            ViewGroup subToTalParent = (ViewGroup) parentView.getChildAt(5);
            TextView subTotal = (TextView) subToTalParent.getChildAt(1);
            Float mPrice = Float.parseFloat(priceView.getText().toString());
            mTotal -= mPrice;
            Float subTot = Float.parseFloat(subTotal.getText().toString()) - mPrice;
            String subTott = String.format("%.2f", subTot);
            subTotal.setText(subTott);
        }
        //mItemsInCart.setText(Integer.toString(mNoOfItems));
        quantityView.setText(Integer.toString(mCount));
    }


    public void addToCart(View view) {
        ViewGroup motherView = (ViewGroup)view.getParent().getParent();
        TextView quantity = (TextView)((ViewGroup)motherView.getChildAt(3)).getChildAt(1);
        int quantityToAddToCart = Integer.parseInt(quantity.getText().toString());
        if(quantityToAddToCart != 0) {
            quantityInCart += quantityToAddToCart;
            mItemsInCart.setText(Integer.toString(quantityInCart));
            Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
            toast.show();
            String currTotal = String.format("%.2f", mTotal);
            mCurrentTotal.setText(currTotal);
            Button btn = (Button) view;
            btn.setText("ADDED TO CART");
            btn.setEnabled(false);
        }
        else{
            Toast toast = Toast.makeText(this, R.string.toast_invalid_quantity_message, Toast.LENGTH_SHORT);
            toast.show();

        }
    }
}
