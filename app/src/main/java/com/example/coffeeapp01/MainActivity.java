package com.example.coffeeapp01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
//create variables
    int noOfCoffee = 0;
    int priceOfCoffee = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //method for submitOrder Button
    public void submitOrder(View view){
        //call the method display() and pass an interger
        //display(noOfCoffee);
        //int totalPrice = noOfCoffee*priceOfCoffee;
        //TextView priceTextView = (TextView)findViewById(R.id.price_text_view);
        //priceTextView.setText("Total $"+ totalPrice+"\n"+ "Thank you!!");
       // priceTextView.setText(creatOrderSummary());

        //get users name
        EditText nameField = (EditText)findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        //figure out if user wants whipped cream
        CheckBox whippedCreamCheckBox = (CheckBox)findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        //figure out if user wants chocolate toppings
        CheckBox chocolateCheckBox = (CheckBox)findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        //calculate the price
        int price = calculatePrice(hasWhippedCream,hasChocolate);

        //open a new intent and send off the message to the new intent

        //creat an order summary
        String message = creatOrderSummary(name,price,hasWhippedCream,hasChocolate);

        TextView priceTextView = (TextView)findViewById(R.id.price_text_view);
        //priceTextView.setText("Total $"+ totalPrice+"\n"+ "Thank you!!");
        priceTextView.setText(message);

        //Creat an Intent to send the message to the new activity that has been created
        Intent intent = new Intent(this, DisplayOrderDetails.class);
        intent.putExtra("name",name);
        intent.putExtra("message",message);
        intent.putExtra("price",Integer.toString(price));
        startActivity(intent);



    }

    //method to creat a message
    public String creatOrderSummary(String name, int price,boolean addwhippedCream,boolean addChocolate){
        String priceMessage = "Name:"+name+"\n"+
                "Add Whipped Cream?"+addwhippedCream+"\n"+
                "Add Chocolate?"+addChocolate+"\n"+
                "Quantity: "+noOfCoffee+"\n"+
                "Total: $"+price+"\n"+
                "Thank you!";
        return priceMessage;



    }

    //method to calculate the price
    public int calculatePrice(boolean addwhippedCream, boolean addChocolate){
        int basePrice =4;
        if (addwhippedCream ==true)
            basePrice = basePrice+1;
        if (addChocolate)
            basePrice = basePrice+2;
        return basePrice * noOfCoffee;

    }


    public void display(int number){


        TextView quantityTextView = (TextView)findViewById(R.id.quantity_text_view);
    quantityTextView.setText(""+ number);

    }


//code for the increment button "+"
    public void increment(View view){
        noOfCoffee = noOfCoffee+1;
        if (noOfCoffee >=10 ) {
            noOfCoffee = 10;
        }
        display(noOfCoffee);



    }
//code for the decrement button "-"
public void decrement(View view) {
    noOfCoffee = noOfCoffee-1;
    if (noOfCoffee<= 0 ){
        noOfCoffee = 0;
    }
    display(noOfCoffee);

}

}