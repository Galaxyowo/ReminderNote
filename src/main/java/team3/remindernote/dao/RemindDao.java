package team3.remindernote.dao;

import team3.remindernote.connect.Connect;
import team3.remindernote.model.Remind;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RemindDao {

    public int insertRemind(Remind remind) {
        String sql = "insert into remind(title, created_date, remind_time) " +
                "values (?,?,?)";
        try {
            Connection connect = Connect.getConnect();
            PreparedStatement pre = connect.prepareStatement(sql);
            pre.setString(1, remind.getTitle());
            pre.setTimestamp(2, remind.getCreatedDate());
            pre.setTimestamp(3, remind.getRemindTime());
            return pre.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    public int updateRemind(Remind remind) {
        String sql = "update remind set title =?,created_date=?, remind_time=?" +
                " where id = ?";
        try {
            Connection connect = Connect.getConnect();
            PreparedStatement pre = connect.prepareStatement(sql);
            pre.setString(1, remind.getTitle());
            pre.setTimestamp(2, remind.getCreatedDate());
            pre.setTimestamp(3, remind.getRemindTime());
            pre.setInt(4, remind.getId());
            return pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Boolean checkById(Integer id) {
        String query = "select * from remind where id =?";
        PreparedStatement preparedStatement;
        try {
            Connection connect = Connect.getConnect();
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
