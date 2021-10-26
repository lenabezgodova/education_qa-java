package ru.stqa.pft.soap;




import com.acme.mypackage.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {

        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("109.86.174.236");
        String ipLocation2 = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("109.86.174.236");


        Assert.assertEquals(ipLocation, "UA");
        Assert.assertEquals(ipLocation2, "UA");
    }

}
