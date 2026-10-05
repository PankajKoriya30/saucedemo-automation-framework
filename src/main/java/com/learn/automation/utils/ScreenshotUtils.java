package com.learn.automation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenshotUtils {

    public static String captureScreenshot(WebDriver driver, String screenshotName) throws IOException {

        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
            Path screenshotDirectory = Paths.get(Constants.SCREENSHOT_DIRECTORY);
            Files.createDirectories(screenshotDirectory);
            Path destinationPath = screenshotDirectory.resolve(screenshotName + ".png");
            Files.copy(sourceFile.toPath(), destinationPath,
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            return "../../" + destinationPath.toString().replace("\\", "/");
        } catch (IOException e) {
            throw new RuntimeException("Failed to capture screenshot", e);
        }
    }
}
