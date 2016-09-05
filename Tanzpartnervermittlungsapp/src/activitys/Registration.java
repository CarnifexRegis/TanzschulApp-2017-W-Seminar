package activitys;

import protocol.ErrorCode;
import task.LoginTask;
import task.RegisterTask;

import com.example.Tanzpartnervermittlung.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
/**
 * 
 * @author Simon Stolz
 * @Sources: http://stackoverflow.com/questions/5308200/clear-text-in-edittext-when-entered
 * 				http://www.tutorialspoint.com/android/android_radiogroup_control.htm
 * 
 *
 */
public class Registration extends Activity {
	TextView[] errorViews = new TextView[7];
	TextView rErrorView;
	TextView rFNErrorView;
	TextView rLNErrorView;
	TextView rAgeErrorView;
	TextView rEmailErrorView;
	TextView rKeyErrorView;
	
	EditText rFNameInsert;
	EditText rLNameInsert;
	EditText rAgeInsert;
	EditText rEmailInsert1;
	EditText rEmailInsert2;
	EditText rKeyInsert1;
	EditText rKeyInsert2;
	
	Boolean[] correct = new Boolean[6];
	Registration r = this;
	String fn;
	String ln;
	int age;
	String eMail;
	String key;
	boolean gender;
	boolean aVisible;
	
	 private RadioGroup radioSexGroup;
	 private RadioButton radioSexButton;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration);
		
		final CheckBox ageVisible = (CheckBox) findViewById(R.id.rAgeVisibleBox);
		
		
		rFNameInsert = (EditText) findViewById(R.id.rFNameInsert);
		rLNameInsert= (EditText) findViewById(R.id.rLNameInsert);
		rAgeInsert = (EditText) findViewById(R.id.rAgeInsert);
		rEmailInsert1 = (EditText) findViewById(R.id.rEmailInsert1);
		rEmailInsert2 = (EditText) findViewById(R.id.rEmailInsert2);
		rKeyInsert1 = (EditText) findViewById(R.id.rKeyInsert1);
		rKeyInsert2 = (EditText) findViewById(R.id.rKeyInsert2);
//		errorViews [0] = (TextView) findViewById(R.id.rErrorView);
//		errorViews [1] = (TextView) findViewById(R.id.rNameErrorView);
//		errorViews [2] = (TextView) findViewById(R.id.rSurnameErrorView);
//		errorViews [3] = (TextView) findViewById(R.id.rAgeErrorView);
//		errorViews [4] = (TextView) findViewById(R.id.rEmailErrorView);
//		errorViews [6] = (TextView) findViewById(R.id.rKeyErrorView);
		final TextView rErrorView = (TextView) findViewById(R.id.rErrorView);
		final TextView rSexErrorView = (TextView) findViewById(R.id.rSexErrorView);
		final TextView rFNErrorView = (TextView) findViewById(R.id.rFNameErrorView);
		final TextView rLNErrorView = (TextView) findViewById(R.id.rLNameErrorView);
		final TextView rAgeErrorView = (TextView) findViewById(R.id.rAgeErrorView);
		final TextView rEmailErrorView = (TextView) findViewById(R.id.rEmailErrorView);
		final TextView rKeyErrorView = (TextView) findViewById(R.id.rKeyErrorView);
		rErrorView.setVisibility(View.GONE);
		rSexErrorView.setVisibility(View.GONE);
		rFNErrorView.setVisibility(View.GONE);
		rLNErrorView.setVisibility(View.GONE);
		rAgeErrorView.setVisibility(View.GONE);
		rEmailErrorView.setVisibility(View.GONE);
		rKeyErrorView.setVisibility(View.GONE);
		
		rErrorView.setTextColor(0xffff0000);
		rSexErrorView.setTextColor(0xffff0000);
		rFNErrorView.setTextColor(0xffff0000);
		rLNErrorView.setTextColor(0xffff0000);
		rAgeErrorView.setTextColor(0xffff0000);
		rEmailErrorView.setTextColor(0xffff0000);
		rKeyErrorView.setTextColor(0xffff0000);
//	for(int i=0; i < errorViews.length;i++){
//		errorViews[i].setVisibility(View.GONE);
//		errorViews[i].setTextColor(0xffff0000);
//	}
//	final TextView rErrorView = errorViews [0] ;
//	final TextView rNameErrorView = errorViews [1] ;
//	final TextView rSurnameErrorView = errorViews [2] ;
//	final TextView rAgeErrorView = errorViews [3] ;
//	final TextView rEmailErrorView = errorViews [4] ;
//	final TextView rKeyErrorView = errorViews [6] ;
	final Button registerButton=  (Button) findViewById(R.id.registerButton);
	registerButton.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
		ln = rLNameInsert.getText().toString();
		fn = rFNameInsert.getText().toString();
		try{
		age = Integer.valueOf(rAgeInsert.getText().toString());}
		catch(Exception e){}
		eMail= rEmailInsert1.getText().toString();
		key = rKeyInsert1.getText().toString();
		aVisible = ageVisible.isChecked();
		radioSexGroup= (RadioGroup) findViewById(R.id.radioSexGroup);
		int selectedId = radioSexGroup.getCheckedRadioButtonId();
		 correct[0]= false;
		 correct[1]= false;
		 correct[2]= false;
		 correct[3]= false;
		 correct[4]= false;
		 correct[5]= false;
        if(selectedId !=-1){
		
        	if(selectedId == 2131165249){//0x7f070043
        		gender = true;
        		correct[5]= true;
        	}else{
        	if(selectedId == 2131165250){//0x7f070044
        		gender = false;
        		correct[5]= true;
        		rSexErrorView.setVisibility(View.GONE);
        	}else{
        		rSexErrorView.setVisibility(View.VISIBLE);
        		rSexErrorView.setText("Ups das h�tte nich passieren d�rfen: Fehler beim ausw�hlen des Geschlechts.");
        	}
        	
        	}
		}else {
			rSexErrorView.setVisibility(View.VISIBLE);
			rSexErrorView.setText(getResources().getString(R.string.required_field));
		}
        
		radioSexButton=(RadioButton)findViewById(selectedId);
		if(fn.length()>1){
			correct[0]= true;
			rFNErrorView.setVisibility(View.GONE);
		}else{
			rFNErrorView.setVisibility(View.VISIBLE);
			rFNErrorView.setText(getResources().getString(R.string.required_field));
		}
		if(ln.length()>1){
			correct[1]= true;
			rLNErrorView.setVisibility(View.GONE);
		}else{
			rLNErrorView.setVisibility(View.VISIBLE);
			rLNErrorView.setText(getResources().getString(R.string.required_field));
		}
		if(age>0){
			correct[2]= true;
			rAgeErrorView.setVisibility(View.GONE);
		}else{
			rAgeErrorView.setVisibility(View.VISIBLE);
			rAgeErrorView.setText(getResources().getString(R.string.required_field));
		}
		String eMail2 = rEmailInsert2.getText().toString();
		if(eMail.length()>0){
			if(eMail.equals(eMail2)){
				correct[3]= true;
				rEmailErrorView.setVisibility(View.GONE);
			}else{
				rEmailErrorView.setVisibility(View.VISIBLE);
				rEmailErrorView.setText(getResources().getString(R.string.no_accordance_email));
				rEmailInsert1.setText("");
				rEmailInsert2.setText("");
			}
		}else{
			rEmailErrorView.setVisibility(View.VISIBLE);
			
			rEmailErrorView.setText(getResources().getString(R.string.required_field));
		}
		String key2 = rKeyInsert2.getText().toString();
		if(key.length()>0){
			if(key.equals(key2)){
				correct[4]= true;
				rKeyErrorView.setVisibility(View.GONE);
			}else{
				rKeyErrorView.setVisibility(View.VISIBLE);
				rKeyErrorView.setText(getResources().getString(R.string.no_accordance_key));
				rKeyInsert1.setText("");
				rKeyInsert2.setText("");
			}
		}else{
			rKeyErrorView.setVisibility(View.VISIBLE);
			rKeyErrorView.setText(getResources().getString(R.string.required_field));
		}
		
		
			if(isCorrect()){
				RegisterTask registerTask = new RegisterTask(r,fn, ln, eMail, key, age, gender, aVisible);
				registerTask.execute();
				rErrorView.setVisibility(View.GONE);
			}
			
			
			
		}});
	
	}
	public boolean isCorrect(){
		for(int i=0;i< correct.length;i++ ){
			if (!correct[i]){
			return false;}
		}
		return true;
	}
	
	public void connectionError(){
		rErrorView.setVisibility(View.VISIBLE);
		if(!isOnline(this)){
			rErrorView.setText(getResources().getString(R.string.check_connection));
		}else{
			rErrorView.setText(getResources().getString(R.string.connection_failed));
		}
		}
	
	public boolean isOnline(Context context) {
	    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    android.net.NetworkInfo networkinfo = cm.getActiveNetworkInfo();
	    if (networkinfo != null && networkinfo.isConnected()) {
	        return true;
	    }
	    return false;}
	
	public void onError(String error){
		String ae = ErrorCode.ae.getError();
		rErrorView.setVisibility(View.VISIBLE);
		switch (error){
		case "alreadyExists" :
			rErrorView.setText("Es existiert bereits ein Account mit dieser E-Mail!");
		break;
		case "notFound" : 
			rErrorView.setText("Es ist ein Fehler aufgetreten!");
		break;
		}
		}
	public void getLoginValues(int id){
		if(id > -1){
			rErrorView.setVisibility(View.GONE);
			Intent intent = new Intent(getApplicationContext(),EditProfile.class);
			intent.putExtra("ID", id);
	//		intent.putExtra("gender", gender);
			startActivity(new Intent(intent));
			}
	}
	@Override
	public void onBackPressed(){
		super.onBackPressed();
		Intent intent = new Intent(getApplicationContext(),LogIn.class);
			 startActivity(new Intent(intent));
	}
		
		
	}
