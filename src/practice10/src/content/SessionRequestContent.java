package content;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Класс позволяет извлекать необходимую информацию из экземпляра-запроса и
 * сохранять ее в своем экземпляре.
 * 
 * @author Nikita Datsenko
 *
 */
public class SessionRequestContent {

	private HttpServletRequest request;

	private HttpSession session;

	private Map<String, Object> requestAttributes = new HashMap<>();

	private Map<String, String> requestParameters = new HashMap<>();

	private Map<String, Object> sessionAttributes = new HashMap<>();

	public SessionRequestContent(HttpServletRequest request) {
		this.request = request;
		session = request.getSession();
		extractValues();
	}

	/**
	 * метод извлечения информации из запроса
	 */
	private void extractValues() {
		extractRequestAttributes();
		extractRequestParameters();
		extractSessionAttributes();
	}

	private void extractRequestAttributes() {
		Enumeration<String> attributeNames = request.getAttributeNames();
		String attributeName;
		while (attributeNames.hasMoreElements()) {
			attributeName = attributeNames.nextElement();
			requestAttributes.put(attributeName, request.getAttribute(attributeName));
		}
	}

	private void extractRequestParameters() {
		Enumeration<String> parameterNames = request.getParameterNames();
		String parameterName;
		while (parameterNames.hasMoreElements()) {
			parameterName = parameterNames.nextElement();
			requestParameters.put(parameterName, request.getParameter(parameterName));
		}
	}

	private void extractSessionAttributes() {
		Enumeration<String> attributeNames = session.getAttributeNames();
		String attributeName;
		while (attributeNames.hasMoreElements()) {
			attributeName = attributeNames.nextElement();
			sessionAttributes.put(attributeName, session.getAttribute(attributeName));
		}
	}

	/**
	 * метод добавления в запрос данных для передачи в jsp
	 */
	public void insertAttributes() {
		setAttributes();
		setSessionAttributes();
	}

	private void setAttributes() {
		if (requestAttributes.isEmpty()) {
			return;
		}
		for (Map.Entry<String, Object> pair : requestAttributes.entrySet()) {
			request.setAttribute(pair.getKey(), pair.getValue());
		}
	}

	private void setSessionAttributes() {
		if (sessionAttributes.isEmpty()) {
			return;
		}
		for (Map.Entry<String, Object> pair : sessionAttributes.entrySet()) {
			session.setAttribute(pair.getKey(), pair.getValue());
		}
	}

	/**
	 * методы для получения извлеченных данных:
	 */
	public Map<String, Object> getRequestAttributes() {
		return requestAttributes;
	}

	public Map<String, String> getRequestParameters() {
		return requestParameters;
	}

	public Map<String, Object> getSessionAttributes() {
		return sessionAttributes;
	}

	/**
	 * метод для очистки сессии
	 */
	public void clearSession() {
		sessionAttributes.clear();
		session.invalidate();
	}

}
