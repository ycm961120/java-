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
	//��ʼ������
	public void init() {
		f = new Frame("RandomForest_������");
		f.setBounds(300,100,300,300);
		f.setLayout(new FlowLayout());
		
		lb_csv = new Label("csv�ļ�λ��");
		tf_csv = new TextField(30);
		lb_data = new Label("ң��ͼ��λ��");
		tf_data = new TextField(30);
		lb_save = new Label("�������λ��");
		tf_save = new TextField(30);
		but = new Button("��ô����ʼʵ���");
		
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
		//����GUI�����Ͻǵġ�x���˳�
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		//����ʵ��
		but.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				//��ȡcsv��·����ң��ͼ���·���ͽ�������·��
				String csvPath = tf_csv.getText();
				String dataPath = tf_data.getText();
				String savePath = tf_save.getText();
				
				System.out.println("csvPath:"+csvPath);
				System.out.println("dataPath:"+dataPath);
				System.out.println("savePath:"+savePath);
				
				//����ȡ��������·�����͸�python����ˣ����У�����
				try {
					Socket s = new Socket("192.168.1.104",10005);
					//��java��߿�ʼ���͸�python�����
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
					
					//����python����˷��صķ�����Ϣ
					BufferedReader bufIn = 
							new BufferedReader(new InputStreamReader(s.getInputStream()));
					String str = bufIn.readLine();
					System.out.println(str);
					
					
				}
				catch(Exception e_py) {
					//�Ժ�Ҳ������GUInewһ��TextArea����ӡ��������
					System.out.println("����python������ʧ��");
				}
				
				
				
				//������python������,�����������һ�����������ַ������ܵ���pthon��������
				/*
				try {
					String[] args = new String[] {"python","c:\\test\\user.py"};
					
					//���python�����е������˰�,������óɹ���
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










































