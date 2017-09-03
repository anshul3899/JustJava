package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.name;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity=0;
    String summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {


       EditText textName=(EditText) findViewById(R.id.plain_text_input);
        String name= textName.getText().toString();

        CheckBox WhippedCreamCheckbox=(CheckBox) findViewById(R.id.notify_me_checkbox);
         boolean hasWhippedCream = WhippedCreamCheckbox.isChecked();
        CheckBox ChocolateAdded=(CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = ChocolateAdded.isChecked();

        if(quantity<1){
            Context context = getApplicationContext();
            CharSequence text = "You can't order less than 1 coffee!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        if(quantity>99)
        {
            Context context = getApplicationContext();
            CharSequence text = "You can't order more than 100 coffee!";
            int duration = Toast.LENGTH_SHORT;

           Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;

        }
        int price=calculatePrice();

        displayMessage(createOrderSummary(price,hasWhippedCream,hasChocolate,name));

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto"));
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, "anshulyadav1911@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for"+name);
        intent.putExtra(Intent.EXTRA_TEXT, summary);

        startActivity(Intent.createChooser(intent, "Send Email"));


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
if (quantity>99)
    return;
        else

    display(++quantity);

    }

    public void decrement(View view) {
        if (quantity<1)
            return;


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


    private String createOrderSummary(int price, boolean bool, boolean boolchoc,String name){
        if(bool==true)
        {
                    price=price+quantity*1;
         }
         if (boolchoc==true) {
             price=price+quantity*2;
         }


        summary="Name: "+ name +"\nAdded Whipped Cream ? "+bool+"\nAdded Chocolate ? "+boolchoc+"\nQuantity: "+quantity+"\nTotal: $"+price+"\nThank You!";
        return summary;
    }

}
