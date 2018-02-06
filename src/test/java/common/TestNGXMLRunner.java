package common;

import com.beust.jcommander.internal.Lists;
import org.testng.TestNG;

import java.util.List;
public class TestNGXMLRunner {
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        List<String> suites = Lists.newArrayList();
        suites.add("TestNG\\PetitSuite.xml");
        testng.setTestSuites(suites);
        testng.run();
    }
}
