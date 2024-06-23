package lk.ijse.pos.entity;

import javafx.scene.chart.PieChart;
import lk.ijse.dmscooltech.db.DbConnection;
import lk.ijse.dmscooltech.repository.ItemRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DashBoard {
    static ItemRepo itemRepo = new ItemRepo();

    public static ArrayList<PieChart.Data> getPieChartData() throws SQLException {
        String sql = "select iCode,SUM(qty)as orderCount from orderdetails group by iCode order by ordercount desc limit 5";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ArrayList<PieChart.Data> data = new ArrayList<>();
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            Item item = itemRepo.searchByItemCode(resultSet.getString(1));
            data.add(
                    new PieChart.Data(item.getDescription(), resultSet.getInt(2))
            );
        }
        return data;
    }
}
