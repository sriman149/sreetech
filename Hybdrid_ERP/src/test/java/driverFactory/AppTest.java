package driverFactory;

import java.io.IOException;

import org.testng.annotations.Test;

public class AppTest {

@Test
public void kickStart() throws IOException
{
	DriverScript ds=new DriverScript();
	ds.startTest();
}
}
