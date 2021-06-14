package happybank.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelezionaOperazione extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private JComboBox<String> opComboBox;
	
	public SelezionaOperazione(Controller controller)
	{
		this.controller = controller;
		
		setLayout(new BorderLayout());
		
		JPanel innerPanel = new JPanel();
		{
			innerPanel.setLayout(new GridLayout(1, 1, 10, 10));
			innerPanel.add(new JLabel("Operazione"));

			opComboBox = new JComboBox<String>(controller.getOperazioniDisponibili());
			innerPanel.add(opComboBox);
		}
		add(innerPanel, BorderLayout.CENTER);
		
		OkCancelButtonPanel okCancelPanel = new OkCancelButtonPanel();
		okCancelPanel.addActionListener(this);
		add(okCancelPanel, BorderLayout.PAGE_END);
	}

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		switch(evt.getActionCommand())
		{
			case "Ok":
				controller.selezionaOperazione((String) opComboBox.getSelectedItem());
				break;
			default:
				controller.inizio();
				break;				
		}
	}

}
