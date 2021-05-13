package agenda.model;

public abstract class Detail{

	private String description;

	public abstract String getName();

	public String getDescription() {
		return description;
	}

	public abstract String getValues();

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return getName() + " :: " + getDescription() + "\n" + getValues();
	}
}
