package main;

import javafx.scene.control.TextField;

public class Controller {
  private TextField resultText=null;
  public OperateModel model=null;

public Controller( TextField resultText, OperateModel model){
	  this.resultText=resultText;
	  this.model=model;
  }
  public void  handleBackspace(){
	  this.model.handleBackspace();
	  this.resultText.setText(this.model.getResultString());
  }
  public void handleC(){
	  this.model.handleC();
	  this.resultText.setText(this.model.getResultString());
  }
  public void handleMemory(String text){
	  this.model.handleMemory(text);
	  this.resultText.setText(this.model.getResultString());
  }
  public void handleNumber(String text){
	  this.model.handleNumber(text);
	  this.resultText.setText(this.model.getResultString());
  }
  public void handleOperator(String text){
	  this.model.handleOperator(text);
	  this.resultText.setText(this.model.getResultString());
  }
  public void clearError(){
	  this.model.clearError();
	  this.resultText.setText("0");
  }
}
