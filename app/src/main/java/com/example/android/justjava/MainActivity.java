/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * Declare and initialize quantity
     */
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     * Not being used.
     */

    public void submitOrder(View view) {
        int price = calculatePrice();
        displayPrice(price);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numcoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numcoffees);

        // Write number into log: Log.v(String, String)
        Log.v("number", "" + numcoffees);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        if(number != 0){
            priceTextView.setText(NumberFormat.getCurrencyInstance().format(number) + "\n" + displayMessage());
        }
        else{
             priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
        }
    }

    /**
     * This method displays the given text on the screen.
     */
    private String displayMessage() {
        String message = "Thank you!";
        return message;
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {

        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {

        quantity = quantity - 1;
        if (quantity < 0) {
            quantity = 0;
        }
        displayQuantity(quantity);
    }


    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private int calculatePrice() {
        int price =  quantity * 5;
        return price;
    }
}