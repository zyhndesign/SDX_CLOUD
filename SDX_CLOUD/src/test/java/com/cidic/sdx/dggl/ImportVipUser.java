package com.cidic.sdx.dggl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.cidic.sdx.dggl.model.Vipuser;
import com.cidic.sdx.dggl.service.VipUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class ImportVipUser {

	@Autowired
	@Qualifier("vipUserServiceImpl")
	private VipUserService vipUserServiceImpl;

	private static final Map<String, String> map = new HashMap<String, String>() {
	
		private static final long serialVersionUID = 1L;

		{
			put("王华英", "120");
			put("马春", "121");
			put("彭小康", "122");
			put("周碧波", "123");
			put("田美林", "124");
			put("罗聪", "125");
			put("黄文军", "126");
			put("彭煌梅", "127");
			put("黄艳", "128");
			put("周云", "129");
			put("潘金辉", "130");
			put("颜林", "131");
			put("刘希", "132");
			put("冯瑶", "133");

		}
	};

	@Test
	public void testInsertUser() {

		try {
			List<Vipuser> list = this.readXlsx("d:\\sdx_vip.xlsx");
			System.out.println("总共有:" + list.size() + "条数据");
			
			int i = 0;
			for (Vipuser user : list) {

				vipUserServiceImpl.createVipUser(user);
				++i;
				System.out.println("完成写入第:" + i + "条数据");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public List<Vipuser> readXlsx(String path) throws IOException {

		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		Vipuser user = null;
		List<Vipuser> list = new ArrayList<Vipuser>();
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
					user = new Vipuser();
					XSSFCell cell0 = xssfRow.getCell(0);
					XSSFCell cell1 = xssfRow.getCell(1);
					XSSFCell cell2 = xssfRow.getCell(2);
					XSSFCell cell3 = xssfRow.getCell(3);
					XSSFCell cell4 = xssfRow.getCell(4);
					XSSFCell cell5 = xssfRow.getCell(5);
					XSSFCell cell6 = xssfRow.getCell(6);
					XSSFCell cell7 = xssfRow.getCell(7);
					XSSFCell cell8 = xssfRow.getCell(8);
					XSSFCell cell9 = xssfRow.getCell(9);
					XSSFCell cell10 = xssfRow.getCell(10);

					String shopName = getValue(cell0);
					user.setShopname(shopName);

					String vipNum = getValue(cell1);
					user.setCardnumber(vipNum);

					String vipname = getValue(cell2);
					user.setVipname(vipname);

					String phoneNum = getValue(cell3);
					user.setPhonenumber(phoneNum);

					String birthday = getValue(cell4);
					user.setBirthday(stringToDate(birthday));

					String gender = getValue(cell5);
					if (gender.equals("女")) {
						user.setGender((byte) 0);
					} else {
						user.setGender((byte) 1);
					}

					String integral = getValue(cell6);
					user.setIntegral((short)Double.parseDouble(integral));

					String consumeSum = getValue(cell7);
					user.setConsumesum(BigDecimal.valueOf(Double.parseDouble(consumeSum)));

					String consumeCount = getValue(cell8);
					user.setConsumenumber((short)Double.parseDouble(consumeCount));

					String userId = map.get(getValue(cell10));
					
					User dgUser = new User();
					if (userId == null || userId.equals("") || userId.equals("null")){
						dgUser.setId(122);
					}
					else{
						dgUser.setId(Integer.parseInt(userId));
					}
					
					user.setValid(0);
					user.setUser(dgUser);
					
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

	private Date stringToDate(String dateString) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(dateString);
			return date;
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
