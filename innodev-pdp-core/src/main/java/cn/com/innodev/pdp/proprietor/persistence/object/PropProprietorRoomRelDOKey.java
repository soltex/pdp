package cn.com.innodev.pdp.proprietor.persistence.object;

public class PropProprietorRoomRelDOKey {
    private String proprietorId;

    private Integer roomId;

    public String getProprietorId() {
        return proprietorId;
    }

    public void setProprietorId(String proprietorId) {
        this.proprietorId = proprietorId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
}