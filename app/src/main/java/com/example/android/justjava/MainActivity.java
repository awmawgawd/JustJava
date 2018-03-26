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

       String message = "Total: $" + (quantity * 5);
       message = message + "\nThank You!";
       displayMessage(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);


        // Write number into log: Log.v(String, String)
        Log.v("number", "" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number){
        TextView priceTextView = (TextView)findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     *
     * This method is called when the plus button is clicked.
     */
    public void increment(View view){

        quantity = quantity + 1;
        display(quantity);
    }

    /**
     *
     * This method is called when the minus button is clicked.
     * 
     */
    public void decrement(View view){

        quantity = quantity - 1;
        if(quantity < 0){
            quantity = 0;
        }
        display(quantity);
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

}