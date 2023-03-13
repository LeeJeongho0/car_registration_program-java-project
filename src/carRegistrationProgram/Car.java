package carRegistrationProgram;

import java.util.Objects;

public class Car {
	private String name;
	private String dongHosu;
	private String reason;
	private String date;
	private String engine;
	private String licensePlate;

	public Car() {
		super();
	}

	public Car(String name, String reason, String date, String licensePlate) {
		this(name, null, reason, date, null, licensePlate);
	}

	public Car(String name, String dongHosu, String date, String engine, String licensePlate) {
		this(name, dongHosu, null, date, engine, licensePlate);
	}

	public Car(String name, String dongHosu, String reason, String date, String engine, String licensePlate) {
		super();
		this.name = name;
		this.dongHosu = dongHosu;
		this.reason = reason;
		this.date = date;
		this.engine = engine;
		this.licensePlate = licensePlate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDongHosu() {
		return dongHosu;
	}

	public void setDongHosu(String dongHosu) {
		this.dongHosu = dongHosu;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(licensePlate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return Objects.equals(licensePlate, other.licensePlate);
	}

	@Override
	public String toString() {
		return name + "\t" + dongHosu + "\t" + date + "\t" + engine + "\t" + licensePlate;
	}
	
	public String toString1() {
		return name + "\t" + reason + "\t" + date + "\t" + licensePlate;
	}

	public String toString2() {
		return name + "\t" + reason + "\t" + date;
	}

}
