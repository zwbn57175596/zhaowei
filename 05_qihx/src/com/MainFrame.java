package com;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.border.EtchedBorder;

//��λ��qihxĿ¼����ִ��.bat����ִ��java -jar qihx.jar

public class MainFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2744431865362954852L;

	// ��������
	private List<JTextField> jTextFields = new ArrayList<JTextField>();

	// para3
	private JComboBox jComboBoxPara3;

	// para6
	private JComboBox jComboBoxPara6;

	// para9
	private JComboBox jComboBoxPara9;

	// para10
	private JComboBox jComboBoxPara10;

	public MainFrame(String title, String[] labelNames)
			throws HeadlessException {
		super(title);
		initPanel(labelNames);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(200, 100, 550, 600); // �������ھ�����Ļ��ʼλ��x=200��y=100����=700����=600��
		this.setSize(550, 600); // ���ô��ڵĴ�С����=700����=600��
		this.pack();
		this.setVisible(true);
	}

	private void initPanel(String[] labelNames) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		JLabel jLabel = null;
		JLabel jLabel1 = null;
		JTextField jField = null;

		jLabel1 = new JLabel("*�غ�ú���������֧����ȫ�Է����Ͱ�ȫ�����ж�����¼�������*");
		jLabel1.setBounds(60, 20, 400, 30);
		// jLabel1.getBackground(Color BLUE); //���ô˱���ı���ɫ
		panel.add(jLabel1);

		JPanel panelinner = new JPanel();
		panelinner.setLayout(null);
		// ���ô�panel�ı߿�
		panelinner.setBorder(new EtchedBorder());
		panelinner.setBounds(50, 80, 450, 430);
		panelinner.setPreferredSize(new Dimension(450, 430));
		this.add(panelinner, BorderLayout.CENTER);

		for (int i = 0; i < labelNames.length; i++) {

			jLabel = new JLabel(labelNames[i] + "\t ��\t\t");
			jLabel.setBounds(20, 20 + i * 40, 200, 30);
			panelinner.add(jLabel);

			if (i != 2 && i != 5 && i != 8 && i != 9) {
				jField = new JTextField();
				jField.setBounds(230, 20 + i * 40, 200, 30);
				this.jTextFields.add(jField);
				panelinner.add(jField);
			}

			if (i == 2) {
				jComboBoxPara3 = new JComboBox();
				jComboBoxPara3.setBounds(230, 20 + i * 40, 200, 30);
				jComboBoxPara3.addItem("ê��ê�̵���");
				jComboBoxPara3.addItem("ê����ê�̵��");
				jComboBoxPara3.addItem("ê��ê�̵���");
				panelinner.add(jComboBoxPara3);
			}
			if (i == 5) {
				jComboBoxPara6 = new JComboBox();
				jComboBoxPara6.setBounds(230, 20 + i * 40, 200, 30);
				jComboBoxPara6.addItem("��ú");
				jComboBoxPara6.addItem("��ú");
				jComboBoxPara6.addItem("Ӳú");
				panelinner.add(jComboBoxPara6);
			}
			if (i == 8) {
				jComboBoxPara9 = new JComboBox();
				jComboBoxPara9.setBounds(230, 20 + i * 40, 200, 30);
				jComboBoxPara9.addItem("ǿ��");
				jComboBoxPara9.addItem("һ��");
				jComboBoxPara9.addItem("����");
				panelinner.add(jComboBoxPara9);
			}
			if (i == 9) {
				jComboBoxPara10 = new JComboBox();
				jComboBoxPara10.setBounds(230, 20 + i * 40, 200, 30);
				jComboBoxPara10.addItem("ǿ��");
				jComboBoxPara10.addItem("һ��");
				jComboBoxPara10.addItem("����");
				panelinner.add(jComboBoxPara10);
			}

		}

		JButton jButton = new JButton("����");
		jButton.setBounds(200, 25 + labelNames.length * 50, 80, 35);
		jButton.addActionListener(this);

		JButton jButton1 = new JButton("����");
		jButton1.setBounds(300, 25 + labelNames.length * 50, 80, 35);
		jButton1.addActionListener(new ActionListener() {
			// �������д������ذ�ť����ı���ļ��㷽��
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < jTextFields.size(); i++) {
					jTextFields.get(i).setText("");
				}
			}
		});
		panel.add(jButton1);
		panel.add(jButton);

		panel.setPreferredSize(new Dimension(550, 600));
		this.add(panel, BorderLayout.CENTER);
	}

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		new MainFrame("�غ�ú�㶥��֧����ȫ�Էּ�����", new String[] { "���ֵ��mm��",
				"����ٶȣ�mm/d��", "�����Ҫλ��", "�в��ȣ�m��", "ú�����ȣ���/m3��", "ú��ǿ��",
				"��������ٶȣ�mm/d��", "�����������mm��", "ú����в���ˮӰ�����", "�����ܸ�Ӧ����ѹӰ��̶�" });
	}

	// �������д���㷽��
	@Override
	public void actionPerformed(ActionEvent e) {
		long para1 = Integer.parseInt(this.jTextFields.get(0).getText());
		long para2 = Integer.parseInt(this.jTextFields.get(1).getText());
		String para3 = (String) this.jComboBoxPara3.getSelectedItem();
		Float para4 = Float.parseFloat(this.jTextFields.get(2).getText());
		long para5 = Integer.parseInt(this.jTextFields.get(3).getText());
		String para6 = (String) this.jComboBoxPara6.getSelectedItem();
		long para7 = Integer.parseInt(this.jTextFields.get(4).getText());
		long para8 = Integer.parseInt(this.jTextFields.get(5).getText());
		String para9 = (String) this.jComboBoxPara9.getSelectedItem();
		String para10 = (String) this.jComboBoxPara10.getSelectedItem();

		// �����ע�����ݣ����жϺͼ�������...
		double val1;
		double val2;
		double val3;
		double val4;
		double val5;
		double val6;
		double val7;
		double val8;
		double val9;
		double val10;
		double val_result;
		if (para1 <= 50) {
			val1 = 0.0230;
		} else if (para1 > 50 && para1 < 100) {
			val1 = 0.0534;
		} else {
			val1 = 0.2071;
		}
		if (para2 <= 5) {
			val2 = 0.0115;
		} else if (para2 > 5 && para2 < 50) {
			val2 = 0.0410;
		} else {
			val2 = 0.1460;
		}
		if (para3 == "ê��ê�̵���") {
			val3 = 0.0112;
		} else if (para3 == "ê����ê�̵��") {
			val3 = 0.0284;
		} else {
			val3 = 0.1198;
		}
		if (para4 <= 0.3) {
			val4 = 0.0790;
		} else if (para4 > 0.3 && para4 < 0.5) {
			val4 = 0.0204;
		} else {
			val4 = 0.0088;
		}
		if (para5 <= 10) {
			val5 = 0.0044;
		} else if (para5 > 10 && para5 < 50) {
			val5 = 0.0197;
		} else {
			val5 = 0.0626;
		}
		if (para6 == "��ú") {
			val6 = 0.0417;
		} else if (para6 == "��ú") {
			val6 = 0.0117;
		} else {
			val6 = 0.0033;
		}
		if (para7 <= 10) {
			val7 = 0.0035;
		} else if (para7 > 10 && para7 < 30) {
			val7 = 0.0082;
		} else {
			val7 = 0.0316;
		}
		if (para8 <= 50) {
			val8 = 0.0023;
		} else if (para8 > 50 && para8 < 200) {
			val8 = 0.0054;
		} else {
			val8 = 0.0210;
		}
		if (para9 == "ǿ��") {
			val9 = 0.0128;
		} else if (para9 == "һ��") {
			val9 = 0.0033;
		} else {
			val9 = 0.0014;
		}
		if (para10 == "ǿ��") {
			val10 = 0.0128;
		} else if (para10 == "һ��") {
			val10 = 0.0033;
		} else {
			val10 = 0.0014;
		}
		val_result = val1 + val2 + val3 + val4 + val5 + val6 + val7 + val8
				+ val9 + val10;
		System.out.println(val_result);
		String last_result = "";
		if (val_result >= 0.6200 && val_result <= 0.7300) {
			last_result = "�ر�Σ��!!!------�輰ʱ��ȡ��ǿ��ʩ�����Ľ�֧������";
		} else if (val_result >= 0.5100 && val_result < 0.6200) {
			last_result = "Σ��!------ؽ���ȡ��ǿ��ʩ�����Ľ�֧������";
		} else if (val_result >= 0.4000 && val_result < 0.5100) {
			last_result = "��Σ��------���ȡ��ǿ��ʩ";
		} else if (val_result >= 0.2900 && val_result < 0.4000) {
			last_result = "�ϰ�ȫ------�ɲ�ȡ��ǿ��ʩ�����Ż�����";
		} else if (val_result >= 0.1800 && val_result < 0.2900) {
			last_result = "��ȫ^_^------֧���ȶ���ǿ�������ǿ֧����֧����������";
		} else if (val_result >= 0.0700 && val_result < 0.1800) {
			last_result = "�ر�ȫ^_^------֧���ȶ���ǿ�������ǿ֧�������Ż�֧������";
		}
		// �����ã�
		// String result = new String( para1 + "***" + para2 + "***" + para3 +
		// "***" + para4 + "***" + para5 + "***" + para6 + "***" + para7 + "***"
		// + para8 + "***" + para9 + "***" + para10);
		String result = new String(last_result); // ������㣬�����ȫ�����봦����
		// show result
		JDialog dialog = new JDialog(this, "�غ�ú�㶥��֧����ȫ�Էּ����");
		dialog.setBounds(300, 240, 500, 100);
		JPanel jPanel = new JPanel();

		JLabel jLabe2 = new JLabel("�ж����Ϊ��");
		jLabe2.setBounds(0, 0, 520, 80);
		jLabe2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 18));
		jPanel.add(jLabe2, BorderLayout.WEST);

		JLabel jLabel = new JLabel(result);
		jLabel.setBounds(0, 80, 520, 80);
		jLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 18));
		jPanel.add(jLabel, BorderLayout.CENTER);
		dialog.add(jPanel, BorderLayout.CENTER);
		dialog.setVisible(true);
	}

}