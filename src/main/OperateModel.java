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
	            // ����ĵ�һ������
	           resultString=key;
	        } else if ((key.equals(".")) && (resultString.indexOf(".") < 0)) {
	            // �������С���㣬����֮ǰû��С���㣬��С���㸽�ڽ���ı���ĺ���
	            resultString+=".";
	        } else if (!key.equals(".")) {
	            // �������Ĳ���С���㣬�����ָ��ڽ���ı���ĺ���
	            resultString+=key;
	        }
	        // �Ժ�����Ŀ϶����ǵ�һ��������
	        firstDigit = false;

		
	}
	public void handleOperator(String key) {
		 if (operator.equals("/")) {
	            // ��������
	            // �����ǰ����ı����е�ֵ����0
	            if (getNumberFromText() == 0.0) {
	                // �������Ϸ�
	                operateValidFlag = false;
	                resultString="��������Ϊ�㣡";
	            } else {
	                resultNum /= getNumberFromText();
	            }
	        } else if (operator.equals("1/x")) {
	            // ��������
	            if (resultNum == 0.0) {
	                // �������Ϸ�
	                operateValidFlag = false;
	                //resultText.setText("��û�е���");
	                resultString="��û�е�����";
	            } else {
	                resultNum = 1 / resultNum;
	            }
	        } else if (operator.equals("+")) {
	            // �ӷ�����
	            resultNum += getNumberFromText();
	        } else if (operator.equals("-")) {
	            // ��������
	            resultNum -= getNumberFromText();
	        } else if (operator.equals("*")) {
	            // �˷�����
	            resultNum *= getNumberFromText();
	        } else if (operator.equals("sqrt")) {
	            // ƽ��������
	            resultNum = Math.sqrt(resultNum);
	        } else if (operator.equals("%")) {
	            // �ٷֺ����㣬����100
	            resultNum = resultNum / 100;
	        } else if (operator.equals("+/-")) {
	            // ������������
	            resultNum = resultNum * (-1);
	        } else if (operator.equals("=")) {
	            // ��ֵ����
	            resultNum = getNumberFromText();
	        }
	        if (operateValidFlag) {
	            // ˫���ȸ�����������
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
	        // ����������û����İ�ť
	        operator = key;
	        firstDigit = true;
	        operateValidFlag = true;
		
	}
	public void handleC() {
		// ��ʼ���������ĸ���ֵ
        resultString="0";
        firstDigit = true;
        operator = "=";			
	}
	public void handleBackspace() {
        String text = resultString;
        int i = text.length();
        if (i > 0) {
            // �˸񣬽��ı����һ���ַ�ȥ��
            text = text.substring(0, i - 1);
            if (text.length() == 0) {
                // ����ı�û�������ݣ����ʼ���������ĸ���ֵ
                resultString="0";
                firstDigit = true;
                operator = "=";
            } else {
                // ��ʾ�µ��ı�
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
