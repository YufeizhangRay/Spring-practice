package cn.zyf.spring.beans.spel;

public class Person {

	private String name;
	private String city;
	//根据 Car 的 price 确定 info: Car 的 price >= 300000:金领
	//否则:白领
	private String info;
	
	private Car car;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", city=" + city + ", info=" + info + ", car=" + car + "]";
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
}
