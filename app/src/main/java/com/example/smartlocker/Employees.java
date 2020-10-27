package com.example.smartlocker;

public class Employees {
    private String Label;
    private String LockerID;
    private String Number;
    private String Position;
    private String Status;
    private String DoorState;

    public Employees(String label, String lockerID, String number, String position, String status, String doorState) {
        Label = label;
        LockerID = lockerID;
        Number = number;
        Position = position;
        Status = status;
        DoorState = doorState;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public String getLockerID() {
        return LockerID;
    }

    public void setLockerID(String lockerID) {
        LockerID = lockerID;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDoorState() {
        return DoorState;
    }

    public void setDoorState(String doorState) {
        DoorState = doorState;
    }
}
