import java.io.Serializable;

public class Attraction implements Serializable
{
    public String name;
    public String address;
    public double latitude;
    public double longitude;

    Attraction(String name,String address,double latitude,double longitude)
    {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
