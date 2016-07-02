package tipcalculator.android.akashneil.com.tipcalculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class MainActivity extends ActionBarActivity {

    EditText billTotal;
    EditText tipPercentage;
    EditText persons;
    TextView individualTip, individualTotal, totalTip, totalAmount, roundedPercentage;
    Button lessTip, addTip, lessPersons, addPersons, round, reset, exit;
    SharedPreferences sharedpreferences;
    Editor toEdit;
    String MyPREFERENCES="tipcalculator.com.akashneil.android.tipcalculator.FROM";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billTotal=(EditText)findViewById(R.id.txtBillTotal);
        tipPercentage=(EditText)findViewById(R.id.txtTipPercentage);
        roundedPercentage=(TextView)findViewById(R.id.txtRoundedPercentage);
        persons=(EditText)findViewById(R.id.txtPersons);
        individualTip=(TextView)findViewById(R.id.txtIndividualTip);
        individualTotal=(TextView)findViewById(R.id.txtIndividualTotal);
        totalTip=(TextView)findViewById(R.id.txtTotalTip);
        totalAmount=(TextView)findViewById(R.id.txtTotalAmount);
        lessTip=(Button)findViewById(R.id.btnLessTip);
        addTip=(Button)findViewById(R.id.btnMoreTip);
        lessPersons=(Button)findViewById(R.id.btnLessPersons);
        addPersons=(Button)findViewById(R.id.btnMorePersons);
        round=(Button)findViewById(R.id.btnRound);
        reset=(Button)findViewById(R.id.btnResetValues);
        exit=(Button)findViewById(R.id.btnExit);

        billTotal.setText("0");
        tipPercentage.setText("10");
        roundedPercentage.setText("Amounts not rounded.");
        persons.setText("1");
        individualTip.setText("0");
        individualTotal.setText("0");
        totalTip.setText("0");
        totalAmount.setText("0");

        billTotal.setSelectAllOnFocus(true);
        tipPercentage.setSelectAllOnFocus(true);
        persons.setSelectAllOnFocus(true);
        billTotal.setFocusableInTouchMode(true);

        //Disabling copy/paste
        billTotal.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });

        tipPercentage.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });

        persons.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });



        round.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double individualTipPayable = Double.valueOf(individualTip.getText().toString());
                double individualTotalAmountPayable = Double.valueOf(individualTotal.getText().toString());
                double totalTipPayable = Double.valueOf(totalTip.getText().toString());
                double totalAmountPayable = Double.valueOf(totalAmount.getText().toString());

                //Round up/down
                double individualTipPayableFinal = Math.round(individualTipPayable);
                double individualTotalAmountPayableFinal = Math.round(individualTotalAmountPayable);
                double totalTipPayableFinal = Math.round(totalTipPayable);
                double totalAmountPayableFinal = Math.round(totalAmountPayable);

                //Display results - after rounding up/down
                individualTotal.setText(String.valueOf(individualTotalAmountPayableFinal));
                individualTip.setText(String.valueOf(individualTipPayableFinal));
                totalTip.setText(String.valueOf(totalTipPayableFinal));
                totalAmount.setText(String.valueOf(totalAmountPayableFinal));

                //update tip percentage
                double newTipPercentage = ((totalTipPayableFinal/totalAmountPayableFinal)*100);
                String blankTotalBill = billTotal.getText().toString();
                if (blankTotalBill.equals("0")) {
                    Toast.makeText(getApplicationContext(), "'Bill Total' cannot be left zero!.", Toast.LENGTH_LONG).show();
                }
                else {
                    DecimalFormat df = new DecimalFormat("###.##");
                    roundedPercentage.setText(df.format(newTipPercentage));
                }

            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                billTotal.setText("0");
                tipPercentage.setText("10");
                persons.setText("1");
                individualTip.setText("0");
                individualTotal.setText("0");
                totalTip.setText("0");
                totalAmount.setText("0");
            }
        });

        addTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = tipPercentage.getText().toString();
                //String replace = temp.replace(".0", "");
                double updated = Double.valueOf(temp)+1.0;
                //int tipPercent = Integer.valueOf(replace);
                //int updated = tipPercent + 1;
                tipPercentage.setText(String.valueOf(updated));
            }
        });

        lessTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = tipPercentage.getText().toString();
                double updated = Double.valueOf(temp)-1.0;
                tipPercentage.setText(String.valueOf(updated));
            }
        });

        addPersons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int totalPersons = Integer.valueOf(persons.getText().toString());
                int updated = totalPersons + 1;
                persons.setText(String.valueOf(updated));
            }
        });

        lessPersons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int totalPersons = Integer.valueOf(persons.getText().toString());
                int updated = totalPersons - 1;
                persons.setText(String.valueOf(updated));
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if (sharedpreferences.contains("Z"))
        {
            tipPercentage.setText(sharedpreferences.getString("Z", ""));
        }


        billTotal.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                String blankTotalBill = billTotal.getText().toString();
                if (blankTotalBill.length()<1){
                    Toast.makeText(getApplicationContext(), "Please enter a value!", Toast.LENGTH_LONG).show();
                    billTotal.setText("0");
                    billTotal.selectAll();
                }
                else if (blankTotalBill.length()>12){
                    Toast.makeText(getApplicationContext(), "You are entering too many decimals!", Toast.LENGTH_LONG).show();
                    billTotal.setText("0");
                    billTotal.selectAll();
                }
                else if (blankTotalBill.equals(".")){
                    Toast.makeText(getApplicationContext(), "Please enter a value!", Toast.LENGTH_LONG).show();
                    billTotal.setText("0");
                    billTotal.selectAll();
                }
                else {
                    if (s.length() != 0)
                        calculate();
                }
            }
        });



            tipPercentage.addTextChangedListener(new TextWatcher() {

                @Override
                public void afterTextChanged(Editable s) {
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start,
                                          int before, int count) {
                    String blankTipPercentage = tipPercentage.getText().toString();
                    if (blankTipPercentage.length()<1){
                        Toast.makeText(getApplicationContext(), "Please enter a value!", Toast.LENGTH_LONG).show();
                        tipPercentage.setText("0");
                        tipPercentage.selectAll();
                    }
                    else if (blankTipPercentage.length()>12){
                        Toast.makeText(getApplicationContext(), "You are entering too many decimals!", Toast.LENGTH_LONG).show();
                        tipPercentage.setText("0");
                        tipPercentage.selectAll();
                    }
                    else if (blankTipPercentage.equals(".")){
                        Toast.makeText(getApplicationContext(), "Please enter a value!", Toast.LENGTH_LONG).show();
                        tipPercentage.setText("0");
                        tipPercentage.selectAll();
                    }
                    else {
                        if (s.length() != 0)
                            calculate();
                    }

                }
            });




        persons.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                String blankPersons = persons.getText().toString();
                if (blankPersons.length()<1){
                    Toast.makeText(getApplicationContext(), "Please enter a value!", Toast.LENGTH_LONG).show();
                    persons.setText("1");
                    persons.selectAll();
                }
                if (blankPersons.equals(".")){
                    Toast.makeText(getApplicationContext(), "Please enter a value!", Toast.LENGTH_LONG).show();
                    persons.setText("1");
                    persons.selectAll();
                }
                else {
                    if (s.length() != 0)
                        calculate();
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void calculate (){

        double amtOnBill = Double.valueOf(billTotal.getText().toString());
        double tipPercent = Double.valueOf(tipPercentage.getText().toString());
        double totalPersons = Double.valueOf(persons.getText().toString());

        int totalOnBill = (int) amtOnBill;
        int tipped = (int)tipPercent;
        int personCount = (int) totalPersons;
        String tips =tipPercentage.getText().toString();
        String blankTotalBill = billTotal.getText().toString();

        if (tipped>100){
            Toast.makeText(getApplicationContext(), "Tip Percentage cannot be more than 100.", Toast.LENGTH_LONG).show();
            tipPercentage.setText("100");
        }
        else if (tipped<0){
            Toast.makeText(getApplicationContext(), "Tip Percentage cannot be negative.", Toast.LENGTH_LONG).show();
            tipPercentage.setText("0");
        }
        else if (personCount<1) {
            Toast.makeText(getApplicationContext(), "Number of persons cannot be less than 1.", Toast.LENGTH_LONG).show();
            persons.setText("1");
        }
        else if (personCount>999999999){
            persons.setText("1");
        }
         else if (totalOnBill>4000000){
            billTotal.setText("0");
        }
        else if (tips.equals(".0")||tips.equals("0.")){
            tipPercentage.setText("0");
        }
        else if (tips.equals("NaN")){
            tipPercentage.setText("0");
        }
        else if (tips.contains(".0")){
            String replace = tips.replace(".0", "");
            String updateTip = String.valueOf(replace);
            tipPercentage.setText(updateTip);
        }
        else if (blankTotalBill.equals(" ")){
            billTotal.setText("0");
        }
        else {

            double individualTipPayable = (amtOnBill * (tipPercent / 100)) / totalPersons;
            double individualTotalAmountPayable = (amtOnBill + (amtOnBill * (tipPercent / 100)))/ totalPersons;
            double totalTipPayable = amtOnBill * (tipPercent / 100);
            double totalAmountPayable = (amtOnBill + (amtOnBill * (tipPercent / 100)));

          /*  //Round up/down
            double individualTipPayableFinal = Math.round(individualTipPayable);
            double individualTotalAmountPayableFinal = Math.round(individualTotalAmountPayable);
            double totalTipPayableFinal = Math.round(totalTipPayable);
            double totalAmountPayableFinal = Math.round(totalAmountPayable); */


            DecimalFormat df = new DecimalFormat("###.##");

            //Display results
            individualTip.setText(df.format(individualTipPayable));
            individualTotal.setText(df.format(individualTotalAmountPayable));
            totalTip.setText(df.format(totalTipPayable));
            totalAmount.setText(df.format(totalAmountPayable));
            roundedPercentage.setText("Amounts not rounded.");

            String W = String.valueOf(tipPercent);

            store(W);
        }
    }



    public void store(String tipper){
        //String tipper = tipPercentage.getText().toString();
        Editor editor = sharedpreferences.edit();
        editor.putString("Z",tipper);
        editor.commit();
    }
}
