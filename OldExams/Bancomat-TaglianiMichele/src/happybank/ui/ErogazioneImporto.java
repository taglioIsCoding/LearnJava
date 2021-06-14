package happybank.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import happybank.model.ImportoErogato;

public class ErogazioneImporto extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private Controller controller;
	
	
	public ErogazioneImporto(Controller controller, ImportoErogato prelevate)
	{
		this.controller = controller;
		
		setLayout(new BorderLayout());
		
		JPanel innerPanel = new JPanel();
		{
			innerPanel.setLayout(new GridLayout(2, 2, 10, 10));

			innerPanel.add(new JLabel("Banconote da " + prelevate.getGrandeTaglio() + ": "));
			innerPanel.add(new JLabel("" + prelevate.getNumGrandeTaglio()));
			
			innerPanel.add(new JLabel("Banconote da " + prelevate.getPiccoloTaglio() + ": "));
			innerPanel.add(new JLabel("" + prelevate.getNumPiccoloTaglio()));
		}
		add(innerPanel, BorderLayout.CENTER);
		
		OkCancelButtonPanel buttonsPanel = new OkCancelButtonPanel();
		buttonsPanel.addActionListener(this);
		buttonsPanel.setOkEnabled(true);
		buttonsPanel.setCancelEnabled(false);
		add(buttonsPanel, BorderLayout.PAGE_END);
	}

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		controller.inizio();
	}

}
