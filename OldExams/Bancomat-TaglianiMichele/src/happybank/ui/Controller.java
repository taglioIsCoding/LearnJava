package happybank.ui;

import java.awt.Component;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import happybank.model.*;
import happybank.persistence.*;

public class Controller
{
	private AbstractBancomat bancomat;
	private HashMap<String, TesseraBancomat> tessereBancomatMap;
	private MainView mainView;

	private TesseraBancomat tesseraCorrente;

	public Controller(MainView mainView)
	{
		this.mainView = mainView;
	}

	public void loadData(BancomatConfigurationReader configReader, 
			ContoCorrenteReader contiReader, 
			TesseraBancomatReader bancomatReader)
	{
		try
		{
			FileReader configStreamReader = new FileReader("BancomatConfiguration.txt");
			bancomat = new HappyBancomat();
			configReader.configura(configStreamReader, bancomat);
			configStreamReader.close();

			FileReader contiStreamReader = new FileReader("ContiCorrenti.txt");
			List<ContoCorrente> contiCorrenti = contiReader.read(contiStreamReader);
			HashMap<String, ContoCorrente> contoCorrenteMap = new HashMap<String, ContoCorrente>();
			for (ContoCorrente cc : contiCorrenti)
			{
				contoCorrenteMap.put(cc.getId(), cc);
			}
			contiStreamReader.close();

			tessereBancomatMap = new HashMap<String, TesseraBancomat>();
			FileReader tessereStreamReader = new FileReader("TessereBancomat.txt");
			List<TesseraBancomat> tessere = bancomatReader.read(tessereStreamReader, contoCorrenteMap);
			for (TesseraBancomat tessera : tessere)
			{
				tessereBancomatMap.put(tessera.getId(), tessera);
			}
			tessereStreamReader.close();
		}
		catch (BadFileFormatException | IOException e)
		{
			showMessage(e.toString());
		}
	}

	public void showMessage(String message)
	{
		JOptionPane.showMessageDialog((Component) mainView, message);
	}

	public void inizio()
	{
		tesseraCorrente = null;
		mainView.setView(new SelezionaBancomatControllaPin(this));
	}

	public String[] getIdTessereBancomat()
	{
		return tessereBancomatMap
				.keySet()
				.toArray(new String[0]);
	}

	public void checkPin(String idTessera, String pin)
	{
		TesseraBancomat tessera = tessereBancomatMap.get(idTessera);
		if (tessera.getPin().equals(pin))
		{
			tesseraCorrente = tessera;
			mainView.setView(new SelezionaOperazione(this));
		}
		else
		{
			showMessage("Pin errato!");
			tesseraCorrente = null;
		}
	}

	public String[] getOperazioniDisponibili()
	{
		return new String[] {"Prelievo", "Saldo"};
	}

	public void selezionaOperazione(String op)
	{
		switch (op)
		{
			case "Prelievo":
				mainView.setView(new SelezionaImporto(this));
				break;
			case "Saldo":
				mainView.setView(new MostraSaldo(this));
				break;
			default:
				throw new IllegalArgumentException();
		}
	}

	public int getSaldoCorrente()
	{
		return tesseraCorrente
				.getContoCorrente()
				.getSaldo();
	}

	public void preleva(int importo)
	{
		try
		{
			ImportoErogato prelevate = bancomat.erogaImporto(tesseraCorrente, importo);
			mainView.setView(new ErogazioneImporto(this, prelevate));
		}
		catch (ImportoNonErogabileException e)
		{
			showMessage("Errore nel prelievo: \n" + e.getMessage());
		}
	}

}
