package com.example.coffeeapp01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayOrderDetails extends AppCompatActivity {
    String message;
    String name;
    String price;
    CoffeeDBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_order_details);
        dbHandler = new CoffeeDBHandler(this,null,null,1);


        //get the intent from the MainActivity
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        message = intent.getStringExtra("message");
        price = intent.getStringExtra("price");


        //Capture the layouts Textview and set the string
        TextView textView = (TextView) findViewById(R.id.textView3);
        textView.setText(message);


    }

    //method to run when the button is clicked to open Gmail
    public void sendEmail(View view) {
        String[] addresses = {"Yanxiang@abc.com", "Jia@rmit.edu.au"};
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffe Order");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }
    //METHOD TO SAVE DATA in SQLite database
    public void saveData(View view){
        //Creat an object or Order
        Order order = new Order(name, Integer.parseInt(price));
        dbHandler.addOrder(order);
        Toast.makeText(getApplicationContext(),"Data Saved!!!", Toast.LENGTH_SHORT).show();

    }
    //method that helps to gendrate sales report
    public void salesReport(View view){
        String dbString = dbHandler.databaseToString();
        Intent salesIntent = new Intent(this,DisplaySalesDetails.class);
        salesIntent.putExtra("db", dbString);
        startActivity(salesIntent);


    }
}