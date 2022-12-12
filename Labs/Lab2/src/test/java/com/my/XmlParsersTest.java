package com.my;

import com.my.entity.Device;
import com.my.entity.Devices;
import com.my.parser.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.my.entity.ComponentsGroup.*;
import static com.my.entity.Port.LPT;
import static com.my.entity.Port.USB;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class XmlParsersTest {

    public static final String FILE = "src\\main\\resources\\MyXML.xml";
    public static final String INVALID_FILE = "resources\\MyXML.xml";

    private Devices expected = new Devices();

    @Before
    public void setUp() {
        Device device1 = new Device();
        device1.setId(1);
        device1.setCritical(false);
        device1.setName("iPod");
        device1.setOrigin("USA");
        device1.setPrice(250d);
        device1.setComponentsGroup(MULTIMEDIA);
        Device.Options options1 = new Device.Options();
        options1.setPeripherals(false);
        options1.setEnergyConsumption(5);
        options1.setPresenceCooler(false);
        options1.setPorts(USB);
        device1.setOptions(options1);

        Device device2 = new Device();
        device2.setId(2);
        device2.setCritical(false);
        device2.setName("HP LaserJet 1022");
        device2.setOrigin("China");
        device2.setPrice(200d);
        device2.setComponentsGroup(PRINTER);
        Device.Options options2 = new Device.Options();
        options2.setPeripherals(true);
        options2.setEnergyConsumption(500);
        options2.setPresenceCooler(true);
        options2.setPorts(LPT);
        device2.setOptions(options2);

        Device device3 = new Device();
        device3.setId(3);
        device3.setCritical(true);
        device3.setName("Sven S-50");
        device3.setOrigin("China");
        device3.setPrice(100d);
        device3.setComponentsGroup(SOUND);
        Device.Options options3 = new Device.Options();
        options3.setPeripherals(true);
        options3.setEnergyConsumption(50);
        options3.setPresenceCooler(false);
        options3.setPorts(USB);
        device3.setOptions(options3);

        List<Device> deviceList = new ArrayList<>();
        deviceList.add(device1);
        deviceList.add(device2);
        deviceList.add(device3);

        expected.setDevice(deviceList);
    }

    @Test
    public void testDomParser() {
        DOMParser parser = (DOMParser) ParserFactory.getInstance().create(ParserType.DOMParser);

        Devices actual = parser.parse(FILE);

        assertArrayEquals(expected.getDevice().toArray(), actual.getDevice().toArray());
    }

    @Test
    public void testSaxParser() {
        SAXParser parser = (SAXParser) ParserFactory.getInstance().create(ParserType.SAXParser);

        Devices actual = parser.parse(FILE);

        assertArrayEquals(expected.getDevice().toArray(), actual.getDevice().toArray());
    }

    @Test
    public void testStaxParser() {
        StAXParser parser = (StAXParser) ParserFactory.getInstance().create(ParserType.StAXParser);

        Devices actual = parser.parse(FILE);

        assertArrayEquals(expected.getDevice().toArray(), actual.getDevice().toArray());
    }

    @Test
    public void testDomParserInvalid() {
        DOMParser parser = (DOMParser) ParserFactory.getInstance().create(ParserType.DOMParser);

        Devices actual = parser.parse(INVALID_FILE);

        assertEquals(0, actual.getDevice().size());
    }

    @Test
    public void testSaxParserInvalid() {
        SAXParser parser = (SAXParser) ParserFactory.getInstance().create(ParserType.SAXParser);

        Devices actual = parser.parse(INVALID_FILE);

        assertEquals(0, actual.getDevice().size());
    }


}
