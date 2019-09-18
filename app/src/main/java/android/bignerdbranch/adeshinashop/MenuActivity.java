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
        TextView quantityView = getQuantityView(view);
        int mCount = Integer.parseInt(quantityView.getText().toString());
        if (mCount == 0){
            LinearLayout addToCartAndSubtotal = (LinearLayout) ((ViewGroup)view.getParent().getParent()).getChildAt(5);
            addToCartAndSubtotal.setVisibility(view.VISIBLE);
        }
        mCount++;
        mNoOfItems++;
        quantityView.setText(Integer.toString(mCount));
        TextView subTotalTextView = getSubtotalTextView((ViewGroup) view.getParent());
        Float mPrice = getItemPrice((ViewGroup) view.getParent());
        mTotal += mPrice;
        subTotalTextView.setText(String.format("%.2f", Float.parseFloat(subTotalTextView.getText().toString()) + mPrice));
    }

    public void quantityDecrease(View view) {
        TextView quantityView = getQuantityView(view);
        int mCount = Integer.parseInt(quantityView.getText().toString());
        if (mCount == 1){
            LinearLayout addToCartAndSubtotal = (LinearLayout) ((ViewGroup)view.getParent().getParent()).getChildAt(5);
            addToCartAndSubtotal.setVisibility(view.INVISIBLE);
        }
        if (mCount != 0) {
            mCount--;
            mNoOfItems--;
            TextView subTotalTextView = getSubtotalTextView((ViewGroup) view.getParent());
            Float mPrice = getItemPrice((ViewGroup) view.getParent());
            mTotal -= mPrice;
            subTotalTextView.setText(String.format("%.2f", Float.parseFloat(subTotalTextView.getText().toString()) - mPrice));
        }
        quantityView.setText(Integer.toString(mCount));
    }

    public void addToCart(View view) {
        ViewGroup motherView = (ViewGroup)view.getParent().getParent();
        TextView quantity = (TextView)((ViewGroup)motherView.getChildAt(3)).getChildAt(1);
        int quantityToAddToCart = Integer.parseInt(quantity.getText().toString());
        quantityInCart += quantityToAddToCart;
        mItemsInCart.setText(Integer.toString(quantityInCart));
        Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
        toast.show();
        String currTotal = String.format("%.2f", mTotal);
        mCurrentTotal.setText(currTotal);
        Button btn = (Button) view;
        btn.setText("" + quantityToAddToCart + " OF THIS ITEM ADDED TO CART");
        btn.setEnabled(false);


    }

    public float getItemPrice(ViewGroup viewGroup){
        ViewGroup priceParent = (ViewGroup) ((ViewGroup)viewGroup.getParent()).getChildAt(2);
        TextView priceView = (TextView) priceParent.getChildAt(0);
        return Float.parseFloat(priceView.getText().toString());
    }

    public TextView getSubtotalTextView(ViewGroup viewGroup){
        ViewGroup subToTalParent = (ViewGroup)((ViewGroup)viewGroup.getParent()).getChildAt(5);
        return (TextView) subToTalParent.getChildAt(1);

    }

    public TextView getQuantityView(View view){
        return (TextView)((ViewGroup)view.getParent()).getChildAt(1);
    }
}
