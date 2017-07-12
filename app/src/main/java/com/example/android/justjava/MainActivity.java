package com.example.android.justjava;

import android.content.Context;
import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    int quantity=0;

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price=calculatePrice();
        displayMessage(createOrderSummary(price));

    }

    /**
     * Calculates the price of the order.
     *
     */
    private int calculatePrice()
    {
         return quantity * 5;
    }

    public void increment(View view) {

        display(++quantity);

    }

    public void decrement(View view) {

        display(--quantity);


    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    public void reset(){
        quantity=0;
        String message="Total: $0";
        displayMessage(message);

    }

    private String createOrderSummary(int price){
        String summary="Name: Anshul Yadav"+"\nQuantity: "+quantity+"\nTotal: $"+price+"\nThank You!";
        return summary;
    }

}
