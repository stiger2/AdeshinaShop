package android.bignerdbranch.adeshinashop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
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

    public static final String EXTRA_MESSAGE = "android.adeshinashop.extra.MESSAGE";
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

    /**
     * Method to increase the quantity
     * @param view increase button
     */
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

    /**
     * Method to decrease the quantity of items
     * @param view decrease button
     */
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

    /**
     * Method to add items to cart
     * @param view addToCart Button
     */
    public void addToCart(View view) {
        ViewGroup motherView = (ViewGroup)view.getParent().getParent();
        TextView quantity = (TextView)((ViewGroup)motherView.getChildAt(3)).getChildAt(1);
        int quantityToAddToCart = Integer.parseInt(quantity.getText().toString());
        quantityInCart += quantityToAddToCart;
        mItemsInCart.setText(Integer.toString(quantityInCart));

        String currTotal = String.format("%.2f", mTotal);
        mCurrentTotal.setText(currTotal);
        Button btn = (Button) view;
        btn.setText("ADDED TO CART");
        btn.setEnabled(false);
    }

    /**
     * Method to get the price of an item
     * @param viewGroup the viewGroup that contains the item
     * @return item price
     */
    public float getItemPrice(ViewGroup viewGroup){
        ViewGroup priceParent = (ViewGroup) ((ViewGroup)viewGroup.getParent()).getChildAt(2);
        TextView priceView = (TextView) priceParent.getChildAt(0);
        return Float.parseFloat(priceView.getText().toString());
    }

    /**
     * Method to get the subtotal TextView
     * @param viewGroup The viewGroup that contains the subtotal TextView
     * @return subTotal TextView
     */
    public TextView getSubtotalTextView(ViewGroup viewGroup){
        ViewGroup subToTalParent = (ViewGroup)((ViewGroup)viewGroup.getParent()).getChildAt(5);
        return (TextView) subToTalParent.getChildAt(1);

    }

    /**
     * Method to get the previous quantity of the item selected for updating
     * @param view the increase/Decrease button view
     * @return quantity TextView
     */
    public TextView getQuantityView(View view){
        return (TextView)((ViewGroup)view.getParent()).getChildAt(1);
    }

    /**
     * Checkout Method launches the checkout activity and send the total amount through an intent
     * @param view checkout textView
     */
    public void checkout(View view) {
        if (mNoOfItems != 0) {
            Intent intent = new Intent(this, CheckoutActivity.class);
            intent.putExtra(EXTRA_MESSAGE, Float.toString(mTotal));
            startActivity(intent);
        }
        else{
            Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
