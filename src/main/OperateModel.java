package main;

public class OperateModel {
	private double  memory=0.0;
	private double  resultNum=0.0;
	private boolean firstDigit=true;
	private boolean operateValidFlag=true;
	private String resultString="0";
	private String operator="=";
	public void handleMemory(String label) {
		if(label.equals("MS"))
		{
			memory=Double.parseDouble(resultString);
		}
		else if(label.equals("MR")){
		//	resultText.setText(String.valueOf(memory));
			resultString=String.valueOf(memory);
		}
        else if(label.equals("MC")){
			memory=0.0;
		}
        else if(label.equals("M+")){
        	memory+=Double.parseDouble(resultString);
        }
        else{
        	memory-=Double.parseDouble(resultString);
        }
	}
	public String getResultString() {
		return resultString;
	}
	public void setResultString(String resultString) {
		this.resultString = resultString;
	}
	public void handleNumber(String key) {
		 if (firstDigit) {
	            // 输入的第一个数字
	           resultString=key;
	        } else if ((key.equals(".")) && (resultString.indexOf(".") < 0)) {
	            // 输入的是小数点，并且之前没有小数点，则将小数点附在结果文本框的后面
	            resultString+=".";
	        } else if (!key.equals(".")) {
	            // 如果输入的不是小数点，则将数字附在结果文本框的后面
	            resultString+=key;
	        }
	        // 以后输入的肯定不是第一个数字了
	        firstDigit = false;

		
	}
	public void handleOperator(String key) {
		 if (operator.equals("/")) {
	            // 除法运算
	            // 如果当前结果文本框中的值等于0
	            if (getNumberFromText() == 0.0) {
	                // 操作不合法
	                operateValidFlag = false;
	                resultString="除数不能为零！";
	            } else {
	                resultNum /= getNumberFromText();
	            }
	        } else if (operator.equals("1/x")) {
	            // 倒数运算
	            if (resultNum == 0.0) {
	                // 操作不合法
	                operateValidFlag = false;
	                //resultText.setText("零没有倒数");
	                resultString="零没有倒数！";
	            } else {
	                resultNum = 1 / resultNum;
	            }
	        } else if (operator.equals("+")) {
	            // 加法运算
	            resultNum += getNumberFromText();
	        } else if (operator.equals("-")) {
	            // 减法运算
	            resultNum -= getNumberFromText();
	        } else if (operator.equals("*")) {
	            // 乘法运算
	            resultNum *= getNumberFromText();
	        } else if (operator.equals("sqrt")) {
	            // 平方根运算
	            resultNum = Math.sqrt(resultNum);
	        } else if (operator.equals("%")) {
	            // 百分号运算，除以100
	            resultNum = resultNum / 100;
	        } else if (operator.equals("+/-")) {
	            // 正数负数运算
	            resultNum = resultNum * (-1);
	        } else if (operator.equals("=")) {
	            // 赋值运算
	            resultNum = getNumberFromText();
	        }
	        if (operateValidFlag) {
	            // 双精度浮点数的运算
	            long t1;
	            double t2;
	            t1 = (long) resultNum;
	            t2 = resultNum - t1;
	            if (t2 == 0) {
	                resultString=String.valueOf(t1);
	                
	            } else {
	               resultString=String.valueOf(resultNum);
	            }
	        }
	        // 运算符等于用户按的按钮
	        operator = key;
	        firstDigit = true;
	        operateValidFlag = true;
		
	}
	public void handleC() {
		// 初始化计算器的各种值
        resultString="0";
        firstDigit = true;
        operator = "=";			
	}
	public void handleBackspace() {
        String text = resultString;
        int i = text.length();
        if (i > 0) {
            // 退格，将文本最后一个字符去掉
            text = text.substring(0, i - 1);
            if (text.length() == 0) {
                // 如果文本没有了内容，则初始化计算器的各种值
                resultString="0";
                firstDigit = true;
                operator = "=";
            } else {
                // 显示新的文本
                resultString=text;
            }
        }

		
	}
	public void clearError() {
		// TODO Auto-generated method stub
		this.resultString="0";
		firstDigit=true;
	}


	 public double getNumberFromText() {
	        double result = 0;
	        try {
	            result = Double.valueOf(resultString).doubleValue();
	        } catch (NumberFormatException e) {
	        }
	        return result;
	    }

}
