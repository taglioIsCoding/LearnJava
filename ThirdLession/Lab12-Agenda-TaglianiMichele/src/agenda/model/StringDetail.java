package agenda.model;

public abstract class StringDetail extends Detail {

	private String value;

	@Override
	public abstract String getName();

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String getValues() {
		return getValue();
	}
}
