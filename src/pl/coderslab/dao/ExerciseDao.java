package pl.coderslab.dao;

import pl.coderslab.models.Exercise;
import pl.coderslab.models.User;

import java.sql.*;
import java.util.Arrays;

public class ExerciseDao {

    private static final String URL =
            "jdbc:mysql://localhost:3306/warsztaty_2?useSSL=false&characterEncoding=utf8";
    private static  final String USER = "root";
    private static  final String PASSWORD = "coderslab";
    private static final String CREATE_EXERCISE_QUERY = "INSERT INTO exercise(title, description) VALUES(?,?)";
    private static final String READ_EXERCISE_QUERY="SELECT * FROM exercise WHERE id =?";
    private static final String UPDATE_EXERCISE_QUERY = "UPDATE exercise SET title=?, description=? WHERE id =?";
    private static final String DELETE_EXERCISE_QUERY = "DELETE FROM exercise WHERE id =?";
    private static final String FIND_ALL_EXERCISE_QUERY = "SELECT * FROM exercise";


    public Exercise create(Exercise exercise) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_EXERCISE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, exercise.getTitle());
            statement.setString(2, exercise.getDecription());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                exercise.setId(resultSet.getInt(1));
            }
            return exercise;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Exercise read(int exerciseId){
        try { Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(READ_EXERCISE_QUERY);
            statement.setInt(1, exerciseId);
            ResultSet resultSet=statement.executeQuery();
            if(resultSet.next()){
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getInt("id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDecription(resultSet.getString("description"));
                return exercise;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Exercise update(Exercise exercise){
        try {Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement statement = connection.prepareStatement(UPDATE_EXERCISE_QUERY);
            statement.setString(1,exercise.getTitle());
            statement.setString(2,exercise.getDecription());
            statement.setInt(3,exercise.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void delete(Exercise exercise){
        try {Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(DELETE_EXERCISE_QUERY);
            statement.setInt(1,exercise.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Exercise[] addToArray(Exercise e, Exercise[] exercise){
        Exercise[] tmp = Arrays.copyOf(exercise,exercise.length+1);
        tmp[exercise.length]=e;
        return tmp;
    }
    public Exercise[] findAll(){
        try {Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
        PreparedStatement statement = connection.prepareStatement(FIND_ALL_EXERCISE_QUERY);
        ResultSet resultSet=statement.executeQuery();
        Exercise[] exercises = new Exercise[0];
        while(resultSet.next()){
            Exercise exercise = new Exercise();
            exercise.setId(resultSet.getInt("id"));
            exercise.setTitle(resultSet.getString("title"));
            exercise.setDecription(resultSet.getString("description"));
            exercises=addToArray(exercise,exercises);
        }
        return exercises;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
