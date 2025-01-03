package be.thomasmore.pcbuilder;

import be.thomasmore.pcbuilder.models.CPU;
import be.thomasmore.pcbuilder.models.MOBO;
import be.thomasmore.pcbuilder.models.PcBuild;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CPUTest {

    @Test
    void testGettersAndSetters() {
        CPU cpu = new CPU();

        cpu.setId(1);
        assertEquals(1, cpu.getId());

        cpu.setName("Ryzen 5 5600X");
        assertEquals("Ryzen 5 5600X", cpu.getName());

        cpu.setManufacturer("AMD");
        assertEquals("AMD", cpu.getManufacturer());

        cpu.setArchitecture("Zen 3");
        assertEquals("Zen 3", cpu.getArchitecture());

        cpu.setWattageUsage(65);
        assertEquals(65, cpu.getWattageUsage());

        cpu.setSocketType("AM4");
        assertEquals("AM4", cpu.getSocketType());

        cpu.setCpuModel("5600X");
        assertEquals("5600X", cpu.getCpuModel());

        cpu.setCoreCount(6);
        assertEquals(6, cpu.getCoreCount());

        cpu.setClockSpeed(3700);
        assertEquals(3700, cpu.getClockSpeed());

        cpu.setPrice(299.99);
        assertEquals(299.99, cpu.getPrice());
    }

    @Test
    void testPcBuildsGetterAndSetter() {
        CPU cpu = new CPU();
        List<PcBuild> builds = new ArrayList<>();
        builds.add(new PcBuild());
        cpu.setPcBuilds(builds);

        assertEquals(1, cpu.getPcBuilds().size());
    }

    @Test
    void testIsCompatibleWithTrue() {
        CPU cpu = new CPU();
        cpu.setSocketType("AM4");

        MOBO mobo = new MOBO();
        mobo.setSocketType("AM4");

        assertTrue(cpu.isCompatibleWith(mobo));
    }

    @Test
    void testIsCompatibleWithFalse() {
        CPU cpu = new CPU();
        cpu.setSocketType("AM4");

        MOBO mobo = new MOBO();
        mobo.setSocketType("LGA1200");

        assertFalse(cpu.isCompatibleWith(mobo));
    }
}
