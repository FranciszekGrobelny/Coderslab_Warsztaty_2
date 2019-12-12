package pl.coderslab.dao;

import pl.coderslab.models.UserGroup;

import java.sql.*;
import java.util.Arrays;

public class UserGroupDao {
    private static final String URL =
            "jdbc:mysql://localhost:3306/warsztaty_2?useSSL=false&characterEncoding=utf8";
    private static  final String USER = "root";
    private static  final String PASSWORD = "coderslab";
    private static final String CREATE_USER_GROUP_QUERY =
            "INSERT INTO user_group(name) VALUES (?)";
    private static final String READ_USER_GROUP_QUERY =
            "SELECT * FROM user_group where id = ?";
    private static final String UPDATE_USER_GROUP_QUERY =
            "UPDATE user_group SET name = ? where id = ?";
    private static final String DELETE_USER_GROUP_QUERY =
            "DELETE FROM user_group WHERE id = ?";
    private static final String FIND_ALL_USER_GROUP_QUERY =
            "SELECT * FROM user_group";


    public UserGroup create(UserGroup user) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_GROUP_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserGroup read(int userId) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = conn.prepareStatement(READ_USER_GROUP_QUERY);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserGroup user = new UserGroup();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(UserGroup user) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_GROUP_QUERY);
            statement.setString(1, user.getName());
            statement.setInt(2, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(int userId) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_GROUP_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private UserGroup[] addToArray(UserGroup u, UserGroup[] users) {
        UserGroup[] tmpUsers = Arrays.copyOf(users, users.length + 1);
        tmpUsers[users.length] = u;
        return tmpUsers;
    }
    public UserGroup[] findAll() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            UserGroup[] users = new UserGroup[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USER_GROUP_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserGroup user = new UserGroup();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                users = addToArray(user, users);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }}


}
