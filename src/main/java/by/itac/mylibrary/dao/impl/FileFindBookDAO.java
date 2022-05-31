package by.itac.mylibrary.dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import by.itac.mylibrary.dao.FindBookDAO;
import by.itac.mylibrary.dao.exception.DAOException;
import by.itac.mylibrary.entity.Book;

public class FileFindBookDAO implements FindBookDAO {

	private final String fileName = "db-home-library.txt";
	
	private final String separator = "__ __";
	private final int idIndex = 0;
	private final int authorIndex = 1;
	private final int titleIndex = 2;
	private final int yearIndex = 3;
	
	ClassLoader classLoader = getClass().getClassLoader();
	String path  = classLoader.getResource(fileName).getPath();
	File file = new File(path);

	@Override
	public Book find(int id) throws DAOException {
		
		int idOfBook;
		Book foundBook = new Book();

		try (BufferedReader fileReader = new BufferedReader(new FileReader(file));
			 BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file))) {

			String book;

			while ((book = fileReader.readLine()) != null) {

				String[] bookFields = book.split(separator);
				idOfBook = Integer.parseInt(bookFields[idIndex]);
				
				if (idOfBook == id) {
					foundBook.setId(idOfBook);
					foundBook.setAuthor(bookFields[authorIndex]);
					foundBook.setTitle(bookFields[titleIndex]);
					foundBook.setYear(Integer.parseInt(bookFields[yearIndex]));
				}
			}
		} catch (IOException e) {
			throw new DAOException("Input-output error", e);
		}
		return foundBook;

	}

	@Override
	public List<Book> findByTitle(String title) throws DAOException {

		List<Book> foundBooks = new ArrayList<>();
		Book foundBook = new Book();

		try (BufferedReader fileReader = new BufferedReader(new FileReader(file));
			 BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file))) {

			String book;

			while ((book = fileReader.readLine()) != null) {

				String[] bookFields = book.split(separator);

				if (title.equals(bookFields[titleIndex])) {

					foundBook.setId(Integer.parseInt(bookFields[idIndex]));
					foundBook.setAuthor(bookFields[authorIndex]);
					foundBook.setTitle(bookFields[titleIndex]);
					foundBook.setYear(Integer.parseInt(bookFields[yearIndex]));
					
					foundBooks.add(foundBook);
				}

			}
		} catch (IOException e) {
			throw new DAOException("Input-output error", e);
		}
		return foundBooks;

	}

	public List<Book> findByAuthor(String author) throws DAOException {
		
		List<Book> foundBooks = new ArrayList<>();
		Book foundBook = new Book();

		try (BufferedReader fileReader = new BufferedReader(new FileReader(file));
			 BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file))) {

			String book;

			while ((book = fileReader.readLine()) != null) {

				String[] bookFields = book.split(separator);

				if (author.equals(bookFields[authorIndex])) {

					foundBook.setId(Integer.parseInt(bookFields[idIndex]));
					foundBook.setAuthor(bookFields[authorIndex]);
					foundBook.setTitle(bookFields[titleIndex]);
					foundBook.setYear(Integer.parseInt(bookFields[yearIndex]));
					foundBooks.add(foundBook);
				}

			}
		} catch (IOException e) {
			throw new DAOException("Input-output error", e);
		}
		return foundBooks;

	}

}
