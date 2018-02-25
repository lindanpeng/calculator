package main;
import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
public class Calculator extends Application  {
	/** �������ϵļ�����ʾ���� */
	private final String[] KEYS = { "7", "8", "9", "/", "sqrt", "4", "5", "6", "*", "%", "1", "2", "3", "-", "1/x", "0",
			"+/-", ".", "+", "=" };
	/** �������ϵĹ��ܼ�����ʾ���� */
	private final String[] COMMANDS = { "��", "CE", "C" };
	/** ��������ߵ�M����ʾ���� */
	private final String[] M = { "MC", "MR", "MS", "M+","M-" };
	/** �˵�����*/
	private final String[] MENU={"�༭","�鿴","����"};
	/**  �����Ӳ˵�����*/
	/*
	 * UI���
	 */
	private Button keys[] = new Button[KEYS.length];
	private Button commands[] = new Button[COMMANDS.length];
	private Button btn_memory[] = new Button[M.length];
	private MenuItem items[][]={{new MenuItem("����"),new MenuItem("ճ��")},
			{new MenuItem("��׼��"),new MenuItem("��ѧ��"),new MenuItem("���ַ���")},
			{new MenuItem("�鿴����"),new MenuItem("���ڼ�����")}};
	private Menu  menus[]=new Menu[MENU.length];
	private MenuBar menuBar=null;
	private BorderPane allPane = null;
	private TextField resultText = null;
	//������
    private ButtonHandler buttonHandler=null;
    //���ݴ���ģ��
    private OperateModel model=null;
    //����̨
    private Controller control=null;
	public Calculator() {
		buttonHandler=new ButtonHandler();
		allPane=new BorderPane();
		model=new OperateModel();
		resultText = new TextField();
		resultText.setEditable(false);
		resultText.setMinSize(300,40);
		resultText.setAlignment(Pos.CENTER_RIGHT);
		control=new Controller(this.resultText,this.model);
		this.initComponent();
		this.AddAction();
	}
  /*
   * �������
   */
	public void initComponent() {
	  	BorderPane mainPane = new BorderPane();
		BorderPane  centerPane=new BorderPane();
		BorderPane  buttonPane = new BorderPane();
		GridPane  calPane = new GridPane();
		GridPane  commandPane = new GridPane();
		GridPane  mPane = new GridPane();
		HBox resultTextPane=new HBox();
		menuBar=new MenuBar();
		menuBar.setStyle("-fx-color: #E0EEEE");
		resultTextPane.getChildren().add(resultText);
		for (int i = 0; i < KEYS.length; i++) {
			keys[i] = new Button(KEYS[i]);
			keys[i].setMinSize(50, 40);
			keys[i].setStyle("-fx-color: white;");
			calPane.add(keys[i], i % 5, i / 5);
		}
		
		for (int i = 0; i < COMMANDS.length; i++) {
			commands[i] = new Button(COMMANDS[i]);
			commands[i].setMinSize(83, 40);
			commands[i].setStyle("-fx-color: orange;");
			commandPane.add(commands[i], i % 5, i / 5);
		}
		for (int i = 0; i < M.length; i++) {
			btn_memory[i] = new Button(M[i]);
			btn_memory[i].setMinSize(50, 40);
			mPane.add(btn_memory[i], 0, i);
		}
		for(int i=0;i<MENU.length;i++){
			this.menus[i]=new Menu(MENU[i]);
            this.menus[i].getItems().addAll(items[i]);
			this.menuBar.getMenus().add(menus[i]);
		}		
		buttonPane.setTop(commandPane);
		buttonPane.setCenter(calPane);
		centerPane.setCenter(buttonPane);
		centerPane.setLeft(mPane);
		mainPane.setTop(resultTextPane);
		mainPane.setCenter(centerPane);
		allPane.setTop(menuBar);
		allPane.setCenter(mainPane);
	}
	/*
	 * ע�������
	 */
	public void AddAction() {
	
	for(int i=0;i<KEYS.length;i++)
     {
		keys[i].setOnMouseClicked(this.buttonHandler);
     }
	for(int i=0;i<COMMANDS.length;i++){
		commands[i].setOnMouseClicked(this.buttonHandler);
	}
	for(int i=0;i<M.length;i++){
		btn_memory[i].setOnMouseClicked(this.buttonHandler);
	}
	items[2][0].setOnAction(new EventHandler<ActionEvent>(){
 
		@Override
		public void handle(ActionEvent arg0) {
		JOptionPane.showMessageDialog(null, "���������������ã��汿����","����",JOptionPane.INFORMATION_MESSAGE);
		}
		
	});
	items[2][1].setOnAction(new EventHandler<ActionEvent>(){

		@Override
		public void handle(ActionEvent arg0) {
			JOptionPane.showMessageDialog(null, "����������֣���翪��,����ؾ�,лл��","about",JOptionPane.INFORMATION_MESSAGE);
		}
		
	});
	}
   
	@Override
	public void start(Stage myStage) throws Exception {
		Scene scene = new Scene(allPane);
		myStage.setScene(scene);
		myStage.setTitle("������");
		myStage.setResizable(false);
		myStage.setWidth(305);
		myStage.setHeight(290);
		myStage.show();
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
	/*
	 * �¼�������
	 */
	class ButtonHandler implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent e) {
			// TODO Auto-generated method stub
			  // ��ȡ�¼�Դ�ı�ǩ
	        String label = ((Button)e.getSource()).getText();
	        if (label.equals(COMMANDS[0])) {
	            // �û�����"Backspace"��
	            control.handleBackspace();
	        } else if (label.equals(COMMANDS[1])) {
	            // �û�����"CE"��
	            control.clearError();
	        } else if (label.equals(COMMANDS[2])) {
	            // �û�����"C"��
	            control.handleC();
	        } 
	        else if ("0123456789.".indexOf(label) >= 0) {
	            // �û��������ּ�����С�����
	            control.handleNumber(label);
	        } 
	        else if(label.startsWith("M")){
	        	control.handleMemory(label);
	        }
	        else {
	            // �û������������
	            control.handleOperator(label);
	        }

	}
}

	
}