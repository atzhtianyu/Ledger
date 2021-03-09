package dao;

import entity.Record;
import util.DBUtil;
import util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecordDAO {
    public int getTotal(Record record) {
        int total = 0;
        try (
                Connection c = DBUtil.getConnection();
                Statement s = c.createStatement();
        ) {
            String sql = "select count(*) from record";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
            System.out.println("total:" + total);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public void add(Record record) {
        String sql = "insert into record values(null, ?, ?, ?, ?)";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {
            ps.setInt(1, record.spend);
            ps.setInt(2, record.cid);
            ps.setString(3, record.comment);
            ps.setDate(4, DateUtil.util2sql(record.date));
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                record.id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Record record) {
        String sql = "update record set spend = ?, cid = ?, comment = ?, date = ? where id = ?";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {
            ps.setInt(1, record.spend);
            ps.setInt(2, record.cid);
            ps.setString(3, record.comment);
            ps.setDate(4, DateUtil.util2sql(record.date));
            ps.setInt(5, record.id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (
                Connection c = DBUtil.getConnection();
                Statement s = c.createStatement();
        ) {
            String sql = "delete from record where id = " + id;
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Record get(int id) {
        Record record = null;
        String sql = "select * from record where id = ?";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                record = new Record();
                record.id = id;
                record.spend = rs.getInt("spend");
                record.cid = rs.getInt("cid");
                record.comment = rs.getString("comment");
                record.date = rs.getDate("date");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return record;
    }

    public List<Record> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Record> list(int start, int count) {
        List<Record> records = new ArrayList<>();
        String sql = "select * from record order by id desc limit ?, ?";
        try (
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {
            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Record record = new Record();
                int id = rs.getInt("id");
                int spend = rs.getInt("spend");
                int cid = rs.getInt("cid");
                String comment = rs.getString("comment");
                record.id = id;
                record.spend = spend;
                record.cid = cid;
                record.comment = comment;
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }
}
