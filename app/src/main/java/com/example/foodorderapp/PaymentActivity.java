package com.example.foodorderapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {

    EditText addressInput, paymentInput;
    RadioGroup paymentMethodGroup;
    Button paymentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        
        addressInput = findViewById(R.id.addressInput);
        paymentInput = findViewById(R.id.paymentInput);
        paymentMethodGroup = findViewById(R.id.paymentMethodGroup);
        paymentButton = findViewById(R.id.paymentButton);

      
        paymentButton.setOnClickListener(v -> {
            String address = addressInput.getText().toString().trim();
            String paymentDetails = paymentInput.getText().toString().trim();
            int selectedMethodId = paymentMethodGroup.getCheckedRadioButtonId();

            if (address.isEmpty()) {
                Toast.makeText(this, "Please enter delivery address", Toast.LENGTH_SHORT).show();
                return;
            }

            if (selectedMethodId == -1) {
                Toast.makeText(this, "Please select payment method", Toast.LENGTH_SHORT).show();
                return;
            }

            // Example: Show info as Toast 
            String method = selectedMethodId == R.id.bkashOption ? "bKash" : "Nagad";
            Toast.makeText(this, "Payment Method: " + method + "\nAddress: " + address +
                    "\nDetails: " + paymentDetails, Toast.LENGTH_LONG).show();
        });
    }
}
