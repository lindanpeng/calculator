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
	/** 计算器上的键的显示名字 */
	private final String[] KEYS = { "7", "8", "9", "/", "sqrt", "4", "5", "6", "*", "%", "1", "2", "3", "-", "1/x", "0",
			"+/-", ".", "+", "=" };
	/** 计算器上的功能键的显示名字 */
	private final String[] COMMANDS = { "←", "CE", "C" };
	/** 计算器左边的M的显示名字 */
	private final String[] M = { "MC", "MR", "MS", "M+","M-" };
	/** 菜单名称*/
	private final String[] MENU={"编辑","查看","帮助"};
	/**  帮助子菜单名称*/
	/*
	 * UI组件
	 */
	private Button keys[] = new Button[KEYS.length];
	private Button commands[] = new Button[COMMANDS.length];
	private Button btn_memory[] = new Button[M.length];
	private MenuItem items[][]={{new MenuItem("复制"),new MenuItem("粘贴")},
			{new MenuItem("标准型"),new MenuItem("科学型"),new MenuItem("数字分组")},
			{new MenuItem("查看帮助"),new MenuItem("关于计算器")}};
	private Menu  menus[]=new Menu[MENU.length];
	private MenuBar menuBar=null;
	private BorderPane allPane = null;
	private TextField resultText = null;
	//监听器
    private ButtonHandler buttonHandler=null;
    //数据处理模型
    private OperateModel model=null;
    //控制台
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
   * 绘制组件
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
	 * 注册监听器
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
		JOptionPane.showMessageDialog(null, "连计算器都不会用，真笨！！","帮助",JOptionPane.INFORMATION_MESSAGE);
		}
		
	});
	items[2][1].setOnAction(new EventHandler<ActionEvent>(){

		@Override
		public void handle(ActionEvent arg0) {
			JOptionPane.showMessageDialog(null, "本计算器由郑晓如开发,盗版必究,谢谢！","about",JOptionPane.INFORMATION_MESSAGE);
		}
		
	});
	}
   
	@Override
	public void start(Stage myStage) throws Exception {
		Scene scene = new Scene(allPane);
		myStage.setScene(scene);
		myStage.setTitle("计算器");
		myStage.setResizable(false);
		myStage.setWidth(305);
		myStage.setHeight(290);
		myStage.show();
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
	/*
	 * 事件处理类
	 */
	class ButtonHandler implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent e) {
			// TODO Auto-generated method stub
			  // 获取事件源的标签
	        String label = ((Button)e.getSource()).getText();
	        if (label.equals(COMMANDS[0])) {
	            // 用户按了"Backspace"键
	            control.handleBackspace();
	        } else if (label.equals(COMMANDS[1])) {
	            // 用户按了"CE"键
	            control.clearError();
	        } else if (label.equals(COMMANDS[2])) {
	            // 用户按了"C"键
	            control.handleC();
	        } 
	        else if ("0123456789.".indexOf(label) >= 0) {
	            // 用户按了数字键或者小数点键
	            control.handleNumber(label);
	        } 
	        else if(label.startsWith("M")){
	        	control.handleMemory(label);
	        }
	        else {
	            // 用户按了运算符键
	            control.handleOperator(label);
	        }

	}
}

	
}