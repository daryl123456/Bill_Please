package sg.edu.rp.c346.id19042545.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText etAmount,etPax,etDiscount;
    ToggleButton tbtnSvs,tbtnGst;
    TextView tvBill,tvPay;
    Button btnSplit,btnReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etAmount =findViewById(R.id.editTextAmount);
        etPax =findViewById(R.id.editTextPax);
        tbtnSvs = findViewById(R.id.toggleButtonSvs);
        tbtnGst = findViewById(R.id.toggleButtonGst);
        etDiscount = findViewById(R.id.editTextDiscount);
        tvBill =findViewById(R.id.textViewTotal);
        tvPay =findViewById(R.id.textViewEach);
        btnSplit =findViewById(R.id.buttonSplit);
        btnReset =findViewById(R.id.buttonReset);

    btnSplit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {



            if(etAmount.getText().toString().trim().length()==0||etPax.getText().toString().trim().length()==0) {
                Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
                return;
            }
            else{
                double total =0.0;
                double eachPay=0.0;
                int charges=0;
                int intDiscount=0;
                String amount = etAmount.getText().toString();
                String pax = etPax.getText().toString();
                double doubleAmount = Double.parseDouble(amount);
                int Intpax = Integer.parseInt(pax);

                if(etDiscount.getText().toString().trim().length()>0) {
                    String discount = etDiscount.getText().toString();
                    intDiscount = Integer.parseInt(discount);
                }
                if (tbtnSvs.isChecked() == true) {
                    charges += 10;
                }
                if (tbtnGst.isChecked() == true) {
                    charges += 7;
                }
                total = (((doubleAmount / 100) * (100 + charges)) / 100) * (100 - intDiscount);
                eachPay = total / Intpax;
                String totaloutput = String.format("Total Bill: $%.2f", total);
                String eachoutput = String.format("Each Pay: $%.2f", eachPay);
                tvBill.setText(totaloutput);
                tvPay.setText(eachoutput);
            }
        }
    });
    btnReset.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            etAmount.setText("");
            etPax.setText("");
            tbtnSvs.setChecked(false);
            tbtnGst.setChecked(false);
            etDiscount.setText("");
            tvBill.setText(R.string.total_bill);
            tvPay.setText(R.string.each);
        }
    });
    }
}
