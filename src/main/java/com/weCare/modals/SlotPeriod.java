package com.weCare.modals;

//@ToString
public enum SlotPeriod {

	
	SLOT_1("10:00AM-10:30AM"),
    SLOT_2("10:30AM-11:00AM"),
    SLOT_3("11:00AM-11:30AM"),
    SLOT_4("11:30AM-12:00PM"),
    SLOT_5("12:00PM-12:30PM"),
    SLOT_6("12:30PM-13:00PM"),
    SLOT_7("13:00PM-13:30PM"),
    SLOT_8("13:30PM-14:00PM"),
    SLOT_9("14:40PM-15:00PM"),
    SLOT_10("15:00PM-15:30PM");

    private final String slotRange;

    SlotPeriod(String slotRange) {
        this.slotRange = slotRange;
    }

    public String getSlotRange() {
        return slotRange;
    }
	
}
