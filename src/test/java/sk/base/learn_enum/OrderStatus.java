package sk.base.learn_enum;

public enum OrderStatus {
	paySuccess("2","支付成功");
	private String value;
	private String name;
	private OrderStatus(String value, String name) {
		this.value = value;
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
}
