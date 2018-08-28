package com.em.common.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {
	/****
	 * 十六进制文件头请参考txt
	 * 
	 * @title
	 * @ClassName FileType
	 * @return TODO
	 * @Author min.teng
	 * @Date 2018年4月24日 下午6:09:26
	 */

	public enum FileType {

		/**
		 * JEPG.
		 */
		JPEG("FFD8FF"),

		/**
		 * PNG.
		 */
		PNG("89504E47"),

		/**
		 * GIF.
		 */
		GIF("47494638"),

		/**
		 * TIFF.
		 */
		TIFF("49492A00"),
		/**
		 * RTF.
		 */
		RTF("7B5C727466"),
		/**
		 * DOC
		 */
		DOC("D0CF11E0"),
		/**
		 * XLS
		 */
		XLS("D0CF11E0"),
		/**
		 * ACCESS
		 */
		MDB("5374616E64617264204A"),

		/**
		 * Windows Bitmap.
		 */
		BMP("424D"),

		/**
		 * CAD.
		 */
		DWG("41433130"),

		/**
		 * Adobe Photoshop.
		 */
		PSD("38425053"),

		/**
		 * XML.
		 */
		XML("3C3F786D6C"),

		/**
		 * HTML.
		 */
		HTML("68746D6C3E"),

		/**
		 * Adobe Acrobat.
		 */
		PDF("255044462D312E"),

		/**
		 * ZIP Archive.
		 */
		ZIP("504B0304"),

		/**
		 * RAR Archive.
		 */
		RAR("52617221"),

		/**
		 * Wave.
		 */
		WAV("57415645"),

		/**
		 * AVI.
		 */
		AVI("41564920");

		// /**
		// * TXT
		// */
		// TXT("67867887");

		private String value = "";

		/**
		 * Constructor.
		 * 
		 * @param type
		 */
		private FileType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	/**
	 * 二进制转化为16进制
	 */
	private static String bytes2hex(byte[] bytes) {
		StringBuilder hex = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String temp = Integer.toHexString(bytes[i] & 0xFF);
			if (temp.length() == 1) {
				hex.append("0");
			}
			hex.append(temp.toLowerCase());
		}
		return hex.toString();
	}

	/**
	 * 读取文件头
	 */
	private static String getFileHeader(String filePath) throws IOException {
		byte[] b = new byte[28];// 这里需要注意的是,每个文件的magic
								// word的长度都不相同,因此需要使用startwith
		InputStream inputStream = null;
		inputStream = new FileInputStream(filePath);
		inputStream.read(b, 0, 28);
		inputStream.close();

		return bytes2hex(b);
	}

	/**
	 * 判断文件类型
	 */
	public static FileType getType(String filePath) throws IOException {

		String fileHead = getFileHeader(filePath);
		if (fileHead == null || fileHead.length() == 0) {
			return null;
		}
		fileHead = fileHead.toUpperCase();
		FileType[] fileTypes = FileType.values();
		for (FileType type : fileTypes) {
			if (fileHead.startsWith(type.getValue())) {
				return type;
			}
		}
		return null;
	}

	/**
	 * 
	 * @Title: main
	 * @Description:
	 * @return: void
	 * @author: teng.min
	 * @Date 2018年4月24日
	 */

	public static void main(String[] args) throws Exception {
		// TODO 判断文件后缀名类型，自己完成，这里就不写了，取文件后缀名字符串比较即可。
		// 下面方法为通过文件内容来返回文件类型。用十六进制来判断
		System.out.println(FileUtil.getType("D:\\lyt.rar").toString());
	}
}

/***
 * 常见文件的文件头（十进制）

jpg: 255,216

gif: 71,73

bmp: 66,77

png: 137,80

doc: 208,207

docx: 80,75

xls: 208,207

xlsx: 80,75

js: 239,187

swf: 67,87

txt: 70,67

mp3: 73,68

wma: 48,38

mid: 77,84

rar: 82,97

zip: 80,75

xml: 60,63

常用文件的文件头如下(16进制)：

JPEG (jpg)，文件头：FFD8FF

PNG (png)，文件头：89504E47

GIF (gif)，文件头：47494638

TIFF (tif)，文件头：49492A00

Windows Bitmap (bmp)，文件头：424D

CAD (dwg)，文件头：41433130

Adobe Photoshop (psd)，文件头：38425053

Rich Text Format (rtf)，文件头：7B5C727466

XML (xml)，文件头：3C3F786D6C

HTML (html)，文件头：68746D6C3E

Email [thorough only] (eml)，文件头：44656C69766572792D646174653A

Outlook Express (dbx)，文件头：CFAD12FEC5FD746F

Outlook (pst)，文件头：2142444E

MS Word/Excel (xls.or.doc)，文件头：D0CF11E0

MS Access (mdb)，文件头：5374616E64617264204A

WordPerfect (wpd)，文件头：FF575043

Postscript (eps.or.ps)，文件头：252150532D41646F6265

Adobe Acrobat (pdf)，文件头：255044462D312E

Quicken (qdf)，文件头：AC9EBD8F

Windows Password (pwl)，文件头：E3828596

ZIP Archive (zip)，文件头：504B0304

RAR Archive (rar)，文件头：52617221

Wave (wav)，文件头：57415645

AVI (avi)，文件头：41564920

Real Audio (ram)，文件头：2E7261FD

Real Media (rm)，文件头：2E524D46

MPEG (mpg)，文件头：000001BA

MPEG (mpg)，文件头：000001B3

Quicktime (mov)，文件头：6D6F6F76

Windows Media (asf)，文件头：3026B2758E66CF11

MIDI (mid)，文件头：4D546864
*/
