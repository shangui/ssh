存放测试类

Example: DemoTest

public class DemoTest {
	public static void main(String[] args) {
		String URL = "http://localhost:8080/wristball/mbclient";
		String params = "{\"at\":\"wbPK\",\"vs\":\"14\",\"os\":\"ios\",\"pkid\":\"0791313b-4620-11e5-ab10-00163e00161b\",\"userid\":\"7e51044d-461f-11e5-ab10-00163e00161b\",\"speedcurrent\":\"2000\"}";
		params = "params=" + params;
		System.out.println(HttpClientUtil.post(URL, params));
	}
}