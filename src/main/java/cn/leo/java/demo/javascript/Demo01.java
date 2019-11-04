
package cn.leo.java.demo.javascript;

/** * 直接调用js代码 */
public class Demo01 {
	public static void main(String[] args) throws Exception {
		/**

		
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
		String jsFileName = "/cn/leo/java/demo/javascript/Demo01.js"; // 读取js文件
		InputStream inputStream = Demo01.class.getResourceAsStream(jsFileName);
		
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

		//InputStreamReader reader = new InputStreamReader(inputStream);

//		FileReader reader = new FileReader(jsFileName);// 定义一个fileReader对象，用来初始化BufferedReader
		BufferedReader bReader = new BufferedReader(inputStreamReader);// new一个BufferedReader对象，将文件内容读取到缓存
		StringBuilder sb = new StringBuilder();// 定义一个字符串缓存，将字符串存放缓存中
		String s = "";
		while ((s = bReader.readLine()) != null) {// 逐行读取文件内容，不读取换行符和末尾的空格
			sb.append(s + "\n");// 将读取的字符串添加换行符后累加存放在缓存中
			//System.out.println(s);
		}
		bReader.close();
		inputStreamReader.close();
		String str = sb.toString();
		//System.out.println(str);

		engine.eval(str);
		
		Object url = String.valueOf(engine.get("url"));
		System.out.println(url);
		Map params = (Map) engine.get("params");
		System.out.println(params);
		sun.org.mozilla.javascript.internal.NativeArray signParamsArr = (NativeArray) engine.get("signParamsArr");
		System.out.println(signParamsArr);
		Object signMethodName = String.valueOf(engine.get("signMethodName"));
		System.out.println(signMethodName);
 */
	}
}