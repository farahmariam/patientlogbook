<%
session.setAttribute("username", null);
session.setAttribute("userid", null);
session.setAttribute("loginname", null);

session.setAttribute("data-url-logbook", null);




session.invalidate();

ServletContext context = request.getServletContext();
String loginUrl = context.getInitParameter("login-url");
response.sendRedirect(loginUrl);




%>