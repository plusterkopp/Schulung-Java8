package appl;

public class Application {
	
	static void demo1() {
		Thermostat1 thermostat = new Thermostat1();
		Heater1 heater = new Heater1();
		thermostat.addThermostatListener(heater);
		thermostat.run();
	}
	static void demo2() {
		Thermostat2 thermostat = new Thermostat2();
		Heater2 heater = new Heater2();
		thermostat.addMaxAlarmListener(() -> heater.onMaxAlarm());
		thermostat.addMinAlarmListener(() -> heater.onMinAlarm());
		thermostat.run();
	}
	static void demo3() {
		Thermostat2 thermostat = new Thermostat2();
		Heater2 heater = new Heater2();
		thermostat.addMaxAlarmListener(heater::onMaxAlarm);
		thermostat.addMinAlarmListener(heater::onMinAlarm);
		thermostat.run();
	}
	public static void main(String[] args) {
		demo1();
		demo2();
		demo3();
	}

}
