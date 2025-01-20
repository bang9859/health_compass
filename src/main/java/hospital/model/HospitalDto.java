package hospital.model;

public class HospitalDto {
    private String name;
    private String address;
    private String phone;
    private String type;
    private String emergency;
    private String hpid;
    private String operatingHours;
    private double latitude;
    private double longitude;

    public HospitalDto(String name, String address, String phone, String type, String emergency, String hpid,
    		String operatingHours, double latitude, double longitude) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.type = type;
        this.emergency = emergency;
        this.hpid = hpid;
        this.operatingHours = operatingHours;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "병원명: " + name + "\n" +
               "주소: " + address + "\n" +
               "전화번호: " + phone + "\n" +
               "병원 종류: " + type + "\n" +
               "응급실운영여부: " + ("1".equals(emergency) ? "운영" : "미운영") + "\n" +
               "기관코드: " + hpid + "\n" +
               "위도: " + latitude + "\n" +
               "경도: " + longitude + "\n" +
               "진료 시간:\n" + operatingHours;
    }
}

