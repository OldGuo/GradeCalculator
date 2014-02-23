package com.oldGuo.GradeCalculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GradeCalculatorActivity extends Activity {

	private TextView currentHeader,wantHeader,worthHeader,needHeader;
	private TextView currentText,wantText,finalText,needText;
	private double currentGrade,wantGrade,weight,needGrade;
	
    public GradeCalculatorActivity(){
    	currentGrade = wantGrade = weight = needGrade = 0;
    }
    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
      
        //the headers, changing the color
        currentHeader = (TextView)findViewById(R.id.currentGrade);
        currentHeader.setTextColor(0xFF000000);

        wantHeader = (TextView)findViewById(R.id.whatWant);
        wantHeader.setTextColor(0xFF000000);

        worthHeader = (TextView)findViewById(R.id.finalWorth);
        worthHeader.setTextColor(0xFF000000);

        needHeader = (TextView)findViewById(R.id.whatNeed);
        needHeader.setTextColor(0xFF000000);

		currentText = (EditText) findViewById(R.id.currentText); //the percentages
        currentText.addTextChangedListener(new TextWatcher() {
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		String currentString = currentText.getText().toString();
        		if(currentText.getText().toString().length() > 0){ //make sure the edittext isnt empty
        			currentGrade = Double.valueOf(currentString);
        		}else{
        			currentGrade = 0;
        		}

        		needGrade = (wantGrade - (1-(weight/100))*currentGrade)/(weight/100);
        		needGrade = Math.round( needGrade * 1000.0 ) / 1000.0;
        		needText.setText(needGrade + "");

				int position = currentString.length(); //always sets the selection at the last position
				Editable etext = (Editable) currentText.getText();
				Selection.setSelection(etext,position);
        	}
        	public void afterTextChanged(Editable s) {
        	}
        	public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
        });
        wantText = (EditText) findViewById(R.id.wantText);
        wantText.addTextChangedListener(new TextWatcher() {
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		String wantString = wantText.getText().toString();
        		if(wantText.getText().toString().length() > 0){
        			wantGrade = Double.valueOf(wantString);
        		}else{
        			wantGrade = 0;
        		}

        		needGrade = (wantGrade - (1-(weight/100))*currentGrade)/(weight/100);
        		needGrade = Math.round( needGrade * 1000.0 ) / 1000.0;
        		needText.setText(needGrade + "");

				int position = wantString.length(); //always sets the selection at the last position
				Editable etext = (Editable) wantText.getText();
				Selection.setSelection(etext,position);
        	}
        	public void afterTextChanged(Editable s) {}
        	public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
        });
        finalText = (EditText) findViewById(R.id.finalText);
        finalText.addTextChangedListener(new TextWatcher() {
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		String finalString = finalText.getText().toString();
        		if(finalText.getText().toString().length() > 0){
        			weight = Double.valueOf(finalString);
        		}else{
        			weight = 0;
        		}

        		needGrade = (wantGrade - (1-(weight/100))*currentGrade)/(weight/100);
        		needGrade = Math.round( needGrade * 1000.0 ) / 1000.0;
        		needText.setText(needGrade + "");

				int position = finalString.length(); //always sets the selection at the last position
				Editable etext = (Editable) finalText.getText();
				Selection.setSelection(etext,position);
        	}
        	public void afterTextChanged(Editable s) {}
        	public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
        });

        needText = (EditText) findViewById(R.id.needText);
        needText.setFocusable(false);

		LinearLayout main = (LinearLayout) findViewById(R.id.main_view);
    }
}