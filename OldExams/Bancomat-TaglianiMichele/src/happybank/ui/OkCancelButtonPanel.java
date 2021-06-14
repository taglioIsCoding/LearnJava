package happybank.ui;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class OkCancelButtonPanel extends Panel implements ActionListener
{
	private static final long serialVersionUID = 1L;

	private ArrayList<ActionListener> actionListeners = new ArrayList<ActionListener>();

	private JButton okButton;
	private JButton cancelButton;

	public OkCancelButtonPanel()
	{
		setLayout(new GridLayout(1, 2, 10, 10));

		okButton = new JButton("Ok");
		okButton.addActionListener(this);
		add(okButton);

		cancelButton = new JButton("Annulla");
		cancelButton.addActionListener(this);
		add(cancelButton);
	}
	
	public void setOkEnabled(boolean enabled)
	{
		okButton.setEnabled(enabled);
	}
	
	public void setCancelEnabled(boolean enabled)
	{
		cancelButton.setEnabled(enabled);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		ActionEvent evt;
		if (e.getSource() == okButton)
		{
			evt = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Ok");			
		}
		else
		{
			evt = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Cancel");
		}
		for (ActionListener listener : actionListeners)
		{
			listener.actionPerformed(evt);
		}
	}

	public void addActionListener(ActionListener actionListener)
	{
		actionListeners.add(actionListener);
	}

	public void removeActionListener(ActionListener actionListener)
	{
		actionListeners.remove(actionListener);
	}

}
