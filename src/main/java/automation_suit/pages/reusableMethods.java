package automation_suit.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.net.URL;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class reusableMethods
{	
	/*
	 * Global Declaration
	 */
//	WebDriver driver;
//	
//	/*
//	 * Constructor for resuableMethods class which has driver being called from base class
//	 */
//	public reusableMethods(WebDriver driver) {		
//		this.driver = driver;
//	}
	
	/*
	 * Dataprovider annotation is used to fetch the data from excel 
	 * If we want to run same testcase with more than one set of data - getTestDataMultiRow dataprovider can be used
	 */
	@DataProvider(name="getTestDataMultiRow")
	public static Object [][] fileDataMultiRow(Method m)
		{
		
		/*
		 * Fetching the details of test and where the test data excel is present.
		 * Paramter datafilename = Excel name to refer
		 * Paramter SheetName = Name of the Method to be given as sheetName
		 * Paramter dataprovider = file location where data sheet is present
		 * Paramter TestName = Method name
		 */
			String datafilename = m.getDeclaringClass().getSimpleName()+"TestData.xlsx";
			String SheetName = m.getDeclaringClass().getSimpleName();
			String dataprovider = System.getProperty("user.dir")+"/"+datafilename;
			String TestName = m.getName();
			
		/* 
		 * Created Two dimension array for getting the data in key value pair  	
		 * HashMap collection methods is used to stored the data
		 * Paramter TestData = It Stores the data in two dimensional array for n number of test case
		 * Paramter tcattributeMap = number of times the same test method should run and the object pointer should point that line
		 * 							 in the Excel
		 */
			Object [][] TestData = getmultirowdatavalues (dataprovider,SheetName,TestName);
			HashMap<String,String> tcattributeMap = null;
			
			Object [][] TestDataOutput = new Object [TestData.length-1][1];
			for (int i=1;i<TestData.length; i++)
			{
				tcattributeMap = new HashMap<String,String>();
				for (int j=0;j<TestData[i].length;j++)
				{
				
					if (TestData[i][j]==null)
					{
						tcattributeMap.put(TestData[0][j].toString(),"");
					}
					else
					{
						tcattributeMap.put(TestData[0][j].toString(),TestData[i][j].toString());
					}
				}
				
				TestDataOutput[i-1][0] = tcattributeMap;
				tcattributeMap = null;
			}
			return TestDataOutput;
		}
	
	/*
	 * getMultirowdatavalues method is used to get data for each and every time
	 * @suppressWarning annotation is used to suppress if any warning issue are present.
	 */
	@SuppressWarnings("all")
	public static String [][] getmultirowdatavalues(String datafilename,String sheetname,String testname)
	{
		try
			{
		/*
		 * Excel method to fetch the data
		 * Paramter totalrows = To get the number of rows in the excel
		 * Paramter totalcolumns = To get the number of columns in the excel
		 * Paramter actulatestdatarows = It is set to 1 because we are going to take rows from 1 and zeroth row has header
		 */
				FileInputStream FS = new FileInputStream(datafilename);
				XSSFWorkbook Workbook = new XSSFWorkbook(FS);
				String [][] datatable = null;
				XSSFSheet Sheet = Workbook.getSheet(sheetname);
				int totalrows = Sheet.getLastRowNum();
				int totalcolumns=  Sheet.getRow(0).getLastCellNum();
				int actulatestdatarows=  1;
				String rowindexsplit = null;
					for(int p=1;p<totalrows;p++)
					{
						XSSFRow rows=Sheet.getRow(p);
						XSSFCell Cell=rows.getCell(0);
							if(Cell.getStringCellValue().equalsIgnoreCase(testname))
							{
								actulatestdatarows++;
								if(actulatestdatarows==2)
								{
									rowindexsplit = Cell.getRowIndex() +"";
								}
								else
								{
									rowindexsplit = rowindexsplit+","+Cell.getRowIndex()+"";
								}
							}
					}
		/*
		 * Here the below block is to fetch the value of the same test Method if we going to run with different set of data
		 * We are splitting it and passing as two or n number of arrays based on the number of times we are going to run the test		
		 */
					
				datatable = new String[actulatestdatarows][totalcolumns+1];
				String [] rowindexes = rowindexsplit.split(",");
					for(int i=0;i<=totalcolumns;i++)
					{
						XSSFRow rows=Sheet.getRow(0);
						if(i==totalcolumns)
						{
							datatable [0][i]="row_number";
						}
						else
						{
							XSSFCell Cell=rows.getCell(i);
							datatable [0][i]=Cell.getStringCellValue();
						}
					}
				for(int i=0;i<rowindexes.length;i++)
					{
						int rownum = Integer.parseInt(rowindexes[i]);
						XSSFRow rows=Sheet.getRow(rownum);
						XSSFCell cell=rows.getCell(0);
						if(cell.getStringCellValue().equals(testname))
							{
								for(int j=0;j<=totalcolumns;j++)
								{
									if(j==totalcolumns)
									{
									datatable[i+1][j]=rowindexes[i];
									}
									else
									{
										cell=rows.getCell(j);
										if(cell==null)
										{
											datatable[i+1][j]=null;
										}
										else
										{
											if (cell.getCellType() == CellType.STRING)
											{
												datatable[i+1][j]=cell.getStringCellValue();
											}
												else if (cell.getCellType() == CellType.NUMERIC)
												{
													String textcell=String.valueOf(cell.getNumericCellValue());
													datatable[i+1][j]=textcell;
												}
											else
											{
												datatable[i+1][j]=String.valueOf(cell.getBooleanCellValue());
											}
										}
									}
								}
							}
						}	
				Workbook.close();
				return datatable;
				}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
				return null;
			}
			catch(IOException IO)
			{
				IO.printStackTrace();
				return null;
			}
		}

	
	/*
	 * Dataprovider annotation is used to fetch the data from excel
	 * If we want to run a test once - getTestData dataprovider can be used
	 */
	@DataProvider(name="getTestData")
	public static Object [][] fileDataRow(Method m)
		{
		
		/*
		 * Fetching the details of test and where the test data excel is present.
		 * Paramter datafilename = Excel name to refer
		 * Paramter SheetName = Name of the Method to be given as sheetName
		 * Paramter dataprovider = file location where data sheet is present
		 * Paramter TestName = Method name
		 */
			String datafilename = m.getDeclaringClass().getSimpleName()+"TestData.xlsx";
			String SheetName = m.getDeclaringClass().getSimpleName();
			String dataprovider = System.getProperty("user.dir")+"/"+datafilename;
			String TestName = m.getName();
			Object [][] TestData = getdatavalues (dataprovider,SheetName,TestName);
			HashMap<String,String> tcattributeMap = null;
			
		/* 
		 * Created Two dimension array for getting the data in key value pair  	
		 * HashMap collection methods is used to stored the data
		 * Paramter TestData = It Stores the data in two dimensional array for n number of test case
		 * Paramter tcattributeMap = number of times the same test method should run and the object pointer should point that line
		 * 							 in the Excel
		 */	
			Object [][] TestDataOutput = new Object [TestData.length-1][1];
			for (int i=1;i<TestData.length; i++)
			{
				tcattributeMap = new HashMap<String,String>();
				for (int j=0;j<TestData[i].length;j++)
				{
				
					if (TestData[i][j]==null)
					{
						tcattributeMap.put(TestData[0][j].toString(),"");
					}
					else
					{
						tcattributeMap.put(TestData[0][j].toString(),TestData[i][j].toString());
					}
				}
				
				TestDataOutput[i-1][0] = tcattributeMap;
				tcattributeMap = null;
			}
			
			return TestDataOutput;


		}
		

	/*
	 * getMultirowdatavalues method is used to get data for each and every time
	 * @suppressWarning annotation is used to suppress if any warning issue are present.
	 */
	
	@SuppressWarnings("all")
	public static String [][] getdatavalues(String datafilename,String sheetname,String testname)
	{
		
		try
			{
			/*
			 * Excel method to fetch the data
			 * Paramter totalrows = To get the number of rows in the excel
			 * Paramter totalcolumns = To get the number of columns in the excel
			 * Paramter actulatestdatarows = It is set to 1 because we are going to take rows from 1 and zeroth row has header
			 */
				FileInputStream FS = new FileInputStream(datafilename);
				XSSFWorkbook Workbook = new XSSFWorkbook(FS);
				String [][] datatable = null;
				XSSFSheet Sheet = Workbook.getSheet(sheetname);
				int totalrows = Sheet.getLastRowNum();
				int totalcolumns=  Sheet.getRow(0).getLastCellNum();
				datatable = new String [2][totalcolumns];

					for(int i=0;i<totalcolumns;i++)
					{
						XSSFRow rows=Sheet.getRow(0);
						XSSFCell Cell=rows.getCell(i);
						datatable [0][i] = Cell.getStringCellValue();
					}

					for(int i=1;i<=totalrows;i++)
					{
						XSSFRow rows=Sheet.getRow(i);
						XSSFCell cell = rows.getCell(0);
						if(cell.getStringCellValue().equals(testname))
							{
								for(int j=0;j<totalcolumns;j++)
								{
								
										cell=rows.getCell(j);
										if(cell==null)
										{
											datatable[1][j]=null;
										}
										else
										{
											if (cell.getCellType() == CellType.STRING)
											{
												datatable[1][j]=cell.getStringCellValue();
											}
												else if (cell.getCellType() == CellType.NUMERIC)
												{
													String textcell=String.valueOf(cell.getNumericCellValue());
													datatable[1][j]=textcell;
												}
											else
											{
												datatable[1][j]=String.valueOf(cell.getBooleanCellValue());
											}
										}
									//}
								}
							}
						}	
				Workbook.close();
				return datatable;
				}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
				return null;
			}
			catch(IOException IO)
			{
				IO.printStackTrace();
				return null;
			}
		}
	
}
