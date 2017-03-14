package com.cidic.sdx.dggl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cidic.sdx.dggl.model.User;
import com.cidic.sdx.dggl.service.AppUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class ImportUsers {

	@Autowired
	@Qualifier("appUserServiceImpl")
	private AppUserService appUserServiceImpl;
	
	
	@Test
	public void testInsertUser() {

		try {
			List<User> list = this.readXlsx("d:\\sdx_dg.xlsx");
			System.out.println("总共有:" + list.size() + "条数据");
			
			int i = 0;
			for (User user : list) {
				int result = appUserServiceImpl.createUser(user);
				++i;
				System.out.println("完成写入第:" + i + "条数据"+",结果为：  "+result);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
	
	public List<User> readXlsx(String path) throws IOException {

		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		User user = null;
		List<User> list = new ArrayList<User>();
		// Read the Sheet
		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}
			// Read the Row
			for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow != null) {
					user = new User();
					XSSFCell cell0 = xssfRow.getCell(0);
					XSSFCell cell1 = xssfRow.getCell(1);
					XSSFCell cell2 = xssfRow.getCell(2);
					XSSFCell cell3 = xssfRow.getCell(3);
					XSSFCell cell4 = xssfRow.getCell(4);
					XSSFCell cell5 = xssfRow.getCell(5);
					XSSFCell cell6 = xssfRow.getCell(6);
					

					String serNum = getValue(cell0);
					user.setSerialnumber(serNum);
					
					String username = getValue(cell1);
					user.setUsername(username);

					String pwd = getValue(cell2);
					user.setPassword(pwd);

					String shopName = getValue(cell3);
					user.setShopname(shopName);
					
					String entryTime = getValue(cell4);
					user.setEntrytime(stringToDate(entryTime));
					
					String gender = getValue(cell5);
					if (gender.equals("女")){
						user.setGender((byte)0);
					}
					else{
						user.setGender((byte)1);
					}
					
					
					String phoneNum = getValue(cell6);
					user.setPhone(phoneNum);
					
					list.add(user);
				}
			}
		}
		return list;
	}

	private String getValue(XSSFCell xssfRow) {
		if (xssfRow != null) {
			if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
				return String.valueOf(xssfRow.getBooleanCellValue());
			} else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
				return String.valueOf(xssfRow.getNumericCellValue());
			} else {
				return String.valueOf(xssfRow.getStringCellValue());
			}
		}
		return "";
	}
	
	private Date stringToDate(String dateString){
		try  
		{  
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");  
		    Date date = sdf.parse(dateString);  
		    return date;
		}  
		catch (ParseException e)  
		{  
		    System.out.println(e.getMessage());  
		    return null;
		}  
	}
}
