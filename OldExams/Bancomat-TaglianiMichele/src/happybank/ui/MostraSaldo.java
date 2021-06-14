package happybank.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MostraSaldo extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private Controller controller;

	public MostraSaldo(Controller controller)
	{
		this.controller = controller;
		
		setLayout(new BorderLayout());
		
		JPanel innerPanel = new JPanel();
		{
			innerPanel.setLayout(new GridLayout(1, 1, 10, 10));

			innerPanel.add(new JLabel("Saldo: "));
			innerPanel.add(new JLabel("" + controller.getSaldoCorrente()));
		}
		add(innerPanel, BorderLayout.CENTER);
		
		OkCancelButtonPanel buttonsPanel = new OkCancelButtonPanel();
		buttonsPanel.addActionListener(this);
		buttonsPanel.setOkEnabled(true);
		buttonsPanel.setCancelEnabled(false);
		add(buttonsPanel, BorderLayout.PAGE_END);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		controller.inizio();
	}
}
