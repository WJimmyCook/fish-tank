/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.tropicalfishwebapp.dao;

import com.tsguild.tropicalfishwebapp.model.Fish;
import com.tsguild.tropicalfishwebapp.model.WaterTypeCount;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jimmy Cook
 */
public class FishDaoDbImpl implements FishDao {

    private static final String SQL_INSERT_FISH
            = "INSERT INTO Fish (name, scientificName, waterType, pictureURL) values (?, ?, ?, ?)";
    private static final String SQL_DELETE_FISH = "DELETE FROM Fish WHERE id = ?";
    private static final String SQL_SELECT_FISH = "SELECT * FROM Fish WHERE id = ?";
    private static final String SQL_UPDATE_FISH = "UPDATE Fish SET name = ?, "
            + "scientificName = ?, waterType = ?, pictureURL = ? WHERE id = ?";
    private static final String SQL_SELECT_ALL_FISH = "SELECT * FROM Fish";
    private static final String SQL_SELECT_WATERTYPE_COUNTS
            = "SELECT waterType, count(*) as num_fish FROM Fish group by waterType;";
    private static final String SQL_SELECT_FISH_BY_NAME = "SELECT * FROM Fish WHERE name = ?";
    
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Fish addFish(Fish fish) {

        jdbcTemplate.update(SQL_INSERT_FISH,
                fish.getName(),
                fish.getScientificName(),
                fish.getWaterType(),
                fish.getPictureURL());
        fish.setId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        return fish;
    }

    @Override
    public Fish getFishByID(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_FISH, new FishMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Fish> getAllFish() {
        return jdbcTemplate.query(SQL_SELECT_ALL_FISH, new FishMapper());
    }

    @Override
    public void updateFish(Fish fish) {
        jdbcTemplate.update(SQL_UPDATE_FISH,
                fish.getName(),
                fish.getScientificName(),
                fish.getWaterType(),
                fish.getPictureURL(),
                fish.getId());
    }

    @Override
    public void removeFish(int id) {
        jdbcTemplate.update(SQL_DELETE_FISH, id);
    }


    @Override
    public List<WaterTypeCount> getWaterTypeCounts() {
        return jdbcTemplate.query(SQL_SELECT_WATERTYPE_COUNTS, new WaterTypeCountMapper());
    }

    @Override
    public List<Fish> searchFishByName(String name) {
        return jdbcTemplate.query(SQL_SELECT_FISH_BY_NAME, new FishMapper(), name);
    }

    private static final class FishMapper implements RowMapper<Fish> {

        @Override
        public Fish mapRow(ResultSet rs, int rowNum) throws SQLException {

            Fish fish = new Fish();
            fish.setId(rs.getInt("id"));
            fish.setName(rs.getString("name"));
            fish.setScientificName(rs.getString("scientificName"));
            fish.setWaterType(rs.getString("waterType"));
            fish.setPictureURL(rs.getString("pictureURL"));

            return fish;

        }

    }

    private static final class WaterTypeCountMapper 
            extends SingleColumnRowMapper<WaterTypeCount> {

        @Override
        public WaterTypeCount mapRow(ResultSet rs, int i) throws SQLException {
            WaterTypeCount count = new WaterTypeCount();
            count.setWaterType(rs.getString("waterType"));
            count.setNumberFish(rs.getInt("num_fish"));
            return count;
        }
    }

}
