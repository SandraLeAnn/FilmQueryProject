package com.skilldistillery.filmquery.app;

import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		// app.test();
		app.launch();
	}

	private void test() {
		Film film = db.findFilmById(1);
		System.out.println(film);
	}

	private void launch() {
		Scanner sc = new Scanner(System.in);

		startUserInterface(sc);

		sc.close();
	}

	private void startUserInterface(Scanner sc) {

		boolean menu = true;

		while (menu) {
			System.out.println("1. Look up a film by ID ");
			System.out.println("2. Look up an actor by ID");
			System.out.println("3. Search films by keyword");
			System.out.println("4. Exit ");

			int userInput = sc.nextInt();

			switch (userInput) {
			case 1:
				System.out.println("Enter a ID number.");
				int idInput = sc.nextInt();

				if (db.findFilmById(idInput) == null) {
					System.out.println("No ID with that number.");
				} else {
					System.out.println(db.findFilmById(idInput));

				}

				break;

			case 2:
				System.out.println("Enter an actor id to search.");
				int actorId = sc.nextInt();
				System.out.println(db.findActorById(actorId));

				break;

			case 3:
				System.out.println("Enter a keyword to search.");
				String keyword = sc.next();
				System.out.println(db.searchFilmByKeyword(keyword));

				break;
			case 4:
				System.out.println("Bye.");
				menu = false;
				break;

			default:
				System.out.println("Invalid. Try again");
				break;
			}

		}
	}
}
