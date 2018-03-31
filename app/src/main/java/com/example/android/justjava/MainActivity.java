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
import android.widget.CheckBox;
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
        boolean isWhippedBool = isWhipped();
        boolean isChocBool = isChoc();
        String orderSummary = createOrderSummary(price, isWhippedBool, isChocBool);
        displayMessage(quantity, orderSummary);
    }

    /**
     *
     * @return if Whipped cream is selected true, or false if not
     */
    private boolean isWhipped(){
        CheckBox wCh = (CheckBox) findViewById(R.id.whipCheck);
        return(wCh.isChecked());

    }

    /**
     *
     * @return if Chocolate is selected true, or false if not
     */
    private boolean isChoc(){
        CheckBox cCh = (CheckBox) findViewById(R.id.chocCheck);
        return(cCh.isChecked());
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
     * This method displays the given text on the screen
     */
    private void displayMessage(int number, String msg) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        if(number != 0){
            orderSummaryTextView.setText(msg);
        }
        else{
            orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(number));
        }
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
        /* $5 per cup */
        int price =  quantity * 5;
        return price;
    }

    /**
     *
     * @param price of order
     * @return String summary of order
     */
    public String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate){
        String priceMessage = "Name: Jacqueline";
        priceMessage += "\nAdd whipped cream? " + hasWhippedCream;
        priceMessage += "\nAdd chocolate? " + hasChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you!" ;
        return priceMessage;
    }
}