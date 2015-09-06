存放服务器的各种服务，该包内的类都是实现Service接口，实现服务器端的业务逻辑和对数据库的操作

Example:TestService

@Transactional
public class TestService implements Service{
	private HbmBaseDAO hbmBaseDAO;
	public HbmBaseDAO getHbmBaseDAO() {
		return hbmBaseDAO;
	}
	public void setHbmBaseDAO(HbmBaseDAO hbmBaseDAO) {
		this.hbmBaseDAO = hbmBaseDAO;
	}
	public Answer getAnswer(Map<String, Object> params) throws Exception {
	}
}
