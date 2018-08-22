package com.demo.testreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.demo.base.AppConstants;
import com.demo.base.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {

	private static ExtentReports extent;
	private static final String OUTPUT_FOLDER = "test-result/";

	static DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	public static String outdir="TestResult_" + dateFormat.format(new Date());

	static Configuration reportConfig = null;

	public static ExtentReports getInstance()  {
		if (extent == null)
			createInstance("test-result/Extent-reportnew.html");

		return extent;
	}

	public static String initDir(String outputDirectory) {

		String path = OUTPUT_FOLDER + File.separator + outputDirectory;
		//String screenShotPath = OUTPUT_FOLDER + File.separator + outputDirectory+File.separator+"screenshots";

		try {
			if (!Files.isDirectory(Paths.get(path))) {
				Files.createDirectories(Paths.get(path));
                AppConstants.screenshotPath = path;
			}

			/*if (!Files.isDirectory(Paths.get(screenShotPath))) {
				Files.createDirectories(Paths.get(screenShotPath));
				AppConstants.screenshotPath = screenShotPath;
			}*/

		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

	public static ExtentReports createInstance(String fileName) {

		String path = initDir(outdir);

		if(reportConfig == null){
			reportConfig = new Configuration(System.getProperty("user.dir")+"/src/main/resources/reportconfig/report.properties");
		}

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path + File.separator + fileName);
		htmlReporter.config().setDocumentTitle(reportConfig.getConfiguration().getProperty("ReportTitle"));
		htmlReporter.config().setReportName(reportConfig.getConfiguration().getProperty("ReportName"));
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setTheme(Theme.STANDARD);

		htmlReporter.config().setEncoding("utf-8");

		extent = new ExtentReports();
		if(reportConfig.getConfiguration().getProperty("env") != null){
			extent.setSystemInfo("Environment", reportConfig.getConfiguration().getProperty("env"));
		}

		if(System.getProperty("Browser") != null){
			extent.setSystemInfo("BrowserName", System.getProperty("Browser"));
		}

		extent.setSystemInfo("TesterName", System.getProperty("user.name"));
		extent.attachReporter(htmlReporter);
		extent.setReportUsesManualConfiguration(true);

		return extent;

	}
}
