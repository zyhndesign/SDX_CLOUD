package com.cidic.sdx.util;

import java.lang.reflect.Field;

import com.cidic.sdx.hpgl.model.HPModel;

public class HpModelUtil {

	public static void FilterNullString(HPModel hpModel) {

		Class<? extends HPModel> hpModelClass = hpModel.getClass();

		Field[] fs = hpModelClass.getDeclaredFields();
		try {
			for (Field field : fs) {
				field.setAccessible(true); // 设置些属性是可以访问的

				String type = field.getType().toString();// 得到此属性的类型
				if (type.endsWith("String")) {
					field.set(hpModel, "");
				} else if (type.endsWith("int") || type.endsWith("Integer")) {
					field.set(hpModel, 0);
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		

	}
}
