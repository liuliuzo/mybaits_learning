package liuliu.learning.db.execption;

/**
 * 
 * @description:
 * @author: liuliu
 * @email: 165348097@qq.com
 * @since JDK 1.8
 */
public class MyException extends RuntimeException {

	private static final long serialVersionUID = -7284388567336938566L;

	private final String name;

	public MyException(String message, final String name) {
		super(message);
		this.name = name;
	}

	public MyException(Throwable cause, final String name) {
		super(cause);
		this.name = name;
	}

	public MyException(String message, Throwable cause, final String name) {
		super(message, cause);
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
