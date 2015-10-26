package lex;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class LexicalAnalyzer {
	
	/*
	 * 自己实现的扫描和缓存类
	 */
	private Scan scan;
	
	/**
	 * 词法分析器类的构造方法 
	 * @param filename 文件位置和文件名
	 */
	public LexicalAnalyzer(String filename){
		this.scan = new Scan(filename);
	}
	
	public static String outputPath = "Output/";
	/**
	 * 将词法分析结果输出到文件中
	 * @param list 词法分析结果的TokenList
	 * @param filename 需要输出到的文件路径和文件名
	 */
	@SuppressWarnings("resource")
	public void output(ArrayList<Token> list,String filename){
		filename = LexicalAnalyzer.outputPath+filename;
		File file = new File(filename);
		while(file.exists()){
			file.delete();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(
					new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		for(int i = 0;i < list.size();i++){
			try {
				out.writeChar((int)'<');
				out.writeInt(list.get(i).type);
				out.writeChar((int)',');
				out.writeChars(list.get(i).value);
				out.writeChar((int)'>');
				out.writeChar((int)'\n');
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过语法分析获得Token序列
	 * @return Token序列
	 */
	public ArrayList<Token> getTokenList(){
		ArrayList<Token> result = new ArrayList<Token>();
		int index = 0;
		while(index < scan.getLength()){
			Token token = analyze(index);
			result.add(token);
			index = scan.getIndex();
		}
		this.scan.retract(scan.getLength()-1);
		return result;
	}
	
	/**
	 * 对某一位置进行词法分析
	 * @param index 字母开始的位置
	 * @return 单个Token
	 */
	public Token analyze(int index){
		int length = scan.getLength();
		int type = 0;
		String value = "";
		while(index < length){
			char ch = scan.getNextChar();
			index++;
			if(isDigit(ch)){
				
			} else if (isLetter(ch)){
				
			} else {
				switch(ch){
				case '=':
				case '+':
				case '-':
				case '*':
				case '/':
				case '<':
				case '>':
				case '!':
				case '|':
				case '&':
				case ';':
				case '{':
				case '}':
				case '[':
				case ']':
				case '(':
				case ')':
				case '#':
				default:
				}
			}
		}
		Token token = new Token(type,value);
		return token;
	}
	
	private boolean isDigit(char c){
		return false;
	}
	
	private boolean isLetter(char c){
		return false;
	}
}
