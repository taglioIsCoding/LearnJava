package happybank;

import java.io.FileNotFoundException;
import java.io.IOException;

import happybank.persistence.*;
import happybank.ui.Controller;
import happybank.ui.MyMainView;

public class Program
{

	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		MyMainView mainView = new MyMainView();
		Controller controller = new Controller(mainView);
		controller.loadData(new MyBancomatConfigurationReader(), 
				new MyContoCorrenteReader(), 
				new MyTesseraBancomatReader());
		controller.inizio();
		mainView.setVisible(true);
	}

}
