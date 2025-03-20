package com.user;

//PACKAGES
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.DBconnection.*; //DB Package
import com.commonFunctions.ImageConvertor;
import com.mysql.cj.jdbc.Blob;

@WebServlet("/usersDBUtil")
public class usersDBUtil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Get Connection to the database
	public static Connection con = databaseConnection.DBconnection();

	// Default Constructor
	public usersDBUtil(Connection dBconnection) {
		// TODO Auto-generated constructor stub
	}

	// Check passwords
	public boolean CheckPassword(String userid, String pass) {
		boolean isPasswordOk = false;

		try {

			// Check for normal user logins
			PreparedStatement pst = con.prepareStatement("Select id,password from users where id = ? and password = ?");
			pst.setString(1, userid);
			pst.setString(2, pass);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {

				if (pass.equals(rs.getString("password"))) {
					isPasswordOk = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isPasswordOk;
	}

	// Update password
	public boolean UpdatePassword(String userid, String newpass) {
		boolean isUpdated = false;

		try {

			// Check for normal user logins
			PreparedStatement pst = con.prepareStatement("UPDATE users SET password = ? where id = ?");

			pst.setString(1, newpass);
			pst.setString(2, userid);

			if (pst.executeUpdate() > 0) {
				isUpdated = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isUpdated;
	}

	// Get number of users
	public int numberOFusers() {
		int numOfUsers = 0;

		try {

			// Check for normal user logins
			PreparedStatement pst = con.prepareStatement("Select count(id) as 'numOfUsers' from users;");

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				numOfUsers = rs.getInt("numOfUsers");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return numOfUsers;
	}

	// Get number of students
	public int numberOFstudents() {
		int numOfstudents = 0;

		try {

			// Check for normal user logins
			PreparedStatement pst = con
					.prepareStatement("Select count(id) as 'numOfUsers' from users where role = 'student';");

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				numOfstudents = rs.getInt("numOfUsers");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return numOfstudents;
	}

	// Get number of teachers
	public int numberOFteachers() {
		int numOfteachers = 0;

		try {

			// Check for normal user logins
			PreparedStatement pst = con
					.prepareStatement("Select count(id) as 'numOfUsers' from users where role = 'teacher';");

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				numOfteachers = rs.getInt("numOfUsers");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return numOfteachers;
	}

	// Login method
	public ArrayList<user> login(String user, String pass) {

		ArrayList<user> loginUser = new ArrayList<user>();
		int id;
		String username;
		String email;
		String password;
		String role;

		try {

			// Check for normal user logins
			PreparedStatement pst = con.prepareStatement("Select * from users where username = ? and password = ?");
			pst.setString(1, user);
			pst.setString(2, pass);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				id = rs.getInt("id");
				username = rs.getString("username");
				email = rs.getString("email");
				password = rs.getString("password");
				role = rs.getString("role");

				loginUser.add(new user(id, username, password, email, role));
			} else {
				loginUser.add(new user(0, "null", "null", "null", "null"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return loginUser;
	}

	// Get all users data
	public ArrayList<user> getAllUsers() {
		ResultSet resultSet = null;
		ArrayList<user> usersList = new ArrayList<user>();

		try {
			// Prepare SQL Statement
			PreparedStatement pst = con.prepareStatement("select * from users");

			// Get all user info
			int id;
			String username;
			String password;
			String email;
			String role;
			String image;

			// Temporary object holder
			user temp;

			// Execute the query
			resultSet = pst.executeQuery();

			while (resultSet.next()) {
				id = resultSet.getInt("id");
				username = resultSet.getString("username");
				password = resultSet.getString("password");
				email = resultSet.getString("email");
				role = resultSet.getString("role");
				image = ImageConvertor.getImage((Blob) resultSet.getBlob("image"));

				temp = new user(id, username, password, email, role);
				temp.setImage(image);

				usersList.add(temp);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return usersList;

	}

	// Get specific userData
	public ArrayList<String> getSpecificUser(int userId) {

		ArrayList<String> currentUser = new ArrayList<String>();

		try {

			// Prepare SQL Statement
			PreparedStatement pst = con.prepareStatement("select * from users where id = ?");

			pst.setInt(1, userId);

			// Execute the query
			ResultSet resultSet = pst.executeQuery();

			byte[] imageData;

			// Populate table rows with data from the database
			if (resultSet.next()) {

				// Retrieve image data as a Blob
				Blob imageBlob = (Blob) resultSet.getBlob("image"); // Replace "image_column_name" with the actual
																	// column name

				imageData = imageBlob.getBytes(1, (int) imageBlob.length());

				// Convert Blob to byte array
				imageData = imageBlob.getBytes(1, (int) imageBlob.length());

				String base64Image = Base64.getEncoder().encodeToString(imageData);
				String username = resultSet.getString("username");
				String email = resultSet.getString("email");
				String password = resultSet.getString("password");

				currentUser.add(0, username);
				currentUser.add(1, email);
				currentUser.add(2, password);
				currentUser.add(3, base64Image);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return currentUser;
	}

	// Get profile pic
	public String getProfilePic(int userId) {

		String userProPic = null;

		try {
			// Prepare SQL Statement
			PreparedStatement pst = con.prepareStatement("select image from users where id = ?");

			pst.setInt(1, userId);
			ResultSet resultSet = pst.executeQuery();
			byte[] imageData;

			// Populate table rows with data from the database
			if (resultSet.next()) {

				// Retrieve image data as a Blob
				Blob imageBlob = (Blob) resultSet.getBlob("image"); // Replace "image_column_name" with the actual
																	// column name

				imageData = imageBlob.getBytes(1, (int) imageBlob.length());

				// Convert Blob to byte array
				imageData = imageBlob.getBytes(1, (int) imageBlob.length());

				// You can use it as needed, for example, you can convert it to Base64 for HTML
				// display
				userProPic = Base64.getEncoder().encodeToString(imageData);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userProPic;
	}

	// Recovery
	public ArrayList<user> recoveryDetails(String RecoverEmail) {

		ArrayList<user> recoveryDetails = new ArrayList<user>();
		int id;
		String username;
		String email;
		String password;
		String role;

		try {

			// Check for normal user logins
			PreparedStatement pst = con.prepareStatement("Select * from users where email = ?");
			pst.setString(1, RecoverEmail);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				id = rs.getInt("id");
				username = rs.getString("username");
				email = rs.getString("email");
				password = rs.getString("password");
				role = rs.getString("role");

				recoveryDetails.add(new user(id, username, password, email, role));
			} else {
				recoveryDetails.clear();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return recoveryDetails;
	}

	// Reset password
	public boolean resetpw(String pass, String email) {

		boolean isUpdated = false;

		try {

			// Check for normal user logins
			PreparedStatement pst = con.prepareStatement("UPDATE users SET password = ? WHERE  email = ?");

			pst.setString(1, pass);
			pst.setString(2, email);
			pst.executeUpdate();

			if (pst.executeUpdate() > 0) {
				isUpdated = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isUpdated;

	}

	// Signup
	public boolean signup(String user, String email, String pass, String role, InputStream profileImage) {
		boolean signup = false;

		try {
			// Check for normal user logins
			PreparedStatement pst = con
					.prepareStatement("insert into users(username,password,email,role,image) values(?,?,?,?,?)");

			pst.setString(1, user);
			pst.setString(2, pass);
			pst.setString(3, email);
			pst.setString(4, role);
			pst.setBlob(5, profileImage);

			if (pst.executeUpdate() > 0) {
				signup = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return signup;
	}

	// Update profile picture
	public boolean UpdateProfilePicture(String userid, InputStream profileImage) {
		boolean isPicUpdated = false;

		try {

			PreparedStatement pst = con.prepareStatement("UPDATE users SET image = ? WHERE id = ?");
			pst.setBlob(1, profileImage);
			pst.setString(2, userid);

			if (pst.executeUpdate() > 0) {
				isPicUpdated = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isPicUpdated;
	}

	// Update profile info
	public boolean UpdateProfile(String userid, String username, String email) {
		boolean isProfileUpdated = false;

		try {

			PreparedStatement pst = con.prepareStatement("UPDATE users SET username = ?,email =? WHERE id = ?");
			pst.setString(1, username);
			pst.setString(2, email);
			pst.setString(3, userid);

			if (pst.executeUpdate() > 0) {
				isProfileUpdated = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isProfileUpdated;
	}

	// Delete User
	public boolean deleteUser(String userid) {
		boolean idDeleted = false;

		try {

			// Delete user
			PreparedStatement pst = con.prepareStatement("delete from users where id = ?");
			pst.setString(1, userid);

			if (pst.executeUpdate() > 0) {
				idDeleted = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return idDeleted;
	}

	// Update account by userprofile
	public boolean updateUserByProfile(user updatedDetails, InputStream image) {

		boolean isUpdateSuccess = false;

		try {

			if (image.available() > 0) {
				// Update the image only if the InputStream is not empty
				PreparedStatement imgUpdate = con.prepareStatement("UPDATE users SET image = ?  WHERE id = ?");
				imgUpdate.setBlob(1, image);
				imgUpdate.setInt(2, updatedDetails.getId());
				imgUpdate.executeUpdate();
			}

			// Check for normal user logins
			PreparedStatement pst = con.prepareStatement(
					"UPDATE users SET username = ? , password = ? ,email = ? ,role = ?  where id = ?");
			pst.setString(1, updatedDetails.getUsername());
			pst.setString(2, updatedDetails.getPassword());
			pst.setString(3, updatedDetails.getEmail());
			pst.setString(4, updatedDetails.getRole());
			pst.setInt(5, updatedDetails.getId());

			if (pst.executeUpdate() > 0) {
				isUpdateSuccess = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isUpdateSuccess;
	}

	// Get username by id
	public String getUserName(String userid) {
		String username = null;

		try {

			// Delete user
			PreparedStatement pst = con.prepareStatement("select username from users where id = ?");
			pst.setString(1, userid);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				username = rs.getString("username");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return username;
	}

	// update user login
	public boolean updateLogin(int userid, String username, String email, String password, String role) {
		boolean isLoginUpdated = false;

		try {
			PreparedStatement pst = con
					.prepareStatement("UPDATE users SET username = ? ,password = ? ,email = ? ,role = ? WHERE id = ?");
			pst.setString(1, username);
			pst.setString(2, password);
			pst.setString(3, email);
			pst.setString(4, role);
			pst.setInt(5, userid);

			if (pst.executeUpdate() > 0) {
				isLoginUpdated = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isLoginUpdated;
	}

	// Get Latest user id
	public int GetLatestUserId() {
		int latestUserId = 0;

		try {

			PreparedStatement newUser = con.prepareStatement("Select * from users ORDER BY id DESC LIMIT 1");
			ResultSet rs = newUser.executeQuery();

			if (rs.next()) {
				latestUserId = rs.getInt("id");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return latestUserId;
	}

	// Delete Account
	public static boolean AccountDeletion(int userid) {
		boolean accDeleted = false;

		try {

			PreparedStatement pst = con.prepareStatement("Delete from users WHERE id = ?");
			pst.setInt(1, userid);

			if (pst.executeUpdate() > 0) {
				accDeleted = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return accDeleted;
	}

}
