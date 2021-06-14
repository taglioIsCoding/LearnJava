package happybank.ui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyMainView extends JFrame implements MainView
{
	private static final long serialVersionUID = 1L;
	
	public MyMainView()
	{
		setSize(400, 200);
	}

	@Override
	public void setView(JPanel panel)
	{
		Container contentPane = getContentPane();
		contentPane.removeAll();
		contentPane.add(panel, BorderLayout.CENTER);
		revalidate();
	}
}
