package cn.com.innodev.pdp.community.persistence.object;

public class ComRoomDO {
    private Integer id;

    private String roomNo;

    private Integer proprietorCount;

    private Integer buildingId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public Integer getProprietorCount() {
        return proprietorCount;
    }

    public void setProprietorCount(Integer proprietorCount) {
        this.proprietorCount = proprietorCount;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }
}