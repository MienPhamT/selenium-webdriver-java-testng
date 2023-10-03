package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class Topic_05_Random {
    WebDriver driver;
   // Java Builtin ( Cung cap san - chi lay ra su dung)
    // Java Libraries ( do ca nhan/ to chuc tu viet)

    public static void main(String[] args) {
        Random random = new Random();
        System.out.println(random.nextBoolean());
        System.out.println(random.nextInt(9999));// random so tu 0-9999
        System.out.println(random.nextDouble());
    }




}
