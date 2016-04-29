import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class Morphology {
	
	public StringBuffer  phrase;//存储当前正在形成的词
	public String str;//存储整个字符串
	public int position;//存储在当前字符串的位置

	
	public static void main(String[] args) {		
		
		
		Morphology m = new Morphology();
		m.position = 0;
		m.phrase = new StringBuffer();
		String array[]={"test1.txt","test2.txt","test3.txt","test4.txt"};
		
		for(int i = 0; i < 4; i++){
			m.str = m.TextIn(array[i]);
			while(m.position < m.str.length()-1){
				m.scaner();
			}
			m.position = 0;
		}
		
		
		
	}
	
	
	
	
	/*
	 * 用于输出字符串到文件中
	 */
	public void TextOut(String str){
		try {
			BufferedWriter bufWriter = new BufferedWriter(new FileWriter("tokenOut.txt",true));
			String input = str+"\r\n ";
			bufWriter.write(input);
			bufWriter.close(); 
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} 
	}
	
	
	/*
	 * 用于从文件中读取文字
	 */
	public String TextIn(String str){
		StringBuffer sb = null;
        try {
        	File file=new File(str);
        	if(!file.exists()||file.isDirectory())
                throw new FileNotFoundException();
            BufferedReader br=new BufferedReader(new FileReader(file));
            String temp=null;
            sb=new StringBuffer();
            temp=br.readLine();
            while(temp!=null){
                sb.append(temp+" ");
                temp=br.readLine();
            }
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
        return sb.toString();
	}
	
	
	
	
	/*
	 * 判断是不是整数
	 */
	public void munberInt(){
		char ch = str.charAt(position);
		while(ch>='0'&&ch<='9'){
			phrase.append(ch);
			position++;
			ch = str.charAt(position);
		}
	}
	
	/*
	 * 判断是不是所有可能的数
	 */
	public void number(){
		char ch = str.charAt(position);
		while((ch>='0'&&ch<='9')||ch == 'E'||ch == '.'){
			if(ch>='0'&&ch<='9'){
				munberInt();
				ch = str.charAt(position);
			}else{
				switch(ch){
					case '.':
						phrase.append(ch);
						position  = position + 1;
						ch = str.charAt(position);
						if(ch>='0'&&ch<='9'){
							munberInt();
						}else{
							TextOut("error");
						}
						break;
					case 'E':
						phrase.append(ch);
						position  = position + 1;
						ch = str.charAt(position);
						if(ch == '+'||ch == '-'){
							phrase.append(ch);
							position  = position + 1;
							ch = str.charAt(position);
							if(ch>='0'&&ch<='9'){
								munberInt();
								if(str.charAt(position) == '.'){
									position = position+1;
									TextOut(phrase.toString());
									phrase.setLength(0);
									TextOut("error");
									
								}
							}else{
								TextOut("error");
							}
						}else if(ch>='0'&&ch<='9'){
							munberInt();
						}else{
							TextOut("error");
						}
						break;
						
				}
			}
		}
	}
	
	
	
	
	/*
	 *分析数据 
	 */
	public void scaner(){
		
		char ch = str.charAt(position);
		while(ch==' ')
	    {
			position++;
			ch = str.charAt(position);
	    }
		if((ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z')|| ch == '_'){
			phrase.append(ch);
			position  = position + 1;
			ch = str.charAt(position);
			while((ch>='0'&&ch<='9')||(ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z'|| ch == '_')){
				phrase.append(str.charAt(position));
				position  = position + 1;
				ch = str.charAt(position);
		    }
			if(phrase.toString().toLowerCase().equals("out")){
				if(phrase.toString().equals("out")){
					TextOut("保留字" + phrase.toString());
				}else{
					TextOut("error");
				}
				
			}else{
				TextOut(phrase.toString());
			}
			phrase.setLength(0);
		}else if((ch>='0'&&ch<='9')|| ch == '-' || ch == '+'){
			if(ch == '-'){
				phrase.append(ch);
				position  = position + 1;
				ch = str.charAt(position);
				number();
			}else if(ch>='0'&&ch<='9'){
				number();
			}else{
				phrase.append(ch);
				position  = position + 1;
				ch = str.charAt(position);
				if(ch>='0'&&ch<='9'){
					number();
				}
			}
			TextOut(phrase.toString());
			phrase.setLength(0);
			
		}else{
			switch(ch){
				case ',':
					TextOut(",");
					position  = position + 1;
					break;
				case ';':
					TextOut(";");
					position  = position + 1;
					break;
				case ')':
					TextOut(")");
					position  = position + 1;
					break;
				case '(':
					TextOut("(");
					position  = position + 1;
					break;
				case ':':
					position  = position + 1;
					ch = str.charAt(position);
					if(ch == '='){
						TextOut(":=");
						position  = position + 1;
					}else{
						TextOut("error");
					}
					break;
				default:
					position  = position + 1;
					TextOut("error");
					break;
			}
		}
	}
}
