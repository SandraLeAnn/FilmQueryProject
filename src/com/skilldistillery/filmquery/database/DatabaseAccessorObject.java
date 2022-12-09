package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDateTimeCode=false&serverTimezone=US/Mountain";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Driver not found");
			throw new RuntimeException("Can't load driver class!");

		}
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

	}

	@Override
	public Film findFilmById(int filmId) {

		String user = "student";
		String pwd = "student";

		String sql = "SELECT * FROM Film WHERE id = ?";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pwd);
			
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, filmId);
			ResultSet rs = pstmt.executeQuery();
			
			Film film = null;
				
				if (rs.next()) {
					film = new Film();
					film.setId(rs.getInt("id"));
					film.setTitle(rs.getString("title"));
					film.setDescription(rs.getString("description"));
					film.setRental_duration(rs.getInt("rental_duration"));
					film.setRelease_year(rs.getInt("release_year"));
					film.setRating(rs.getString("rating"));
					film.setSpecial_features(rs.getString("special_features"));
					film.setLength(rs.getInt("length"));
					film.setReplacement_cost(rs.getInt("replacement_cost"));
					film.setActors(findActorsByFilmId("filmId"));

			}
				pstmt.close();
				conn.close();
				rs.close();
				return film;
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		

	}

	@Override
	public Actor findActorById(int actorId) {
		String user = "student";
		String pwd = "student";

		String sql = "SELECT * FROM actor WHERE id = ?";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pwd);
			
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, actorId);
			ResultSet rs = pstmt.executeQuery();
			
			Actor actor = null;
				
				if (rs.next()) {
					actor = new Actor();
					actor.setId(rs.getInt("id"));
					actor.setFirstName(rs.getString("first_name"));
					actor.setLastName(rs.getString("last_name"));
			

			}
				pstmt.close();
				conn.close();
				rs.close();
				return actor;
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("There is no actor with that Id.");
		return null;
		
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		String user = "student";
		String pwd = "student";

		String sql = "SELECT *"
				+ "FROM actor "
				+ "JOIN film_actor "
				+ "ON film_actor.actor_id = actor.id "
				+ "Join film "
				+ "On film.id = film_actor.film_id"
				+ " WHERE film.id = ?";
		

		try {
			Connection conn = DriverManager.getConnection(URL, user, pwd);
			
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, filmId);
			ResultSet rs = pstmt.executeQuery();
			
			ArrayList<Actor> actors = new ArrayList<>();
				
				if (rs.next()) {
					Actor actor = new Actor();
					actor.setId(rs.getInt("id"));
					actor.setFirstName(rs.getString("first_name"));
					actor.setLastName(rs.getString("last_name"));
			
					actors.add(actor);
			}
				pstmt.close();
				conn.close();
				rs.close();
				return actors;
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("There is no film with that id.");
		
		return null;
	}
}
