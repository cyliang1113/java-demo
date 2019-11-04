package cn.leo.java.demo.swing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Demo01 {
	public static void main(String[] args) {
		JFrame jf = new JFrame("xx");
		jf.setBounds(200, 200, 300, 300);
		jf.setVisible(true);

		// 创建一个面板容器
		JPanel jp = new JPanel();
		jp.setBorder(BorderFactory.createTitledBorder("第一个信息"));
		jf.add(jp);

		JLabel jl = new JLabel("hello1");
		jl.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("click");
			}
		});
		jp.add(jl);

		JLabel jl2 = new JLabel("hello2");
		jp.add(jl2);

	}
}