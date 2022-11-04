package prr.notifications;

public class SMS extends DeliveryMethod {

	@Override
	public String getType() {
		return "SMS";
	}

}
