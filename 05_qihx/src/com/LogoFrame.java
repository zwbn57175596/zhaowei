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

	private static Float ENTER_BUTTON_LEFT = 0.75f; // 进入按钮的左侧起始位置百分比
	private static Float ENTER_BUTTON_TOP = 0.85f; // 进入按钮的右侧起始位置百分比

	private String title;

	public static void main(String[] args) {
		new LogoFrame("特厚煤层顶板支护安全性分级分析");
	}

	public LogoFrame(String title) {
		super(title); // 以指定的TITLE初始化LogoFrame
		this.title = title;// 保存TITLE用取下一个JFrame
		final JLayeredPane jLayeredPane = new JLayeredPane();
		
		JPanel bgPanel = new JPanel();// 图形界面的主Panel
//		this.getContentPane().add(bgPanel);
		ImageIcon image = new ImageIcon(IMAGE_FILE_PATH);// 以指定的文件创建一个ImageIcon对象
		JLabel label = new JLabel(image);// 用ImageIcon对象创建一个JLabel
		label.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());// 设定图片label的大小
		
		final Dimension initialDimension = new Dimension(image.getIconWidth(), image.getIconHeight());
		final Point origin = new Point(0, 0);  
        final Rectangle rectangle = new Rectangle(origin, initialDimension);  
        bgPanel.setBounds(rectangle);  
        bgPanel.add(label);
        
        jLayeredPane.setPreferredSize(initialDimension);
        jLayeredPane.add(bgPanel, JLayeredPane.DEFAULT_LAYER);// 背景层

		JPanel fPane = new JPanel();// 图形界面的主Panel

		JButton enterButton = new JButton("进入");
		this.getLayeredPane().add(enterButton, JLayeredPane.DEFAULT_LAYER);// 将LogoFrame布局分层，将图片label置于上一层
		enterButton.setBounds(
				new Float(ENTER_BUTTON_LEFT * image.getIconWidth()).intValue(),
				new Float(ENTER_BUTTON_TOP * image.getIconHeight()).intValue(),
				80, 35);
		enterButton.addActionListener(this);
		fPane.setBounds(rectangle); 
		fPane.setLayout(null);
		fPane.setOpaque(false);
		fPane.add(enterButton);
        
		jLayeredPane.add(fPane, JLayeredPane.MODAL_LAYER);// 背景层
		
		this.getContentPane().add(jLayeredPane);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(initialDimension);
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
		
		new MainFrame("特厚煤层顶板支护安全性分级分析", new String[] { "离层值（mm）",
				"离层速度（mm/d）", "离层主要位置", "夹层厚度（m）", "煤层节理度（条/m3）", "煤层强度",
				"顶板变形速度（mm/d）", "顶板变形量（mm）", "煤层与夹层受水影响情况", "顶板受高应力或动压影响程度" });

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
