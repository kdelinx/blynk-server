package cc.blynk.server.core.model;

import cc.blynk.server.core.model.enums.PinType;

import static cc.blynk.utils.StringUtils.*;

/**
 * The Blynk Project.
 * Created by Dmitriy Dumanskiy.
 * Created on 03.07.15.
 */
public class Pin {

    public byte pin = -1;

    public boolean pwmMode;

    public boolean rangeMappingOn;

    public PinType pinType;

    public String value;

    public Integer min;

    public Integer max;

    public String label;

    public Pin() {
    }

    public Pin(int pin, PinType pinType) {
        this.pin = (byte) pin;
        this.pinType = pinType;
    }

    public Pin(byte pin, PinType pinType) {
        this.pin = pin;
        this.pinType = pinType;
    }

    public static String makeHardwareBody(char pinType, String pin, String value) {
        return "" + pinType + 'w'
                + BODY_SEPARATOR_STRING + pin
                + BODY_SEPARATOR_STRING + value;
    }

    public static String makeHardwareBody(PinType pinType, byte pin, String value) {
        return "" + pinType.pintTypeChar + 'w'
                + BODY_SEPARATOR_STRING + pin
                + BODY_SEPARATOR_STRING + value;
    }

    public static String makeHardwareBody(boolean pwmMode, PinType pinType, byte pin, String value) {
        return pwmMode ? makeHardwareBody(PinType.ANALOG, pin, value) : makeHardwareBody(pinType, pin, value);
    }

    public boolean isSame(byte pin, PinType type) {
        return this.pin == pin && ((this.pwmMode && type == PinType.ANALOG) || (type == this.pinType));
    }

    public String makeHardwareBody() {
        return pwmMode ? makeHardwareBody(PinType.ANALOG, pin, value) : makeHardwareBody(pinType, pin, value);
    }

    public boolean notEmpty() {
        return pinType != null && value != null && pin > -1;
    }
}
