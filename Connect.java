public void doGet(HttpServletRequest request, HttpServletResponse response) throws
IOException {
 System.out.println("Hello World!");
 response.setContentType("application/json;charset=UTF-8");
 PrintWriter out = response.getWriter();
 out.println("{\"status\": \"cache refreshed\"}");
try {
 // load the driver, contained in db2java.jar
 Class.forName("com.ibm.db2.jcc.DB2Driver");
 // the location of your db2 install, 50000 is the default port, yours may be different
 String url = "jdbc:db2://localhost:50000/test_db";
 String username = "admin";
 String password = "password";
 Connection connection = DriverManager.getConnection(url, username, password);
 try {
 // statements are good for small, short queries, Connection objects allow several ways
to create queries
 Statement st = connection.createStatement();
 String myQuery = "SELECT * FROM test_table WHERE name = 'joe'";
 System.out.println("query: " + myQuery);
 // execute the query
 ResultSet results = st.executeQuery(myQuery);
 // loop through our results
 while (results.next()) {
 String age = results.getString("age");
 System.out.println("joe's age is " + age);
 }
 results.close();
 st.close();
 connection.close();
 } catch (SQLException sqle) {
 sqle.printStackTrace();
 }
 } catch (ClassNotFoundException e) {
 System.err.println(e.toString());
 } catch (SQLException e) {
 e.printStackTrace();
 }