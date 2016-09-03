package com.alessandroamico.myvapetool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void resetFields(View view) {
        ((TextView) findViewById(R.id.resistanceText)).setText("");
        ((TextView) findViewById(R.id.voltageText)).setText("");
        ((TextView) findViewById(R.id.currentText)).setText("");
        ((TextView) findViewById(R.id.powerText)).setText("");
    }

    public void calculate(View view) {
        Toast waringToast = Toast.makeText(getApplicationContext(), R.string.warning, Toast.LENGTH_SHORT);
        int count = 0;
        Editable resistance, voltage, current, power;
        Double myResistance = 0.0, myVoltage = 0.0, myCurrent = 0.0, myPower = 0.0;


        resistance = ((TextView) findViewById(R.id.resistanceText)).getEditableText();
        voltage = ((TextView) findViewById(R.id.voltageText)).getEditableText();
        current = ((TextView) findViewById(R.id.currentText)).getEditableText();
        power = ((TextView) findViewById(R.id.powerText)).getEditableText();

        if(resistance.length() != 0) {
            myResistance = new Double(resistance.toString());
            count++;
        }
        if(voltage.length() != 0) {
            myVoltage = new Double(voltage.toString());
            count++;
        }
        if(current.length() != 0) {
            myCurrent = new Double(current.toString());
            count++;
        }
        if(power.length() != 0) {
            myPower = new Double(power.toString());
            count++;
        }

        if (count <= 1) {
            waringToast.show();
            return;
        }

        if (resistance.length() != 0 && voltage.length() != 0) {
            if (current.length() == 0)
                myCurrent = myVoltage / myResistance;
            if (power.length() == 0)
                myPower = myCurrent * myVoltage;

            ((TextView) findViewById(R.id.currentText)).setText(myCurrent.toString());
            ((TextView) findViewById(R.id.powerText)).setText(myPower.toString());
        }

        if (resistance.length() != 0 && current.length() != 0) {
            if (power.length() == 0) {
                Double tmp = Math.pow(myCurrent, 2);
                myPower = tmp * myResistance;
            }
            if (voltage.length() == 0)
                myVoltage = myPower / myCurrent;

            ((TextView) findViewById(R.id.powerText)).setText(myPower.toString());
            ((TextView) findViewById(R.id.voltageText)).setText(myVoltage.toString());
        }

        if (resistance.length() != 0 && power.length() != 0) {
            if (current.length() == 0) {
                Double temp = myPower / myResistance;
                myCurrent = Math.sqrt(temp);
            }
            if (voltage.length() == 0)
                myVoltage = myPower / myCurrent;

            ((TextView) findViewById(R.id.currentText)).setText(myCurrent.toString());
            ((TextView) findViewById(R.id.voltageText)).setText(myVoltage.toString());
        }

        if (voltage.length() != 0 && current.length() != 0) {
            if (power.length() == 0)
                myPower = myCurrent * myVoltage;
            if (resistance.length() == 0) {
                Double tmp = Math.pow(myCurrent, 2);
                myResistance = myPower / tmp;
            }

            ((TextView) findViewById(R.id.powerText)).setText(myPower.toString());
            ((TextView) findViewById(R.id.resistanceText)).setText(myResistance.toString());
        }

        if (voltage.length() != 0 && power.length() != 0) {
            Double tmp;
            if (resistance.length() == 0) {
                tmp = Math.pow(myVoltage, 2);
                myResistance = tmp / myPower;
            }
            if (current.length() == 0) {
                tmp = myPower / myResistance;
                myCurrent = Math.sqrt(tmp);
            }

            ((TextView) findViewById(R.id.resistanceText)).setText(myResistance.toString());
            ((TextView) findViewById(R.id.currentText)).setText(myCurrent.toString());
        }

        if (current.length() != 0 && power.length() != 0) {
            if (voltage.length() == 0)
                myVoltage = myPower / myCurrent;
            if (resistance.length() == 0) {
                Double tmp = Math.pow(myCurrent, 2);
                myResistance = myPower / tmp;
            }

            ((TextView) findViewById(R.id.voltageText)).setText(myVoltage.toString());
            ((TextView) findViewById(R.id.resistanceText)).setText(myResistance.toString());
        }
    }
}
