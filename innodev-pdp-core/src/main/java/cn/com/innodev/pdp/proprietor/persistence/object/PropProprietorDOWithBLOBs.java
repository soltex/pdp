package cn.com.innodev.pdp.proprietor.persistence.object;

public class PropProprietorDOWithBLOBs extends PropProprietorDO {
    private String interest;

    private String roomids;

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getRoomids() {
        return roomids;
    }

    public void setRoomids(String roomids) {
        this.roomids = roomids;
    }
}