package com.weCare.modals;

public enum SlotPeriod {

	
	SLOT_1("10:00 - 10:30"),
    SLOT_2("10:30 - 11:00"),
    SLOT_3("11:00 - 11:30"),
    SLOT_4("11:30 - 12:00"),
    SLOT_5("12:00 - 12:30"),
    SLOT_6("12:30 - 13:00"),
    SLOT_7("13:00 - 13:30"),
    SLOT_8("13:30 - 14:00");

    private final String slotRange;

    SlotPeriod(String slotRange) {
        this.slotRange = slotRange;
    }

    public String getSlotRange() {
        return slotRange;
    }
	
}
