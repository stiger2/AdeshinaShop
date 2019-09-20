package android.bignerdbranch.adeshinashop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CheckoutActivity extends AppCompatActivity {
    private TextView mTotalBeforeTaxTextView;
    private TextView mProvincialTaxTextView;
    private TextView mFederaltaxTextView;
    private TextView mTotalTextView;

    private Float mTotalBeforeTax;
    private static final Float mProvincialTax = 0.10f;
    private static final Float mFederalTax = 0.05f;
    private Float mProTax;
    private Float mFedTax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        mTotalBeforeTaxTextView = findViewById(R.id.before_tax_amount_textView);
        mProvincialTaxTextView = findViewById(R.id.gst_amount_textView);
        mFederaltaxTextView = findViewById(R.id.pst_amount_textView);
        mTotalTextView = findViewById(R.id.total_amount_textView);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MenuActivity.EXTRA_MESSAGE);
        mTotalBeforeTax = Float.parseFloat(message);
        mTotalBeforeTaxTextView.setText("$" + String.format("%.2f", mTotalBeforeTax));
        mProTax = mTotalBeforeTax * mProvincialTax;
        mFedTax = mTotalBeforeTax * mFederalTax;
        mProvincialTaxTextView.setText("$" + String.format("%.2f", mProTax));
        mFederaltaxTextView.setText("$" + String.format("%.2f", mFedTax));
        mTotalTextView.setText("$" + String.format("%.2f", mTotalBeforeTax + mFedTax));
    }


}
