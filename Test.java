package test;
import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;



public class Test {
	private Frame f;
	private Label lb_csv;
	private Label lb_data;
	private Label lb_save;
	private TextField tf_csv;
	private TextField tf_data;
	private TextField tf_save;
	private Button but;
	
	Test(){
		init();
	}
	//初始化界面
	public void init() {
		f = new Frame("RandomForest_加速器");
		f.setBounds(300,100,300,300);
		f.setLayout(new FlowLayout());
		
		lb_csv = new Label("csv文件位置");
		tf_csv = new TextField(30);
		lb_data = new Label("遥感图像位置");
		tf_data = new TextField(30);
		lb_save = new Label("结果保存位置");
		tf_save = new TextField(30);
		but = new Button("那么，开始实验吧");
		
		f.add(lb_csv);
		f.add(tf_csv);
		f.add(lb_data);
		f.add(tf_data);
		f.add(lb_save);
		f.add(tf_save);
		f.add(but);
		
		myEvent();
		
		f.setVisible(true);
	}
	
	
	public void myEvent() {
		//能让GUI点右上角的“x”退出
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		//核心实现
		but.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				//获取csv的路径，遥感图像的路径和结果保存的路径
				String csvPath = tf_csv.getText();
				String dataPath = tf_data.getText();
				String savePath = tf_save.getText();
				
				System.out.println("csvPath:"+csvPath);
				System.out.println("dataPath:"+dataPath);
				System.out.println("savePath:"+savePath);
				
				//将获取到的三个路径发送给python服务端，可行！！！
				try {
					Socket s = new Socket("192.168.1.104",10005);
					//从java这边开始发送给python服务端
					BufferedWriter bufOut = 
							new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
					bufOut.write(csvPath);
					bufOut.newLine();
					bufOut.write(dataPath);
					bufOut.newLine();
					bufOut.write(savePath);
					bufOut.newLine();
					bufOut.write("over");
					bufOut.flush();
					
					//接收python服务端返回的反馈信息
					BufferedReader bufIn = 
							new BufferedReader(new InputStreamReader(s.getInputStream()));
					String str = bufIn.readLine();
					System.out.println(str);
					
					
				}
				catch(Exception e_py) {
					//以后也可以在GUInew一个TextArea，打印在那上面
					System.out.println("连接python服务器失败");
				}
				
				
				
				//建立与python的连接,跟上面的性质一样，但是这种方法不能调用pthon第三方库
				/*
				try {
					String[] args = new String[] {"python","c:\\test\\user.py"};
					
					//这个python调用有点像服务端啊,还真调用成功了
					Process proc = Runtime.getRuntime().exec(args);
					BufferedReader in = 
							new BufferedReader(new InputStreamReader(proc.getInputStream()));
					String line = null;
					while ((line=in.readLine())!=null) {
						System.out.println(line);
					}
				}
				catch(Exception ex) {
					System.out.println("error!!");
				}
				
				*/
				
				
			}
		});
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws Exception{
		new Test();
	}
}










































