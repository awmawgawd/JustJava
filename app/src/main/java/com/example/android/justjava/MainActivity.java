/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * Declare and initialize quantity
     */
    int quantity;


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
        /** Retrieve quantity **/
        TextView qt = (TextView)findViewById(R.id.quantity_text_view);
        quantity = Integer.parseInt(qt.getText().toString());
        /** Retrieve Name **/
        String name = getName();
        /** Check if whipped cream and chocolate selected **/
        boolean isWhippedBool = isWhipped();
        boolean isChocBool = isChoc();
        /** Calculate price of drink **/
        int price = calculatePrice(isWhippedBool, isChocBool);
        /** Create order summary **/
        String orderSummary = createOrderSummary(name, price, isWhippedBool, isChocBool);

        /** Print out order summary **/
        //displayMessage(quantity, orderSummary);

        composeEmail(name, orderSummary);
    }

    private String getName(){
        EditText nameText = (EditText) findViewById(R.id.nameET);
        return nameText.getText().toString();
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

//    /**
//     * This method displays the given text on the screen
//     */
//    private void displayMessage(int number, String msg) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        if(number != 0){
//            orderSummaryTextView.setText(msg);
//        }
//        else{
//            orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(number));
//        }
//    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {

        quantity = quantity + 1;
        if(quantity > 100){
            Toast.makeText(getApplicationContext(), "Maximum quantity is 100.", Toast.LENGTH_SHORT).show();
            quantity = 100;

        }
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {

        quantity = quantity - 1;
        /** Prevent quantity from going below 1 **/
        if (quantity < 1) {
            Toast.makeText(getApplicationContext(), "Minimum quantity is 1.", Toast.LENGTH_SHORT).show();
            quantity = 1;

        }
        displayQuantity(quantity);
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private int calculatePrice(boolean whip, boolean choc) {
        /* $5 per cup. $1 extra for whipped cream. $2 extra for chocolate */
        int price =  quantity * 5;
        if(whip){
            price += quantity * 1;
        }
        if(choc){
            price += quantity * 2;
        }

        return price;
    }

    /**
     *
     * @param price of order
     * @return String summary of order
     */
    public String createOrderSummary(String namez, int price, boolean hasWhippedCream, boolean hasChocolate){
        String priceMessage = "Name: " + namez;
        priceMessage += "\nAdd whipped cream? " + hasWhippedCream;
        priceMessage += "\nAdd chocolate? " + hasChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you!" ;
        return priceMessage;
    }

    public void composeEmail(String namez, String orderSumm) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for " + namez);
        intent.putExtra(Intent.EXTRA_TEXT, orderSumm);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}