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


public class MenuActivity extends AppCompatActivity {
    private TextView mItemsInCart;
    private TextView mTotal;
    private int mNoOfItems = 0;
    private int mCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mItemsInCart = findViewById(R.id.current_inCart);
    }

    public void quantityIncrease(View view) {
        ViewGroup motherView = (ViewGroup) view.getParent();
        TextView quantityView = (TextView) motherView.getChildAt(1);
        int mCount = Integer.parseInt(quantityView.getText().toString());
        mCount++;
        mNoOfItems++;
        mItemsInCart.setText(Integer.toString(mNoOfItems));
        quantityView.setText(Integer.toString(mCount));

        ViewGroup parentView = (ViewGroup) motherView.getParent();
        ViewGroup priceParent = (ViewGroup) parentView.getChildAt(2);
        TextView priceView = (TextView) priceParent.getChildAt(0);
        ViewGroup subToTalParent = (ViewGroup) parentView.getChildAt(6);
        TextView subTotal = (TextView) subToTalParent.getChildAt(1);
        Float mPrice = Float.parseFloat(priceView.getText().toString());
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
            ViewGroup subToTalParent = (ViewGroup) parentView.getChildAt(6);
            TextView subTotal = (TextView) subToTalParent.getChildAt(1);
            Float mPrice = Float.parseFloat(priceView.getText().toString());
            Float subTot = Float.parseFloat(subTotal.getText().toString()) - mPrice;
            String subTott = String.format("%.2f", subTot);
            subTotal.setText(subTott);
        }
        mItemsInCart.setText(Integer.toString(mNoOfItems));
        quantityView.setText(Integer.toString(mCount));
    }
}
