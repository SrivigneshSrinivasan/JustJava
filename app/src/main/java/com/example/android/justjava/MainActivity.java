package com.example.android.justjava;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int numberOfCoffees = 1;
    int basePrice = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onConfigurationChanged(final Configuration newConfig) { // Ignore orientation change to keep activity from restarting
        super.onConfigurationChanged(newConfig);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        final String[] TO = new String[]{"Srivigneshfb@gmail.com"};
        final String SUBJECT = "JustJava order for " + fetchUserName();
        int price = calculateAmount(basePrice);
        String priceMessage = "Name: " + fetchUserName() + "\nAdd Whipped Cream?" + isWhippedCreamChecked() + "\nAdd Chocolate?" + isChocolateChecked() + "\nQuantity: " + numberOfCoffees + "\nTotal: $" + price + "\nThank you!";
        sendEmail(TO, SUBJECT, priceMessage);

        //displayMessage(priceMessage);
        /**TextView quantityTextView = (TextView) findViewById(R.id.thank_you_message);
         if (numberOfCoffees > 0) {
         quantityTextView.setText("Thanks for ordering " + numberOfCoffees + " cups of coffee. Don't forget to pay " + calculateAmount() + "!");
         }else{
         quantityTextView.setText("You haven't ordered at least one coffee!");
         }*/
    }

    private void sendEmail(String[] recipients, String subject, String message) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, recipients);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send email using"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this,
                    "There is no email client installed.",
                    Toast.LENGTH_SHORT).show();
        }


    }

    /**
     * This method fetches the Username from edit text field.
     */
    private String fetchUserName() {
        EditText name = (EditText) findViewById(R.id.userName);
        return name.getText().toString();
    }

    /**
     * This method checks whether chocolate topping check box is checked.
     */
    private boolean isChocolateChecked() {
        CheckBox hasChecked = (CheckBox) findViewById(R.id.chocolate);
        return hasChecked.isChecked();
    }

    /**
     * This method checks whether whipped cream topping check box is checked.
     */
    private boolean isWhippedCreamChecked() {
        CheckBox hasChecked = (CheckBox) findViewById(R.id.whippedCream);
        return hasChecked.isChecked();
    }


    /**
     * This method calculates the amount for the coffees.
     */
    private int calculateAmount(int basePrice) {
        if (isWhippedCreamChecked()) {
            basePrice += 1;
        }
        if (isChocolateChecked()) {
            basePrice += 2;
        }
        return numberOfCoffees * basePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
//    private void displayPrice(int number) {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
//    }

    /**
     * This method displays the incremented price on the screen.
     */
    public void increment(View view) {
        if (numberOfCoffees > 99) {
            Toast.makeText(this, "You cannot order more than 100 cups of coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        numberOfCoffees += 1;
        displayQuantity(numberOfCoffees);
        // displayPrice(numberOfCoffees * basePrice);
    }

    /**
     * This method displays the decremented price on the screen.
     */
    public void decrement(View view) {
        if (numberOfCoffees <= 1) {
            Toast.makeText(this, "You cannot order less than 1 cup of coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        numberOfCoffees -= 1;
        displayQuantity(numberOfCoffees);
        //displayPrice(numberOfCoffees * basePrice);
    }

    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(message);
//    }
}


