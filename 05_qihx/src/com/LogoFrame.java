package com;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class LogoFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 98531209790806924L;

	private static URL IMAGE_FILE_PATH = LogoFrame.class.getResource("qihx.jpg");

	private static Float ENTER_BUTTON_LEFT = 0.75f; // ���밴ť�������ʼλ�ðٷֱ�
	private static Float ENTER_BUTTON_TOP = 0.85f; // ���밴ť���Ҳ���ʼλ�ðٷֱ�

	private String title;

	public static void main(String[] args) {
		new LogoFrame("�غ�ú�㶥��֧����ȫ�Էּ�����");
	}

	public LogoFrame(String title) {
		super(title); // ��ָ����TITLE��ʼ��LogoFrame
		this.title = title;// ����TITLE��ȡ��һ��JFrame
		final JLayeredPane jLayeredPane = new JLayeredPane();
		
		JPanel bgPanel = new JPanel();// ͼ�ν������Panel
//		this.getContentPane().add(bgPanel);
		ImageIcon image = new ImageIcon(IMAGE_FILE_PATH);// ��ָ�����ļ�����һ��ImageIcon����
		JLabel label = new JLabel(image);// ��ImageIcon���󴴽�һ��JLabel
		label.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());// �趨ͼƬlabel�Ĵ�С
		
		final Dimension initialDimension = new Dimension(image.getIconWidth(), image.getIconHeight());
		final Point origin = new Point(0, 0);  
        final Rectangle rectangle = new Rectangle(origin, initialDimension);  
        bgPanel.setBounds(rectangle);  
        bgPanel.add(label);
        
        jLayeredPane.setPreferredSize(initialDimension);
        jLayeredPane.add(bgPanel, JLayeredPane.DEFAULT_LAYER);// ������

		JPanel fPane = new JPanel();// ͼ�ν������Panel

		JButton enterButton = new JButton("����");
		this.getLayeredPane().add(enterButton, JLayeredPane.DEFAULT_LAYER);// ��LogoFrame���ֲַ㣬��ͼƬlabel������һ��
		enterButton.setBounds(
				new Float(ENTER_BUTTON_LEFT * image.getIconWidth()).intValue(),
				new Float(ENTER_BUTTON_TOP * image.getIconHeight()).intValue(),
				80, 35);
		enterButton.addActionListener(this);
		fPane.setBounds(rectangle); 
		fPane.setLayout(null);
		fPane.setOpaque(false);
		fPane.add(enterButton);
        
		jLayeredPane.add(fPane, JLayeredPane.MODAL_LAYER);// ������
		
		this.getContentPane().add(jLayeredPane);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(initialDimension);
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
		
		new MainFrame("�غ�ú�㶥��֧����ȫ�Էּ�����", new String[] { "���ֵ��mm��",
				"����ٶȣ�mm/d��", "�����Ҫλ��", "�в��ȣ�m��", "ú�����ȣ���/m3��", "ú��ǿ��",
				"��������ٶȣ�mm/d��", "�����������mm��", "ú����в���ˮӰ�����", "�����ܸ�Ӧ����ѹӰ��̶�" });

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
