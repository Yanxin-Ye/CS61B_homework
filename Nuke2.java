package nuke2;


import java.net.*;
import java.io.*;



public abstract class Nuke2 {
	public static String omit(String s) {
		
		char[]c= s.toCharArray();
		String s1="";
		s1 +=c[0];
		for (int i=2;i<s.length();i++) 
			s1+=c[i];
			return s1;	
		}
	

	public static void main(String[] args) throws Exception {
		//��ȡ���̵������string
		BufferedReader keyboard;
		String inputword;
		keyboard=new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Please enter the word: ");
		System.out.flush();
		inputword= keyboard.readLine();
		//��string�ĵڶ�����ĸȥ��
		String res=omit(inputword);
		
		//System.out.println(inputword);
		System.out.println(res);

	}

}
