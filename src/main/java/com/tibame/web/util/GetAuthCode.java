package com.tibame.web.util;

import java.util.*;

public class GetAuthCode {

	public static String genAuthCode() {

		StringBuilder sbBuilder = new StringBuilder();
		// 用while迴圈重複八次取隨機的英文大小寫跟數字
		while (sbBuilder.length() <= 8) {
			//數字1~9
			int num = (int) (Math.random() * 9) + 1;
			//英文大寫A~Z 整數轉字元
			char bigl = (char) ((int) (Math.random() * 26) + 65);
			//英文小寫a~z 整數轉字元
			char sml = (char) ((int) (Math.random() * 26) + 97);

			//隨機1~3
			int i = (int) (Math.random() * 3) + 1;
            
			//每一次迴圈隨機帶入1個數字或是英文大小寫進去ArrayList裡面然後重複8次
			switch (i) {
			case 1:
				sbBuilder.append(num);
				break;
			case 2:
				sbBuilder.append(bigl);
				break;
			case 3:
				sbBuilder.append(sml);
				break;
			}
		}
		
		
		return sbBuilder.toString();
	}
	
}
