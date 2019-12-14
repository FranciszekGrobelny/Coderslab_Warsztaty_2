package pl.coderslab.dao;


import pl.coderslab.models.Solution;

import java.sql.*;
import java.util.Arrays;

public class SolutionDao {
    private static final String URL = "jdbc:mysql://localhost:3306/warsztaty_2?useSSL=false&characterEncoding=utf8";
    private static  final String USER = "root";
    private static  final String PASSWORD = "coderslab";
    private static final String CREATE_SOLUTION_QUERY="INSERT INTO solution(created, updated, description) VALUES(?,?,?)";
    private static final String READ_SOLUTION_QUERY="SELECT * FROM solution WHERE id=?";
    private static final String UPDATE_SOLUTION_QUERY="UPDATE solution SET created=?, updated=?, description=? WHERE id=?";
    private static final String DELETE_SOLUTION_QUERY="DELETE FROM solution WHERE id=?";
    private static final String FIND_ALL_SOLUTION_QUERY="SELECT * FROM solution";

    public Solution create(Solution solution){
        try { Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement statement = connection.prepareStatement(CREATE_SOLUTION_QUERY,Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, Date.valueOf(solution.getCreated()));
            statement.setDate(2, Date.valueOf(solution.getUpdated()));
            statement.setString(3, solution.getDescription());
            ResultSet resultSet= statement.getGeneratedKeys();
            if(resultSet.next()){
                solution.setId(resultSet.getInt(1));
            }
            return solution;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Solution read(int solutionId){
        try { Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement statement = connection.prepareStatement(READ_SOLUTION_QUERY);
            statement.setInt(1, solutionId);
            ResultSet resultSet= statement.executeQuery();
            if(resultSet.next()){
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                //solution.setCreated(resultSet.getDate("created"));
                //solution.getUpdated(resultSet.getDate("updated"));
                solution.setDescription(resultSet.getString("description"));
                return solution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void update(Solution solution){
        try { Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement statement= connection.prepareStatement(UPDATE_SOLUTION_QUERY);
            statement.setDate(1, Date.valueOf(solution.getCreated()));
            statement.setDate(2, Date.valueOf(solution.getUpdated()));
            statement.setString(3,solution.getDescription());
            statement.setInt(4,solution.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int solutionId){
        try { Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement statement = connection.prepareStatement(DELETE_SOLUTION_QUERY);
            statement.setInt(1,solutionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Solution[] addToArray(Solution s, Solution[] solutions){
        Solution[] tmp = new Solution[0];
        tmp = Arrays.copyOf(solutions,solutions.length+1);
        tmp[tmp.length]=s;
        return tmp;
    }

    public Solution[] findAll(){
        try { Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_SOLUTION_QUERY);
            Solution[] solutions = new Solution[0];
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
              Solution solution = new Solution();
              solution.setId(resultSet.getInt("id"));
              //solution.setCreated(resultSet.getDate("created"));
              //solution.setCreated(resultSet.getDate("updated"));
              solution.setDescription(resultSet.getString("description"));
              addToArray(solution,solutions);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
